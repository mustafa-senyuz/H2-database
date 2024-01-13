package com.database.test.data_service;

import java.util.List;

import com.database.test.entity.Student;


public interface StudentService {
	
	List<Student> getALLStudents();
	
	Student findbyID(int id);
	
	Student save(Student newStudent);	// Student save(Arbeiter neuarbeiter) gibi de kullanilabilir
	
	Student updatebyID(int id);
	
	void deletebyID(int id);


}
