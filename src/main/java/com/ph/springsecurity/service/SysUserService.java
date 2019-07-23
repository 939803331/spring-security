package com.ph.springsecurity.service;

import com.ph.springsecurity.dao.SysUserMapper;
import com.ph.springsecurity.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
	@Autowired
	private SysUserMapper userMapper;

	public SysUser selectById(Integer id) {
		return userMapper.selectById(id);
	}

	public SysUser selectByName(String name) {
		return userMapper.selectByName(name);
	}
}
