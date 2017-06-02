package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 4/14/17.
 */

public class Settings
{
    private static Settings mInstance = new Settings();
    public static Settings getInstance()
    {
        return mInstance;
    }

    private float mGraphXMin = -3.0f;
    private float mGraphXMax = 3.0f;
    private float mGraphXDelta = 0.02f;

    private float mFullGraphImMin = -5.0f;
    private float mFullGraphImMax = 5.0f;
    private float mFullGraphImDelta = 0.02f;

    private eGraphPointsGeneratorType mGraphPointsGeneratorType = eGraphPointsGeneratorType.QuadraticImSlopeX;
    private eGraphScreenState mGraphScreenState = eGraphScreenState.eShowOneGraph;

    public Settings()
    {
        load();
    }

    public float getGraphXMax()
    {
        return mGraphXMax;
    }
    public float getGraphXMin()
    {
        return mGraphXMin;
    }
    public float getGraphXDelta()
    {
        return mGraphXDelta;
    }
    public float getFullGraphImMin()
    {
        return mFullGraphImMin;
    }
    public float getFullGraphImMax()
    {
        return mFullGraphImMax;
    }
    public float getFullGraphImDelta()
    {
        return mFullGraphImDelta;
    }

    public void setGraphXMin( float xMin )
    {
        mGraphXMin = xMin;
        save();
    }
    public void setGraphXMax( float xMax )
    {
        mGraphXMax = xMax;
        save();
    }
    public void setGraphXDelta( float xDelta )
    {
        mGraphXDelta = xDelta;
        save();
    }
    public void setFullGraphImMin( float imMin )
    {
        mFullGraphImMin = imMin;
        save();
    }
    public void setFullGraphImMax( float imMax )
    {
        mFullGraphImMax = imMax;
        save();
    }
    public void setFullGraphImDelta( float imDelta )
    {
        mFullGraphImDelta = imDelta;
        save();
    }

    public eGraphPointsGeneratorType getGraphPointsGeneratorType()
    {
        return mGraphPointsGeneratorType;
    }

    public void setGraphPointsGeneratorType( eGraphPointsGeneratorType type )
    {
        mGraphPointsGeneratorType = type;
        save();
    }

    public eGraphScreenState getGraphScreenState()
    {
        return mGraphScreenState;
    }

    public void setGraphScreenState( eGraphScreenState state )
    {
        mGraphScreenState = state;
        save();
    }

    private void load()
    {

    }

    private void save()
    {

    }
}
