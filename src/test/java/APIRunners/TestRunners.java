package APIRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Feature",
        glue = {"stepDefinitions"},
        plugin = {"pretty","html:report"},
        monochrome = true,
        strict = true
)

public class TestRunners {

}
