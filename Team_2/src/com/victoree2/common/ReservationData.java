package com.victoree2.common;

public class ReservationData {//입실퇴실, 남은시간 기록
	private String id; //id
	private String name; //이름
	private String startDay; //결제일
	private String endDay; //종료일
	private String remainderPeriod; //남은기간
	private boolean payStatus; //1: 정기구독권 2: 시간제사용자
	private int indexX; // 정기구독권의 경우 고정 자리 정보값을 받는다.
	private int indexY;
	
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getRemainderPeriod() {
		return remainderPeriod;
	}
	public void setRemainderPeriod(String remainderPeriod) {
		this.remainderPeriod = remainderPeriod;
	}
	public boolean isPayStatus() {
		return payStatus;
	}
	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}
}
