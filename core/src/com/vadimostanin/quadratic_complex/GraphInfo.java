package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class GraphInfo implements Disposable
{
	private Array<Vector3> mPoints = new Array<Vector3>();
	private Color mColor = new Color();
	
	public GraphInfo( Array<Vector3> points, Color color )
	{
		mPoints.addAll( points );
		mColor.set( color );
	}
	
	public Array<Vector3> Points()
	{
		return mPoints;
	}
	
	public Color Color()
	{
		return mColor;
	}

	@Override
	public void dispose()
	{
		mPoints.clear();
	}
}
