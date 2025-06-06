package com.jitsu;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(


        features = "src/test/resources/features",
        plugin = {
                "pretty", "html:target/site/serenity/",
                "json:rerun/serenity-reports/cucumber_report.json"
        }
)
public class TestSuite {
}
