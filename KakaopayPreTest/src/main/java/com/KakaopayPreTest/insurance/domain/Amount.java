package com.KakaopayPreTest.insurance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author leebj
 *
 */
@Entity
public class Amount {
	


	@Id
	@GeneratedValue
	private Long id;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institute institute;
	
	@Column
	private String  year;
	
	@Column
	private String month;
	
	@Column
	private int amount;
	
	public Amount() {

	}

	/**
	 * @param institute
	 * @param year
	 * @param month
	 * @param amount
	 */
	public Amount(Institute institute, String year, String month, int amount) {
		super();
		this.institute = institute;
		this.year = year;
		this.month = month;
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
}
