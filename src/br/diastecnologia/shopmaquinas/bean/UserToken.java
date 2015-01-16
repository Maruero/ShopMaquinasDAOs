package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UserToken")
@NamedQuery(name="UserToken.findAll", query="SELECT a FROM UserToken a")
public class UserToken implements Serializable{

	private static final long serialVersionUID = -7133877229519688523L;

	@Id
	@Column(updatable=false)
	private String token;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserID",  referencedColumnName="UserID")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object obj){
		if( obj == null || !(obj instanceof UserToken)){
			return false;
		}
		
		UserToken user = (UserToken)obj;
		return this.token.equals(user.getToken());
	}
	
	@Override
	public int hashCode(){
		return this.token.hashCode();
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	

}
