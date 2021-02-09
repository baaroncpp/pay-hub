package com.payhub.interswitch.service.imp;

import com.jajjamind.commons.utils.UUIDUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.payhub.interswitch.constants.*;
import com.payhub.interswitch.jpa.entity.*;
import com.payhub.interswitch.model.*;
import com.payhub.interswitch.network.InterSwitchAPIService;
import com.payhub.interswitch.network.RetrofitInterSwitchService;
import com.payhub.interswitch.repository.*;
import com.payhub.interswitch.security.InterSwitchAuth;
import com.payhub.interswitch.service.InterswitchTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class TransactionServiceUsecase implements InterswitchTransactionService {
		
	@Autowired
	private InterSwitchAPIService interSwitchAPIService;
	
	@Value("${interswitch.clientid}")
	private String CLIENT_ID;
	
	@Value("${interswitch.clientsecret}")
	private String CLIENT_SECRET_KEY;
	
	@Value("${interswitch.terminalid}")
	private String TERMINAL_ID;
	
	@Value("${interswitch.signiture.method}")
	private String SIGNATURE_METHOD;
	
	@Value("${interswitch.bankcbnCode}")
	private String bankCbnCode;
	
	@Value("${interswitch.requestreference.prefix}")
	private String INTERSWITCH_PREFIX;
	
	@Value("${interswitch.validate.customer.url}")
	private String VALIDATE_CUSTOMER_URL;
	
	@Value("${interswitch.payment.notification.url}")
	private String MAKE_PAYMENT_URL;
	
	private PaymentNotificationResponseRepository paymentNotificationRepository;
	private PaymentNotificationRequestRepository paymentNotificationRequestRepository;
	private PaymentItemRepository paymentItemRepository; 
	
	@Autowired
	public TransactionServiceUsecase(PaymentNotificationResponseRepository paymentNotificationRepository,
									 PaymentItemRepository paymentItemRepository,
									 PaymentNotificationRequestRepository paymentNotificationRequestRepository) {
		this.paymentNotificationRepository = paymentNotificationRepository;
		this.paymentNotificationRequestRepository = paymentNotificationRequestRepository;
		this.paymentItemRepository = paymentItemRepository;
	}


	@Override
	public List<Biller> getBillers()  throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		String additionalParameters = null;
		
		Map<String, String> headers = InterSwitchAuth.generateInterswitchAuth("GET", "https://interswitch.io/api/v1/quickteller/billers", additionalParameters);
		interSwitchAPIService = RetrofitInterSwitchService.getInterSwitchAPIService(headers);
	
		Call<BillersModel> call = interSwitchAPIService.getBillers();
		Response<BillersModel> response = null;
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("response code: "+response.code());
		System.out.println(response.raw().request().url().toString());
		
		if(response.body() != null) {
			return response.body().getBillers();
		}else {
			throw new RuntimeException("failed to fetch billers");
		}
		
	}

	@Override
	public List<PaymentItem> getBillerItems(int billerId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String additionalParameters = null;
		
		Map<String, String> headers = InterSwitchAuth.generateInterswitchAuth("GET", "api/v1/quickteller/billers/{billerId}/paymentitems", additionalParameters);
		interSwitchAPIService = RetrofitInterSwitchService.getInterSwitchAPIService(headers);
		Call<PaymentItemsModel> call = interSwitchAPIService.getBillerPaymentItems(billerId);
		
		Response<PaymentItemsModel> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.body() != null) {
			return response.body().getPaymentitems();
		}else {
			throw new RuntimeException("failed to fetch payment items, error : "+ response.code());
		}
		
	}

	@Override
	public TransactionStatusResponse getTransactionStatus(String reference) {
		
		Call<TransactionStatusResponse> call = interSwitchAPIService.getTransactionStatus(reference);
		
		Response<TransactionStatusResponse> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.body() != null) {
			return response.body();
		}else {
			throw new RuntimeException("failed to fetch transaction status");
		}
	}

	@Override
	public BalanceInquiryResponse getBalanceIquiry(String inquiryType) {
		
		Call<BalanceInquiryResponse> call = null;
		
		if(inquiryType.equals(InquiryType.ACTUAL_BALANCE.name())) {
			call = interSwitchAPIService.getBalanceInquiry(InquiryType.ACTUAL_BALANCE.getValue(), TERMINAL_ID);
		}
		
		if(inquiryType.equals(InquiryType.TRANSACTIONAL_BALANCE.name())) {
			call = interSwitchAPIService.getBalanceInquiry(InquiryType.ACTUAL_BALANCE.getValue(), TERMINAL_ID);
		}
		
		Response<BalanceInquiryResponse> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.body() != null) {
			return response.body();
		}else {
			throw new RuntimeException("failed to fetch balance inquiry");
		}
	}

	@Override
	public Object validateCustomer(CustomerClientModel customer) throws UnsupportedEncodingException, NoSuchAlgorithmException {
				
		CustomerDetails cd = new CustomerDetails();
		String requestRef = INTERSWITCH_PREFIX +getRandomInteger(100000, 10000000);
		
		cd.setAmount(Long.toString(customer.getAmount()));
		cd.setCustomerEmail(customer.getCustomerEmail());
		cd.setPaymentCode(customer.getPaymentCode());
		cd.setCustomerMobile(customer.getCustomerMobile());
		cd.setCustomerId(customer.getCustomerId());
		cd.setRequestReference(requestRef);
		cd.setBankCbnCode(bankCbnCode);
		cd.setTerminalId(TERMINAL_ID);
		
		String additionalParameters = null;

		Map<String, String> headers = InterSwitchAuth.generateInterswitchAuth("POST",VALIDATE_CUSTOMER_URL, additionalParameters);
		
		InterSwitchAPIService interSwitchAPIService2 = RetrofitInterSwitchService.getInterSwitchAPIService(headers);

		Call<JsonObject> call = interSwitchAPIService2.validateCustomer(cd);
			
		Response<JsonObject> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.body() != null && response.code() == 200) {
			
			if(response.body().has("transactionRef") && response.body().has("customerId")) {

				Gson gson = new Gson();
				CustomerDetailsResponse customerDetailsResponse = gson.fromJson(response.body(), CustomerDetailsResponse.class);
				customerDetailsResponse.setRequestReference(requestRef);
				
				return customerDetailsResponse;
 			}else{				
				String msg = response.body().get("responseMessage").toString();
				String requestReference =  response.body().get("requestReference").toString();
				
				throw new RuntimeException((msg.concat(" REQUESTREFERENCE:- ").concat(requestReference)).replaceAll("\\\\", ""));
			}

		}else {
			throw new RuntimeException("failed to validate customer");
		}
	}

	@Override
	public PaymentNotificationResponse sendPaymentNotification(PaymentNotificationClientModel payment) {
		
		PaymentNotification paymentNotification = new PaymentNotification();
		
		paymentNotification.setTerminalId(TERMINAL_ID);
		paymentNotification.setBankCbnCode(bankCbnCode);
		paymentNotification.setAmount(payment.getAmount());
		paymentNotification.setCustomerEmail(payment.getCustomerEmail());
		paymentNotification.setCustomerId(payment.getCustomerId());
		paymentNotification.setPaymentCode(payment.getPaymentCode());
		paymentNotification.setSurcharge(payment.getSurcharge());
		paymentNotification.setTransactionRef(payment.getTransactionRef());
		paymentNotification.setRequestReference(payment.getRequestReference());
		paymentNotification.setCustomerEmail(payment.getCustomerMobile());
		
		paymentNotificationRequestRepository.save(paymentNotification);//add request to DB
		
		String additionalParameters = payment.getAmount()+ TERMINAL_ID + payment.getRequestReference() + payment.getCustomerId() + payment.getPaymentCode();
		
		Map<String, String> headers = InterSwitchAuth.generateInterswitchAuth("POST", MAKE_PAYMENT_URL, additionalParameters);
		
		InterSwitchAPIService interSwitchAPIService2 = RetrofitInterSwitchService.getInterSwitchAPIService(headers);
		
		Call<JsonObject> call = interSwitchAPIService2.sendPaymentNotification(paymentNotification);
		
		Response<JsonObject> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.body() != null) {
					
			if(response.body().has("transactionRef") && response.body().has("requestReference")) {

				Gson gson = new Gson();
				PaymentNotificationResponse paymentNotificationResponse = gson.fromJson(response.body(), PaymentNotificationResponse.class);
				
				paymentNotificationResponse.setId(UUIDUtil.getUUID());
				paymentNotificationResponse.setCustomer("Payvault");
				paymentNotificationResponse.setAdditionalInfo("Payvault");
				paymentNotificationResponse.setCreatedon(new Date());
				
				paymentNotificationRepository.save(paymentNotificationResponse);
				
				return paymentNotificationResponse;
 			}else{				
				String msg = response.body().get("responseMessage").toString();
				String requestReference =  response.body().get("requestReference").toString();
				
				throw new RuntimeException(msg+" REQUESTREFERENCE:- "+requestReference);
			}

		}else {
			throw new RuntimeException("failed to process payment");
		}
	}

	@Override
	public CategoriesModel getCategories() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String additionalParameters = null;
		
		Map<String, String> headers = InterSwitchAuth.generateInterswitchAuth("GET", "https://interswitch.io/api/v1/quickteller/categorys", additionalParameters);
		interSwitchAPIService = RetrofitInterSwitchService.getInterSwitchAPIService(headers);
		
		Call<CategoriesModel> call = interSwitchAPIService.getCategories();
		Response<CategoriesModel> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.body() != null) {
			return response.body();
		}else {
			throw new RuntimeException("failed to fetch categories");
		}
		
	}

	@Override
	public PaymentNotificationResponse getPaymentNotificationByRequestRef(String requestRef) {
		// TODO Auto-generated method stub
		Optional<PaymentNotificationResponse> payment = paymentNotificationRepository.findByRequestReference(requestRef);
		
		if(!payment.isPresent()) {
			throw new RuntimeException("Payment with requestReference "+requestRef+" not found");
		}
		return payment.get();
	}

	@Override
	public List<PaymentNotificationResponse> getCustomerPaymentNotifications(Pageable pageable, String customer) {
		
		Page<PaymentNotificationResponse> payments = paymentNotificationRepository.findByCustomer(pageable, customer);
		
		if(payments.isEmpty()) {
			throw new RuntimeException("No payments found");
		}
		return payments.toList();
	}

	@Override
	public List<PaymentNotificationResponse> getAllPaymentNotifications(Pageable pageable) {
		
		Page<PaymentNotificationResponse> payments = paymentNotificationRepository.findAll(pageable);
		
		if(payments.isEmpty()) {
			throw new RuntimeException("No payments found");
		}
		return payments.toList();
	}

	@Override
	public PaymentNotificationResponse makePayment(PaymentModel paymentModel) throws IOException {
		
		Optional<PaymentItem> pi = paymentItemRepository.findByPaymentCode(paymentModel.getPaymentCode());
		if(!pi.isPresent()) {
			throw new RuntimeException("Payment Item with paymentCode: "+ paymentModel.getPaymentCode()+" not registered with payvault, contact admin");
		}

		//payment to interswitch
		PaymentNotificationClientModel paymentNotificationClientModel = new PaymentNotificationClientModel();
		
		//multiply by 100 for all amounts to interswitch
		paymentNotificationClientModel.setAmount(Long.toString(paymentModel.getAmount() * 100));
		paymentNotificationClientModel.setRequestReference(paymentModel.getRequestReference());
		paymentNotificationClientModel.setCustomerEmail("info@payhubug.com");
		paymentNotificationClientModel.setCustomerId(paymentModel.getCustomerNumber());
		paymentNotificationClientModel.setCustomerMobile("0773039553");
		paymentNotificationClientModel.setSurcharge("0");
		paymentNotificationClientModel.setTransactionRef(paymentModel.getTransactionRef());
		paymentNotificationClientModel.setPaymentCode(paymentModel.getPaymentCode());

		return makePaymentToInterswitch(paymentNotificationClientModel);
	}
	
	public PaymentNotificationResponse makePaymentToInterswitch(PaymentNotificationClientModel payment) throws IOException {
		
		PaymentNotification paymentNotification = new PaymentNotification();
		
		paymentNotification.setTerminalId(TERMINAL_ID);
		paymentNotification.setBankCbnCode(bankCbnCode);
		paymentNotification.setAmount(payment.getAmount());
		paymentNotification.setCustomerEmail(payment.getCustomerEmail());
		paymentNotification.setCustomerId(payment.getCustomerId());
		paymentNotification.setPaymentCode(payment.getPaymentCode());
		paymentNotification.setSurcharge(payment.getSurcharge());
		paymentNotification.setTransactionRef(payment.getTransactionRef());
		paymentNotification.setRequestReference(payment.getRequestReference());
		paymentNotification.setCustomerEmail(payment.getCustomerMobile());
		
		paymentNotificationRequestRepository.save(paymentNotification);//add request to DB
		
		String additionalParameters = payment.getAmount()+ TERMINAL_ID + payment.getRequestReference() + payment.getCustomerId() + payment.getPaymentCode();
		Map<String, String> headers = InterSwitchAuth.generateInterswitchAuth("POST", MAKE_PAYMENT_URL, additionalParameters);
		
		InterSwitchAPIService interSwitchAPIService2 = RetrofitInterSwitchService.getInterSwitchAPIService(headers);
		
		Call<JsonObject> call = interSwitchAPIService2.sendPaymentNotification(paymentNotification);
		
		Response<JsonObject> response = null;
		
		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(response.isSuccessful()  && response.body() != null) {
					
			if(response.body().has("transactionRef") && response.body().has("requestReference")) {

				Gson gson = new Gson();
				PaymentNotificationResponse paymentNotificationResponse = gson.fromJson(response.body(), PaymentNotificationResponse.class);
				
				paymentNotificationResponse.setId(UUIDUtil.getUUID());
				paymentNotificationResponse.setCustomer("Payvault");
				paymentNotificationResponse.setAdditionalInfo("Payvault");
				paymentNotificationResponse.setCreatedon(new Date());
				
				paymentNotificationRepository.save(paymentNotificationResponse);
				
				return paymentNotificationResponse;
 			}else{	
				String msg = response.body().get("responseMessage").getAsString();
				String requestReference =  response.body().get("requestReference").getAsString();
				String responseCode = response.body().get("responseCode").getAsString().trim();

				throw new RuntimeException(requestReference+": "+msg);
			}

		}else{
			throw new RuntimeException("failed to connect to Interswitch");
		}
	}

	public int getRandomInteger(int maximum, int minimum){ return ((int) (Math.random()*(maximum - minimum))) + minimum; }

}
