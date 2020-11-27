package com.jajjamind.payvault.user;

import junit.runner.BaseTestRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.util.Assert;

/**
 * @author akena
 * 19/11/2020
 * 23:41
 **/
public class UserSteps {

    @Step("Given a traveller has a frequent flyer account with {0} points")
    public void a_traveller_has_a_frequent_flyer_account_with_balance(int initialBalance) {
       System.out.println(initialBalance);
    }

    @Step("When the traveller flies {0} km")
    public void the_traveller_flies(int distance) {
       System.out.println(1);

    }

    @Step("Then the traveller should have a balance of {0} points")
    public void traveller_should_have_a_balance_of(int expectedBalance ) {
        Assert.isTrue(1 == 1);
    }

    @Step
    public void a_traveller_joins_the_frequent_flyer_program() {
        Assert.isTrue(1 == 1);
    }

    @Step
    public void traveller_should_have_a_status_of(boolean expectedStatus) {
        Assert.isTrue(1 == 1);
    }
}
