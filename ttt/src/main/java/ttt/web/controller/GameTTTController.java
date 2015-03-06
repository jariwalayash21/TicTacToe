package ttt.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import ttt.model.Game;
import ttt.model.GameStart;
import ttt.model.StatusOfGame;
import ttt.model.User;
import ttt.model.dao.UserDao;
import ttt.service.Hosts;
import ttt.service.Joins;

@Controller
public class GameTTTController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	Hosts h;
	
	@Autowired
	Joins j;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "Register.html")
	public String add(ModelMap models) {
		models.put("user", new User());
		return "Register";
	}

	@RequestMapping(value = "Login.html")
	public String add1(ModelMap models) {
		models.put("user", new User());
		return "Login";
	}

	@RequestMapping(value = "Register.html", method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user, ModelMap models,
			BindingResult bindingResult) {
		userDao.saveUser(user);
		models.put("user", new User());
		return "Login";
	}

	@RequestMapping(value = "Login.html", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, ModelMap models,
			BindingResult bindingResult, HttpSession session) {
		User exsistingUser = userDao.getUser(user.getUsername());
		if (exsistingUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", exsistingUser);
			return "Navigator";
		}
		return "Login";
	}

	@RequestMapping(value = "Navigator.html")
	public String navigator(ModelMap models) {
		return "Navigator";
	}

	@RequestMapping(value = "Display.html")
	public String navigator(ModelMap models, HttpSession session) {
		GameStart gameStart = new GameStart();
		Game game = new Game();
		game.setStartTime(new Date());
		User user1 = (User) session.getAttribute("user");
		game.setUser1(user1);
		game.setUser2(null);
		game = userDao.saveGame(game);
		session.setAttribute("game", game);
		session.setAttribute("gameStart", gameStart);
		models.put("matrix", gameStart.getMatrix());
		return "Display1";
	}

	@RequestMapping(value = "Display1.html", method = RequestMethod.GET)
	public String Display(ModelMap models, HttpSession session,
			@RequestParam Integer i, @RequestParam Integer j) {
		Game game = (Game) session.getAttribute("game");
		User user = (User) session.getAttribute("user");
		GameStart gameStart = (GameStart) session.getAttribute("gameStart");
		gameStart.doGet(i, j);
		if (gameStart.getWon() != null
				&& gameStart.getWon().equalsIgnoreCase("I")) {
			game.setResult("loss");
			game.setEndTime(new Date());
		} else if (gameStart.getWon() != null
				&& gameStart.getWon().equalsIgnoreCase("You")) {
			game.setResult("win");
			game.setEndTime(new Date());
		} else if (gameStart.getTied() != null
				&& gameStart.getTied().equalsIgnoreCase("Tied")) {
			game.setResult("tie");
			game.setEndTime(new Date());
		}
		game.setUser1(user);
		game.setUser2(null);
		userDao.saveGame(game);
		models.put("matrix", gameStart.getMatrix());
		models.put("Tied", gameStart.getTied());
		models.put("won", gameStart.getWon());
		return "Display1";
	}

	@RequestMapping(value = "NewGame.html", method = RequestMethod.GET)
	public String newGameStart(ModelMap models, HttpSession session) {
		GameStart gameStart = (GameStart) session.getAttribute("gameStart");
		Game game = (Game) session.getAttribute("game");
		User user = (User) session.getAttribute("user");
		if (gameStart.getWon() != null
				&& gameStart.getWon().equalsIgnoreCase("I")) {
			game.setResult("loss");
		} else if (gameStart.getWon() != null
				&& gameStart.getWon().equalsIgnoreCase("You")) {
			game.setResult("win");
		} else if (gameStart.getTied() != null
				&& gameStart.getTied().equalsIgnoreCase("Tied")) {
			game.setResult("tie");
		} else {
			game.setResult("loss");
		}
		game.setEndTime(new Date());
		game.setUser1(user);
		game.setUser2(null);
		userDao.saveGame(game);
		gameStart = new GameStart();
		session.setAttribute("gameStart", gameStart);
		models.put("matrix", gameStart.getMatrix());
		return "Display1";
	}

	@RequestMapping(value = "logout.html")
	public String logout(ModelMap models, HttpSession session) {
		if (session.getAttribute("gameStart") != null) {
			GameStart gameStart = (GameStart) session.getAttribute("gameStart");
			Game game = (Game) session.getAttribute("game");
			User user = (User) session.getAttribute("user");
			if (gameStart.getWon() != null
					&& gameStart.getWon().equalsIgnoreCase("I")) {
				game.setResult("loss");
			} else if (gameStart.getWon() != null
					&& gameStart.getWon().equalsIgnoreCase("You")) {
				game.setResult("win");
			} else if (gameStart.getTied() != null
					&& gameStart.getTied().equalsIgnoreCase("Tied")) {
				game.setResult("tie");
			} else {
				game.setResult("loss");
			}
			game.setEndTime(new Date());
			game.setUser1(user);
			game.setUser2(null);
			userDao.saveGame(game);
		}
		session.invalidate();
		models.put("user", new User());
		return "Login";
	}

	@RequestMapping(value = "SaveGame.html", method = RequestMethod.GET)
	public String SaveGame(ModelMap models, HttpSession session) {
		GameStart gameStart = (GameStart) session.getAttribute("gameStart");
		Game game = (Game) session.getAttribute("game");
		String gameBoard = "";
		for (int i = 0; i < gameStart.getMatrix().size(); i++) {
			for (int j = 0; j < gameStart.getMatrix().get(i).size(); j++) {
				gameBoard = gameBoard + gameStart.getMatrix().get(i).get(j)
						+ ",";
			}
		}
		StatusOfGame statusOfGame = new StatusOfGame();
		if (userDao.getStatusOfGame(game.getId()).size() != 0) {
			statusOfGame = userDao.getStatusOfGame(game.getId()).get(0);
		}
		statusOfGame.setBoard(gameBoard);
		statusOfGame.setGame_id(game.getId());
		userDao.saveStatusOfGame(statusOfGame);
		game.setSavedTime(new Date());
		userDao.saveGame(game);
		models.put("matrix", gameStart.getMatrix());
		models.put("Tied", gameStart.getTied());
		models.put("won", gameStart.getWon());
		return "Navigator";
	}

	@RequestMapping(value = "gameDetails.html")
	public String Display(ModelMap models, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int noOffinishedGamesByUser = userDao.getFinishedGamesByUser(user)
				.size();
		int noOfAIGamesPlayedByUser = userDao.getNoOfAIGamesPlayedByUser(user)
				.size();
		int noOfHumanGamesPlayedByUser = userDao.getNoOfHumanGamesPlayedByUser(
				user).size();
		int noOfAIGamesWonByUser = userDao.getNoOfAIGamesWonByUser(user).size();
		int noOfHumanGamesWonByUser = userDao.getNoOfHumanGamesWonByUser(user)
				.size();
		int winRateAgainstAI = 0;
		if (noOfAIGamesPlayedByUser != 0) {
			winRateAgainstAI = noOfAIGamesWonByUser * 10000
					/ noOfAIGamesPlayedByUser;
		}
		int winRateAgainstHuman = 0;
		if (winRateAgainstHuman != 0) {
			winRateAgainstHuman = noOfHumanGamesWonByUser * 10000
					/ noOfHumanGamesPlayedByUser;
		}
		models.put("noOffinishedGamesByUser", noOffinishedGamesByUser);
		models.put("noOfAIGamesPlayedByUser", noOfAIGamesPlayedByUser);
		models.put("noOfHumanGamesPlayedByUser", noOfHumanGamesPlayedByUser);
		models.put("winRateAgainstAI", (double) winRateAgainstAI / 100);
		models.put("winRateAgainstHuman", (double) winRateAgainstHuman / 100);
		List<String> opponents_usernames = new ArrayList<String>();
		List<Long> gamePlayLength = new ArrayList<Long>();
		List<String> results = new ArrayList<String>();
		List<Game> gamePlayedThisMonth = userDao
				.getGamesPlayedByUserForCurrentMonth(user);
		for (Game g : gamePlayedThisMonth) {
			if (g.getUser1().getUsername().equalsIgnoreCase(user.getUsername())) {
				if (g.getUser2() == null) {
					opponents_usernames.add("AI");
				} else {
					opponents_usernames.add(g.getUser2().getUsername());
				}
				results.add(g.getResult());
			} else {
				System.out.println("in else");
				opponents_usernames.add(g.getUser1().getUsername());
				if (g.getResult().equalsIgnoreCase("win")) {
					results.add("Lose");
				} else if (g.getResult().equalsIgnoreCase("lose")) {
					results.add("Win");
				} else {
					results.add(g.getResult());
				}
			}
			gamePlayLength.add(g.getEndTime().getTime()
					- g.getStartTime().getTime());
		}
		models.put("opponents_usernames", opponents_usernames);
		models.put("gamePlayLength", gamePlayLength);
		models.put("results", results);
		models.put("gamePlayedThisMonth", gamePlayedThisMonth);
		return "gameDetails";
	}

	@RequestMapping(value = "savedGameDetails.html")
	public String savedGameDetails(ModelMap models, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Game> savedGamesByUser = userDao.getSavedGamesByUser(user);
		models.put("savedGamesByUser", savedGamesByUser);
		return "savedGameDetails";
	}

	@RequestMapping(value = "resumeGame.html", method = RequestMethod.GET)
	public String resumeGame(ModelMap models, HttpSession session,
			@RequestParam Integer gameID) {
		GameStart gameStart = new GameStart();
		Game game = userDao.getGame(gameID);
		StatusOfGame statusOfGame = userDao.getStatusOfGame(gameID).get(0);
		String gameBoard = statusOfGame.getBoard();
		List<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 9; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("" + gameBoard.charAt(i * 2));
			i++;
			temp.add("" + gameBoard.charAt(i * 2));
			i++;
			temp.add("" + gameBoard.charAt(i * 2));
			matrix.add(temp);
		}
		gameStart.setMatrix(matrix);
		session.setAttribute("game", game);
		session.setAttribute("gameStart", gameStart);
		models.put("matrix", gameStart.getMatrix());
		models.put("Tied", gameStart.getTied());
		models.put("won", gameStart.getWon());
		return "Display1";
	}
	
	@RequestMapping(value = "DisplayTwoPlayerOptions.html")
	public String DisplayTwoPlayerOptions(ModelMap models, HttpSession session) {
		return "DisplayTwoPlayerOptions";
	}
	
	@RequestMapping(value = "host.html")
	public String host(ModelMap models, HttpSession session) {
		User user = (User) session.getAttribute("user");
		h.add(""+user.getId());
		int no_of_wait = h.getIds().size();
		models.put("no_of_wait", no_of_wait);
		return "host";
	}
	
	@RequestMapping(value = "join.html")
	public String join(ModelMap models, HttpSession session) {
		User user = (User) session.getAttribute("user");
		j.add(""+user.getId());
		int no_of_wait = j.getIds().size();
		models.put("no_of_wait", no_of_wait);
		return "join";
	}
	
	@RequestMapping(value = "hGame.html", method = RequestMethod.GET)
	public String hGame(ModelMap models, HttpSession session,@RequestParam Integer id) {
		User user = userDao.getUser(id);
		Game g = new Game();
		List<Game> games = new ArrayList<Game>();
		g.setStartTime(new Date());
		g.setUser1(user);
		g = userDao.saveGame(g);
		games.add(g);
		servletContext.setAttribute("games", games);
		GameStart gameStart = new GameStart();
		servletContext.setAttribute("gameStart", gameStart);
		session.setAttribute("g", g);
		models.put("matrix", gameStart.getMatrix());
		models.put("Tied", gameStart.getTied());
		models.put("won", gameStart.getWon());
		models.put("joinUser", user.getUsername());
		return "hGame";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "jGame.html", method = RequestMethod.GET)
	public String jGame(ModelMap models, HttpSession session,@RequestParam Integer id) {
		User user = userDao.getUser(id);
		GameStart gameStart = new GameStart();
		if(servletContext.getAttribute("games")!= null){
			List<Game> games = (List<Game>) servletContext.getAttribute("games");
			gameStart = (GameStart) servletContext.getAttribute("gameStart");
		}
		models.put("matrix", gameStart.getMatrix());
		h.remove(""+id);
		models.put("hostUser", user.getUsername());
		return "jGame";
	}
	
	@RequestMapping("/h.json")
    public String hJson( ModelMap models )
    {
        models.put( "ids", h.getIds() );
        return "jsonView";
    }

    @RequestMapping("/h.deferred.json")
    @ResponseBody
    public DeferredResult<String> hDeferred()
    {
        DeferredResult<String> result = new DeferredResult<String>();
        h.addResult( result );
        return result;
    }
    
    @RequestMapping("/j.json")
    public String jJson( ModelMap models )
    {
        models.put( "ids", j.getIds() );
        return "jsonView";
    }

    @RequestMapping("/j.deferred.json")
    @ResponseBody
    public DeferredResult<String> jDeferred()
    {
        DeferredResult<String> result = new DeferredResult<String>();
        j.addResult( result );
        return result;
    }
    
    @RequestMapping("/g.json")
    public String gJson( ModelMap models )
    {
        models.put( "ids", j.getIds() );
        return "jsonView";
    }

    @RequestMapping("/g.deferred.json")
    @ResponseBody
    public DeferredResult<String> gDeferred()
    {
        DeferredResult<String> result = new DeferredResult<String>();
        j.addResult( result );
        return result;
    }
}
