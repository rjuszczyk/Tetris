package com.swbgames.tetris.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

class GameThread extends Thread {
	  private int canvasWidth = 200;
	  private int canvasHeight = 400;
	  
	  private boolean run = false;
	    
	  private float bubbleX;
	  private float bubbleY;
	   
	  private SurfaceHolder sh;
	  private Context ctx;
	  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  
	  public GameThread(SurfaceHolder surfaceHolder, Context context) {
	    sh = surfaceHolder;
	    ctx = context;
	  }
	  public void doStart() {
	    synchronized (sh) {
	      // Start bubble in centre and create some random motion
	      bubbleX = canvasWidth / 2;
	      bubbleY = canvasHeight / 2;
	      
	    }
	  }
	  public void run() {
	    while (run) {
	      Canvas c = null;
	      try {
	        c = sh.lockCanvas(null);
	        synchronized (sh) {
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
	    bubbleX = canvasWidth   / 2 + (float)Math.random() * 10;
	    bubbleY = canvasHeight  / 2 + (float)Math.random() * 10;

	    canvas.restore();
	    
	    canvas.drawColor(Color.BLUE);
	    canvas.drawCircle(bubbleX, bubbleY, 50, paint);
	  }
	}