package com.mindgate.main.restservicetokenencryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EncryptionDecryptionUtility {

	public final static String key="qwertyuiop1234567890!@#$%^&*()12";
	public final static String algorithms="AES";
	
	public String encryptData(TokenWrapper tokenWrapper) {
		ObjectMapper mapper=new ObjectMapper();
		try {
			String json=mapper.writeValueAsString(tokenWrapper);
			SecretKeySpec keySpec=new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithms);
			Cipher cipher=Cipher.getInstance(algorithms);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] encryptedData=cipher.doFinal(json.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public TokenWrapper decryptData(String data) {
		try {
		SecretKeySpec keySpec=new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithms);
		Cipher cipher=Cipher.getInstance(algorithms);
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decodeedData=Base64.getDecoder().decode(data);
		System.out.println(decodeedData);
		byte[] decryptedData=cipher.doFinal(decodeedData);
		ObjectMapper mapper=new ObjectMapper();
		return mapper.readValue(new String(decryptedData, StandardCharsets.UTF_8),TokenWrapper.class);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
			e.printStackTrace();
			return null;
		}
	}
	
}
