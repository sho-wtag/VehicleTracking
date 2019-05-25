package com.shahadat.vehicletracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "vehicle")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long vehicle_id;
	
	@Column(name = "lat", nullable = false)
	private String lat;
	
	@Column(name = "lang", nullable = false)
	private String lang;
	
	@Column(name = "tracking_time", nullable = false)
	@CreatedDate
	private Date tracking_time;
	
	public Vehicle() {
		
	}

	public Vehicle(long vehicle_id, String lat, String lang, Date tracking_time) {
		super();
		this.vehicle_id = vehicle_id;
		this.lat = lat;
		this.lang = lang;
		this.tracking_time = tracking_time;
	}

	public long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Date getTracking_time() {
		return tracking_time;
	}

	public void setTracking_time(Date tracking_time) {
		this.tracking_time = tracking_time;
	}
	
	

}
