package com.database.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.test.entity.Student;


@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

													
}
