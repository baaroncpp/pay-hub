package com.jajjamind.payvault.core.api.agent;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.agent.models.Company;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akena
 * 19/01/2021
 * 12:18
 **/
@RestController
@RequestMapping("/company")
public class CompanyApi implements BaseApi<Company> {
    @Override
    public Company add(Company company) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Company get(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Company update(Company company) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Company delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Company> getAll() {
        throw new UnsupportedOperationException();
    }
}
