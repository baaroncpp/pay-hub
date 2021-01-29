package com.payhub.data.airtel.service.imp;

import com.google.gson.JsonObject;
import com.payhub.data.airtel.constant.BundlePaymentStatus;
import com.payhub.data.airtel.constant.DseErrorCodes;
import com.payhub.data.airtel.entity.Bundle;
import com.payhub.data.airtel.entity.BundlePayment;
import com.payhub.data.airtel.entity.BundleReceipt;
import com.payhub.data.airtel.model.BundlePaymentModel;
import com.payhub.data.airtel.network.DseApiService;
import com.payhub.data.airtel.network.RetrofitDseService;
import com.payhub.data.airtel.repository.BundlePaymentRepository;
import com.payhub.data.airtel.repository.BundleReceiptRepository;
import com.payhub.data.airtel.repository.BundleRepository;
import com.payhub.data.airtel.service.BundlePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.com.jajjamind.commons.utils.UUIDUtil;
import java.io.IOException;
import java.util.*;

@Service
public class BundlePaymentServiceImp implements BundlePaymentService {
		
	private BundlePaymentRepository bundlePaymentRepository;
	private BundleRepository bundleRepository;
	private BundleReceiptRepository bundleReceiptRepository;
	
	@Autowired
	public BundlePaymentServiceImp(BundleReceiptRepository bundleReceiptRepository, BundlePaymentRepository bundlePaymentRepository, BundleRepository bundleRepository) {
		this.bundlePaymentRepository = bundlePaymentRepository;
		this.bundleRepository = bundleRepository;
		this.bundleReceiptRepository = bundleReceiptRepository;
	}

	@Override
	public BundlePayment buyBundle(BundlePaymentModel bundlePaymentModel) throws IOException {
		// TODO Auto-generated method stub
		Optional<Bundle> bundle = bundleRepository.findById(bundlePaymentModel.getBundleid());
		if(!bundle.isPresent()) {
			throw new RuntimeException("Bundle with id: "+bundlePaymentModel.getBundleid()+" does not exist");
		}

		DseApiService dseApiService = RetrofitDseService.getRetrofitDseService();

		Call<JsonObject> call = dseApiService.getBundle(bundlePaymentModel.getCustomerNumber(), bundle.get().getCode());

		BundlePayment bundlePayment = new BundlePayment();
		BundleReceipt bundleReceipt = new BundleReceipt();
		Response<JsonObject> response = null;

		try {
			response = call.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(response.body() != null) {

			if(response.body().has("resultDesc") && response.body().has("resultCode")) {
				String msg = response.body().get("resultDesc").getAsString();
				int errorCode = response.body().get("resultCode").getAsInt();

				List<DseErrorCodes> dseErrorCodes = new ArrayList<>(Arrays.asList(DseErrorCodes.values()));
				Optional<DseErrorCodes> ec = dseErrorCodes.stream().filter(code -> code.getCode() == errorCode).findFirst();

				if(ec.isPresent()){
					bundlePayment.setId(UUIDUtil.getUUID());
					bundlePayment.setBundle(bundle.get());
					bundlePayment.setCreatedOn(new Date());
					bundlePayment.setCustomerNumber(bundlePaymentModel.getCustomerNumber());

					if(errorCode == 200 || errorCode == 202){
						bundlePayment.setStatus(BundlePaymentStatus.COMPLETED);
						bundleReceipt.setBundlePaymentStatus(BundlePaymentStatus.COMPLETED);
					}else if( errorCode == 201 ){
						bundleReceipt.setBundlePaymentStatus(BundlePaymentStatus.PENDING);
						bundlePayment.setStatus(BundlePaymentStatus.PENDING);
					}else {
						bundlePayment.setStatus(BundlePaymentStatus.FAILED);
						bundleReceipt.setBundlePaymentStatus(BundlePaymentStatus.FAILED);
					}
					//create bundle receipt
					bundleReceipt.setBundlePayment(bundlePayment);
					bundleReceipt.setId(UUIDUtil.getUUID());
					bundleReceipt.setResponseDescription(msg);
				}
			}
			bundlePaymentRepository.save(bundlePayment);
			bundleReceiptRepository.save(bundleReceipt);
		}else {
			throw new RuntimeException("Failed to connect to Airtel bundle");
		}

		return bundlePayment;
	}

	@Override
	public BundlePayment getBundlePaymentById(String id) {
		// TODO Auto-generated method stub
		Optional<BundlePayment> bundlePayment = bundlePaymentRepository.findById(id);
		
		if(!bundlePayment.isPresent()) {
			throw new RuntimeException("Bundle payment with id: "+id+" does not exist");
		}		
		return bundlePayment.get();
	}

	@Override
	public List<BundlePayment> getBundlePayments(Pageable pageable) {
		// TODO Auto-generated method stub		
		Page<BundlePayment> bundlePayments = bundlePaymentRepository.findAllByIsPaymentSuccessfullTrue(pageable);
		
		return bundlePayments.getContent();
	}

	@Override
	public List<BundlePayment> getBundlePaymentByBundle(String bundleid, Pageable pageable) {
		// TODO Auto-generated method stub
		Optional<Bundle> bundle = bundleRepository.findById(bundleid);
		
		if(!bundle.isPresent()) {
			throw new RuntimeException("No bundle with Id: "+bundleid);
		}
		return bundlePaymentRepository.findAllByBundle(bundle.get(), pageable).getContent();
	}

	@Override
	public List<BundlePayment> getBundlePaymentByDate(Date startDate, Date endDate, Pageable pageable) {
		// TODO Auto-generated method stub
		return bundlePaymentRepository.findAllBycreatedOnBetween(startDate, endDate);
	}

	@Override
	public BundlePayment updateBundlePayment(BundlePayment bundlePayment) {
		return null;
	}

	@Override
	public List<BundlePayment> getUnsuccessfulBundlePayments(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<BundlePayment> bundlePayments = bundlePaymentRepository.findAllByIsPaymentSuccessfullFalse(pageable);
		return bundlePayments.getContent();
	}

	@Override
	public List<BundlePayment> getSusccessfulBundlePayments(Pageable pageable) {
		Page<BundlePayment> bundlePayments = bundlePaymentRepository.findAllByIsPaymentSuccessfullTrue(pageable);
		return bundlePayments.getContent();
	}

}
