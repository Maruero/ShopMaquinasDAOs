package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="AdPropertyValue")
@NamedQuery(name="AdPropertyValue.findAll", query="SELECT apv FROM AdPropertyValue apv")
@IdClass(AdPropertyValueID.class)
public class AdPropertyValue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int adID;
	
	@Id
	private int adPropertyID;
	
	private String value;
	
	@ManyToOne
	@JoinColumn(name="AdPropertyID", referencedColumnName="AdPropertyID")
	private AdProperty adProperty;

	public int getAdID() {
		return adID;
	}

	public void setAdID(int adID) {
		this.adID = adID;
	}

	public int getAdPropertyID() {
		return adPropertyID;
	}

	public void setAdPropertyID(int adPropertyID) {
		this.adPropertyID = adPropertyID;
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

	public AdProperty getAdProperty() {
		return adProperty;
	}

	public void setAdProperty(AdProperty adProperty) {
		this.adProperty = adProperty;
	}
}

class AdPropertyValueID implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int adID;
	private int adPropertyID;
	public int getAdID() {
		return adID;
	}

	public void setAdID(int adID) {
		this.adID = adID;
	}

	public int getAdPropertyID() {
		return adPropertyID;
	}

	public void setAdPropertyID(int adPropertyID) {
		this.adPropertyID = adPropertyID;
	}
}
