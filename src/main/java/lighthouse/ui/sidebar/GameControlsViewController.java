package lighthouse.ui.sidebar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import lighthouse.ui.board.BoardViewController;

public class GameControlsViewController {
	private final JComponent component;
	
	public GameControlsViewController(BoardViewController board) {
		component = new JPanel();
		
		component.add(buttonOf("New Game", board::newGame));
		component.add(buttonOf("Edit", board::edit));
	}
	
	private JButton buttonOf(String label, Runnable action) {
		JButton button = new JButton(label);
		button.addActionListener(l -> action.run());
		return button;
	}
	
	public JComponent getComponent() {
		return component;
	}
}