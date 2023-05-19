package tqs.amanacu.estore;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("tqs/amanacu/estore")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "tqs.amanacu.estore")
public class Runner {
    // This is just a runner class, no implementation needed here
}
