package com.vadimostanin.quadratic_complex;

public class ScreenOrientation
{
	private static ScreenOrientation mInstance = new ScreenOrientation();
	
	public static ScreenOrientation getInstance()
	{
		return mInstance;
	}
	
	private IScreenOrientation mOrientation;
	
	public void setOrientationManager( IScreenOrientation orientation )
	{
		mOrientation = orientation;
	}
	
	public void changeMode( IScreenOrientation.eScreenOrientation mode )
	{
		mOrientation.changeMode( mode );
	}
}
