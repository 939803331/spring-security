package com.ph.springsecurity.service;

import com.ph.springsecurity.dao.SysUserRoleMapper;
import com.ph.springsecurity.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {
	@Autowired
	private SysUserRoleMapper userRoleMapper;

	public List<SysUserRole> listByUserId(Integer userId) {
		return userRoleMapper.listByUserId(userId);
	}
}
