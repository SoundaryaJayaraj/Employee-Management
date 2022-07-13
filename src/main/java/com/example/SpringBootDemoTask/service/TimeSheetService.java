package com.example.SpringBootDemoTask.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.example.SpringBootDemoTask.dto.TsResponseDto;

public interface TimeSheetService {

	public Pageable findPaginated(int pageNo, int pageSize,String sortField, String sortDir);

	//public List<TsResponseDto> findTotalHoursByDateAndEmpId(Integer empId, @RequestBody TsReqDto dto);

	public List<TsResponseDto> findTotalHoursByDateAndEmpId(Integer empId);
}
