package TestRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
	@CucumberOptions(
			/*run specific feature file*/
			//features = ".//Features/Customer.feature",
			
			/* To run all files of features file*/
			//features = ".//Features/",
			
			features = {".//Features/Customer.feature",".//Features/LoginFeature.feature"},
			glue="StepDefination",
			dryRun = false,
			monochrome = true,
			tags="@Sanity", //scenario under sanity will be run ..  Different ways: @Sanity or @Regression.. @Sanity and not @ Regression ..@Sanity and @ Regression
			plugin = {"pretty","html:target/cucumber-reports/reports_html.html"}
			
			//	plugin = {"pretty","junit:target/cucumber-reports/reports_xml.xml","html:target/cucumber-reports/reports_html.html","json:target/cucumber-reports/reports_json.json"}
			)			
			public class Run {

}
 