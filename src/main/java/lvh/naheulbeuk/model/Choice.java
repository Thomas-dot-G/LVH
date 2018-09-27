package lvh.naheulbeuk.model;

public class Choice {
	
	private String persoId;
		
	private String targetPageNumberId;
	
	private String targetStoryId;
	
	private String input;

	public String getPersoId() {
		return persoId;
	}

	public void setPersoId(String persoId) {
		this.persoId = persoId;
	}

	public String getTargetPageNumberId() {
		return targetPageNumberId;
	}

	public void setTargetPageNumberId(String targetPageNumberId) {
		this.targetPageNumberId = targetPageNumberId;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getTargetStoryId() {
		return targetStoryId;
	}

	public void setTargetStoryId(String targetStoryId) {
		this.targetStoryId = targetStoryId;
	}

}
