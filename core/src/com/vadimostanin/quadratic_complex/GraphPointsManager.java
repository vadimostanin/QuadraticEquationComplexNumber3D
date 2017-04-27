package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 4/18/17.
 */

class GraphPointsManager
{
    private IGraphPointsGenerator mImParallelXGenerator;
    private IGraphPointsGenerator mImSlopeXGenerator;
    public GraphPointsManager( float a, float b, float c, float re, float im )
    {
        mImParallelXGenerator = new GraphPointsImParalelXGenerator( a, b, c, im );
        mImSlopeXGenerator = new GraphPointsImSlopeXGenerator( a, b, c, re, im );
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
            case ImParallelX:
                    result = mImParallelXGenerator;
                break;
            case ImSlopeX:
                    result = mImSlopeXGenerator;
                break;
        }
        return result;
    }
}
