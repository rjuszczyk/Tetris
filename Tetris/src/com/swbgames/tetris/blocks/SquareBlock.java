package com.swbgames.tetris.blocks;

import java.util.ArrayList;
import java.util.List;

import com.swbgames.tetris.math.Position;

public class SquareBlock extends Block{
	static List<Position> positions;
	static boolean init = false;
	public SquareBlock() {
		this.position = new Position(0,0);
		if(init == false) {
			positions = new ArrayList<Position>(4);
			positions.add(new Position(-1,-1));
			positions.add(new Position(-1,0));
			positions.add(new Position(0,0));
			positions.add(new Position(0,-1));
			init = true;
		}
	}
	public SquareBlock(int x, int y) {
		this();
		this.position.setX(x);
		this.position.setY(y);
	}
	@Override
	public void rotate() {
				
	}

	@Override
	public List<Position> getLocalBlockPoints() {
		return SquareBlock.positions;
	}

	@Override
	public List<Position> getGlobalBlockPoints() {
		List<Position> globalPositions = new ArrayList<Position>(SquareBlock.positions.size());
		for(int i = 0; i < SquareBlock.positions.size(); i++) {
			globalPositions.add(Position.plus(SquareBlock.positions.get(i), this.position));
		}
		return globalPositions;
	}

}
