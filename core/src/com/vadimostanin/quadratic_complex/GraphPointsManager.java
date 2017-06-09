package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 4/18/17.
 */

class GraphPointsManager
{
    private IGraphPointsGenerator mCosImSlopeXGenerator;
    private IGraphPointsGenerator mQuadraticImSlopeXGenerator;
    private IGraphPointsGenerator mQubicImSlopeXGenerator;
    public GraphPointsManager()
    {
        ;
    }
    
    public void createQuadraticPointGenerator( float a, float b, float c, float re, float im )
    {
    	mQuadraticImSlopeXGenerator = new GraphPointsImSlopeXGenerator( a, b, c, re, im );
    }
    
    public void createCosPointGenerator()
    {
    	mCosImSlopeXGenerator = new GraphPointsCosImSlopeXGenerator();
    }
    
    public void createQubicPointGenerator( float a, float b, float c, float d )
    {
    	mQubicImSlopeXGenerator = new GraphPointsQubicImSlopeXGenerator( a, b, c, d );
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
            case QubicImSlopeX:
            		result = mQubicImSlopeXGenerator;
            	break;
        }
        return result;
    }
}
