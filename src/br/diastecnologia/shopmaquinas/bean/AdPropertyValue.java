package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

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
	
	@Id
	private String value;
	
	@ManyToOne
	@JoinColumn(name="adPropertyID", insertable = false, updatable = false)
	private AdProperty adProperty;

	public int getAdPropertyID() {
		return adPropertyID;
	}

	public void setAdPropertyID(int adPropertyID) {
		this.adPropertyID = adPropertyID;
	}
	
	
	public int getAdID() {
		return adID;
	}

	public void setAdID(int adID) {
		this.adID = adID;
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
			try{
				NumberFormat f = NumberFormat.getInstance(new Locale("pt", "BR"));
				f.setMaximumFractionDigits(2);
				f.setMinimumFractionDigits(2);
				
				return f.parse(value).doubleValue();
			}catch(Exception ex2){
				return 0d;
			}
		}
	}
	
	public static void main(String[] args){
		AdPropertyValue value = new AdPropertyValue();
		value.setValue("25.000,00");
		
		System.out.println( value.getDoubleValue() );
	}

	public AdProperty getAdProperty() {
		return adProperty;
	}

	public void setAdProperty(AdProperty adProperty) {
		this.adProperty = adProperty;
		this.adPropertyID = adProperty.getAdPropertyID();
	}
	
	@Override
	public int hashCode (){
		return adID * adPropertyID * (value != null ? value.hashCode() : 1);
	}
	
	@Override
	public boolean equals(Object other){
		if( other != null && other instanceof AdPropertyValue ){
			AdPropertyValue another = (AdPropertyValue)other;
			return adID == another.adID && adPropertyID == another.adPropertyID && value != null ? value.equals(another.value) : true;
		}else{
			return false;
		}
	}
}

class AdPropertyValueID implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int adID;
	private int adPropertyID;
	private String value;
	
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
	
	@Override
	public int hashCode (){
		return adID * adPropertyID * (value != null ? value.hashCode() : 1);
	}
	
	@Override
	public boolean equals(Object other){
		if( other != null && other instanceof AdPropertyValueID ){
			AdPropertyValueID another = (AdPropertyValueID)other;
			return adID == another.adID && adPropertyID == another.adPropertyID && value != null ? value.equals(another.value) : true;
		}else{
			return false;
		}
	}
}
