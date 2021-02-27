package com.jajjamind.commons.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.system.SystemProperties;

import java.util.Base64;

public abstract class EncryptUtil {
	
	private static Logger DEBUGLOG;
	static {
		DEBUGLOG = LogManager.getLogger();
	}
	
	private static final String AES_INIT_VECTOR = "RandomInitVector";
	//Get this from per
	private static final String AES_KEY = System.getProperty("AES_KEY","NaphWagwan20399$");

	public static String encryptAES(String value) {
	    Validate.notEmpty(value,"");
       
		try {
            IvParameterSpec iv = new IvParameterSpec(AES_INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.getEncoder().encodeToString(encrypted));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
            
            DEBUGLOG.error("Failed to generate AES encryption with error [{}]", new Object[] {ex});
        }

        return null;
    }

   
	public static String decryptAES(String encrypted) {
        try {
            Validate.notEmpty(encrypted,"Cipher text cannot be null or empty");
            IvParameterSpec iv = new IvParameterSpec(AES_INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
