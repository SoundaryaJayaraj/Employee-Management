package com.example.SpringBootDemoTask.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.SpringBootDemoTask.model.LeaveType;

public interface LeaveTypeRepository extends CrudRepository<LeaveType, Integer>{

	Optional<LeaveType> findByLtId(Integer ltId);

}
