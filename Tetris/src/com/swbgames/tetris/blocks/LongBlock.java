package com.swbgames.tetris.blocks;

import java.util.ArrayList;
import java.util.List;

import com.swbgames.tetris.math.Position;

public class LongBlock extends Block{

	static List<Position> positions1;
	static List<Position> positions2;
	static List<Position> positions3;
	static List<Position> positions4;
	static boolean init = false;
	static ArrayList<List<Position> > positionsArray;
	
	private int rotation = 0;
	
	public LongBlock(Position position) {
		this(position.getX(), position.getY());
	}
	public LongBlock() {
		this.position = new Position(0,0);
		if(init == false) {
			positions1 = new ArrayList<Position>(4);
			positions1.add(new Position(0,-1));
			positions1.add(new Position(0,0));
			positions1.add(new Position(0,1));
			positions1.add(new Position(0,2));
			
			positions2 = new ArrayList<Position>(4);
			positions2.add(new Position(-1,0));
			positions2.add(new Position(0,0));
			positions2.add(new Position(1,0));
			positions2.add(new Position(-2,0));
			
			positions3 = new ArrayList<Position>(4);
			positions3.add(new Position(0,-1));
			positions3.add(new Position(0,0));
			positions3.add(new Position(0,1));
			positions3.add(new Position(0,-2));
			
			positions4 = new ArrayList<Position>(4);
			positions4.add(new Position(-1,0));
			positions4.add(new Position(0,0));
			positions4.add(new Position(1,0));
			positions4.add(new Position(2,0));
			
			positionsArray = new ArrayList<List<Position> >(4);
			positionsArray.add(positions1);
			positionsArray.add(positions2);
			positionsArray.add(positions3);
			positionsArray.add(positions4);
			init = true;
		}
	}
	public LongBlock(int x, int y) {
		this();
		this.position.setX(x);
		this.position.setY(y);
	}
	
	@Override
	public void rotate() {
		rotation = (rotation+1) % 4;
	}

	@Override
	public List<Position> getLocalBlockPoints() {
		return LongBlock.positionsArray.get(rotation);
	}

	@Override
	public List<Position> getGlobalBlockPoints() {
		List<Position> globalPositions = new ArrayList<Position>(LongBlock.positionsArray.get(rotation).size());
		for(int i = 0; i < LongBlock.positionsArray.get(rotation).size(); i++) {
			globalPositions.add(Position.plus(LongBlock.positionsArray.get(rotation).get(i), this.position));
		}
		return globalPositions;
	}

}
