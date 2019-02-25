package lighthouse.model;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The game board model which usually represents
 * a subsection of the entire grid.
 */
public class Board {
	private final int columns;
	private final int rows;
	/** Associates ids with bricks. */
	private final Map<Integer, Brick> bricksById = new HashMap<>();
	
	public Board(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}
	
	/** Fetches the board's column count. */
	public int getColumns() {
		return columns;
	}
	
	/** Fetches the board's row count. */
	public int getRows() {
		return rows;
	}
	
	/** Fetches all bricks on this board. */
	public Collection<Brick> getBricks() {
		return bricksById.values();
	}
	
	/** Fetches the cell's color at the specified position. */
	public Color getCell(int x, int y) {
		// TODO: Implement this correctly,
		// currently a random color is returned for debugging
		Random r = ThreadLocalRandom.current();
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}

	public Brick locateBlock(int gridX, int gridY){
		for (Brick brick: bricksById.values()){
			int startX = brick.xPos;
			int startY = brick.yPos;
			for (Direction dir : brick.structure){
				startX += dir.getDx();
				startY += dir.getDy();
				if (startX == gridX && startY == gridY) return brick;
			}
		}
		return null;
	}
}