package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PersonID",  referencedColumnName="PersonID")
	private Person person;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ContractID",  referencedColumnName="ContractID")
	private Contract contract;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="AdID")
	private List<AdPropertyValue> adPropertyValues;
	
	public int getAdID() {
		return adID;
	}

	public void setAdID(int adID) {
		this.adID = adID;
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
	
	public String getYear(){
		String year = "";
		if( adPropertyValues != null ){
			year += adPropertyValues.stream().filter( p-> p.getAdProperty().getName().equals( AdProperties.YEAR.toString() ) ).findFirst().get().getValue();
		}
		return year;
	}
	
	public String getImage(){
		String image = "";
		if( adPropertyValues != null ){
			image += adPropertyValues.stream().filter( p-> 
				p.getAdProperty().getName().equals( AdProperties.IMAGE.toString() )
				&& p.getValue().contains("mini")
					).findFirst().get().getValue();
		}
		return image;
	}
	
	public String getLongDescription(){
		String desc = "";
		if( adPropertyValues != null ){
			Optional<AdPropertyValue> prop = adPropertyValues.stream().filter( p-> p.getAdProperty().getName().equals( AdProperties.LONG_DESCRIPTION.toString() ) ).findFirst();
			if( prop.isPresent() ){
				desc = prop.get().getValue();
			}
		}
		return desc;
	}
	
	public List<String> getImages(){
		List<String> images = null;
		if( adPropertyValues != null ){
			images = adPropertyValues.stream().filter( p-> p.getAdProperty().getName().equals( AdProperties.IMAGE.toString() ) ).map( p-> p.getValue() ).collect(Collectors.toList() );
		}
		return images;
	}
	

}
