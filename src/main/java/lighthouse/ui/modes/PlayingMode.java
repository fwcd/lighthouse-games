package lighthouse.ui.modes;

import lighthouse.model.Board;
import lighthouse.ui.board.controller.BoardResponder;
import lighthouse.ui.perspectives.GamePerspective;
import lighthouse.ui.perspectives.InGamePerspective;
import lighthouse.ui.util.Status;
import lighthouse.util.ColorUtils;
import lighthouse.util.Updatable;

/**
 * The mode in which the player plays. Simple, right?
 */
public class PlayingMode implements GameMode {
	public static final GameMode INSTANCE = new PlayingMode();
	
	private PlayingMode() {}
	
	@Override
	public Status getBaseStatus() { return new Status("Playing", ColorUtils.LIGHT_GREEN); }
	
	@Override
	public GamePerspective getInitialPerspective() { return InGamePerspective.INSTANCE; }
	
	@Override
	public BoardResponder createController(GamePerspective perspective, Board board, Updatable gameUpdater) { return perspective.createPlayController(board, gameUpdater); }
	
	@Override
	public boolean isPlaying() { return true; }
}
