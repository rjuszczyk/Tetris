package com.swbgames.tetris.blocks;

import java.util.List;

import com.swbgames.tetris.math.Position;

public abstract class Block {
	protected Position position;
	/**
	 * Ustawia pozycjê bloku w przestrzeni globalnej
	 * @param x odleglosc od lewej
	 * @param y odleglosc od góry
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
	 * Ustawia pozycjê bloku w przestrzeni globalnej
	 * @param position nowa pozycja
	 */
	public void setPosition(Position position) {
		this.setPosition(position.getX(), position.getY());
	}
	/**
	 * funkcja rotuj¹ca blok zgodnie z ruchem wskazówek zegara
	 */
	public abstract void rotate();
	/**
	 * Funkcja pobieraj¹ca listê pozycji wzglêdem centrum bloku (klocka)
	 * @return lista pozycji w uk³adzie lokalnym
	 */
	public abstract List<Position> getLocalBlockPoints();
	/**
	 * Funkcja pobieraj¹ca listê pozycji wzglêdem pocz¹tku uk³adu 
	 * @return lista pozycji w uk³adzie globalnym
	 */
	public abstract List<Position> getGlobalBlockPoints();
}
