package com.payhub.interswitch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class BillersModel {
	private List<Biller> billers;

	public BillersModel() {
	}

	public List<Biller> getBillers() {
		return billers;
	}

	public void setBillers(List<Biller> billers) {
		this.billers = billers;
	}
}
