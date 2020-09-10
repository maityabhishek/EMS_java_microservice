package com.ems.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class EncryptToHash {
	
	public String getHash(String password) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] messageDigest = md.digest(password.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest);
		String hashtext = no.toString(16);
		while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 
		return hashtext;
		
	}

}
