package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GraphGame extends Game
{
	@Override
	public void create()
	{
		final Screen screen = ScreensCache.getInstace().get( ScreensCache.eScreenType.EquationSolver );

		Gdx.input.setCatchBackKey( true );
		ScreensStack.getInstance().setGame( this );
		ScreensStack.getInstance().push( screen );
	}
}
