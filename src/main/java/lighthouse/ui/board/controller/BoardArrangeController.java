package lighthouse.ui.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lighthouse.ui.board.viewmodel.BoardViewModel;
import lighthouse.util.IntVec;
import lighthouse.util.Updatable;

/**
 * A responder implementation that allows
 * the user to freely arrange bricks.
 */
public class BoardArrangeController extends BoardBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(BoardArrangeController.class);
	private IntVec last;
	private boolean dragging = false;
	
	public BoardArrangeController(BoardViewModel viewModel, Updatable updater) {
		super(viewModel, updater);
	}
	
	@Override
	public void press(IntVec gridPos) {
		BoardViewModel viewModel = getViewModel();
		
		if (viewModel.hasBrickAt(gridPos)) {
			viewModel.getEditState().beginEdit(viewModel.removeBrickAt(gridPos));
			LOG.debug("Pressed at {}", gridPos);
			last = gridPos;
			dragging = true;
			update();
		}
	}
	
	@Override
	public void dragTo(IntVec gridPos) {
		if (dragging) {
			IntVec delta = gridPos.sub(last);
			getViewModel().getEditState().moveBy(delta);
			last = gridPos;
			update();
		}
	}
	
	@Override
	public void release(IntVec gridPos) {
		if (dragging) {
			LOG.debug("Released at {}", gridPos);
			
			BoardViewModel viewModel = getViewModel();
			viewModel.add(viewModel.getEditState().finishEdit(gridPos));
			
			last = null;
			dragging = false;
			update();
		}
	}
}
