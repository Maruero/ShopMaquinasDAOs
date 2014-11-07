package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Document")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
@IdClass(DocumentID.class)
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(updatable=false)
	private int personID;
	
	@Id
	@Column(updatable=false)
	private String documentType;
	private String documentNumber;
	
	
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
		
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
}

class DocumentID implements Serializable {

	private static final long serialVersionUID = 1L;
	private int personID;
	private String documentType;
	
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
