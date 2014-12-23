package br.diastecnologia.shopmaquinas.bean;

import java.io.Serializable;
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

import br.diastecnologia.shopmaquinas.enums.BillingStatus;

@Entity
@Table(name="Billing")
@NamedQuery(name="Billing.findAll", query="SELECT b FROM Billing b")
public class Billing implements Serializable, Comparable<Billing>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private int billingID;
	
	@ManyToOne
	@JoinColumn(name="contractID", referencedColumnName="contractID")
	private Contract contract;
	
	private Date dueDate;
	
	private double amount;
	
	private BillingStatus status;

	public int getBillingID() {
		return billingID;
	}

	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BillingStatus getStatus() {
		return status;
	}

	public void setStatus(BillingStatus status) {
		this.status = status;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Override
	public int compareTo(Billing other) {
		return other.dueDate.compareTo(this.dueDate);
	}
	
}
