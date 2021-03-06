package com.ph.springsecurity.dao;

import com.ph.springsecurity.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysRoleMapper {
	@Select("SELECT * FROM sys_role WHERE id = #{id}")
	SysRole selectById(Integer id);

	@Select("SELECT * FROM sys_role WHERE name = #{roleName}")
	SysRole selectByName(String roleName);
}
