package com.ph.springsecurity.service;

import com.ph.springsecurity.dao.SysRoleMapper;
import com.ph.springsecurity.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
	@Autowired
	private SysRoleMapper roleMapper;

	public SysRole selectById(Integer id){
		return roleMapper.selectById(id);
	}

	public SysRole selectByName(String roleName) {
		return roleMapper.selectByName(roleName);
	}
}
