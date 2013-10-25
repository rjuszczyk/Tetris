package com.swbgames.tetris.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BubbleSurfaceView extends SurfaceView  
implements SurfaceHolder.Callback {
	private SurfaceHolder sh;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Context ctx;
	public BubbleSurfaceView(Context context) {
		super(context);
		sh = getHolder();
		sh.addCallback(this);
		thread = new GameThread(sh, context );
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
		ctx = context;
	    setFocusable(true); // make sure we get key events
	}
	public void surfaceCreated(SurfaceHolder holder) {
	    thread = new GameThread(sh, ctx);
	    Handler handler; //obczaic co to moze robic
	   
	    thread.setRunning(true);
	    thread.start();
	  }
	
	
	// Variables go here
	GameThread thread;
	
		
	public GameThread getThread() {
	  return thread;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) 	  {      
	  thread.setSurfaceSize(width, height);
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
	  boolean retry = true;
	  thread.setRunning(false);
	  while (retry) {
	    try {
	      thread.join();
	      retry = false;
	    } catch (InterruptedException e) {
	    }
	  }
	}
}