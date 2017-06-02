package com.vadimostanin.quadratic_complex;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class ScreensStack
{
	private static ScreensStack mInstance = new ScreensStack();
	
	public static ScreensStack getInstance()
	{
		return mInstance;
	}
	
	private Stack<Screen> mScreensStack = new Stack<Screen>();
	private Game mGame;
	
	public void setGame( Game game )
	{
		mGame = game;
	}
	
	public void push( Screen screen )
	{
		mScreensStack.push( screen );
		
		mGame.setScreen( screen );
	}
	
	public void pop()
	{
		mScreensStack.pop();
		if( true == mScreensStack.empty() )
		{
			Gdx.app.exit();
			return;
		}
		mGame.setScreen( mScreensStack.lastElement() );
	}
}
