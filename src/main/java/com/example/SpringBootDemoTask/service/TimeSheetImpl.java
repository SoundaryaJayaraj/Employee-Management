package com.example.SpringBootDemoTask.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.SpringBootDemoTask.dto.TsResponseDto;
import com.example.SpringBootDemoTask.model.Employee;
import com.example.SpringBootDemoTask.model.TimeSheet;
import com.example.SpringBootDemoTask.repository.EmployeeRepository;
import com.example.SpringBootDemoTask.repository.TimeSheetRepository;

@Service
public class TimeSheetImpl implements TimeSheetService {

	@Autowired
	private TimeSheetRepository tymsheetrepo;

	@Autowired
	private EmployeeRepository employeerepository;

	@Override
	public Pageable findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return pageable;
	}


	@Override
	public List<TsResponseDto> findTotalHoursByDateAndEmpId(Integer empId) {
		List<LocalDate> dates=tymsheetrepo.findAllDateByEmpId(empId);
		List<TsResponseDto> responseDto =new ArrayList<TsResponseDto>();
		for (LocalDate date : dates) {
			TsResponseDto tsResponseDto=new TsResponseDto();
			Employee employee=employeerepository.findById(empId).get();
			List<TimeSheet> tymsheets=tymsheetrepo.findAllByEmpIdAndDate(employee, date);
			Integer duration = 0;
			for (TimeSheet x : tymsheets) {
				Integer i = x.getDuration();
				duration = duration + i;
				tsResponseDto.setDate(date);
				tsResponseDto.setDuration(duration);
				responseDto.add(tsResponseDto);				
			}
		}
		return responseDto;
	}

//	@Override
//	public List<TsResponseDto> findTotalHoursByDateAndEmpId(Integer empId, @RequestBody TsReqDto dto) {
//		List<TsResponseDto> responseDto = new ArrayList<TsResponseDto>();
//		TsResponseDto tsResponseDto = new TsResponseDto();
//		if (dto.getDate() != null) {
//			Employee employee = employeerepository.findById(empId).get();
//			List<TimeSheet> tymsheets = tymsheetrepo.findAllByEmpIdAndDate(employee, dto.getDate());
//			Integer duration = 0;
//			for (TimeSheet x : tymsheets) {
//				Integer i = x.getDuration();
//				duration = duration + i;
//				tsResponseDto.setDate(dto.getDate());
//				tsResponseDto.setDuration(duration);
//				responseDto.add(tsResponseDto);
//			}
//		}
//		if (dto.getDate() == null) {
//			List<LocalDate> dates = tymsheetrepo.findAllDateByEmpId(empId);
//			for (LocalDate date : dates) {
//				List<TimeSheet> tymsheets = tymsheetrepo.findAllByEmpIdAndDate(empId, date);
//				Integer duration = 0;
//				for (TimeSheet x : tymsheets) {
//					Integer i = x.getDuration();
//					duration = duration + i;
//					tsResponseDto.setDate(date);
//					tsResponseDto.setDuration(duration);
//					responseDto.add(tsResponseDto);
//				}
//			}
//		}
//		return responseDto;
//	}

}
