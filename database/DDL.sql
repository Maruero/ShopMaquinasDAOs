drop database shopmaquinas;
create database shopmaquinas;

CREATE TABLE shopmaquinas.Company (
	CompanyID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	ComercialName VARCHAR(200) NOT NULL,
	CNPJ VARCHAR(15) NOT NULL,
	PRIMARY KEY(CompanyID));

CREATE TABLE `shopmaquinas`.`Person` (
  `PersonID` INT NOT NULL AUTO_INCREMENT,
  `Lastname` VARCHAR(200) NULL,
  `Firstname` VARCHAR(200) NULL,
  `Gender` INT NOT NULL,
  CompanyID INT NULL,
  Email VARCHAR(200) NOT NULL,
  Phone VARCHAR(200) NOT NULL,
  PRIMARY KEY (`PersonID`),
  CONSTRAINT `PersonFKCompany`
    FOREIGN KEY (`CompanyID`)
    REFERENCES `shopmaquinas`.`Company` (`CompanyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `shopmaquinas`.`Document` (
  `PersonID` INT NOT NULL,
  `DocumentType` VARCHAR(45) NOT NULL,
  `DocumentNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`PersonID`, `DocumentType`),
  CONSTRAINT `DocumentFKPerson`
    FOREIGN KEY (`PersonID`)
    REFERENCES `shopmaquinas`.`person` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE shopmaquinas.User(
	UserID INT NOT NULL AUTO_INCREMENT ,
	Username VARCHAR(200) NOT NULL,
	Password VARCHAR(200) NOT NULL,
	PersonID INT NOT NULL,
	PRIMARY KEY (UserID),
	CONSTRAINT UserFKPerson
	    FOREIGN KEY (PersonID)
	    REFERENCES shopmaquinas.person (PersonID)
	    ON DELETE NO ACTION
	    ON UPDATE NO ACTION
);

CREATE TABLE shopmaquinas.ContractDefinition(
	ContractDefinitionID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	Description VARCHAR(1000) NOT NULL,
	StartDate DATETIME NOT NULL,
	EndDate DATETIME,
	PRIMARY KEY(ContractDefinitionID)
);

CREATE TABLE shopmaquinas.Contract(
	ContractID INT NOT NULL AUTO_INCREMENT,
	ContractDefinitionID INT NOT NULL,
	PersonID INT NOT NULL,
	StartDate DATETIME NOT NULL,
	EndDate DATETIME,
	PRIMARY KEY(ContractID),
	CONSTRAINT ContractFKContractDefinition
		FOREIGN KEY (ContractDefinitionID)
		REFERENCES shopmaquinas.ContractDefinition (ContractDefinitionID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
    CONSTRAINT ContractFKPerson
		FOREIGN KEY (PersonID)
		REFERENCES shopmaquinas.Person (PersonID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION
);

CREATE TABLE shopmaquinas.ContractDefinitionProperty(
	ContractDefinitionPropertyID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	Description VARCHAR(2000),
	PRIMARY KEY (ContractDefinitionPropertyID)
);

CREATE TABLE shopmaquinas.ContractDefinitionPropertyValue(
	ContractDefinitionPropertyID INT NOT NULL,
	ContractDefinitionID INT NOT NULL,
	Value VARCHAR(2000),
	PRIMARY KEY (ContractDefinitionPropertyID, ContractDefinitionID),
	CONSTRAINT ContractDefinitionPropertyValueFKContractDefinitionProperty
		FOREIGN KEY (ContractDefinitionPropertyID)
		REFERENCES shopmaquinas.ContractDefinitionProperty (ContractDefinitionPropertyID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
	CONSTRAINT ContractDefinitionPropertyValueFKContractDefinition
		FOREIGN KEY (ContractDefinitionID)
		REFERENCES shopmaquinas.ContractDefinition (ContractDefinitionID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION
);

CREATE TABLE shopmaquinas.Ad(
	AdID INT NOT NULL AUTO_INCREMENT,
	ContractID INT NOT NULL,
	PersonID INT NOT NULL,
	StartDate DATETIME NOT NULL,
	EndDate DATETIME,
	PRIMARY KEY (AdID),
	CONSTRAINT AdFKContract
		FOREIGN KEY (ContractID)
		REFERENCES shopmaquinas.Contract (ContractID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
	CONSTRAINT AdFKPerson
		FOREIGN KEY (PersonID)
		REFERENCES shopmaquinas.Person (PersonID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION
);

CREATE TABLE shopmaquinas.AdProperty(
	AdPropertyID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	Description VARCHAR(2000),
	PRIMARY KEY (AdPropertyID)
);

CREATE TABLE shopmaquinas.AdPropertyValue(
	AdID INT NOT NULL,
	AdPropertyID INT NOT NULL,
	Value VARCHAR(2000),
	PRIMARY KEY(AdID,AdPropertyID),
	CONSTRAINT AdPropertyValueFKAd
		FOREIGN KEY (AdID)
		REFERENCES shopmaquinas.Ad (AdID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
	CONSTRAINT AdPropertyValueFKAdProperty
		FOREIGN KEY (AdPropertyID)
		REFERENCES shopmaquinas.AdProperty (AdPropertyID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION
);

CREATE TABLE shopmaquinas.Message(
	MessageID INT NOT NULL AUTO_INCREMENT,
	FromPersonID INT NOT NULL,
	ToPersonID INT NOT NULL,
	AdID INT,
	Date datetime NOT NULL,
	Text VARCHAR(2000),
	PRIMARY KEY(MessageID),
	CONSTRAINT MessageFKPersonFrom
		FOREIGN KEY (FromPersonID)
		REFERENCES shopmaquinas.Person (PersonID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
	CONSTRAINT MessageFKPersonTo
		FOREIGN KEY (ToPersonID)
		REFERENCES shopmaquinas.Person (PersonID)
		ON DELETE NO ACTION
	    ON UPDATE NO ACTION
);


-- PROPRIEDADES DOS PLANOS
INSERT INTO shopmaquinas.ContractDefinitionProperty (Name, Description) VALUES ('TYPE', 'Tipo do plano');
INSERT INTO shopmaquinas.ContractDefinitionProperty (Name, Description) VALUES ('PRICE', 'Preço do plano');
INSERT INTO shopmaquinas.ContractDefinitionProperty (Name, Description) VALUES ('AD_QUANTITY', 'Quantidade de anúncios');

-- PLANOS
INSERT INTO shopmaquinas.ContractDefinition (Name, Description, StartDate) VALUES ('Empresarial', 'Plano empresarial básico', '2014-11-02');
INSERT INTO shopmaquinas.ContractDefinition (Name, Description, StartDate) VALUES ('Particular', 'Plano particular básico', '2014-11-02');

-- VALOR DAS PROPRIEDADES DOS PLANOS
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (1, 1, 'Empresarial');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (2, 1, '50.00');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (3, 1, '1');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (1, 2, 'Particular');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (2, 2, '30.00');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (3, 2, '1');

-- EMPRESAS
INSERT INTO shopmaquinas.Company(Name, ComercialName, CNPJ) VALUES ('ShopMaquinas', 'Shop Maquinas Agrícolas', '123.1111/123');

-- PESSOAS
INSERT INTO shopmaquinas.Person(Firstname, Lastname, Gender, Email, Phone, CompanyID) VALUES ('Administrador', 'ShopMaquinas', 0, 'admin@shopmaquinas.com.br', '11982131379', 1);
INSERT INTO shopmaquinas.Person(Firstname, Lastname, Gender, Email, Phone) VALUES ('Marcelo', 'Dias', 0, 'maruero@gmail.com', '11982131379');

-- DOCUMENTOS
INSERT INTO shopmaquinas.Document(PersonID, DocumentType, DocumentNumber) VALUES (2, 'CPF', '00972625100');

-- USUÁRIOS
INSERT INTO shopmaquinas.User(Username, Password, PersonID) VALUES ('francis', 'francis123', 1);
INSERT INTO shopmaquinas.User(Username, Password, PersonID) VALUES ('maruero', 'dias1986', 2);

-- CONTRATOS
INSERT INTO shopmaquinas.Contract(ContractDefinitionID, PersonID, StartDate) VALUES (1, 1, '2014-11-02');
INSERT INTO shopmaquinas.Contract(ContractDefinitionID, PersonID, StartDate) VALUES (2, 2, '2014-11-02');

-- PROPRIEDADES DOS ANÚNCIOS
INSERT INTO shopmaquinas.AdProperty(Name, Description) VALUES ('BRAND', 'Marca do produto anunciado');
INSERT INTO shopmaquinas.AdProperty(Name, Description) VALUES ('HIGHLIGHTED', 'Anúncio aparecerá como destaque');
INSERT INTO shopmaquinas.AdProperty(Name, Description) VALUES ('IMAGE', 'Imagem do anúncio');
INSERT INTO shopmaquinas.AdProperty(Name, Description) VALUES ('MODEL', 'Modelo do produto anunciado');
INSERT INTO shopmaquinas.AdProperty(Name, Description) VALUES ('PRICE', 'Preço do produto anunciado');

-- ANÚNCIOS
INSERT INTO shopmaquinas.Ad(ContractID, PersonID, StartDate) VALUES (1, 1, '2014-11-02');
INSERT INTO shopmaquinas.Ad(ContractID, PersonID, StartDate) VALUES (2, 2, '2014-11-02');

-- VALOR DAS PROPRIEDADES DOS ANÚNCIOS
INSERT INTO shopmaquinas.AdPropertyValue(AdID, AdPropertyID, Value) VALUES (1, 1, 'Trator Ferreira');
INSERT INTO shopmaquinas.AdPropertyValue(AdID, AdPropertyID, Value) VALUES (1, 4, '100000');
INSERT INTO shopmaquinas.AdPropertyValue(AdID, AdPropertyID, Value) VALUES (2, 1, 'Trator Dias');
INSERT INTO shopmaquinas.AdPropertyValue(AdID, AdPropertyID, Value) VALUES (2, 2, '101000');
INSERT INTO shopmaquinas.AdPropertyValue(AdID, AdPropertyID, Value) VALUES (2, 4, '101000');

-- MENSAGEM
INSERT INTO shopmaquinas.Message(Date, FromPersonID, ToPersonID, AdID, Text) VALUES( '2014-11-02', 1, 2, 1, 'Teste de mensagem');


