package com.wplab.service;

public class Seat {
    private int seat_number;
    private String seat_type;
    private Integer rsv_seq; // 예약 번호는 nullable
    
	public int getSeat_number() {
		return seat_number;
	}
	
	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	
	public String getSeat_type() {
		return seat_type;
	}
	
	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	
	public Integer getRsv_seq() {
		return rsv_seq;
	}
	
	public void setRsv_seq(Integer rsv_seq) {
		this.rsv_seq = rsv_seq;
	}
    
    

}
