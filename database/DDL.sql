drop database shopmaquinas;
create database shopmaquinas;

CREATE TABLE shopmaquinas.Address(
	AddressID INT NOT NULL AUTO_INCREMENT,
	Cep VARCHAR(8) NOT NULL,
	UF VARCHAR(2) NOT NULL,
	City VARCHAR(300) NOT NULL,
	Neighborhood VARCHAR(300) NOT NULL,
	Street VARCHAR(300) NOT NULL,
	Number VARCHAR(20) NULL,
	Complement VARCHAR(300) NULL,
	PRIMARY KEY(AddressID)
);

CREATE TABLE shopmaquinas.Person (
  PersonID INT NOT NULL AUTO_INCREMENT,
  PersonType VARCHAR(200) NOT NULL,
  Lastname VARCHAR(200) NULL,
  Firstname VARCHAR(200) NULL,
  Gender INT NOT NULL,
  AddressID INT NULL,
  Email VARCHAR(200) NOT NULL,
  Phone VARCHAR(200) NOT NULL,
  PRIMARY KEY (PersonID),
  CONSTRAINT PersonFKAddress
    FOREIGN KEY (AddressID)
    REFERENCES shopmaquinas.Address (AddressID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE shopmaquinas.Image (
	ImageID INT NOT NULL AUTO_INCREMENT,
	Path VARCHAR(200) NOT NULL,
	PersonID INT NOT NULL,
	PRIMARY KEY(ImageID),
	CONSTRAINT ImageFKPerson
    FOREIGN KEY (PersonID)
    REFERENCES shopmaquinas.Person (PersonID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	


CREATE TABLE shopmaquinas.Document (
  PersonID INT NOT NULL,
  DocumentType VARCHAR(45) NOT NULL,
  DocumentNumber VARCHAR(45) NULL,
  PRIMARY KEY (PersonID, DocumentType),
  CONSTRAINT DocumentFKPerson
    FOREIGN KEY (PersonID)
    REFERENCES shopmaquinas.Person (PersonID)
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
	    REFERENCES shopmaquinas.Person (PersonID)
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

CREATE TABLE shopmaquinas.Billing(
	BillingID INT NOT NULL AUTO_INCREMENT,
	ContractID INT NOT NULL,
	DueDate DATETIME NOT NULL,
	Amount DECIMAL(9,2) NOT NULL,
	Status VARCHAR(20) NOT NULL,
	PRIMARY KEY(BillingID),
	CONSTRAINT BillingFKContract
		FOREIGN KEY (ContractID)
		REFERENCES shopmaquinas.Contract (ContractID)
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
	Visible BIT NOT NULL,
	PRIMARY KEY (AdPropertyID)
);

CREATE TABLE shopmaquinas.AdPropertyValue(
	AdID INT NOT NULL,
	AdPropertyID INT NOT NULL,
	Value VARCHAR(767) NOT NULL,
	PRIMARY KEY(AdID,AdPropertyID, Value),
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

DROP TABLE IF EXISTS shopmaquinas.Message;
CREATE TABLE shopmaquinas.Message(
	MessageID INT NOT NULL AUTO_INCREMENT,
	FromPersonID INT NOT NULL,
	ToPersonID INT NOT NULL,
	AdID INT,
	Date datetime NOT NULL,
	Text VARCHAR(2000),
	Status INT NOT NULL,
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

DROP TABLE IF EXISTS shopmaquinas.Category;
CREATE TABLE shopmaquinas.Category(
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS shopmaquinas.Type;
CREATE TABLE shopmaquinas.Type(
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	ParentId INT NOT NULL,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS shopmaquinas.Subtype;
CREATE TABLE shopmaquinas.Subtype(
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	ParentId INT NOT NULL,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS shopmaquinas.Brand;
CREATE TABLE shopmaquinas.Brand(
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	ParentId INT NOT NULL,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS shopmaquinas.Model;
CREATE TABLE shopmaquinas.Model(
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(200) NOT NULL,
	ParentId INT NOT NULL,
	PRIMARY KEY(ID)
);


-- PROPRIEDADES DOS PLANOS
INSERT INTO shopmaquinas.ContractDefinitionProperty (Name, Description) VALUES ('TYPE', 'Tipo do plano');
INSERT INTO shopmaquinas.ContractDefinitionProperty (Name, Description) VALUES ('PRICE', 'Preço do plano (R$)');
INSERT INTO shopmaquinas.ContractDefinitionProperty (Name, Description) VALUES ('AD_QUANTITY', 'Quantidade de anúncios');

-- PLANOS
INSERT INTO shopmaquinas.ContractDefinition (Name, Description, StartDate) VALUES ('Empresarial', 'Plano empresarial básico', '2014-11-02');
INSERT INTO shopmaquinas.ContractDefinition (Name, Description, StartDate) VALUES ('Particular', 'Plano particular básico', '2014-11-02');

-- VALOR DAS PROPRIEDADES DOS PLANOS
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (1, 1, 'Empresarial');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (2, 1, '50.00');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (3, 1, '5');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (1, 2, 'Particular');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (2, 2, '30.00');
INSERT INTO shopmaquinas.ContractDefinitionPropertyValue (ContractDefinitionPropertyID, ContractDefinitionID, Value) VALUES (3, 2, '5');

-- PROPRIEDADES DOS ANÚNCIOS
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('TYPE', 'Tipo', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('GROUP', 'Grupo', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('CATEGORY', 'Categoria', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('BRAND', 'Marca', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('MODEL', 'Modelo', true);

INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('HIGHLIGHTED', 'Anúncio aparecerá como destaque', false);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('IMAGE', 'Imagem do anúncio', false);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('LONG_DESCRIPTION', 'Descrição', false);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('PRICE', 'Preço', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('PUBLISHED', 'Anúncio aprovado', false);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('YEAR', 'Ano de fabricação', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('COLOR', 'Cor', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('UNIQUE_OWNER', 'Único dono', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('EXCHANGE', 'Aceita troca', true);
INSERT INTO shopmaquinas.AdProperty(Name, Description, Visible) VALUES ('HOURS', 'Horas', true);


