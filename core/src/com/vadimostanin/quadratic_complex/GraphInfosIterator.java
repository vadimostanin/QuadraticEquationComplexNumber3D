package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by vadim on 17.04.17.
 */

public class GraphInfosIterator implements Iterator<GraphInfo>
{
    private Array<GraphInfo> mGraphs;
    private int mIndex;
    public GraphInfosIterator()
    {
    }

    public void setGraphInfos( Array<GraphInfo> graphs )
    {
        mGraphs = graphs;
        reset();
    }

    public void reset()
    {
        mIndex = 0;
    }

    @Override
    public boolean hasNext()
    {
        return mIndex < mGraphs.size ;
    }

    @Override
    public GraphInfo next()
    {
        final GraphInfo result = mGraphs.get( mIndex );
        mIndex++;
        return result;
    }

    @Override
    public void remove()
    {
        mGraphs = null;
        reset();
    }
}
