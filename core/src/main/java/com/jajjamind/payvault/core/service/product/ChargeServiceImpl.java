package com.jajjamind.payvault.core.service.product;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.product.models.Charge;
import com.jajjamind.payvault.core.jpa.models.product.TProductCharge;
import com.jajjamind.payvault.core.repository.product.ProductChargeRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 13/01/2021
 * 22:52
 **/
@Service
public class ChargeServiceImpl implements ChargeService,BasePricingService<TProductCharge>{

    @Autowired
    public ProductChargeRepository productChargeRepository;

    @Autowired
    public AuditService auditService;

    //TODO Move exception to a web application exception
    @Override
    public Charge addCharge(Charge charge) {
        Validate.notNull(charge,"Charge object cannot be null");
        charge.validate();

        List<TProductCharge> tariff = productChargeRepository.findTariffChargeByGroupIdentifier(charge.getTariffGroupIdentifier());
        verifyThatTariffWithinRange(charge,tariff);

        TProductCharge productCharge = new TProductCharge();
        BeanUtils.copyProperties(charge,productCharge);

        auditService.stampAuditedEntity(productCharge);
        productChargeRepository.save(productCharge);

        return charge;
    }


    //TODO Add pagination and filters with jooq
    @Override
    public List<Charge> getAllCharges() {


        throw new UnsupportedOperationException();
    }

    @Override
    public Charge getChargeById(Long id) {
        Optional<TProductCharge> productCharge = productChargeRepository.findById(id);
        Validate.isTrue(productCharge.isPresent(),ErrorMessageConstants.CHARGE_WITH_ID_NOT_FOUND,String.valueOf(id));

        final Charge charge = new Charge();
        BeanUtils.copyProperties(productCharge.get(),charge);

        return charge;
    }

    @Override
    public Charge getChargeByName(String name) {
        return null;
    }

    @Override
    public Charge updateCharge(Charge charge) {

        charge.validate();
        Validate.notNull(charge.getId(),"Charge Id is required to perform update");

        Optional<TProductCharge> productCharge = productChargeRepository.findById(charge.getId());
        Validate.isTrue(productCharge.isPresent(),ErrorMessageConstants.CHARGE_WITH_ID_NOT_FOUND,String.valueOf(charge.getId()));

        TProductCharge currentCharge = productCharge.get();
        BeanUtils.copyProperties(charge,currentCharge);

        List<TProductCharge> tariff = productChargeRepository.findTariffChargeByGroupIdentifier(charge.getTariffGroupIdentifier());
        verifyThatTariffWithinRange(charge,tariff);

        auditService.stampAuditedEntity(currentCharge);

        productChargeRepository.save(currentCharge);
        return charge;
    }

    @Override
    public boolean disableCharge(Long chargeId) {
        Optional<TProductCharge> productCharge = productChargeRepository.findById(chargeId);
        Validate.isTrue(productCharge.isPresent(),ErrorMessageConstants.CHARGE_WITH_ID_NOT_FOUND,String.valueOf(chargeId));

        TProductCharge currentCharge = productCharge.get();
        currentCharge.setNonActive(Boolean.TRUE);

        auditService.stampAuditedEntity(currentCharge);
        productChargeRepository.save(currentCharge);
        return Boolean.TRUE;
    }
}
