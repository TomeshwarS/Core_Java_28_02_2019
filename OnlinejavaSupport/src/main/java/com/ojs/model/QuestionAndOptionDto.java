package com.ojs.model;

public class QuestionAndOptionDto {
	private Long queSerialNo;
	private String queName;
	private String optionName1;
	private String optionName2;
	private String optionName3;
	private String optionName4;
	private Long optionId1;
	private Long optionId2;
	private Long optionId3;
	private Long optionId4;
	private Long answerId;
	public Long getQueSerialNo() {
		return queSerialNo;
	}
	public void setQueSerialNo(Long queSerialNo) {
		this.queSerialNo = queSerialNo;
	}
	public String getQueName() {
		return queName;
	}
	public void setQueName(String queName) {
		this.queName = queName;
	}
	public String getOptionName1() {
		return optionName1;
	}
	public void setOptionName1(String optionName1) {
		this.optionName1 = optionName1;
	}
	public String getOptionName2() {
		return optionName2;
	}
	public void setOptionName2(String optionName2) {
		this.optionName2 = optionName2;
	}
	public String getOptionName3() {
		return optionName3;
	}
	public void setOptionName3(String optionName3) {
		this.optionName3 = optionName3;
	}
	public String getOptionName4() {
		return optionName4;
	}
	public void setOptionName4(String optionName4) {
		this.optionName4 = optionName4;
	}
	public Long getOptionId1() {
		return optionId1;
	}
	public void setOptionId1(Long optionId1) {
		this.optionId1 = optionId1;
	}
	public Long getOptionId2() {
		return optionId2;
	}
	public void setOptionId2(Long optionId2) {
		this.optionId2 = optionId2;
	}
	public Long getOptionId3() {
		return optionId3;
	}
	public void setOptionId3(Long optionId3) {
		this.optionId3 = optionId3;
	}
	public Long getOptionId4() {
		return optionId4;
	}
	public void setOptionId4(Long optionId4) {
		this.optionId4 = optionId4;
	}
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	@Override
	public String toString() {
		return "QuestionAndOptionDto [queSerialNo=" + queSerialNo + ", queName=" + queName + ", optionName1="
				+ optionName1 + ", optionName2=" + optionName2 + ", optionName3=" + optionName3 + ", optionName4="
				+ optionName4 + ", optionId1=" + optionId1 + ", optionId2=" + optionId2 + ", optionId3=" + optionId3
				+ ", optionId4=" + optionId4 + ", answerId=" + answerId + "]";
	}

}
