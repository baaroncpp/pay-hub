package com.jajjamind.payvault.core.service.product;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.product.models.ProductCommission;
import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;
import com.jajjamind.payvault.core.repository.product.ProductCommissionRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 18/01/2021
 * 03:32
 **/
@Service
public class ProductCommissionServiceImpl implements ProductCommissionService, BasePricingService<TProductCommission> {

    @Autowired
    public ProductCommissionRepository productCommissionRepository;

    @Autowired
    public AuditService auditService;

    @Override
    public ProductCommission add(ProductCommission productCommission) {

        Validate.notNull(productCommission,"Charge object cannot be null");
        productCommission.validate();

        List<TProductCommission> tariffs = productCommissionRepository.findTariffChargeByGroupIdentifier(productCommission.getTariffGroupIdentifier());
        verifyThatTariffWithinRange(productCommission,tariffs);

        TProductCommission tProductCommission = new TProductCommission();
        BeanUtils.copyProperties(productCommission,tProductCommission);

        auditService.stampAuditedEntity(tProductCommission);
        productCommissionRepository.save(tProductCommission);

        return productCommission;
    }

    @Override
    public ProductCommission get(Long id) {
        return null;
    }

    @Override
    public ProductCommission update(ProductCommission productCommission) {

        productCommission.validate();
        Validate.notNull(productCommission.getId(),"Charge Id is required to perform update");

        Optional<TProductCommission> productCharge = productCommissionRepository.findById(productCommission.getId());
        Validate.isTrue(productCharge.isPresent(), ErrorMessageConstants.CHARGE_WITH_ID_NOT_FOUND,String.valueOf(productCommission.getId()));

        TProductCommission currentCharge = productCharge.get();
        BeanUtils.copyProperties(productCommission,currentCharge);

        List<TProductCommission> tariff = productCommissionRepository.findTariffChargeByGroupIdentifier(productCommission.getTariffGroupIdentifier());
        verifyThatTariffWithinRange(productCommission,tariff);

        auditService.stampAuditedEntity(currentCharge);

        productCommissionRepository.save(currentCharge);
        return productCommission;
    }

    @Override
    public ProductCommission delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ProductCommission> getAll() {
        throw new UnsupportedOperationException();
    }
}
