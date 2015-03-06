package ttt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "StatusOfGame")
public class StatusOfGame {
	
	    @Id
	    @GeneratedValue
	    private Integer id;

	    private Integer game_id;
	
		private String board;
		
		private String occupiedBy;
		
		public String getBoard() {
			return board;
		}

		public void setBoard(String board) {
			this.board = board;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getGame_id() {
			return game_id;
		}

		public void setGame_id(Integer game_id) {
			this.game_id = game_id;
		}

		public String getOccupiedBy() {
			return occupiedBy;
		}

		public void setOccupiedBy(String occupiedBy) {
			this.occupiedBy = occupiedBy;
		}
	
}
