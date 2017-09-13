package com.studentapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapp.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
