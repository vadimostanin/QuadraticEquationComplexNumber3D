package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Iterator;

/**
 * Created by vadim on 17.04.17.
 */

public class GraphPointsImTimesXPointsGenerator extends IGraphPointsGenerator
{
    private GraphInfosIterator mIterator = new GraphInfosIterator();
    private QuadraticSolver mQuadraticSolver;
    private final Complex mYSolution = new Complex();
    private Array<GraphInfo> mGraphs = new Array<GraphInfo>();

    public GraphPointsImTimesXPointsGenerator( float a, float b, float c )
    {
        mQuadraticSolver = new QuadraticSolver( a, b, c );
    }

    public void generateOne()
    {
        clearGraphInfos();
        mIterator.setGraphInfos( mGraphs );
    }

    public void generateRange()
    {
        clearGraphInfos();
        generateGraphsPoints();
        mIterator.setGraphInfos( mGraphs );
    }

    @Override
    public void getPoint( float re, float im, Vector3 result )
    {

    }

    private void clearGraphInfos()
    {
        for( Disposable graphInfo : mGraphs )
        {
            graphInfo.dispose();
        }
        mGraphs.clear();
    }

    private void addGraphPoints(Array<Vector3> points, Color color )
    {
        final GraphInfo graphInfo = new GraphInfo( points, color );
        mGraphs.add( graphInfo );
    }

    private void generateGraphPoints( float Im, Color color )
    {
        final Array<Vector3> points = new Array<Vector3>();
        fillGraphPoints( Im, points );
        addGraphPoints( points, color );
    }

    private void fillGraphPoints( float Im, Array<Vector3> points )
    {
        for( float x_value = Settings.getInstance().getGraphXMin(); x_value <= Settings.getInstance().getGraphXMax() ; x_value += Settings.getInstance().getGraphXDelta() )
        {
            mYSolution.set( mQuadraticSolver.getY(x_value, Im ) );
            points.add( new Vector3( x_value, ( float ) mYSolution.re(), ( float ) ( mYSolution.im() + Im ) ) );
        }
    }

    private void generateGraphsPoints()
    {
        final float imMin = Settings.getInstance().getFullGraphImMin();
        final float imMax = Settings.getInstance().getFullGraphImMax();
        final float imDelta = Settings.getInstance().getFullGraphImDelta();
        for( float im_value = imMin; im_value <= imMax; im_value += imDelta )
        {
            final float absImValue = Math.abs( im_value );
            final float maxIm = Math.max( Math.abs( imMin ), Math.abs( imMax ) );
            final float maxImCount = maxIm / imDelta;
            final float hValue = Math.abs( ( 360.0f / maxImCount ) * ( absImValue / imDelta ) );

            final Color color = Color.BLACK;
            com.kotcrab.vis.ui.util.ColorUtils.HSVtoRGB( hValue, 100.0f, 100.0f, color );
            generateGraphPoints( im_value, color );
        }
    }

    @Override
    public Iterator<GraphInfo> iterator()
    {
        mIterator.reset();
        return mIterator;
    }
}
