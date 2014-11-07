package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Contract")
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int contractID;
	
	private int contractDefinitionID;
	
	private Integer personID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date StartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date EndDate;
	
	@ManyToOne
	@JoinColumn(name="ContractDefinitionID", referencedColumnName="ContractDefinitionID")
	private ContractDefinition contractDefinition;
	
	@ManyToOne
	@JoinColumn(name="PersonID",  referencedColumnName="PersonID")
	private Person person;
	
	@OneToMany
	@JoinColumn(name="ContractID")
	private List<Ad> ads;
	
	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public int getContractDefinitionID() {
		return contractDefinitionID;
	}

	public void setContractDefinitionID(int contractDefinitionID) {
		this.contractDefinitionID = contractDefinitionID;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public ContractDefinition getContractDefinition() {
		return contractDefinition;
	}

	public void setContractDefinition(ContractDefinition contractDefinition) {
		this.contractDefinition = contractDefinition;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
