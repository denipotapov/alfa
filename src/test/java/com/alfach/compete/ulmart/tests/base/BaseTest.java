package com.alfach.compete.ulmart.tests.base;

import com.alfach.compete.conf.Configuration;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

/**
 * Created by User on 11/3/2016.
 */
public class BaseTest {

    protected TestNGCucumberRunner cucumberRunner;

    @DataProvider
    public Object[][] features() {
        return cucumberRunner.provideFeatures();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        cucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeSuite
    public void init() {
        Configuration.loadProperties();
    }



}
