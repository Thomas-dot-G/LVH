package lvh.naheulbeuk.model.output;

import org.springframework.http.HttpStatus;

public class LVHError {
	
	private HttpStatus code;
	
	private String description;

	public LVHError(HttpStatus status, String description) {
		this.code = status;
		this.description = description;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
