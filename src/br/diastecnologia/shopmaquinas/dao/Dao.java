package br.diastecnologia.shopmaquinas.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
//import org.springframework.beans.factory.annotation.Value;



import br.com.caelum.vraptor.environment.Property;
import br.diastecnologia.shopmaquinas.bean.Ad;
import br.diastecnologia.shopmaquinas.bean.AdProperty;
import br.diastecnologia.shopmaquinas.bean.AdPropertyValue;
import br.diastecnologia.shopmaquinas.bean.Company;
import br.diastecnologia.shopmaquinas.bean.Contract;
import br.diastecnologia.shopmaquinas.bean.ContractDefinition;
import br.diastecnologia.shopmaquinas.bean.ContractDefinitionProperty;
import br.diastecnologia.shopmaquinas.bean.ContractDefinitionPropertyValue;
import br.diastecnologia.shopmaquinas.bean.Document;
import br.diastecnologia.shopmaquinas.bean.Message;
import br.diastecnologia.shopmaquinas.bean.Person;
import br.diastecnologia.shopmaquinas.bean.User;

public class Dao{
	
	@Inject
	@Property("daos.ad.pagesize")
	private String emailSmtp;
	protected int adPageSize = 10;
	
	private static EntityManagerFactory entityManagerFactory;
	private static JinqJPAStreamProvider streams;
	public EntityManager em;
	protected boolean activeTransaction;
	
	public Dao(){
		if( Dao.entityManagerFactory == null ){
			entityManagerFactory = Persistence.createEntityManagerFactory("default");
			streams = new JinqJPAStreamProvider(entityManagerFactory);
		}
		em = entityManagerFactory.createEntityManager();
	}

	public void beginTransaction(){
		activeTransaction = true;
		em.getTransaction().begin();
	}
	
	public void commitTransaction(){
		activeTransaction = false;
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public void rollbackTransaction(){
		activeTransaction = false;
		em.getTransaction().rollback();
		em.close();
	}
	
	public JinqStream<Ad> ads() { 
		return streams.streamAll(em, Ad.class); 
	}
	
	public JinqStream<AdProperty> adProperties() { 
		return streams.streamAll(em, AdProperty.class); 
	}
	
	public JinqStream<AdPropertyValue> adPropertyValues() { 
		return streams.streamAll(em, AdPropertyValue.class); 
	}
	
	public JinqStream<Company> companies() { 
		return streams.streamAll(em, Company.class); 
	}
	
	public JinqStream<Contract> contracts() { 
		return streams.streamAll(em, Contract.class); 
	}
	
	public JinqStream<ContractDefinition> contractDefinitions() { 
		return streams.streamAll(em, ContractDefinition.class); 
	}
	
	public JinqStream<ContractDefinitionProperty> contractDefinitionProperties() { 
		return streams.streamAll(em, ContractDefinitionProperty.class); 
	}
	
	public JinqStream<ContractDefinitionPropertyValue> contractDefinitionPropertyValues() { 
		return streams.streamAll(em, ContractDefinitionPropertyValue.class); 
	}
	
	public JinqStream<Document> documents() { 
		return streams.streamAll(em, Document.class); 
	}
	
	public JinqStream<Person> persons() { 
		return streams.streamAll(em, Person.class); 
	}
	
	public JinqStream<User> users() { 
		return streams.streamAll(em, User.class); 
	}
	
	public JinqStream<Message> messages(){
		return streams.streamAll(em, Message.class);
	}

	public boolean isActiveTransaction() {
		return activeTransaction;
	}

	public void setActiveTransaction(boolean activeTransaction) {
		this.activeTransaction = activeTransaction;
	}
	
	
	
		
		
}
