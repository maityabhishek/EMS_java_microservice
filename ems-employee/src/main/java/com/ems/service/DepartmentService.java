package com.ems.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ems.model.Department;

@Service
public interface DepartmentService extends CrudRepository<Department,Integer> {

	Department findById(int id);
}
