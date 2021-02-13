package com.jajjamind.payvault.core.api.product;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.product.models.Charge;
import com.jajjamind.payvault.core.service.product.ChargeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:02
 **/
@Tag(name = "Product Charge",description = "Handle charges incured by products in the system")
@RestController
@RequestMapping("/charge")
public class ProductChargeApi implements BaseApi<Charge> {

    @Autowired
    public ChargeService chargeService;

    @Override
    public Charge add(@Validated  @RequestBody  Charge charge) {
        return chargeService.addCharge(charge);
    }

    @Override
    public Charge get(Long id) {
        return null;
    }

    @Override
    public Charge update(Charge charge) {
        return null;
    }

    @Override
    public Charge delete(long id) {
        return null;
    }

    @Override
    public List<Charge> getAll() {
        throw new UnsupportedOperationException();
    }
}
