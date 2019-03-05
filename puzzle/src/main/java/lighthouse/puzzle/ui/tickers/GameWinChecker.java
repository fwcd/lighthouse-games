package lighthouse.puzzle.ui.tickers;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import lighthouse.puzzle.model.PuzzleGameState;
import lighthouse.puzzle.ui.board.viewmodel.BoardStatistics;
import lighthouse.ui.ObservableStatus;
import lighthouse.ui.scene.AnimationRunner;
import lighthouse.ui.scene.viewmodel.graphics.ConfettiAnimation;
import lighthouse.ui.tickers.Ticker;
import lighthouse.ui.util.Status;
import lighthouse.util.ColorUtils;

public class GameWinChecker implements Ticker {
	private final JComponent parent;
	private final PuzzleGameState game;
	private final ObservableStatus status;
	private final BoardStatistics statistics;
	private final AnimationRunner animationRunner;
	private boolean alreadyWon = false;
	
	public GameWinChecker(
		JComponent parent,
		AnimationRunner animationRunner,
		PuzzleGameState game,
		ObservableStatus status,
		BoardStatistics statistics
	) {
		this.parent = parent;
		this.animationRunner = animationRunner;
		this.status = status;
		this.game = game;
		this.statistics = statistics;
	}
	
	@Override
	public void tick() {
		if (!alreadyWon && game.isWon() && !game.getBoard().isEmpty()) {
			alreadyWon = true;
			
			String message = "FINISHED GAME in " + statistics.getMoveCount() + " moves!";
			if (game.getLevel().isTooEasy()) {
				message += " (Pretty easily though, since your goal animationRunner exactly matches your starting animationRunner)";
			} else {
				message += " Hooray!";
			}
			animationRunner.play(new ConfettiAnimation());
			status.set(new Status("Won", ColorUtils.LIGHT_VIOLET));
			JOptionPane.showMessageDialog(parent, message);
		}
	}
	
	public void reset() {
		alreadyWon = false;
	}
}
