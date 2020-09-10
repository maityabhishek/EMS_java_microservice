package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.model.Task;

@Repository
public interface TaskService extends JpaRepository<Task,Integer> {

	Task findById(int id);
	List<Task> findAllByManager(String Manager);
	List<Task> findAllByEmployee(String Employee);
	
	

}
