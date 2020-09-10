package com.ems.repository;

import org.springframework.stereotype.Repository;

import com.ems.model.UserRole;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRoleAccess extends CrudRepository<UserRole,Integer> {
	

}
