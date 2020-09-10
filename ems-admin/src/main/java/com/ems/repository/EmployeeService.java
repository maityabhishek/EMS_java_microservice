package com.ems.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ems.model.Employee;

@Repository
public interface EmployeeService extends CrudRepository<Employee,String> {
	
	Optional<Employee> findById(String id);
	List<Employee> findAllByStatus(int status);
	List<Employee> findAllByManagerAndStatus(String manager,int status);


}
