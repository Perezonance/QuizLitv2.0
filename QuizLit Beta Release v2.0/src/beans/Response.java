package beans;

public class Response {
	private String question;
	private boolean isCorrect;
	public Response(String question, boolean isCorrect) {
		super();
		this.question = question;
		this.isCorrect = isCorrect;
	}
	
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
