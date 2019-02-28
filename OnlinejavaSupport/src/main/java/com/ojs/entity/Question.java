package com.ojs.entity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.ojs.mysql.connection.MySqlConnection;

public class Question {
	private Long id;
	private String name;
	private Long marks;
	private Long answerId;
	
	private Long que_serial_no;
	List<Option> listOptions;

	
	public Long getQue_serial_no() {
		return que_serial_no;
	}

	public void setQue_serial_no(Long que_serial_no) {
		this.que_serial_no = que_serial_no;
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

	public Long getMarks() {
		return marks;
	}

	public void setMarks(Long marks) {
		this.marks = marks;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public List<Option> getListOptions() {
		return listOptions;
	}

	public void setListOptions(List<Option> listOptions) {
		this.listOptions = listOptions;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", name=" + name + ", marks=" + marks + ", answerId=" + answerId
				+ ", que_serial_no=" + que_serial_no + ", listOptions=" + listOptions + "]";
	}


}
