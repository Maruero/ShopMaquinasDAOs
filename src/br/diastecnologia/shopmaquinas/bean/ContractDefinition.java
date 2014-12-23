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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ContractDefinition")
@NamedQuery(name="ContractDefinition.findAll", query="SELECT cf FROM ContractDefinition cf")
public class ContractDefinition implements Serializable{	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int contractDefinitionID;
	
	private String name;
	
	private String description;
	
	@OneToMany
	@JoinColumn(name="ContractDefinitionID")
	private List<ContractDefinitionPropertyValue> contractDefinitionPropertyValues; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	public int getContractDefinitionID() {
		return contractDefinitionID;
	}
	public void setContractDefinitionID(int contractDefinitionID) {
		this.contractDefinitionID = contractDefinitionID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<ContractDefinitionPropertyValue> getContractDefinitionPropertyValues() {
		return contractDefinitionPropertyValues;
	}
	public void setContractDefinitionPropertyValues(
			List<ContractDefinitionPropertyValue> contractDefinitionPropertyValues) {
		this.contractDefinitionPropertyValues = contractDefinitionPropertyValues;
	}
	
	
	
	
}
