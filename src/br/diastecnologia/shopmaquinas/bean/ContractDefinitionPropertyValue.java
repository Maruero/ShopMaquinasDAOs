package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ContractDefinitionPropertyValue")
@NamedQuery(name="ContractDefinitionPropertyValue.findAll", query="SELECT cdpv FROM ContractDefinitionPropertyValue cdpv")
@IdClass(ContractDefinitionPropertyValueID.class)
public class ContractDefinitionPropertyValue implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int contractDefinitionPropertyID;
	
	@Id
	private int contractDefinitionID;
	
	private String value;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="ContractDefinitionPropertyID", referencedColumnName="ContractDefinitionPropertyID")
	private ContractDefinitionProperty contractDefinitionProperty;
	
	public ContractDefinitionProperty getContractDefinitionProperty() {
		return contractDefinitionProperty;
	}
	public void setContractDefinitionProperty(
			ContractDefinitionProperty contractDefinitionProperty) {
		this.contractDefinitionProperty = contractDefinitionProperty;
	}
	public int getContractDefinitionPropertyID() {
		return contractDefinitionPropertyID;
	}
	public void setContractDefinitionPropertyID(int contractDefinitionPropertyID) {
		this.contractDefinitionPropertyID = contractDefinitionPropertyID;
	}
	public int getContractDefinitionID() {
		return contractDefinitionID;
	}
	public void setContractDefinitionID(int contractDefinitionID) {
		this.contractDefinitionID = contractDefinitionID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Boolean getBooleanValue(){
		try{
			return Boolean.parseBoolean(value);
		}catch(Exception ex){
			return false;
		}
	}
	
	public Double getDoubleValue(){
		try{
			return Double.parseDouble(value);
		}catch(Exception ex){
			return 0d;
		}
	}
	
	
}

class ContractDefinitionPropertyValueID implements Serializable{

	private static final long serialVersionUID = 1L;
	private int contractDefinitionPropertyID;
	private int contractDefinitionID;
	
	public int getContractDefinitionPropertyID() {
		return contractDefinitionPropertyID;
	}
	public void setContractDefinitionPropertyID(int contractDefinitionPropertyID) {
		this.contractDefinitionPropertyID = contractDefinitionPropertyID;
	}
	public int getContractDefinitionID() {
		return contractDefinitionID;
	}
	public void setContractDefinitionID(int contractDefinitionID) {
		this.contractDefinitionID = contractDefinitionID;
	}
}
