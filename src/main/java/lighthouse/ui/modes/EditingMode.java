package lighthouse.ui.modes;

import lighthouse.model.Board;
import lighthouse.ui.board.controller.BoardResponder;
import lighthouse.ui.perspectives.GamePerspective;
import lighthouse.ui.perspectives.StartPerspective;
import lighthouse.ui.util.Status;
import lighthouse.util.ColorUtils;

/**
 * A mode that allows the user to edit the
 * current level.
 */
public class EditingMode implements GameMode {
	public static final GameMode INSTANCE = new EditingMode();
	
	private EditingMode() {}
	
	@Override
	public Status getBaseStatus() { return new Status("Editing", ColorUtils.LIGHT_ORANGE); }
	
	@Override
	public GamePerspective getInitialPerspective() { return StartPerspective.INSTANCE; }
	
	@Override
	public BoardResponder createController(GamePerspective perspective, Board board) { return perspective.createEditController(board); }
	
	@Override
	public boolean isPlaying() { return false; }
}