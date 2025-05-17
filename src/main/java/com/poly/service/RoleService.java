package com.poly.service;

import org.springframework.stereotype.Service;

import com.poly.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService  {
    List<Role> getAllRole();
    Optional<Role> findRoleById(int id);

}
