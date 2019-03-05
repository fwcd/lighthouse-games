package lighthouse.puzzle.ui.modes;

import lighthouse.puzzle.ui.board.controller.BoardResponder;
import lighthouse.puzzle.ui.board.viewmodel.BoardViewModel;
import lighthouse.puzzle.ui.perspectives.GamePerspective;
import lighthouse.puzzle.ui.perspectives.InGamePerspective;
import lighthouse.ui.scene.AnimationRunner;
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
	public BoardResponder createController(GamePerspective perspective, BoardViewModel board, Updatable gameUpdater, AnimationRunner animationRunner) {
		return perspective.createPlayController(board, gameUpdater, animationRunner);
	}
	
	@Override
	public boolean isPlaying() { return true; }
}
