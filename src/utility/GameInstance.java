package utility;

/**
 * 
 * @author Ben Talbot
 * Holds the rock, paper, scissors game logic
 *
 */
public class GameInstance {

	private String playerChoice;
	private String opponentChoice;
	
	/**
	 * No args constructor
	 */
	public GameInstance() {
		
	}	
	/**
	 * 
	 * @param playerChoice
	 * @param opponentChoice
	 * @return decision The games verdict.
	 * Takes in both players' choices and returns a verdict
	 */
	public String decideWinner(String playerChoice, String opponentChoice) {
		String decision = "";
		if (playerChoice.equals(opponentChoice)) {
			decision = "draw";
		} else if (playerChoice.equals("rock") && opponentChoice.equals("paper")){
			decision = "lose";
		} else if (playerChoice.equals("paper") && opponentChoice.equals("scissors")){
			decision = "lose";
		} else if (playerChoice.equals("scissors") && opponentChoice.equals("rock")){
			decision = "lose";
		} else if (playerChoice.equals("rock") && opponentChoice.equals("scissors")){
			decision = "win";
		} else if (playerChoice.equals("paper") && opponentChoice.equals("rock")){
			decision = "win";
		} else if (playerChoice.equals("scissors") && opponentChoice.equals("paper")){
			decision = "win";
		}
		
		return decision;
	}
}
