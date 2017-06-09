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
//		final QubicEquationSolver solver = new QubicEquationSolver( 1, 1, 1, 1 );
//		Complex y = solver.getY( 1, 2 );
		VisUI.load();
		final Screen screen = ScreensCache.getInstace().get( ScreensCache.eScreenType.FunctionChoose );

		Gdx.input.setCatchBackKey( true );
		ScreensStack.getInstance().setGame( this );
		ScreensStack.getInstance().push( screen );
	}
}
