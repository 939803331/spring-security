package com.ph.springsecurity.service;

import com.ph.springsecurity.dao.SysPermissionMapper;
import com.ph.springsecurity.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionService {
	@Autowired
	private SysPermissionMapper permissionMapper;

	/**
	 * 获取指定角色所有权限
	 */
	public List<SysPermission> listByRoleId(Integer roleId) {
		return permissionMapper.listByRoleId(roleId);
	}
}
