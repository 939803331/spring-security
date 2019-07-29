package com.ph.springsecurity.entity;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @Author: PiHao
 * Date: Created in 2019-07-29
 * Time: 20:36
 */
public class SmsCode {
	private String code;
	private LocalDateTime expireTime;

	public SmsCode(String code, int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public SmsCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}

	public boolean isExpire() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
}
