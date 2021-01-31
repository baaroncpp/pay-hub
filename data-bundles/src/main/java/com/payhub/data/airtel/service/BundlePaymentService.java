package com.payhub.data.airtel.service;

import com.payhub.data.airtel.entity.BundlePayment;
import com.payhub.data.airtel.model.BundlePaymentModel;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface BundlePaymentService {	
	BundlePayment buyBundle(BundlePaymentModel bundlePaymentModel) throws IOException ;
	BundlePayment getBundlePaymentById(String id);
	List<BundlePayment> getBundlePayments(Pageable pageable);
	List<BundlePayment> getUnsuccessfulBundlePayments(Pageable pageable);
	List<BundlePayment> getSusccessfulBundlePayments(Pageable pageable);
	List<BundlePayment> getBundlePaymentByBundle(String bundleid, Pageable pageable);
	List<BundlePayment> getBundlePaymentByDate(Date startDate, Date endDate, Pageable pageable);
	BundlePayment updateBundlePayment(BundlePayment bundlePayment);
}
