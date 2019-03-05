package lighthouse.model;

import lighthouse.ai.AIMain;
import lighthouse.puzzle.model.PuzzleGameState;

/**
 * The application's model. Contains the game.
 */
public class AppModel {
	private final PuzzleGameState legacyGameState = new PuzzleGameState();
	private final FileSaveState saveState = new FileSaveState();
	private final GameState activeGameState = null;
	private AIMain ai;
	
	@Deprecated
	public PuzzleGameState getGameState() { return legacyGameState; }
	
	public GameState getActiveGameState() { return activeGameState; }
	
	public FileSaveState getSaveState() { return saveState; }
	
	public AIMain getAI() { return ai; }
	
	public void setAI(AIMain ai) { this.ai = ai; }
}