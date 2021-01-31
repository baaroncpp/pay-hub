package com.jajjamind.payvault.user.core.api.agent;

import com.jajjamind.payvault.BaseTestClass;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author akena
 * 13/01/2021
 * 20:48
 **/
@RunWith(SerenityRunner.class)
public class LocationApiTest extends BaseTestClass {

    @Steps
    LocationApiSteps locationApiSteps;

    @Before
    public void init(){
        locationApiSteps.spec = getDefaultRequestSpecification(Boolean.TRUE);
    }

    @Test
    public void shouldGetAllDistrictsInSystem() throws JSONException {
        locationApiSteps.shouldGetAllDistricts()
                .thenVerifyThatAllDistrictsRetrieve();
    }


}
