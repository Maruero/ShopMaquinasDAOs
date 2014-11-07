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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.diastecnologia.shopmaquinas.enums.AdProperties;

@Entity
@Table(name="Ad")
@NamedQuery(name="Ad.findAll", query="SELECT a FROM Ad a")
public class Ad implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int adID;
	
	private int contractID;
	private int personID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name="PersonID",  referencedColumnName="PersonID")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="ContractID",  referencedColumnName="ContractID")
	private Contract contract;
	
	@OneToMany
	@JoinColumn(name="AdID")
	private List<AdPropertyValue> adPropertyValues;
	
	public int getAdID() {
		return adID;
	}

	public void setAdID(int adID) {
		this.adID = adID;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<AdPropertyValue> getAdPropertyValues() {
		return adPropertyValues;
	}

	public void setAdPropertyValues(List<AdPropertyValue> adPropertyValues) {
		this.adPropertyValues = adPropertyValues;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getDescription() {
		
		String desc = "";
		if( adPropertyValues != null ){
			desc += adPropertyValues.stream().filter( p-> p.getAdProperty().getName().equals( AdProperties.BRAND.toString() ) ).findFirst().get().getValue();
			desc += " " + adPropertyValues.stream().filter( p-> p.getAdProperty().getName().equals( AdProperties.MODEL.toString() ) ).findFirst().get().getValue();
		}
		
		return desc;
	}
	

}
