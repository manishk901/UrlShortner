package com.learning.urlshortner.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TinyUrl")
public class TinyUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String url;

	private String shortHand;


	private Integer used;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShortHand() {
		return shortHand;
	}

	public void setShortHand(String shortHand) {
		this.shortHand = shortHand;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

}
