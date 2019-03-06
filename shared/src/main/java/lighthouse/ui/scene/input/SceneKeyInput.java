package lighthouse.ui.scene.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lighthouse.ui.scene.controller.SceneResponder;
import lighthouse.util.Direction;
import lighthouse.util.IntVec;

/**
 * A keyboard grid input implementation.
 */
public class SceneKeyInput extends KeyAdapter implements SceneInput {
	private static final Logger LOG = LoggerFactory.getLogger(SceneKeyInput.class);
	private final Map<Integer, Runnable> bindings = new HashMap<>();
	private final List<SceneResponder> responders = new ArrayList<>();
	private IntVec gridPos = null;
	private boolean dragging = false;
	private boolean hasSimpleArrowKeys = false;
	
	public SceneKeyInput() {
		bindings.put(KeyEvent.VK_UP, this::arrowUpPressed);
		bindings.put(KeyEvent.VK_DOWN, this::arrowDownPressed);
		bindings.put(KeyEvent.VK_LEFT, this::arrowLeftPressed);
		bindings.put(KeyEvent.VK_RIGHT, this::arrowRightPressed);
		bindings.put(KeyEvent.VK_ENTER, this::enterPressed);
	}
	
	@Override
	public void addResponder(SceneResponder responder) {
		responders.add(responder);
	}
	
	public void keyPressed(int keyCode) {
		Runnable action = bindings.get(keyCode);
		if (action != null) {
			LOG.info("Pressed {} at {} - dragging: {} - responders: {}", KeyEvent.getKeyText(keyCode), gridPos, dragging, responders);
			action.run();
		}
	}
	
	private void arrowUpPressed() {
		if (dragging) {
			drag(Direction.UP);
		} else {
			arrowSelect(r -> {
				IntVec tmp = r.up(gridPos);
				LOG.info("Moving up");
				return tmp;
			});
		}
	}
	
	private void arrowDownPressed() {
		if (dragging) {
			drag(Direction.DOWN);
		} else {
			arrowSelect(r -> {
				IntVec tmp = r.down(gridPos);
				LOG.info("Moving down");
				return tmp;
			});
		}
	}
	
	private void arrowLeftPressed() {
		if (dragging) {
			drag(Direction.LEFT);
		} else {
			arrowSelect(r -> {
				IntVec tmp = r.left(gridPos);
				LOG.info("Moving left");
				return tmp;
			});
		}
	}
	
	private void arrowRightPressed() {
		if (dragging) {
			drag(Direction.RIGHT);
		} else {
			arrowSelect(r -> {
				IntVec tmp = r.right(gridPos);
				LOG.info("Moving right");
				return tmp;
			});
		}
	}
	
	private void enterPressed() {
		if (dragging) {
			responders.forEach(r -> r.release(gridPos));
			dragging = false;
		} else {
			responders.forEach(r -> r.press(gridPos));
			dragging = true;
		}
	}
	
	private void drag(Direction dir) {
		if (gridPos != null) {
			IntVec nextPos = gridPos.add(dir);
			boolean success = false;
			
			for (SceneResponder responder : responders) {
				success |= responder.dragTo(nextPos);
			}
			
			if (success) {
				gridPos = nextPos;
			}
		}
	}
	
	private void arrowSelect(Function<SceneResponder, IntVec> directionedSelectAction) {
		if (hasSimpleArrowKeys || hasSelection()) {
			updateSelection(directionedSelectAction);
		} else {
			updateSelection(r -> r.selectAny());
		}
	}
	
	private void updateSelection(Function<SceneResponder, IntVec> action) {
		boolean updatedSelection = false;
		for (SceneResponder responder : responders) {
			IntVec result = action.apply(responder);
			
			if (!updatedSelection) {
				gridPos = result;
				updatedSelection = true;
			}
		}
	}
	
	private boolean hasSelection() {
		return gridPos != null;
	}
	
	public void keyReleased(int keyCode) {
		responders.forEach(r -> r.deselect());
	}
	
	public Set<Integer> getBoundKeys() {
		return bindings.keySet();
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyPressed(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keyReleased(e.getKeyCode());
	}
	
	public void setUseSimpleArrowKeys(boolean hasSimpleArrowKeys) {
		this.hasSimpleArrowKeys = hasSimpleArrowKeys;
	}
}
