package com.payhub.interswitch.model;


public class BillerItem {
	
	private String categoryid;
	private String billerid;
	private Boolean isAmountFixed;
	private String paymentitemid;
	private String paymentitemname;
	private float amount;
	private String code;
	private String currencyCode;
	private String currencySymbol;
	private String itemCurrencySymbol;
	private String sortOrder;
	private String pictureId;
	private String paymentCode;

	public BillerItem() {
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getBillerid() {
		return billerid;
	}

	public void setBillerid(String billerid) {
		this.billerid = billerid;
	}

	public Boolean getAmountFixed() {
		return isAmountFixed;
	}

	public void setAmountFixed(Boolean amountFixed) {
		isAmountFixed = amountFixed;
	}

	public String getPaymentitemid() {
		return paymentitemid;
	}

	public void setPaymentitemid(String paymentitemid) {
		this.paymentitemid = paymentitemid;
	}

	public String getPaymentitemname() {
		return paymentitemname;
	}

	public void setPaymentitemname(String paymentitemname) {
		this.paymentitemname = paymentitemname;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getItemCurrencySymbol() {
		return itemCurrencySymbol;
	}

	public void setItemCurrencySymbol(String itemCurrencySymbol) {
		this.itemCurrencySymbol = itemCurrencySymbol;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
}
