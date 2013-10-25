package com.swbgames.tetris.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.swbgames.tetris.blocks.Block;
import com.swbgames.tetris.blocks.Island;
import com.swbgames.tetris.blocks.LongBlock;
import com.swbgames.tetris.blocks.SquareBlock;
import com.swbgames.tetris.math.Position;

public class GameController {
	private int score;
	private Board board;
	Block currentBlock;
	
	public GameController(int width, int height) {
		this.board = new Board(width, height);
		this.generateNextBlock();
	}
	
	public boolean isCurrentBlock(int x, int y) {
		if(currentBlock.getGlobalBlockPoints().contains(new Position(x,y)))
			return true;
		return false;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Board getBoard() {
		return this.board;
	}
	Random r = new Random();
	public void generateNextBlock() {
		//TODO: to trza zmienic
		List<Position> po = new ArrayList<Position>(2);
		po.add(new Position(0,0));
		po.add(new Position(1,0));
		
		
		if(r.nextBoolean()) 
			currentBlock = new SquareBlock(3,0);
		else
			currentBlock = new LongBlock(3,0);
	}
	private boolean removeOneMoreTime = false;
	public void doStep() {
		if(removeOneMoreTime) {
			int points = board.doRemovingProcess();
			this.score += points;
			if(points>0)
				removeOneMoreTime = true;
			else 
				removeOneMoreTime = false;
		}
		
		if(board.checkMove(currentBlock, Position.down)) {
			currentBlock.move(Position.down);
		System.out.println("step");
		}
		else {
			board.addBlock(currentBlock);
			
			int points = board.doRemovingProcess();
			this.score += points;
			if(points>0)
				removeOneMoreTime = true;
			
			this.generateNextBlock();
		}
	}
	
	public void onMoveLeft() {
		if(board.checkMove(currentBlock, Position.left))
			currentBlock.move(Position.left);
	}
	public void onMoveRight() {
		if(board.checkMove(currentBlock, Position.right))
			currentBlock.move(Position.right);
	}
	public void onMoveDown() {
		if(board.checkMove(currentBlock, Position.down))
			currentBlock.move(Position.down);
	}
	public void onRotate() {
		currentBlock.rotate();
		if(!board.check(currentBlock)) {
			currentBlock.rotate();
			currentBlock.rotate();
			currentBlock.rotate();
		}
	}
	
}
