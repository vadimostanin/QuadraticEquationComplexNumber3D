package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Iterator;

/**
 * Created by vadim on 17.04.17.
 */

public class GraphPointsImSlopeXGenerator extends IGraphPointsGenerator
{
    private GraphInfosIterator mIterator = new GraphInfosIterator();
    private QuadraticSolver mQuadraticSolver;
    private final Complex mYSolution = new Complex();
    private Array<GraphInfo> mGraphs = new Array<GraphInfo>();
    private float mXSlopeAngle;

    public GraphPointsImSlopeXGenerator( float a, float b, float c, float re, float im )
    {
        mQuadraticSolver = new QuadraticSolver( a, b, c );
        mXSlopeAngle = MyUtils.recalcSlopeAngle( re, im );
    }

    public void generateOne()
    {
        clearGraphInfos();
        final Color color = Color.BLACK;
        generateGraphPoints_Slope( mXSlopeAngle, color );
        mIterator.setGraphInfos( mGraphs );
    }

    public void generateRange()
    {
        clearGraphInfos();
        generateGraphsPoints_Slope();
        mIterator.setGraphInfos( mGraphs );
    }

    @Override
    public void getPoint( float xRe, float xIm, Vector3 result )
    {
        final float sloppedIm = MyUtils.getIAxisDisplacement( mXSlopeAngle, xRe );
        final Complex y = mQuadraticSolver.getY( xRe, sloppedIm );
        result.set( xRe, ( float )y.re(), ( float ) ( y.im() + sloppedIm ) );
    }

    @Override
    public void setSlope( float slopeDegrees )
    {
        mXSlopeAngle = slopeDegrees;
    }

    private void addGraphPoints(Array<Vector3> points, Color color )
    {
        final GraphInfo graphInfo = new GraphInfo( points, color );
        mGraphs.add( graphInfo );
    }

    private void clearGraphInfos()
    {
        for( Disposable graphInfo : mGraphs )
        {
            graphInfo.dispose();
        }
        mGraphs.clear();
    }

    private void generateGraphPoints_Slope( float angle, Color color )
    {
        final Array<Vector3> points = new Array<Vector3>();
        fillGraphPoints_Slope( angle, points );
        addGraphPoints( points, color );
    }

    private void fillGraphPoints_Slope( float angle, Array<Vector3> points )
    {
        for( float x_value = Settings.getInstance().getGraphXMin(); x_value <= Settings.getInstance().getGraphXMax(); x_value += Settings.getInstance().getGraphXDelta() )
        {
            float Im;
            if( x_value >= 0)
            {
                Im = MyUtils.getIAxisDisplacement( angle + 180.0f, x_value );
            }
            else
            {
                Im = MyUtils.getIAxisDisplacement( angle, x_value );
            }
            mYSolution.set( mQuadraticSolver.getY( x_value, Im ) );
            points.add( new Vector3( x_value, ( float ) mYSolution.re(), ( float ) ( mYSolution.im() + Im ) ) );
        }
    }

    private void generateGraphsPoints_Slope()
    {
        final float angle_Min = 0.0f;
        final float angleMax = 360.0f;
        final float angleDelta = 1.0f;
        for( float angle_i = angle_Min; angle_i <= angleMax ; angle_i += angleDelta )
        {
            final Color color = Color.BLACK;
            com.kotcrab.vis.ui.util.ColorUtils.HSVtoRGB( angle_i, 100.0f, 100.0f, color );

            generateGraphPoints_Slope( angle_i, color );
        }
    }

    @Override
    public Iterator<GraphInfo> iterator()
    {
        mIterator.reset();
        return mIterator;
    }
}
