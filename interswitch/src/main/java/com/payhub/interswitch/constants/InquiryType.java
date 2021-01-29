package com.payhub.interswitch.constants;

public enum InquiryType {
		
	TRANSACTIONAL_BALANCE(1),
	ACTUAL_BALANCE(0);
	
	private final int index;
	InquiryType(int index){
		this.index = index;
	}
	
	public int getValue() {
		return index;
	}
}
