package lighthouse.ui.board.controller;

import java.util.ArrayList;
import lighthouse.model.*;

/**
 * The primary responder implementation that
 * turns user inputs into changes to the model.
 */
public class BoardController implements BoardResponder {

	Board board;
	boolean dragEvent;
	int up;
	int down;
	int right;
	int left;

	public BoardController(Board model) {
		board = model;
	}
	
	@Override
	public void press(int gridX, int gridY) {
		Brick brick = board.locateBlock(gridX, gridY);
		if (brick == null) return;
		dragEvent = true;
		ArrayList<Edge> edgeList = brick.edges;
	}
	
	@Override
	public void dragTo(int gridX, int gridY) {
		if (!dragEvent) return;

	}
	
	@Override
	public void release(int gridX, int gridY) {
		if (dragEvent != true) return;
		dragEvent = false;
	}

	
}