package com.swbgames.tetris.blocks;

import java.util.List;

import com.swbgames.tetris.math.Position;

public abstract class Block {
	protected Position position;
	/**
	 * Ustawia pozycj� bloku w przestrzeni globalnej
	 * @param x odleglosc od lewej
	 * @param y odleglosc od g�ry
	 */
	public void setPosition(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);
	}
	public void move(Position move) {
		this.position.setX(this.position.getX() + move.getX());
		this.position.setY(this.position.getY() + move.getY());
	}
	/**
	 * Ustawia pozycj� bloku w przestrzeni globalnej
	 * @param position nowa pozycja
	 */
	public void setPosition(Position position) {
		this.setPosition(position.getX(), position.getY());
	}
	/**
	 * funkcja rotuj�ca blok zgodnie z ruchem wskaz�wek zegara
	 */
	public abstract void rotate();
	/**
	 * Funkcja pobieraj�ca list� pozycji wzgl�dem centrum bloku (klocka)
	 * @return lista pozycji w uk�adzie lokalnym
	 */
	public abstract List<Position> getLocalBlockPoints();
	/**
	 * Funkcja pobieraj�ca list� pozycji wzgl�dem pocz�tku uk�adu 
	 * @return lista pozycji w uk�adzie globalnym
	 */
	public abstract List<Position> getGlobalBlockPoints();
}
