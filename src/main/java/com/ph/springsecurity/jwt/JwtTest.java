package com.ph.springsecurity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtTest {
	static String stringKey ="lwlsecret";
	static byte[] encodedKey = Base64.decodeBase64(stringKey);
	static SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

	public static void main(String[] args) {
		//bb();
		aa("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NjQ0NzMzNjgsIm5hbWUiOiJsaXV3aWxsb3ciLCJsZXZlbCI6IjEwMCIsImlzcyI6Imx3bCJ9.-srQwIwAnrKdtywWIQRJiwpqRG9BN1Jdpa4qitNOYsk");
	}

	private static void bb() {
		//设置算法为HS256
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date now = new Date(System.currentTimeMillis());
		JwtBuilder builder = Jwts.builder()
				.setHeaderParam("typ", "JWT")    //设置header
				.setHeaderParam("alg", "HS256")
				.setIssuedAt(now)     //设置iat
				.claim("name", "liuwillow")   //设置payload的键值对
				.claim("level", "100")
				.setIssuer("lwl")
				.signWith(signatureAlgorithm, key);    //签名，需要算法和key
		String jwt = builder.compact();
		System.out.println("生成的jwt:" + jwt);
	}

	private static void aa(String jwt) {
		//获取claims
		Claims claims = Jwts.parser()
				.setSigningKey(key)     //此处的key要与之前创建的key一致
				.parseClaimsJws(jwt)
				.getBody();
		//获取name和level
		String name = (String) claims.get("name");
		String level = (String) claims.get("level");
		System.out.println("name:" + name + " level:" + level);
		System.out.println(claims.getSubject());
	}
}
