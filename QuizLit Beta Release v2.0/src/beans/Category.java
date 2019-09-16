package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id", unique=true, nullable=false)
	private int id;
	
	@Column(name="category_name", nullable=false)
	private String name;
	
	@Column(name="category_description", nullable=false)
	private String description;
	
	@OneToMany(mappedBy="structureCategory")
	private List<QuizStructure> quizStructures;
	
	@OneToMany(mappedBy="questionCategory")
	private List<Question> categoryQuestions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<QuizStructure> getQuizStructures() {
		return quizStructures;
	}

	public void setQuizStructures(List<QuizStructure> quizStructures) {
		this.quizStructures = quizStructures;
	}

	public List<Question> getCategoryQuestions() {
		return categoryQuestions;
	}

	public void setCategoryQuestions(List<Question> questions) {
		this.categoryQuestions = questions;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
