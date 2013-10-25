package com.swbgames.tetris.blocks;

import java.util.ArrayList;
import java.util.List;

import com.swbgames.tetris.math.Position;

public class Island extends Block{

	List<Position> positions;
	
	public Island() {
		this.positions = new ArrayList<Position>();
		this.position = null;
	}
	
	public void addPosition(Position p) {
		if(this.position == null)
			this.position = p;
		this.positions.add(Position.minus(p, this.position));
	}
	@Override 
	public void move(Position move) {
		position.setX(position.getX() + move.getX());
		position.setY(position.getY() + move.getY());
		/*for(Position position1 : positions) {
			position1.setX(position1.getX() + move.getX());
			position1.setY(position1.getY() + move.getY());
		}*/
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < positions.size(); i++) {
			int x = positions.get(i).getX()+this.position.getX();
			int y = positions.get(i).getY()+this.position.getY();
			sb.append("["+x+","+y+ "]," );
		}
		return sb.toString();
	}
	public Island(List<Position> positions) {
		this.position = new Position(positions.get(0).getX(), positions.get(0).getY());
		this.positions = new ArrayList<Position>(positions.size());
		for(int i = 0; i < positions.size(); i++) {
			this.positions.add(Position.minus(positions.get(i), this.position));
		}
	}
	@Override
	public void rotate() {
		
	}

	@Override
	public List<Position> getLocalBlockPoints() {
		return this.positions;
	}

	@Override
	public List<Position> getGlobalBlockPoints() {
		List<Position> globalPositions = new ArrayList<Position>(this.positions.size());
		for(int i = 0; i < this.positions.size(); i++) {
			globalPositions.add(Position.plus(this.positions.get(i), this.position));
		}
		return globalPositions;
	}

}
