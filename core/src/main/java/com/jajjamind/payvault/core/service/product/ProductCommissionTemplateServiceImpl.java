package com.jajjamind.payvault.core.service.product;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.product.models.ProductCommissionTemplate;
import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;
import com.jajjamind.payvault.core.jpa.models.product.TProductCommissionTemplate;
import com.jajjamind.payvault.core.repository.product.ProductCommissionTemplateRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 19/01/2021
 * 00:18
 **/
@Service
public class ProductCommissionTemplateServiceImpl implements ProductCommissionTemplateService,BasePricingService<TProductCommissionTemplate>{

    @Autowired
    public ProductCommissionTemplateRepository productCommissionTemplateRepository;

    @Autowired
    public AuditService auditService;


    @Override
    public ProductCommissionTemplate add(ProductCommissionTemplate productCommissionTemplate) {

        Validate.notNull(productCommissionTemplate,"Charge object cannot be null");
        productCommissionTemplate.validate();

        if(productCommissionTemplate.getPricingType().equals(PricingTypeEnum.TARIFF)) {
            List<TProductCommissionTemplate> tariffs = productCommissionTemplateRepository.findTariffChargeByGroupIdentifier(productCommissionTemplate.getTariffGroupIdentifier());
            verifyThatTariffWithinRange(productCommissionTemplate, tariffs);
        }else{
            productCommissionTemplate.setTariffGroupIdentifier(null);
        }

        TProductCommissionTemplate commissionTemplate = new TProductCommissionTemplate();
        BeanUtils.copyProperties(productCommissionTemplate,commissionTemplate);

        commissionTemplate.setStatus(productCommissionTemplate.getStatus().getBoolValue());

        auditService.stampAuditedEntity(commissionTemplate);
        productCommissionTemplateRepository.save(commissionTemplate);


        return getByName(productCommissionTemplate.getName());
    }

    @Override
    public ProductCommissionTemplate get(Long id) {

        Optional<TProductCommissionTemplate> template = productCommissionTemplateRepository.findById(id);
        Validate.isTrue(template.isPresent(),"Failed to find template with ID %s",id);
        TProductCommissionTemplate tTemplate = template.get();

        return getProductCommissionTemplate(tTemplate);
    }

    @Override
    public ProductCommissionTemplate update(ProductCommissionTemplate productCommissionTemplate) {

        productCommissionTemplate.validate();
        Validate.notNull(productCommissionTemplate.getId(),"Charge Id is required to perform update");

        TProductCommissionTemplate currentCharge = productCommissionTemplateRepository.findById(productCommissionTemplate.getId())
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.CHARGE_WITH_ID_NOT_FOUND,String.valueOf(productCommissionTemplate.getId())));
        Validate.isTrue(productCommissionTemplate.getPricingType().equals(currentCharge.getPricingType()),"You cannot change commission types");

        BeanUtilsCustom.copyProperties(productCommissionTemplate,currentCharge);

        List<TProductCommissionTemplate> tariff = productCommissionTemplateRepository.findTariffChargeByGroupIdentifier(productCommissionTemplate.getTariffGroupIdentifier());
        verifyThatTariffWithinRange(productCommissionTemplate,tariff);

        auditService.stampAuditedEntity(currentCharge);

        productCommissionTemplateRepository.save(currentCharge);


        return getByName(productCommissionTemplate.getName());
    }

    @Override
    public ProductCommissionTemplate delete(Long id) {
        final TProductCommissionTemplate currentCharge = productCommissionTemplateRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.CHARGE_WITH_ID_NOT_FOUND,String.valueOf(id)));

        currentCharge.setStatus(false);
        auditService.stampAuditedEntity(currentCharge);
        productCommissionTemplateRepository.save(currentCharge);

        return getProductCommissionTemplate(currentCharge);
    }

    @Override
    public List<ProductCommissionTemplate> getAll() {

        Iterable<TProductCommissionTemplate> all = productCommissionTemplateRepository.findAll();

        final List<ProductCommissionTemplate> commissionTemplates = new ArrayList<>();

        all.forEach(t -> {
            ProductCommissionTemplate c = getProductCommissionTemplate(t);
            commissionTemplates.add(c);
        });

        return commissionTemplates;
    }

    @Override
    public ProductCommissionTemplate getByName(String name) {
        Optional<TProductCommissionTemplate> template = productCommissionTemplateRepository.findByName(name);
        Validate.isTrue(template.isPresent(),"Failed to find template with name %s",name);
        TProductCommissionTemplate tTemplate = template.get();

        return getProductCommissionTemplate(tTemplate);
    }

    @Override
    public List<ProductCommissionTemplate> getTariffGroup(String groupIdentifier) {
        List<TProductCommissionTemplate> template = productCommissionTemplateRepository.findTariffChargeByGroupIdentifier(groupIdentifier);

        List<ProductCommissionTemplate> tariffs = new ArrayList<>();
        template.forEach(t -> {
            tariffs.add(getProductCommissionTemplate(t));
        });

        return tariffs;
    }

    @Transactional
    @Override
    public void disableTariffGroup(String groupIdentifier) {
        productCommissionTemplateRepository.deactivateTariffGroup(groupIdentifier);
    }

    private ProductCommissionTemplate getProductCommissionTemplate(TProductCommissionTemplate t){
        ProductCommissionTemplate template = new ProductCommissionTemplate();

        BeanUtils.copyProperties(t, template);
        template.setStatus(StatusEnum.getFromBoolean(t.isStatus()));

        return template;
    }
}
