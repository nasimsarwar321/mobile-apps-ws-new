package com.appsdeveloperblogs.share;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.appsdeveloperblogs.web.security.SecurityConstants;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLOMNPQRSTYXZabcdefghijklmopqrstyxz";

	public String generateUserId(int length) {
		return generateRandomString(length);

	}

	public String generateAddressId(int length) {
		return generateRandomString(length);

	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

		}
		System.out.println("Return Value: " + returnValue.toString());
		return returnValue.toString();

	}

	public static boolean hastokenExpried(String token) {

		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token).getBody();

		Date tokenExpirationDate = claims.getExpiration();
		Date todayDate = new Date();

		// System.out.println("tokenExpirationDate" +tokenExpirationDate);
		return tokenExpirationDate.before(todayDate);
	}

	public String generateEmailVerificationToken(String userId) {
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		return token;

	}

}