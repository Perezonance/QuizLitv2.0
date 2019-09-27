package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="quiz_id", unique=true, nullable=false)
	private int id;

	@ManyToOne
	@JoinColumn(name="quiz_student_id", nullable = false)
	private User user;

	@Column(name="quiz_right", nullable=true)
	private int rightAns;

	@Column(name="quiz_wrong", nullable=true)
	private int wrongAns;
	
	@ManyToOne()
	@JoinColumn(name = "quiz_structureId", nullable = false)
	private QuizStructure quizStructure;
		
	@OneToMany(mappedBy="questionCategory", cascade = CascadeType.ALL)
	private List<Question> quizQuestions;


	//Getter and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRightAns() {
		return rightAns;
	}

	public void setRightAns(int rightAns) {
		this.rightAns = rightAns;
	}

	public int getWrongAns() {
		return wrongAns;
	}

	public void setWrongAns(int wrongAns) {
		this.wrongAns = wrongAns;
	}

	public QuizStructure getQuizStructure() {
		return quizStructure;
	}
	
	public List<Question> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(List<Question> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

	public void setQuizStructure(QuizStructure quizStructure) {
		this.quizStructure = quizStructure;
	}

	// constructor
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", user=" + user + ", rightAns=" + rightAns + ", wrongAns=" + wrongAns
				+ ", quizStructure=" + quizStructure + ", quizQuestions=" + quizQuestions + "]";
	}

	
	


}