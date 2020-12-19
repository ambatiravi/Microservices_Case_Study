package com.example.Subscriptionservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class subscription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer bookid;
	private String subscriptionname;
	private String datesubcribed;
	private String datereturned;

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getSubscriptionname() {
		return subscriptionname;
	}

	public void setSubscriptionname(String subscriptionname) {
		this.subscriptionname = subscriptionname;
	}

	public String getDatesubcribed() {
		return datesubcribed;
	}

	public void setDatesubcribed(String datesubcribed) {
		this.datesubcribed = datesubcribed;
	}

	public String getDatereturned() {
		return datereturned;
	}

	public void setDatereturned(String datereturned) {
		this.datereturned = datereturned;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
