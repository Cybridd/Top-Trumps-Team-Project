package online.dwResources;

import commandline.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controlled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	HumanPlayer creator;
	GameRunner runner;
	/**
	 * Constructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initialization here
		// ----------------------------------------------------
	}
	
	@GET
	@Path("/createGame")
	/**
	 * Instantiate new Top Trumps game
	 * @param name - human player's name
	 * @param numOpponents - number of AI opponents
	 * @throws IOException
	 */
	public String createGame(@QueryParam("name") String name, @QueryParam("numOpponents") int numOpponents) throws IOException {
			
		creator = new HumanPlayer(name, true);
		runner = new GameRunner(numOpponents, creator, false);
		runner.createGame();
		
		return "Creating a game now!";
	}
	
	@GET
	@Path("/getDescripts")
	/**
	 * Get attribute descriptions from main deck
	 * @throws IOException
	 */
	public String getDescripts() throws IOException {
		List<String> attDescripts = new ArrayList<String>();
		for (String d : runner.getDeck().getDescripts())
		{
			attDescripts.add(d);
		}
		String listAsJSONString = oWriter.writeValueAsString(attDescripts);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/getPlayerNames")
	/**
	 * Get names of players
	 * @throws IOException
	 */
	public String getPlayerNames() throws IOException {
		List<String> playerNames = new ArrayList<String>();
		for (Player p : runner.getPlayers())
		{
			if(p != null)
			{
			playerNames.add(p.getName());
			}
			else playerNames.add("None");
		}
		String listAsJSONString = oWriter.writeValueAsString(playerNames);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/getHandSizes")
	/**
	 * Get the size of the players' hands
	 * @throws IOException
	 */
	public String getHandSizes() throws IOException {
		List<String> handSizes = new ArrayList<String>();
		for (Player p : runner.getPlayers())
		{
			if(p != null)
			{
			handSizes.add(Integer.toString(p.getHandSize()));
			}
			else handSizes.add("0");
		}
		String listAsJSONString = oWriter.writeValueAsString(handSizes);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/startRound")
	/**
	 * Start a round of Top Trumps
	 * @throws IOException
	 */
	public String startRound() throws IOException {
		
		runner.startRound();
		return "Round started";
	}
	
	@GET
	@Path("/playRound")
	/**
	 * Play a round of Top Trumps
	 * @throws IOException
	 */
	public int playRound() throws IOException {
		
		return runner.playRound();

	}
	
	@GET
	@Path("/getActivePlayer")
	/**
	 * Get the current active player
	 * @throws IOException
	 */
	public int getActivePlayer() throws IOException {
		
		return runner.getActivePlayer() + 1;
	}
	
	@GET
	@Path("/setHumanChoice")
	/**
	 * Send the human attribute selection
	 * to the game
	 * @throws IOException
	 */
	public void setHumanChoice(@QueryParam("choice") int choice) throws IOException {
		
		creator.setSelection(choice);
	}
	
	@GET
	@Path("/finishRound")
	/**
	 * Ends a round of Top Trumps
	 * @throws IOException
	 */
	public String finishRound() throws IOException {
		
		return runner.finishRound();
	}
	
	@GET
	@Path("/isWinner")
	/**
	 * Checks for a winner of the game
	 * @throws IOException
	 */
	public boolean isWinner() throws IOException {
		return runner.isWinner();
		
	}
	
	@GET
	@Path("/getGameStats")
	/**
	 * Get finished game's statistics
	 * @throws IOException
	 */
	public String gameStats() throws IOException {
		return runner.printStats();
		
	}
	
	@GET
	@Path("/getCurrentCards")
	/**
	 * Get the cards in play
	 * @throws IOException
	 */
	public String getCurrentCards() throws IOException {
		List<Card> currentCards = new ArrayList<Card>();
		for (Card c : runner.getCurrentCards())
		{
			if(c != null)
			currentCards.add(c);
		}
		String listAsJSONString = oWriter.writeValueAsString(currentCards);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/getCurrentCardNames")
	/**
	 * Get the names of the cards
	 * currently in play
	 * @throws IOException
	 */
	public String getCurrentCardNames() throws IOException {
		List<String> currentCardNames = new ArrayList<String>();
		for (Player p : runner.getPlayers())
		{
			if(p != null)
			currentCardNames.add(p.getHand().showCard().getName());
			else currentCardNames.add("None");
		}
		String listAsJSONString = oWriter.writeValueAsString(currentCardNames);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/getImageName")
	/**
	 * Get the name of the card
	 * for image retrieval
	 * @throws IOException
	 */
	public String getImageName(@QueryParam("card") int card) throws IOException {
		String cardName;
		if(runner.getPlayer(card) != null)
		{
			cardName = runner.getPlayer(card).getHand().showCard().getName();
		}
		else cardName = "None";
		return cardName;
	}
	
	@GET
	@Path("/getCommunalPileSize")
	/**
	 * Get the current size of the 
	 * communal pile
	 * @throws IOException
	 */
	public int getCommunalPileSize() throws IOException {
		
		return runner.getCommunalPile().size();
	}
	
	@GET
	@Path("/getAttributes")
	/**
	 * Get the attributes for a certain player's
	 * current card
	 * @throws IOException
	 */
	public String getAttributes(@QueryParam("player") int player) throws IOException {
		List<String> cardAttributes = new ArrayList<String>();
		for (int i=0; i<5; i++)
		{
			if(runner.getPlayer(player) != null)
			cardAttributes.add(Integer.toString(runner.getPlayer(player).getHand().showCard().getAttribute(i)));
			else cardAttributes.add("-");
		}
		String listAsJSONString = oWriter.writeValueAsString(cardAttributes);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/getTotalGames")
	/**
	 * Get the total games played
	 * @throws IOException
	 */
	public String totalGames() throws IOException {
		DatabaseInteraction data = new DatabaseInteraction();
		data.viewData();
		return data.getTotalGameNumber();	
	}
	
	@GET
	@Path("/getComputerWins")
	/**
	 * Get the number of times a computer
	 * player has won
	 * @throws IOException
	 */
	public String computerWins() throws IOException {
		DatabaseInteraction data = new DatabaseInteraction();
		data.viewData();
		return data.getComputerWins();	
	}
	
	@GET
	@Path("/getHumanWins")
	/**
	 * Get the number of times a human
	 * player has won
	 * @throws IOException
	 */
	public String humanWins() throws IOException {
		DatabaseInteraction data = new DatabaseInteraction();
		data.viewData();
		return data.getHumanWins();	
	}
	
	@GET
	@Path("/getAvgDraws")
	/**
	 * Get the average number of draws
	 * in a game
	 * @throws IOException
	 */
	public String avgDraws() throws IOException {
		DatabaseInteraction data = new DatabaseInteraction();
		data.viewData();
		return data.getAvgDraws();	
	}
	
	@GET
	@Path("/getMostRounds")
	/**
	 * Get the most rounds played in a game
	 * @throws IOException
	 */
	public String mostRounds() throws IOException {
		DatabaseInteraction data = new DatabaseInteraction();
		data.viewData();
		return data.getMaxRounds();	
	}
}
