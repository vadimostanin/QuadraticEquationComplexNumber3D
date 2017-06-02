package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputMultiplexerManager
{
	private InputMultiplexer mInputMultiolexer = new InputMultiplexer();
	private static InputMultiplexerManager mInstance = new InputMultiplexerManager();
	
	InputMultiplexerManager()
	{
		Gdx.input.setInputProcessor( mInputMultiolexer );
	}
	
	public static InputMultiplexerManager getInstance()
	{
		return mInstance;
	}
	
	public InputMultiplexerManager add( InputProcessor processor )
	{
		mInputMultiolexer.getProcessors().add( processor );
		return this;
	}
	
	public InputMultiplexerManager addFirst( InputProcessor processor )
	{
		mInputMultiolexer.addProcessor( 0, processor );
		return this;
	}
	
	public InputMultiplexerManager remove( InputProcessor processor )
	{
		mInputMultiolexer.removeProcessor( processor );
		return this;
	}
}
