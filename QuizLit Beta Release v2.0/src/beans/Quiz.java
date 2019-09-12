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
	@JoinColumn(name="quiz_student_id")
	private User user;
	
	@Column(name="quiz_right")
	private int rightAns;
	
	@Column(name="quiz_wrong")
	private int wrongAns;
	
	private List<Question> questions;
			
}
