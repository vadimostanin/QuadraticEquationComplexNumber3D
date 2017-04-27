package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;

public class DesktopScreenOrientation implements IScreenOrientation
{
	private eScreenOrientation mCurrMode = eScreenOrientation.LANSCAPE;

	@Override
	public void changeMode( eScreenOrientation mode )
	{
		if( mCurrMode == mode )
		{
			return;
		}
		final int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();
		
		final int newWidth = height;
		final int newHeight = width;
		
		Gdx.graphics.setDisplayMode( newWidth, newHeight, Gdx.graphics.isFullscreen() );
	}

}
