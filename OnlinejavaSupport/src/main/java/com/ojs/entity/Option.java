package com.ojs.entity;

public class Option {

	private Long id;
	private String name;
	private Long q_serrial_id;
	private Long optionId;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQ_serrial_id() {
		return q_serrial_id;
	}

	public void setQ_serrial_id(Long q_serrial_id) {
		this.q_serrial_id = q_serrial_id;
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", name=" + name + ", q_serrial_id=" + q_serrial_id + ", optionId=" + optionId
				+ "]";
	}

}
