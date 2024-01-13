/*Embedded H2 DB*/


package com.database.test.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {

	public Student() {
		 
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 30, columnDefinition = "BIGINT")
	private long classno;

	public int getId() {
		return id;
	}
 
	/*
	   public void setId(int id) { this.id = id; }
	*/
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getClassno() {
		return classno;
	}

	public void setClassno(long classno) {
		this.classno = classno;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", classno=" + classno + "]";
	}
	
}
 


