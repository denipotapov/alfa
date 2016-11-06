package com.alfach.compete.ulmart.tests;

import com.alfach.compete.ulmart.tests.base.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * Created by User on 11/3/2016.
 */
@CucumberOptions(
        features = "src/test/resources/cycle.feature",
        tags = "@cycle",
        glue = "com.alfach.compete.ulmart.befavour")
public class UlmartTests extends BaseTest {

    //ToDo: Change shitty integration way. Not flexible to execute
    @Test()
    public void ShouldCheckCycle() {
        cucumberRunner.runCukes();
    }
}
