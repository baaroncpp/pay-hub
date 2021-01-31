package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.api.agent.models.Country;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.agent.TCompany;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.enums.CountryEnum;
import com.jajjamind.payvault.core.repository.agent.CompanyRepository;
import com.jajjamind.payvault.core.repository.agent.CountryRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 19/01/2021
 * 12:20
 **/

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    public CompanyRepository companyRepository;

    @Autowired
    public AuditService auditService;

    @Autowired
    public CountryRepository countryRepository;

    @Override
    public Company add(Company company) {

        company.validate();

        //Check that no company exist already with details
        Optional<TCompany> companyAlike = companyRepository.findCompanyByTinNumberAndCompanyName(company.getTinNumber(),company.getBusinessName());
        Validate.isTrue(!companyAlike.isPresent(),"There is already an existing company with similar details");

        final CountryEnum country = ValidateUtils.validateAndGetCountry(company.getRegistrationCountry().getIsoAlpha2());

        final TCompany newCompany = new TCompany();

        BeanUtils.copyProperties(company,newCompany);

        Optional<TCountry> validCountry = countryRepository.findByAlpha2Code(country.getIso2Code());

        newCompany.setRegistrationCountry(validCountry.get());

        auditService.stampAuditedEntity(newCompany);

        companyRepository.save(newCompany);

        return company;
    }

    @Override
    public Company get(Long id) {

        Optional<TCompany> tCompany = companyRepository.findById(id);
        Validate.isTrue(tCompany.isPresent(),ErrorMessageConstants.COMPANY_WITH_ID_COULD_NOT_BE_FOUND,id);

        final Company company = new Company();
        BeanUtils.copyProperties(tCompany,company);

        final TCountry country = tCompany.get().getRegistrationCountry();

        final Country companyCountry = new Country();
        BeanUtils.copyProperties(country,companyCountry);
        company.setRegistrationCountry(companyCountry);

        return company;
    }

    @Override
    public Company update(Company company) {

        company.validate();

        Validate.notNull(company.getId(),"Identifier of company to update is required");

        Optional<TCompany> oldCompanyOptional = companyRepository.findById(company.getId());

        Validate.isTrue(oldCompanyOptional.isPresent(),ErrorMessageConstants.COMPANY_WITH_ID_COULD_NOT_BE_FOUND,company.getId());
        TCompany oldCompany = oldCompanyOptional.get();

        //Check that no company exist already with details
        if(!oldCompany.getBusinessName().equals(company.getBusinessName())) {
            Optional<TCompany> companyAlike = companyRepository.findByMatchedCompanyName(company.getBusinessName());
            Validate.isTrue(!companyAlike.isPresent(), "There is already an existing company with similar details");
        }

        final CountryEnum country = ValidateUtils.validateAndGetCountry(company.getRegistrationCountry().getIsoAlpha2());

        BeanUtils.copyProperties(company,oldCompany);

        Optional<TCountry> validCountry = countryRepository.findByAlpha2Code(country.getIso2Code());

        oldCompany.setRegistrationCountry(validCountry.get());

        auditService.stampAuditedEntity(oldCompany);

        companyRepository.save(oldCompany);

        return company;
    }

    @Override
    public Company delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Company> getAll() {
        throw new UnsupportedOperationException();
    }

}
