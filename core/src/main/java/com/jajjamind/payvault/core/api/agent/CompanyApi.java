package com.jajjamind.payvault.core.api.agent;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.service.agent.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 19/01/2021
 * 12:18
 **/
@Tag(name = "Company",description = "Manage companies under which agents will be created")
@RolesAllowed({"ROLE_ADMIN.WRITE","ROLE_ADMIN.READ"})
@RestController
@RequestMapping("/company")
public class CompanyApi implements BaseApi<Company> {

    @Autowired
    public CompanyService companyService;


    @RolesAllowed("ROLE_COMPANY.WRITE")
    @Override
    public Company add(@RequestBody Company company) {
        return companyService.add(company);
    }

    @RolesAllowed({"ROLE_COMPANY.READ","ROLE_COMPANY.WRITE"})
    @Override
    public Company get( Long id) {
        return companyService.get(id);
    }

    @RolesAllowed("ROLE_COMPANY.WRITE")
    @Override
    public Company update(@RequestBody  Company company) {
        return companyService.update(company);
    }

    @Override
    public Company delete( long id) {
        return companyService.delete(id);
    }

    @RolesAllowed({"ROLE_COMPANY.READ","ROLE_COMPANY_WRITE"})
    @Override
    public List<Company> getAll() {

        return companyService.getAll();
    }

    @RolesAllowed({"ROLE_COMPANY.READ","ROLE_COMPANY.WRITE"})
    @GetMapping(value = "/by-tin/{tinNumber}",produces = APPLICATION_JSON)
    public Company getCompanyByTinNumber(@PathVariable("tinNumber") String tinNumber){
        return companyService.findByTinNumber(tinNumber);
    }

    @RolesAllowed({"ROLE_COMPANY.READ","ROLE_COMPANY.WRITE"})
    @GetMapping(value = "/by-businessname/{businessName}",produces = APPLICATION_JSON)
    public Company getCompanyByBusinessName(@PathVariable("businessName") String businessName){
        return companyService.findByBusinessName(businessName);
    }

    @RolesAllowed({"ROLE_COMPANY.READ","ROLE_COMPANY.WRITE"})
    @GetMapping("/query")
    public RecordList getAllCompanies(@RequestParam MultiValueMap map){
        return companyService.getAllCompanies(map);
    }
}
