package com.wplab.service;

import java.sql.Date;

public class Reservation {
    private int rsv_seq;
    private String name;
    private String passwd;
    private String phone;
    private int seat_number;
    private Date rsv_date;
    
	public int getRsv_seq() {
		return rsv_seq;
	}
	
	public void setRsv_seq(int rsv_seq) {
		this.rsv_seq = rsv_seq;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getSeat_number() {
		return seat_number;
	}
	
	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	
	public Date getRsv_date() {
		return rsv_date;
	}
	
	public void setRsv_date(Date rsv_date) {
		this.rsv_date = rsv_date;
	}
    
    
}
