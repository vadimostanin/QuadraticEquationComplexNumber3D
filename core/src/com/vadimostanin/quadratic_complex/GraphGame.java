package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kotcrab.vis.ui.VisUI;

public class GraphGame extends Game
{
	@Override
	public void create()
	{
		VisUI.load();
		final Screen screen = ScreensCache.getInstace().get( ScreensCache.eScreenType.FunctionChoose );

		Gdx.input.setCatchBackKey( true );
		ScreensStack.getInstance().setGame( this );
		ScreensStack.getInstance().push( screen );
	}
}
