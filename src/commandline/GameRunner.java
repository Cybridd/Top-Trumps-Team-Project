package commandline;

/**
 * GameRunner class is the class which manages the running of a top trumps game.
 * This class is constructed from information provided by the creator (human player)
 * consisting of:
 * <ul>
 *  <li>the number of AI opponents</li>
 *  <li>the human player itself </li>
 * </ul>
 */
import java.util.ArrayList;

public class GameRunner {
	private Game currentGame;
	private int AIPlayers;
	private HumanPlayer creator;
	private boolean testLogs;
	
	/** 
	 * Constructor for GameRunner class.
	 * @param aiPlayers number of desired opponents
	 * @param player HumanPlayer object
	 * @param printTestLogs boolean for printing test logs
	 */
	public GameRunner(int aiPlayers, HumanPlayer player, boolean printTestLogs)
	{	
		AIPlayers = aiPlayers;
		creator = player;
		testLogs = printTestLogs;
	}
	
	/**
	 * Method for creating a Game object. Uses
	 * arguments passed in through constructor.
	 */
	public void createGame()
	{
		currentGame = new Game(AIPlayers, creator, testLogs);
	}
	
	/**
	 * Method for starting a round of top trumps.
	 */
	public void startRound()
	{
		currentGame.startRound();
	}
	
	public int playRound()
	{
		return currentGame.playRound();
	}
	
	/**
	 * Method for returning the current active player.
	 * @return active player in a round
	 */
	public int getActivePlayer()
	{
		return currentGame.getActivePlayer();
	}
	
	/**
	 * Method for finishing a round of top trumps.
	 * Returned string used for display in web page.
	 * @return String result of round (who won, draw etc)
	 */
	public String finishRound()
	{
		currentGame.finishRound();
		return currentGame.getRoundResult();
	}
	
	/**
	 * Getter methods that call getter methods in Game class.
	 * Used by RESTAPI to retrieve information for web page.
	 * @return various game component getter methods
	 */
	public Player[] getPlayers()
	{
		return currentGame.getPlayers();
	}
	
	public Player getPlayer(int player)
	{
		return currentGame.getPlayer(player);
	}
	public Deck getDeck()
	{
		return currentGame.getDeck();
	}
	
	public Card[] getCurrentCards()
	{
		return currentGame.getCurrentCards();
	}
	
	public ArrayList<Card> getCommunalPile()
	{
		return currentGame.getCommunalPile();
	}
	
	/** 
	 * 	Get whether someone has won the game. Used by 
	 *  program logic in CLI mode and finishRound 
	 *  Javascript method in web page.
	 * @return winner boolean game win condition
	 */
	public boolean isWinner()
	{
		return currentGame.isWinner();
	}
	
	public String printStats()
	{
		return currentGame.gameStats();
	}
		
}
