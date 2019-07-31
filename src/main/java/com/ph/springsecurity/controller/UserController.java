package com.ph.springsecurity.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserController {

	public static void main(String[] args) {
		String s = "2019-06-21 11:23:33";

		LocalDateTime parse = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(parse);
	}
}
