package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
