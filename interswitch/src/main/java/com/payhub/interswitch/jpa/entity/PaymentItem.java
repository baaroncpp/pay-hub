package com.payhub.interswitch.jpa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_paymentitem", schema = "interswitch")
public class PaymentItem  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String categoryid;
	
	@Column(name = "interswitch_billerid", unique = true, nullable = false)
	private String billerid;
	
	@Column(name = "isamountfixed")
	private Boolean isAmountFixed;
	
	@Transient
	private String paymentitemid;
	
	@Column(name = "paymentitemname", nullable = false)
	private String paymentitemname;
	
	@Column(name = "amount", nullable = false)
	private String amount;
	
	@Transient
	private String billerType;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "currencycode")
	private String currencyCode;
	
	@Column(name = "currencysymbol")
	private String currencySymbol;
	
	@Transient
	private String itemCurrencySymbol;
	
	@Transient
	private String sortOrder;
	
	@Transient
	private String pictureId;
	
	@Id
	@Column(name = "paymentcode", unique = true, nullable = false)
	private String paymentCode;
	
	@Column(name = "createdon", nullable = false)
	private Date createdon;

	public PaymentItem() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBillerType() {
		return billerType;
	}

	public void setBillerType(String billerType) {
		this.billerType = billerType;
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

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
}
