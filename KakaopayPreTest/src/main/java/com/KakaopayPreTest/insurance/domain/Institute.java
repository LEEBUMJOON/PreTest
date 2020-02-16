package com.KakaopayPreTest.insurance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Institute {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
    private String name;

    @Column
    private String code;
    

	/**
	 * @param name
	 * @param code
	 */
	public Institute( ) {		
	
	}

	public Long getId() {
		return id;
	}

	/**
	 * @param name
	 * @param code
	 */
	public Institute(String name, String code) {		
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
