package lvh.naheulbeuk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TestCondition {
	
	private Boolean testPassed; // true, test passed, false, test failed, null no test required
			
	private String testName;
	
	private Integer testResultHigherThan;
	
	private Integer testResultLowerThan;

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

	public Integer getTestResultHigherThan() {
		return testResultHigherThan;
	}

	public void setTestResultHigherThan(Integer testResultHigherThan) {
		this.testResultHigherThan = testResultHigherThan;
	}

	public Integer getTestResultLowerThan() {
		return testResultLowerThan;
	}

	public void setTestResultLowerThan(Integer testResultLowerThan) {
		this.testResultLowerThan = testResultLowerThan;
	}
	
}
