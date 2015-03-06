package ttt.model.dao;

import java.util.List;

import ttt.model.Game;
import ttt.model.User;
import ttt.model.StatusOfGame;


public interface UserDao {

	User getUser( String username );
	User getUser( int id );
	Game getGame( int id );
	List<StatusOfGame> getStatusOfGame( int id );
	List<User> getUsers();
    List<Game> getGamesWithAI( User user );
    List<Game> getSavedGamesByUser( User user );
    User saveUser( User user );
    Game saveGame( Game game );
    StatusOfGame saveStatusOfGame( StatusOfGame game );
    List<Game> getFinishedGamesByUser(User user);
    List<Game> getNoOfAIGamesWonByUser(User user);
    List<Game> getNoOfAIGamesPlayedByUser(User user);
    List<Game> getNoOfHumanGamesWonByUser(User user);
    List<Game> getNoOfHumanGamesPlayedByUser(User user);
    List<Game> getGamesPlayedByUserForCurrentMonth(User user);
}
