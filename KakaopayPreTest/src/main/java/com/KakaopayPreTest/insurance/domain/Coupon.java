package com.KakaopayPreTest.insurance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author leebj
 *
 */
@Entity
public class Coupon {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String  code;
	
	@Column
	private String Issuance;
	
	@Column
	private String applStartDate;
	
	@Column
	private String applEndDate;

	/**
	 * @param id
	 * @param code
	 * @param applStartDate
	 * @param applEndDate
	 */
	public Coupon(Long id, String code, String Issuance, String applStartDate, String applEndDate) {
		super();
		this.id = id;
		this.code = code;
		this.Issuance = Issuance;
		this.applStartDate = applStartDate;
		this.applEndDate = applEndDate;
	}


	/**
	 * 
	 */
	public Coupon() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApplStartDate() {
		return applStartDate;
	}

	public void setApplStartDate(String applStartDate) {
		this.applStartDate = applStartDate;
	}

	public String getApplEndDate() {
		return applEndDate;
	}

	public void setApplEndDate(String applEndDate) {
		this.applEndDate = applEndDate;
	}


	public String getIssuance() {
		return Issuance;
	}


	public void setIssuance(String issuance) {
		Issuance = issuance;
	}
	
	
}
