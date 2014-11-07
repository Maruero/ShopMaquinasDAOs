package br.diastecnologia.shopmaquinas.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Message")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int messageID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="FromPersonID",  referencedColumnName="PersonID")
	private Person fromPerson;
	
	@ManyToOne
	@JoinColumn(name="ToPersonID",  referencedColumnName="PersonID")
	private Person toPerson;
	
	@ManyToOne
	@JoinColumn(name="AdID",  referencedColumnName="AdID")
	private Ad ad;
	
	private String text;

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(Person fromPerson) {
		this.fromPerson = fromPerson;
	}

	public Person getToPerson() {
		return toPerson;
	}

	public void setToPerson(Person toPerson) {
		this.toPerson = toPerson;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
