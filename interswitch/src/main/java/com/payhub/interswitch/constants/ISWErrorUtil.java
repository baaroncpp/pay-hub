package com.payhub.interswitch.constants;

public class ISWErrorUtil {
	
	public static String errorMessage(String code) {
		
		if(code.equals("90025")) {
			return "NON EXISTENT TRANSACTION";
		}else
		
		if(code.equals("70017")) {
			return "INVALID PAYMENT ITEM";
		}else
		
		if(code.equals("70018")) {
			return "INVALID REQUEST REFERENCE";
		}else
		
		if(code.equals("90096")) {
			return "ISW INTERNAL ERROR";
		}else
		
		if(code.equals("90063")) {
			return "AUTHORIZATION ERROR";
		}else
		
		if(code.equals("90092")) {
			return "ROUTING_ERROR";
		}else
		
		if(code.equals("90091")) {
			return "BILLER TEMPORARILY UNAVAILABLE";
		}else
		
		if(code.equals("90009")) {
			return "REQUEST IN PROGRESS";
		}else
		
		if(code.equals("Q3")) {
			return "PENDING ADVISE REQEST";
		}else
		
		if(code.equals("90020")) {
			return "TRANSACTION DECLINED BY BILLER";
		}else
		
		if(code.equals("70031")) {
			return "INCORRECT BANK CBN CODE";
		}else
		
		if(code.equals("90006")) {
			return "ERROR RESPONSE FROM HOST";
		}else
		
		if(code.equals("90013")) {
			return "INVALID AMOUNT";
		}else
		
		if(code.equals("70013")) {
			return "UN RECOGNIZABLE CUSTOMER NUMBER";
		}else
		
		if(code.equals("900A9")) {
			return "MISSING PHONE NUMBER";
		}else
		
		if(code.equals("90094")) {
			return "DUPLICATE RECORD";
		}else
		
		if(code.equals("90023")) {
			return "UN ACCEPTABLE TRANSACTION FEE";
		}else
		
		if(code.equals("90051")) {
			return "INSUFFICIENT FUNDS ON ISW ACCOUNT";
		}else
		
		if(code.equals("70019")) {
			return "FAILED TO SEND REQUEST DOWNSTREAM";
		}else
		
		if(code.equals("30020")) {
			return "DUPLICATE TRANSFER CODE";
		}else
		
		if(code.equals("70010")) {
			return "BILLER NOT FOUND";
		}else
		
		if(code.equals("70012")) {
			return "UNRECOGNIZED TERMINAL OWNER";
		}else
		
		if(code.equals("70014")) {
			return "UNRECOGNIZED PAYMENT CHANNEL";
		}else
		
		if(code.equals("70015")) {
			return "COLLECTIONS ACCOUNT NOT SETUP";
		}else
		
		if(code.equals("70017")) {
			return "PAYMENT TYPE CODE NOT RECOGNIZED";
		}else
		
		if(code.equals("70018")) {
			return "TRANSACTION REFERENCE NOT FOUND";
		}else
		
		if(code.equals("70020")) {
			return "COLLECTING BANK SETTINGS NOT CONFIGURED";
		}else
		
		if(code.equals("70021")) {
			return "LEAD BANK NOT FOUND OR SETUP";
		}else
		
		if(code.equals("70025")) {
			return "BANK NOT SETUP OR ENABLED FOR BILL PAYMENT";
		}else
		
		if(code.equals("70026")) {
			return "BILLER NOT ENABLED FOR CHANNEL";
		}else
		
		if(code.equals("70027")) {
			return "BANK NOT ENABLED FOR BILLER";
		}else
		
		if(code.equals("70028")) {
			return "TERMINAL OWNER NOT ENABLED FOR BILLER";
		}else
		
		if(code.equals("70029")) {
			return "TERMINAL OWNER NOT ENABLED FOR CHANNEL";
		}else
		
		if(code.equals("70031")) {
			return "UNRECOGNIZED CBN BANK CODE";
		}else
		
		if(code.equals("70037")) {
			return "INCORRECT FEE SETUP ON PAYMENT ITEM";
		}else
		
		if(code.equals("70041")) {
			return "EXPIRED TRANSACTION OR TRANSACTION TRANSMISSION TO BILLER FAILED";
		}else
		
		if(code.equals("90012")) {
			return "INVALID TRANSACTION";
		}else {
			return "";
		}
		
	}
	
}
