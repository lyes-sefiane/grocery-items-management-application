package io.github.lyes_sefiane.groceryitems;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages(value = {"com.lyess.groceryitems.controller",
        "com.lyess.groceryitems.service",
        "com.lyess.groceryitems.repository"})
@SuiteDisplayName("Grocery Items Management Application Tests")
class GroceryItemsManagementApplicationTests {

}
