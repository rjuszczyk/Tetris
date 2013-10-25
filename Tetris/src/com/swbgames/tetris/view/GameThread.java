package com.swbgames.tetris.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import com.swbgames.tetris.game.GameController;
import com.swbgames.tetris.math.Position;

class GameThread extends Thread {
	  private int canvasWidth = 200;
	  private int canvasHeight = 400;
	  
	  private boolean run = false;
	    
	  private float bubbleX;
	  private float bubbleY;
	   
	  private SurfaceHolder sh;
	  private Context ctx;
	  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  
	  private GameController gameController;
	  
	  public GameThread(SurfaceHolder surfaceHolder, Context context, int width, int height) {
	    sh = surfaceHolder;
	    ctx = context;
	    gameController = new GameController(width, height);
	  }
	  
	  private long lastTime = System.currentTimeMillis();
	  private long timeToRefresh = 0;
	  public static final long REFRESH_TIME = 1000;
	  public void doStart() {
		  synchronized (sh) {
			  lastTime = System.currentTimeMillis();
			  timeToRefresh = 0;
	      
	    }
	  }
	  public void moveLeft() {
		  synchronized (sh) {
			  this.gameController.onMoveLeft();			  
		  }
	  }
	  public void moveRight() {
		  synchronized (sh) {
			  this.gameController.onMoveRight();			  
		  }
	  }
	  public void moveDown() {
		  synchronized (sh) {
			  this.gameController.onMoveDown();			  
		  }
	  }
	  public void rotate() {
		  synchronized (sh) {
			  this.gameController.onRotate();			  
		  }
	  }
	  
	  public void run() {
	    while (run) {
	      Canvas c = null;
	      try {
	        c = sh.lockCanvas(null);
	        synchronized (sh) {
	        	long deltaTime = System.currentTimeMillis() - lastTime;
		  	    lastTime = System.currentTimeMillis();
		  	    timeToRefresh += deltaTime;
		  	    if( timeToRefresh > REFRESH_TIME) {
		  	    	timeToRefresh = 0;
		  	    	gameController.doStep();
		  	    }
		  	    doDraw(c);
	        }
	      } finally {
	        if (c != null) {
	          sh.unlockCanvasAndPost(c);
	        }
	      }
	    }
	  }
	    
	  public void setRunning(boolean b) { 
	    run = b;
	  }
	  public void setSurfaceSize(int width, int height) {
	    synchronized (sh) {
	      canvasWidth = width;
	      canvasHeight = height;
	      doStart();
	    }
	  }
	  private void doDraw(Canvas canvas) {
		  float canvasRatio = (float) canvasWidth / (float) canvasHeight;
		  float boardRatio = (float) this.gameController.getBoard().getWidth() / (float) this.gameController.getBoard().getHeight();
		  float x = 0;
		  float y = 0;
		  float widthR = 1;
		  float heightR = 1;
		  if(canvasRatio >= boardRatio) {
			  x = (canvasRatio - boardRatio) / 2;
			  widthR = /*canvasRatio -*/ boardRatio/canvasRatio;
		  } else {
			  y = (1.0f/canvasRatio - 1.0f/boardRatio) / 2;
			  heightR = (/*1.0f/canvasRatio -*/ canvasRatio/boardRatio);
		  }
		  canvas.restore();
		  canvas.drawColor(Color.BLACK);
		  paint.setColor(Color.BLUE);
		  canvas.drawRect(x *(float) canvasWidth, y * (float)canvasHeight, (x+widthR)*(float)canvasWidth, (y+heightR) *(float) canvasHeight, paint);
		  
		  paint.setColor(Color.CYAN);
		  float blockSize = widthR * canvasWidth / gameController.getBoard().getWidth();
		  System.out.println("adsa");
		  //Log.i("DUPA",("width="+canvasWidth+"  x = "  + (x * canvasWidth) + "   w=" + (widthR*canvasWidth))); 
		  for(int i = 0; i < gameController.getBoard().getBlocks().size(); i++) {
			  Position block = gameController.getBoard().getBlocks().get(i);
			  canvas.drawRect(x * canvasWidth + block.getX()*blockSize, y * canvasHeight + block.getY()*blockSize, x * canvasWidth + (block.getX()+1)*blockSize, y * canvasHeight + (block.getY()+1)*blockSize, paint);
		  }
		  List<Position> currentBlockPositions = gameController.getCurrentBlock().getGlobalBlockPoints();
		  for(int i = 0; i < currentBlockPositions.size(); i++) {
			  Position block = currentBlockPositions.get(i);
			  canvas.drawRect(x * canvasWidth + block.getX()*blockSize, y * canvasHeight + block.getY()*blockSize, x * canvasWidth + (block.getX()+1)*blockSize, y * canvasHeight + (block.getY()+1)*blockSize, paint);
		  }
		  
		  
	    bubbleX = canvasWidth   / 2 + (float)Math.random() * 10;
	    bubbleY = canvasHeight  / 2 + (float)Math.random() * 10;

	  
	//    for(int i = 0; i < )
	   
	   //canvas.drawCircle(bubbleX, bubbleY, 50, paint);
	  }
	}