package com.example.SpringBootDemoTask.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootDemoTask.model.Employee;
import com.example.SpringBootDemoTask.model.LeaveDetails;

@Repository
public interface LeaveDetailsRepository extends CrudRepository<LeaveDetails, Integer> {

	List<LeaveDetails> findAllByEmpId(Employee emaployee);

}
