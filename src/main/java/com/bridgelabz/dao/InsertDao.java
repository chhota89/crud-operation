package com.bridgelabz.dao;

import java.util.List;

import com.bridgelabz.dto.EmployeeDto;

public interface InsertDao {
	boolean isInserted(EmployeeDto employeeDto);
	boolean isInserted(List<EmployeeDto> empList);
	void update(EmployeeDto employeeDto);
	void delete(EmployeeDto employeeDto);
	List<EmployeeDto> getEmployeeList();
	List<String> displayName();
	List<EmployeeDto> getAssendingOrder();
	List<EmployeeDto> getTenEmployeeList(int start);
}
