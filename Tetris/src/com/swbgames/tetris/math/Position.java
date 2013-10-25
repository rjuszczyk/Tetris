package com.swbgames.tetris.math;

public class Position implements Comparable<Position> {
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	@Override
	public int compareTo(Position other) {
		if(other.x == this.x && other.y == this.y)
			return 0;
		
		return -1;
	}
	@Override
	public boolean equals(Object other) {
		Position otherP = (Position)other;
		return (this.x == otherP.x && this.y == otherP.y);
	}
	
	public static Position minus(Position a, Position b) {
		return new Position(a.x - b.x, a.y - b.y);
	}
	
	public static Position plus(Position a, Position b) {
		return new Position(a.x + b.x, a.y + b.y);
	}
	public static final Position down = new Position(0, 1);
	public static final Position left = new Position(-1, 0);
	public static final Position right = new Position(1, 0);
	
}
