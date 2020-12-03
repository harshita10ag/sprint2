/**
 * author--Harshita Agarwal
 * create service entity with attributes
 * add all service and generate total amount of bill
 */
package com.cg.corona.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "service_master")
public class Laboratory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int patientId;
	private String testName;
	private double testPrice;
	private String roomType;
	private double roomPrice;
	private double totalBill;
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	/**
	 * @return the testPrice
	 */
	public double getTestPrice() {
		return testPrice;
	}
	/**
	 * @param testPrice the testPrice to set
	 */
	public void setTestPrice(double testPrice) {
		this.testPrice = testPrice;
	}
	/**
	 * @return the totalBill
	 */
	public double getTotalBill() {
		return totalBill;
	}
	/**
	 * @param totalBill the totalBill to set
	 */
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}
	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	/**
	 * @return the roomPrice
	 */
	public double getRoomPrice() {
		return roomPrice;
	}
	/**
	 * @param roomPrice the roomPrice to set
	 */
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	
}
