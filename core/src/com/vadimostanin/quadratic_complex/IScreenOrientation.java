package com.vadimostanin.quadratic_complex;

public interface IScreenOrientation
{
	public enum eScreenOrientation
	{
		PORTRAIT,
		LANSCAPE
	}

	void changeMode( eScreenOrientation mode );
}
