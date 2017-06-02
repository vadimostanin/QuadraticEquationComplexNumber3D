package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

public class MyGdxGame extends ApplicationAdapter
{
	Game mGame;
	
	@Override
	public void create ()
	{
		mGame = new GraphGame();
		mGame.create();
	}

	@Override
	public void render ()
	{
		mGame.render();
	}
}
