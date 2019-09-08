package beans;

import javax.persistence.*;

@Entity
@Table(name="quiz")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_quiz_id", unique=true, nullable=false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="quiz_student_id")
	private User user;
	
	private int rightAns;
	
	private int wrongAns;
			
}
