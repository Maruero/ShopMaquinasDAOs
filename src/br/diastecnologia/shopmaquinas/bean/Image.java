package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Image")
@NamedQuery(name="Image.findAll", query="SELECT c FROM Image c")
public class Image implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int imageID;
	
	private String path;
	
	@ManyToOne
	@JoinColumn(name="personID", referencedColumnName="personID")
	private Person person;
	
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public boolean equals(Object obj){
		if( obj == null || !(obj instanceof Image)){
			return false;
		}
		
		Image image = (Image)obj;
		return imageID == image.imageID;
	}
	
	@Override
	public int hashCode(){
		return imageID;
	}
	
}
