package lighthouse.ui.board.controller;

import lighthouse.model.Board;
import lighthouse.ui.stage.LevelStageVisitor;
import lighthouse.ui.stage.LevelStages;

/**
 * A GameStageVisitor that determines the editing
 * controller to pick based on a stage.
 */
public class EditingControllerPicker implements LevelStageVisitor<BoardResponder> {
	private final Board board;
	
	public EditingControllerPicker(Board board) {
		this.board = board;
	}
	
	@Override
	public BoardResponder visitStart(LevelStages.Start stage) { return new BoardDrawController(board); }
	
	@Override
	public BoardResponder visitCurrent(LevelStages.Current stage) { return new BoardDrawController(board); }
	
	@Override
	public BoardResponder visitGoal(LevelStages.Goal stage) { return new BoardArrangeController(board); }
}