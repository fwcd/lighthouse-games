package lighthouse.model;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import lighthouse.util.IntVec;

/**
 * The game board model representing
 * the entire state of the "Schimmler"-game.
 */
public class Board {
	private final int columns;
	private final int rows;
	private final Deque<Brick> bricks = new ArrayDeque<>();
	private transient BoardEditState editState;
	
	public Board(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}
	
	/** Fetches the board's column count. */
	public int getColumns() { return columns; }
	
	/** Fetches the board's row count. */
	public int getRows() { return rows; }
	
	/** Fetches all bricks on this board. */
	public Collection<Brick> getBricks() { return bricks; }
	
	/** Pushes a brick onto the board. */
	public void add(Brick brick) { bricks.push(brick); }
	
	/** Fetches the cell's color at the specified position. */
	public Color colorAt(IntVec gridPos) {
		Brick brick = locateBrick(gridPos);
		if (brick == null) {
			return Color.BLACK;
		} else {
			return brick.getColor();
		}
	}
	
	public Color colorAt(int x, int y) {
		return colorAt(new IntVec(x, y));
	}

	public Brick locateBrick(IntVec gridPos) {
		return bricks.stream()
			.filter(brick -> brick.contains(gridPos))
			.findFirst()
			.orElse(null);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Board)) return false; 
		Board board = (Board) obj;
		return board.bricks.equals(bricks);
	}
	
	/** Deeply copies this board. */
	public Board copy() {
		Board copied = new Board(columns, rows);
		for (Brick brick : bricks) {
			copied.bricks.push(brick.copy());
		}
		return copied;
	}
	
	/** Fetches the current editing state of the board. */
	public BoardEditState getEditState() {
		if (editState == null) {
			// Lazy initialization/reinitalization after deserialization
			editState = new BoardEditState();
		}
		return editState;
	}
}
