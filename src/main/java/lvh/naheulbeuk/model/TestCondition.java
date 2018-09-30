package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TestCondition {
	
	private Boolean testPassed; // true, test passed, false, test failed, null no test required
			
	private String testName;

	public Boolean getTestPassed() {
		return testPassed;
	}

	public void setTestPassed(Boolean testPassed) {
		this.testPassed = testPassed;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
	
}
