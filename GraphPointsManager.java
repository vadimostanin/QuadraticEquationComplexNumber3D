package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 4/18/17.
 */

class GraphPointsManager
{
    private IGraphPointsGenerator mCosImSlopeXGenerator;
    private IGraphPointsGenerator mQuadraticImSlopeXGenerator;
    public GraphPointsManager( float a, float b, float c, float re, float im )
    {
        mCosImSlopeXGenerator = new GraphPointsCosImSlopeXGenerator();
        mQuadraticImSlopeXGenerator = new GraphPointsImSlopeXGenerator( a, b, c, re, im );
    }

    public void setSlope( float slopeDegrees )
    {
        getGenerator().setSlope( slopeDegrees );
    }

    public IGraphPointsGenerator getGenerator()
    {
        final eGraphPointsGeneratorType type = Settings.getInstance().getGraphPointsGeneratorType();
        IGraphPointsGenerator result = null;

        switch( type )
        {
            case CosImSlopeX:
                    result = mCosImSlopeXGenerator;
                break;
            case QuadraticImSlopeX:
                    result = mQuadraticImSlopeXGenerator;
                break;
        }
        return result;
    }
}
