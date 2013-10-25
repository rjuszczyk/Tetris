package com.swbgames.tetris.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.swbgames.tetris.blocks.Block;
import com.swbgames.tetris.blocks.Island;
import com.swbgames.tetris.math.Position;

public class Board {
	List<Position> blocks;
	List<Position> lastAdded;
	
	int width;
	int height;
	public Board() {
		this(8,20);
	}
	public List<Position> getBlocks() {
		return this.blocks;
	}
	public void addBlock(Block block) {
		blocks.addAll(block.getGlobalBlockPoints());
		lastAdded.clear();
		lastAdded.addAll(block.getGlobalBlockPoints());
	}
	public boolean checkMove(Block block, Position move) {
		List<Position> block_positions = block.getGlobalBlockPoints();
		for(Position pos : block_positions) {
			Position moved = Position.plus(pos, move);
			if(blocks.contains(moved))
				return false;
			if(moved.getX() >= width || moved.getY() >= height || moved.getX() < 0 || moved.getY() < 0)
				return false;
		}
		
		return true;
	}
	public boolean check(Block block) {
		for(int i = 0; i < block.getGlobalBlockPoints().size(); i++) {
			Position pos = block.getGlobalBlockPoints().get(i);
			if(blocks.contains(pos))
				return false;
			if(pos.getX() >= width || pos.getY() >= height || pos.getX() < 0 || pos.getY() < 0)
				return false;
		}
		return true;
	}
	public List<Integer> checkFullLines() {
		List<Integer> linesToDelete = new LinkedList<Integer>();
		for(int i = 0; i < lastAdded.size(); i++) {
			Position position = lastAdded.get(i);
			int y = position.getY();
			int count = 0;
			for(int j = 0; j < blocks.size(); j++) {
				if(blocks.get(j).getY() == y) 
					count++;
			}
			if(count == this.width) {
				linesToDelete.add(y);
			}
		}
		return linesToDelete;
	}
	public int doRemovingProcess() {
		List<Integer> linesToRemove;
		linesToRemove = checkFullLines();
		createIslands();
		int score = linesToRemove.size();
		if(linesToRemove.size() > 0) {
						
			removeLines(linesToRemove);
		
			List<Block> islands = createIslands();
		
		
			boolean moved2 = false;
			do {
				moved2 = false;
				for(Block island : islands) {
					boolean moved  = false;
					blocks.removeAll(island.getGlobalBlockPoints());
					
					do{
						moved = false;
						
						if (checkMove(island, Position.down)){
							System.out.println("moved from: " + island);
							
							island.move(Position.down);
							System.out.println("moved to: " + island);
							moved = true;
							moved2 = true;
						} else {
							this.addBlock(island);
						}
					} while (moved == true);
				}
			} while(moved2);
			linesToRemove = checkFullLines();
		} 
		return score;
	}
	
	public void removeLines(List<Integer> lines) {
		for(int i = 0; i < lines.size(); i++)
			removeLine(lines.get(i));
	}
	public void removeLine(int y){
		for (Iterator<Position> iterator = blocks.iterator(); iterator.hasNext(); ) {
		    Position position = iterator.next();
		    if(position.getY() == y)
		    	iterator.remove();
		}
	}
	public int getBlockPosition(int x, int y) {
		for(int i = 0; i < blocks.size(); i++)
			if(blocks.get(i).getX() == x && blocks.get(i).getY() == y)
				return i;
	    return -1;
	}
	
	private boolean process(int x, int y, int marker) {
		int position = this.getBlockPosition(x, y);
		if(position != -1 && markers[position] == -1) {
			markers[position] = marker;
			process(x-1, y-1, marker);
			process(x, y-1, marker);
			process(x+1, y-1, marker);
			process(x-1, y, marker);
			process(x+1, y, marker);
			process(x+1, y+1, marker);
			process(x-1, y+1, marker);
			process(x, y+1, marker);
			return true;
		} else 
			return false;
	}
	
	public int[] markers = new int[10];// = new int[blocks.size()];
	public List<Block> createIslands() {
		markers = new int[blocks.size()];
		
		for(int i = 0 ; i < blocks.size(); i++)
			markers[i] = -1;
		int curr = 0;
		for(Position block : blocks) {
			if(process(block.getX(), block.getY(), curr))
				curr++;
		}
		
		List<Block> islands = new ArrayList<Block>(curr);
		
		for(int i = 0; i < curr; i++) {
			ArrayList<Position> pos = new ArrayList<Position>();
			for(int j = 0; j < markers.length; j++) {
				if(markers[j] == i) {
					pos.add(blocks.get(j));
				}
			}
			islands.add(new Island(pos));
		}
		return islands;
	}
	public Board(int width, int height) {
		blocks    = new ArrayList<Position>(width*height);
		lastAdded = new LinkedList<Position>();
		
		this.width = width;
		this.height = height;
	}
	
}
