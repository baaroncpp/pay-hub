package com.payhub.data.airtel.service.imp;

import com.payhub.data.airtel.entity.BundlePayment;
import com.payhub.data.airtel.entity.BundleReceipt;
import com.payhub.data.airtel.repository.BundlePaymentRepository;
import com.payhub.data.airtel.repository.BundleReceiptRepository;
import com.payhub.data.airtel.service.BundleReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BundleReceiptServiceImp implements BundleReceiptService {

    private BundleReceiptRepository bundleReceiptRepository;
    private BundlePaymentRepository bundlePaymentRepository;

    @Autowired
    public BundleReceiptServiceImp(BundleReceiptRepository bundleReceiptRepository,
                                   BundlePaymentRepository bundlePaymentRepository){
        this.bundleReceiptRepository = bundleReceiptRepository;
        this.bundlePaymentRepository = bundlePaymentRepository;
    }

    @Override
    public List<BundleReceipt> getBundleReceipt(Pageable pageable) {
        return null;
    }

    @Override
    public BundleReceipt getBundleReceiptByBundlePaymentId(String bundlePaymentId) {

        Optional<BundlePayment> bundlePayment = bundlePaymentRepository.findById(bundlePaymentId);
        if(bundlePayment.isEmpty()){
            throw new RuntimeException("Bundle payment with id: "+bundlePaymentId+"does not exist");
        }

        Optional<BundleReceipt> bundleReceipt = bundleReceiptRepository.findBybundlePayment(bundlePayment.get());
        if(bundleReceipt.isEmpty()){
            throw new RuntimeException("Receipt for bundle payment with id: "+bundlePaymentId+"does not exist");
        }
        return bundleReceipt.get();
    }
}
