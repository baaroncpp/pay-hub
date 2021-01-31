package com.jajjamind.payvault.core.service.product;

import com.jajjamind.payvault.core.api.product.models.Charge;

import java.util.List;

/**
 * @author akena
 * 13/01/2021
 * 22:51
 **/
public interface  ChargeService {

    Charge addCharge(Charge charge);
    List<Charge> getAllCharges();
    Charge getChargeById(Long id);
    Charge getChargeByName(String name);
    Charge updateCharge(Charge charge);
    boolean disableCharge(Long chargeId);

}
