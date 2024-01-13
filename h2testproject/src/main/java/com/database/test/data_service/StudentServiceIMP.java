package com.database.test.data_service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.test.entity.Student;
import com.database.test.repo.StudentRepo;


@Service
public class StudentServiceIMP implements StudentService{

@Autowired
private StudentRepo sREPO;
	
	// CONST. INJ. 
	public StudentServiceIMP (StudentRepo anystuREPO) {

		 sREPO = anystuREPO;	
	}


	@Override
	public List<Student> getALLStudents() {	//metodu callback

		
			return sREPO.findAll();  
	}


	@Override
	public Student findbyID(int id) {
		
		Optional<Student> result = sREPO.findById(id);	//bulup bulmayacagi belli degil - sarmalama yaparak deger almaya calisir 
		
		Student wantedStudent = null;
		
		if(result.isPresent()) wantedStudent = result.get();
		
		else new RuntimeException(" with " + id + " id there is no Student ");
		
		return wantedStudent;
	}


	@Override
	public Student save(Student newStudent) {
 
		return sREPO.save(newStudent);
		
	}
					

	@Override
	public void deletebyID(int id) {
 
		sREPO.deleteById(id);
		
	}


	@Override
	public Student updatebyID(int id ) {	//düz kayit yapıyor
		
	    Optional<Student> optionalStudent = sREPO.findById(id);
	    
	    if (optionalStudent.isPresent()) {
	    	
	        Student updatedStudent = optionalStudent.get();
	        
	        return sREPO.save(updatedStudent); //  save yöntemi, varlık nesnesinin kimlik alanı (ID) değerine bağlı olarak yeni bir varlık ekler veya mevcut bir varlığı günceller.
	    }
	    
		else new RuntimeException(" with " + id + " id there is no Student ");
	    
	    return null;
	}
 

}
