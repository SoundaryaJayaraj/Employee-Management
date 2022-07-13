package com.example.SpringBootDemoTask.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SpringBootDemoTask.model.Employee;
import com.example.SpringBootDemoTask.model.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {

	List<TimeSheet> findAllByEmpId(Employee employee, Pageable pageable);

	List<TimeSheet> findAllByEmpIdAndDate(Employee employee, LocalDate date);

	List<TimeSheet> findAllByEmpIdAndDate(Employee employee, LocalDate date, Pageable page);

	List<TimeSheet> findAllByEmpId(Employee employee);

	@Query("SELECT u.date FROM TimeSheet AS u WHERE u.empId.id = ?1 GROUP BY u.date")
	List<LocalDate> findAllDateByEmpId(Integer empId);

	List<TimeSheet> findAllByEmpIdAndDate(Integer empId, LocalDate date);

	
}
