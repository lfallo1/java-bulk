package com.catalyst.teammateria.injuryreport.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Password utility
 * @author lfallon
 *
 */
public final class PasswordUtil {
    
    private PasswordUtil() {
    }
    
	/**
	 * Encode password using BCrypt. Salt is auto generated
	 * @param rawPassword
	 * @return
	 */
	public static String encodePassword(String rawPassword) {
		return new BCryptPasswordEncoder().encode(rawPassword);
	}
}
