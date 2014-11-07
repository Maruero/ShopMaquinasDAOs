package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ContractDefinitionProperty")
@NamedQuery(name="ContractDefinitionProperty.findAll", query="SELECT cdp FROM ContractDefinitionProperty cdp")
public class ContractDefinitionProperty implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int ContractDefinitionPropertyID;
	
	private String name;
	
	private String description;

	public int getContractDefinitionPropertyID() {
		return ContractDefinitionPropertyID;
	}

	public void setContractDefinitionPropertyID(int contractDefinitionPropertyID) {
		ContractDefinitionPropertyID = contractDefinitionPropertyID;
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

}
