package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.diastecnologia.shopmaquinas.enums.Gender;
import br.diastecnologia.shopmaquinas.enums.PersonType;

@Entity
@Table(name="Person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int personID;
	private PersonType personType = PersonType.HUMAN;
	private String lastname;
	private String firstname;
	private List<Document> documents;
	private Gender gender = Gender.MALE;
	private List<Contract> contracts;
	private String email;
	private String phone;
	private Address address;
	private List<Image> images;
	private List<Message> messages;
	
	public Person(){
		
	}
	
	public Person( Boolean empty ){
		if( empty ){
			lastname = "";
			firstname = "";
			email = "";
			phone = "";
			address = new Address(empty);
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="PersonID")
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	@Transient
	public Document getFirstDocument(){
		if( documents != null && documents.size() > 0 ){
			return documents.get( 0 );
		}else{
			return null;
		}
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@OneToMany(mappedBy="person", cascade=CascadeType.ALL)
	public List<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="AddressID", columnDefinition="AddressID")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public PersonType getPersonType() {
		return personType;
	}
	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
	
	@OneToMany(mappedBy="person", cascade=CascadeType.ALL)
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	@OneToMany(mappedBy="toPerson", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderBy("date desc")
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
	
}
