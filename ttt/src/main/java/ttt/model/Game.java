package ttt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Games")
public class Game {

	 @Id
	    @Column(name="id")
	    @GeneratedValue
	    
	    private Integer id;
	 
	 	@ManyToOne
	 	@JoinColumn(name="user1")
	 	private User user1;
	 	
	 	@ManyToOne
	 	@JoinColumn(name="user2")
	 	private User user2;
	 	
	 	private String result;
	 	
	 	private Date startTime;
	 	
	 	private Date endTime;
	 	
	 	private Date savedTime;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public User getUser1() {
			return user1;
		}

		public void setUser1(User user1) {
			this.user1 = user1;
		}

		public User getUser2() {
			return user2;
		}

		public void setUser2(User user2) {
			this.user2 = user2;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public Date getSavedTime() {
			return savedTime;
		}

		public void setSavedTime(Date savedTime) {
			this.savedTime = savedTime;
		}
}
