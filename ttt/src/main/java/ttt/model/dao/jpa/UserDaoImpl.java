package ttt.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttt.model.Game;
import ttt.model.StatusOfGame;
import ttt.model.User;
import ttt.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getUsers() {
		return entityManager.createQuery("from User order by id", User.class)
				.getResultList();
	}
	
	@Override
	public User getUser(int id) {
		return entityManager
				.createQuery("from User where id= :id", User.class)
				.setParameter("id", id).getSingleResult();
	}
	
	@Override
	public Game getGame(int id) {
		return entityManager
				.createQuery("from Game where id= :id", Game.class)
				.setParameter("id", id).getSingleResult();
	}
	
	@Override
	public List<StatusOfGame> getStatusOfGame(int id) {
		return entityManager
				.createQuery("from StatusOfGame where game_id= :id", StatusOfGame.class)
				.setParameter("id", id).getResultList();
	}

	@Override
	public List<Game> getGamesWithAI(User user) {
		// TODO Auto-generated method stub
		return entityManager
				.createQuery(
						"from Game where user1= :name and result is not null",
						Game.class).setParameter("name", user).getResultList();
	}

	@Override
	public User getUser(String username) {

		return entityManager
				.createQuery("from User where username= :username", User.class)
				.setParameter("username", username).getSingleResult();
	}

	@Override
	public List<Game> getSavedGamesByUser(User user) {
		// TODO Auto-generated method stub

		return entityManager
				.createQuery(
						"from Game where user1= :name and result is null and savedTime is not null ",
						Game.class).setParameter("name", user).getResultList();
	}
	
	@Override
	public List<Game> getFinishedGamesByUser(User user) {
		return entityManager
				.createQuery(
						"from Game where user1= :name and result is not null ",
						Game.class).setParameter("name", user).getResultList();
	}
	
	@Override
	public List<Game> getNoOfAIGamesPlayedByUser(User user) {
		return entityManager
				.createQuery(
						"from Game where user1= :name and user2 is null",
						Game.class).setParameter("name", user).getResultList();
	}
	
	@Override
	public List<Game> getNoOfHumanGamesPlayedByUser(User user) {
		return entityManager
				.createQuery(
						"from Game where (user1= :name and result = 'win' and user2 is not null)"
						+ "or (user2= :name and result = 'lose')",
						Game.class).setParameter("name", user).getResultList();
	}
	
	@Override
	public List<Game> getNoOfAIGamesWonByUser(User user) {
		return entityManager
				.createQuery(
						"from Game where user1= :name and result = 'win' and user2 is null",
						Game.class).setParameter("name", user).getResultList();
	}
	
	@Override
	public List<Game> getNoOfHumanGamesWonByUser(User user) {
		return entityManager
				.createQuery(
						"from Game where (user1= :name and result = 'win' and user2 is not null)"
						+ "or (user2= :name and result = 'lose')",
						Game.class).setParameter("name", user).getResultList();
	}
	
	@Override
	public List<Game> getGamesPlayedByUserForCurrentMonth(User user) {
		return entityManager
				.createQuery(
						"from Game where (user1= :name or user2= :name) and year(startTime) = year(current_date()) and year(endTime) = year(current_date())"
						+ " and month(startTime) = month(current_date()) and month(endTime) = month(current_date())",
						Game.class).setParameter("name", user).getResultList();
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		return entityManager.merge(user);
	}

	@Override
	@Transactional
	public Game saveGame(Game game) {
		return entityManager.merge(game);
	}

	@Override
	@Transactional
	public StatusOfGame saveStatusOfGame(StatusOfGame game) {
		return entityManager.merge(game);
	}

	
}