package com.payhub.interswitch.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Component
public class InterSwitchAuth {	
	
	private static String CLIENT_ID;
	private static String CLIENT_SECRET;
	private static String TERMINAL_ID;
    private static String SIGNATURE_METHOD;
    
    public static final String AUTHORIZATION_REALM = "InterswitchAuth";
    
    @Value("${interswitch.clientid}")
    public void setCLIENT_ID(String value) {
    	InterSwitchAuth.CLIENT_ID = value;
    }
    
    @Value("${interswitch.clientsecret}")
    public void setCLIENT_SECRET(String value) {
    	InterSwitchAuth.CLIENT_SECRET = value;
    }
    
    @Value("${interswitch.terminalid}")
    public void setTERMINAL_ID(String value) {
    	InterSwitchAuth.TERMINAL_ID = value;
    }
    
    @Value("${interswitch.signiture.method}")
    public void setSIGNATURE_METHOD(String value) {
    	InterSwitchAuth.SIGNATURE_METHOD = value;
    }
		
    public static HashMap<String, String> generateInterswitchAuth(String httpMethod, String url, String additionalParams){
        
    	TimeZone lagosTimeZone = TimeZone.getTimeZone("Africa/Lagos");
        Calendar calendar = Calendar.getInstance(lagosTimeZone);
        long timestamp =  calendar.getTimeInMillis() / 1000;
        String timestampString = timestamp + "";
        UUID uuid = UUID.randomUUID();
        String nonce = uuid.toString().replaceAll("-", "");
        String clientIdBase64 = Base64.getEncoder().encodeToString((CLIENT_ID).getBytes());
        String authorization = AUTHORIZATION_REALM + " " + clientIdBase64;
        String signature = generateSignature(timestampString, nonce, url, httpMethod, additionalParams);
        
        HashMap<String, String> authHeaders = new HashMap<String, String>();
        authHeaders.put("Authorization", authorization);
        authHeaders.put("Timestamp", timestampString);
        authHeaders.put("Nonce", nonce);
        authHeaders.put("Signature", signature);
        authHeaders.put("SignatureMethod", SIGNATURE_METHOD);
        authHeaders.put("TerminalId", TERMINAL_ID);

        return authHeaders;
    }

    public static String generateSignature  (String timestamp, String nonce, String url, String httpMethod, String additionalParams){
        String encodedResourceUrl = "";
        try {
            encodedResourceUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }

        
        String signatureCipher = httpMethod + "&" + encodedResourceUrl + "&" + timestamp + "&" + nonce + "&" + CLIENT_ID + "&"+ CLIENT_SECRET;
        
        if(additionalParams != null) {
        	signatureCipher = signatureCipher + "&" + additionalParams;
        }
        
        System.out.println("cipher "+signatureCipher);
        
        MessageDigest messageDigest;
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
        byte[] signatureBytes = messageDigest.digest(signatureCipher.getBytes());
        // encode signature as base 64
        String signature = new String(Base64.getEncoder().encodeToString(signatureBytes));
        return signature;
    }

}
