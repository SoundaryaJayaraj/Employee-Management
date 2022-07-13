package com.example.SpringBootDemoTask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootDemoTask.dto.ResponseDto;
import com.example.SpringBootDemoTask.dto.TimeSheetDto;
import com.example.SpringBootDemoTask.dto.TsReqDto;
import com.example.SpringBootDemoTask.dto.TsResponseDto;
import com.example.SpringBootDemoTask.exception.ResourceNotFoundException;
import com.example.SpringBootDemoTask.model.Employee;
import com.example.SpringBootDemoTask.model.TimeSheet;
import com.example.SpringBootDemoTask.repository.EmployeeRepository;
import com.example.SpringBootDemoTask.repository.TimeSheetRepository;
import com.example.SpringBootDemoTask.service.TimeSheetService;

@RestController
@RequestMapping("/api/v1")
public class TimeSheetController {

	@Autowired
	private TimeSheetRepository tymsheetrepo;
	@Autowired
	private EmployeeRepository employeerepository;
	@Autowired
	TimeSheetService tymshtservice;

	@PostMapping("/create/timesheet/{empId}")
	public String createTimesheet(@PathVariable(value = "empId") Integer empId, @RequestBody TimeSheetDto dto) {
		try {
			TimeSheet tymsheet = new TimeSheet();
			tymsheet.setDate(dto.getDate());
			tymsheet.setDescription(dto.getDescription());
			tymsheet.setProject(dto.getProject());
			tymsheet.setTask(dto.getTask());
			tymsheet.setDuration(dto.getDuration());
			// finding the employee
			Employee employee = employeerepository.findById(empId).get();
			tymsheet.setEmpId(employee);
			tymsheetrepo.save(tymsheet);
			return "Timesheet Created!!";
		} catch (Exception e) {
			return "Failed to Create!!";
		}
	}

	@PutMapping("/update/timesheet/{tsId}")
	public String updateTimesheet(@PathVariable(value = "tsId") Integer TimeSheetId, @RequestBody TimeSheetDto dto) {
		try {
			// finding the Time Sheet
			TimeSheet tymsheet = tymsheetrepo.findById(TimeSheetId)
					.orElseThrow(() -> new ResourceNotFoundException("No records to update"));
			if (dto.getDate() != null) {
				tymsheet.setDate(dto.getDate());
			}
			if (dto.getDescription() != null) {
				tymsheet.setDescription(dto.getDescription());
			}
			if (dto.getProject() != null) {
				tymsheet.setProject(dto.getProject());
			}
			if (dto.getTask() != null) {
				tymsheet.setTask(dto.getTask());
			}
			tymsheet.setDuration(dto.getDuration());
			tymsheetrepo.save(tymsheet);
			return "Updated Timesheet!!";
		} catch (Exception e) {
			return "Failed to Update Timesheet";
		}
	}

	@GetMapping("/timesheet/{empId}")
	public ResponseDto getTimeSheet(@PathVariable(value = "empId") Integer empId, @RequestBody TsReqDto dto) {
		ResponseDto responsedto = new ResponseDto();
		Pageable page = tymshtservice.findPaginated(dto.getPageNo(), dto.getPageSize(), dto.getSortField(),
				dto.getSortDir());
		// finding the employee
		Employee employee = employeerepository.findById(empId).get();
		if (dto.getDate() != null) {
			List<TimeSheet> timesheet = tymsheetrepo.findAllByEmpIdAndDate(employee, dto.getDate(), page);
			responsedto.setTymsheet(timesheet);
		} else {
			List<TimeSheet> timesheet = tymsheetrepo.findAllByEmpId(employee, page);
			responsedto.setTymsheet(timesheet);
		}
		responsedto.setSortField(dto.getSortField());
		responsedto.setSortDir(dto.getSortDir());
		return responsedto;

	}

//	@GetMapping("/duration/{empId}")
//	public List<TsResponseDto> getDuration(@PathVariable(value = "empId") Integer empId, @RequestBody TsReqDto dto) {
//		List<TsResponseDto> responseDto = tymshtservice.findTotalHoursByDateAndEmpId(empId, dto);
//		return responseDto;
//	}
	
	@GetMapping("/duration/{empId}")
	public List<TsResponseDto> getDuration(@PathVariable(value = "empId") Integer empId, @RequestBody TsReqDto dto) {
		TsResponseDto tsResponseDto = new TsResponseDto();
		Employee employee = employeerepository.findById(empId).get();
		if (dto.getDate() != null) {
			List<TsResponseDto> responseDto = new ArrayList<TsResponseDto>();
			List<TimeSheet> tymsheets = tymsheetrepo.findAllByEmpIdAndDate(employee, dto.getDate());
			Integer duration = 0;
			for (TimeSheet x : tymsheets) {
				Integer i = x.getDuration();
				duration = duration + i;
			}
			tsResponseDto.setDate(dto.getDate());
			tsResponseDto.setDuration(duration);
			responseDto.add(tsResponseDto);
			return responseDto;
		}
		else {
			List<TsResponseDto> responseDto=tymshtservice.findTotalHoursByDateAndEmpId(empId);
			return responseDto;
		}

	}
}


































//	@GetMapping("/timesheet/{empId}")
//	public ResponseDto getTimeSheetList(@PathVariable(value = "empId") Integer empId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
//		ResponseDto responsedto = new ResponseDto();
//		// finding the employee
//		Employee employee = employeerepository.findById(empId).get();
//		List<TimeSheet> tymsheet= tymsheetrepo.findAllByEmpIdAndDate(employee,date);
//		responsedto.setTymsheet(tymsheet);
//		return responsedto;
//		
//	}

//	@GetMapping("/timesheet/{empId}/{pageNo}")
//	public ResponseDto getTimeSheet(@PathVariable(value = "empId") Integer empId,
//			@PathVariable(value = "pageNo",required=false) int pageNo,
//			@RequestParam(value ="pageSize",required=false) int pageSize,
//			@RequestParam(value ="sortField", required = false) String sortField,
//			@RequestParam(value ="sortDir",required=false) String sortDir,
//			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@RequestParam(required=false)LocalDate date) {
//			ResponseDto responsedto = new ResponseDto();
//			Pageable page = tymshtservice.findPaginated(pageNo, pageSize,sortField, sortDir);
//			// finding the employee
//			Employee employee = employeerepository.findById(empId).get();
//			//List<TimeSheet> timesheet = tymsheetrepo.findAllByEmpId(employee,page);
//			List<TimeSheet> timesheet =tymsheetrepo.findAllByEmpIdAndDate(employee,date,page);
//			responsedto.setTymsheet(timesheet);
//			responsedto.setSortField(sortField);
//			responsedto.setSortDir(sortDir);
//			return responsedto;
//		}


//@GetMapping("/duration/{empId}")
//public Integer getDuration(@PathVariable(value = "empId") Integer empId, @RequestBody TsReqDto dto) {
//	// finding the employee
//	Employee employee = employeerepository.findById(empId).get();
//	if (dto.getDate() != null) {
//		List<TimeSheet> tymsheet = tymsheetrepo.findAllByEmpIdAndDate(employee, dto.getDate());
//		Integer duration = tymsheet.stream().map(x -> x.getDuration())
//				.collect(Collectors.summingInt(Integer::intValue));
//		return duration;
//	} else {
//		List<TimeSheet> tymsheet = tymsheetrepo.findAllByEmpId(employee);
//		Integer duration = tymsheet.stream().map(x -> x.getDuration())
//				.collect(Collectors.summingInt(Integer::intValue));
//		return duration;
//	}
//
//}
