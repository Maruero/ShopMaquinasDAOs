package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.diastecnologia.shopmaquinas.enums.BillingStatus;
import br.diastecnologia.shopmaquinas.enums.ContractStatus;

@Entity
@Table(name="Contract")
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int contractID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="ContractDefinitionID", referencedColumnName="ContractDefinitionID")
	private ContractDefinition contractDefinition;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PersonID",  referencedColumnName="PersonID")
	private Person person;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="ContractID", referencedColumnName="PersonID")
	private List<Ad> ads;
	
	@OneToMany(mappedBy="contract", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OrderBy("dueDate desc")
	private List<Billing> billings;
	
	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
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

	public List<Billing> getBillings() {
		return billings;
	}

	public void setBillings(List<Billing> billings) {
		this.billings = billings;
	}

	@Transient
	public ContractStatus getContractStatus() {
		
		if( billings == null || billings.size() < 1 ){
			return ContractStatus.CANCELED;
		}
		
		if( billings != null && billings.size() > 0 && billings.get( 0 ).getStatus() != BillingStatus.PAID ){
			return ContractStatus.NOT_PAID; 
		}
		
		if( endDate != null && endDate.before(Calendar.getInstance().getTime())){
			return ContractStatus.EXPIRED;
		}
		
		return ContractStatus.ACTIVE;
	}
	
}
