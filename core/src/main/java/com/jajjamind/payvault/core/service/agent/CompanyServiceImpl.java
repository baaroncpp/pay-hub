package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.api.agent.models.Country;
import com.jajjamind.payvault.core.api.agent.models.District;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.exception.ServiceApiNotSupported;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.agent.TCompany;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.agent.TDistrict;
import com.jajjamind.payvault.core.jpa.models.enums.CountryEnum;
import com.jajjamind.payvault.core.repository.agent.CompanyRepository;
import com.jajjamind.payvault.core.repository.agent.CountryRepository;
import com.jajjamind.payvault.core.repository.agent.DistrictRepository;
import com.jajjamind.payvault.core.repository.agent.JooqCompanyRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import com.jajjamind.payvault.core.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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

    @Autowired
    public DistrictRepository districtRepository;

    @Autowired
    public JooqCompanyRepository jooqCompanyRepository;

    @Override
    public Company add(Company company) {

        company.validate();

        //Check that no company exist already with details
        Optional<TCompany> companyAlike = companyRepository.findCompanyByTinNumberAndCompanyName(company.getTinNumber(),company.getBusinessName());
        Validate.isTrue(!companyAlike.isPresent(),"There is already an existing company with similar details");

        final CountryEnum country = ValidateUtils.validateAndGetCountry(company.getRegistrationCountry().getIsoAlpha2());

        final TCompany newCompany = new TCompany();

        BeanUtilsCustom.copyProperties(company,newCompany);

        final TCountry validCountry = countryRepository.findByAlpha2Code(country.getIso2Code())
                .orElseThrow(() -> new BadRequestException("No country found with id %s ",company.getId()));

        if(company.getDistrict() != null){
            final TDistrict district = districtRepository.findById(company.getDistrict().getId())
                    .orElseThrow(() -> new BadRequestException("No district with id %s found",company.getId()));
            newCompany.setDistrict(district);
        }

        newCompany.setRegistrationCountry(validCountry);

        auditService.stampAuditedEntity(newCompany);

        companyRepository.save(newCompany);

        return company;
    }

    @Override
    public Company get(Long id) {

        TCompany tCompany = companyRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.COMPANY_WITH_ID_COULD_NOT_BE_FOUND,id));

        return getCompanyFromTCompany(tCompany);
    }

    private Company getCompanyFromTCompany(TCompany tCompany){
        final Company company = new Company();
        BeanUtilsCustom.copyProperties(tCompany,company);

        final TCountry country = tCompany.getRegistrationCountry();

        final Country companyCountry = new Country();
        BeanUtilsCustom.copyProperties(country,companyCountry);
        company.setRegistrationCountry(companyCountry);

        final District d = new District();
        BeanUtilsCustom.copyProperties(tCompany.getDistrict(),d);
        company.setDistrict(d);

        return company;
    }
    @Override
    public Company update(Company company) {

        company.validate();

        Validate.notNull(company.getId(),"Identifier of company to update is required");

        TCompany oldCompany = companyRepository.findById(company.getId()).orElseThrow(() -> new BadRequestException(
                ErrorMessageConstants.COMPANY_WITH_ID_COULD_NOT_BE_FOUND,company.getId()
        ));

        //Check that no company exist already with details
        if(!oldCompany.getBusinessName().equals(company.getBusinessName())) {
            Optional<TCompany> companyAlike = companyRepository.findByMatchedCompanyName(company.getBusinessName());
            Validate.isTrue(!companyAlike.isPresent(), "There is already an existing company with similar details");
        }

        final CountryEnum country = ValidateUtils.validateAndGetCountry(company.getRegistrationCountry().getIsoAlpha2());

        BeanUtilsCustom.copyProperties(company,oldCompany);

        Optional<TCountry> validCountry = countryRepository.findByAlpha2Code(country.getIso2Code());

        oldCompany.setRegistrationCountry(validCountry.get());

        auditService.stampAuditedEntity(oldCompany);

        companyRepository.save(oldCompany);

        return company;
    }

    @Override
    public Company delete(Long id) {
        throw new ServiceApiNotSupported("Company deletion is not supported");
    }

    @Override
    public List<Company> getAll() {
        throw new ServiceApiNotSupported("This API is not supported. Refer to /query endpoint of same");

    /*    Iterable<TCompany> companies = companyRepository.findAll();
        List<Company> c = new ArrayList<>();
        companies.forEach(t -> {
            final Company company = new Company();
            BeanUtilsCustom.copyProperties(t,company);

            final TCountry country = t.getRegistrationCountry();

            final Country companyCountry = new Country();
            BeanUtilsCustom.copyProperties(country,companyCountry);
            company.setRegistrationCountry(companyCountry);

            final District d = new District();
            BeanUtilsCustom.copyProperties(t.getDistrict(),d);
            company.setDistrict(d);
            c.add(company);
        });

        return c;*/
    }

    @Override
    public Company findByBusinessName(String businessName) {
        final TCompany company = companyRepository.findByBusinessName(businessName).orElseThrow(() -> new BadRequestException("No company found with name %s",businessName));

        return getCompanyFromTCompany(company);
    }

    @Override
    public Company findByTinNumber(String tinNumber) {

        final TCompany company = companyRepository.findByTinNumber(tinNumber).orElseThrow(() -> new BadRequestException("No company found with Tin Number %s",tinNumber));

        return getCompanyFromTCompany(company);
    }

    @Override
    public RecordList getAllCompanies(MultiValueMap map) {
        return jooqCompanyRepository.listAndCount(map);
    }
}
