package com.vadimostanin.quadratic_complex.android;

import android.app.Activity;

import com.badlogic.gdx.Gdx;
import com.vadimostanin.quadratic_complex.IScreenOrientation;

public class AndroidScreenOrientation implements IScreenOrientation
{
	private eScreenOrientation mCurrMode = eScreenOrientation.LANSCAPE;
	private Activity mActivity;

	public AndroidScreenOrientation( Activity activity )
	{
		mActivity = activity;
	}
	@Override
	public void changeMode( eScreenOrientation mode )
	{
		if( mCurrMode == mode )
		{
			return;
		}
		int orientation = mActivity.getRequestedOrientation();
		final int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();

		final int newWidth = height;
		final int newHeight = width;

		Gdx.graphics.setDisplayMode( newWidth, newHeight, Gdx.graphics.isFullscreen() );
		/*
		switch( mode )
		{
			case PORTRAIT :
					mActivity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
				break;
			case LANSCAPE :
					mActivity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
				break;
		}
		*/
	}
}
