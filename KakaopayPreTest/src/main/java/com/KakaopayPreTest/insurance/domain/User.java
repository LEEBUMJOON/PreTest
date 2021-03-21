package com.KakaopayPreTest.insurance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String  userID;
	
	@Column
	private String  couponCode;
	
	@Column
	private String useYn;
	
	@Column
	private String cancleYn;
	
	@Column
	private String excutionDate;

	
	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param userID
	 * @param useYn
	 * @param cancleYn
	 * @param excutionDate
	 */
	public User(Long id, String userID, String useYn, String cancleYn, String excutionDate) {
		super();
		this.id = id;
		this.userID = userID;
		this.useYn = useYn;
		this.cancleYn = cancleYn;
		this.excutionDate = excutionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCancleYn() {
		return cancleYn;
	}

	public void setCancleYn(String cancleYn) {
		this.cancleYn = cancleYn;
	}

	public String getExcutionDate() {
		return excutionDate;
	}

	public void setExcutionDate(String excutionDate) {
		this.excutionDate = excutionDate;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	
	
	
	
}
