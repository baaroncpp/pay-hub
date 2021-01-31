package com.payhub.interswitch.network;

import com.payhub.interswitch.jpa.entity.PaymentNotification;
import com.payhub.interswitch.jpa.entity.PaymentNotificationResponse;
import com.payhub.interswitch.model.*;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

@Component
public interface InterSwitchAPIService {
	
	@GET("api/v1/quickteller/categorys")
	Call<CategoriesModel> getCategories();
	
	@GET("api/v1/quickteller/billers")
	Call<BillersModel> getBillers();
	
	@GET("api/v1A/svapayments/transactions/{requestReference}")
	Call<TransactionStatusResponse> getTransactionStatus(@Path ("requestReference") String requestReference);

	@GET("api/v1/quickteller/billers/{billerid}/paymentitems")
	Call<PaymentItemsModel> getBillerPaymentItems(@Path ("billerid")  int billerid);
	
	@GET("api/v1A/svapayments/terminal/balance/{InquiryType}/{terminalId}")
	Call<BalanceInquiryResponse> getBalanceInquiry(@Path ("InquiryType") int inquiryType, @Path ("terminalId") String terminalId);
	
	@POST("api/v1A/svapayments/validateCustomer")
	Call<JsonObject> validateCustomer(@Body CustomerDetails customerDetails);
	
	@POST("api/v1A/svapayments/sendAdviceRequest")
	Call<JsonObject> sendPaymentNotification(@Body PaymentNotification paymentNotification);
	
	@POST("api/v1A/svapayments/payment")
	Call<PaymentNotificationResponse> makePayment(@Body InterswitchPayment interswitchPayment);
	
	@POST("api/v1A/svapayments/cashwithdrawal")
	Call<CashWithdrawalResponse> cashWithdrawal(@Body CashWithdrawal cashWithdrawal);

}
