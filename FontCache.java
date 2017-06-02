package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vostanin on 4/12/17.
 */

public class FontCache
{
    private Map<Integer, BitmapFont> mFontsCache = new HashMap<Integer, BitmapFont>();

    private static FontCache mInstance = new FontCache();
    public static FontCache getInstance()
    {
        return mInstance;
    }

    public BitmapFont get( int aspectRatio )
    {
        BitmapFont result = mFontsCache.get( aspectRatio );
        if( null != result )
        {
            return result;
        }
        result = FontUtils.getInstance().createFont( aspectRatio );
        mFontsCache.put( aspectRatio, result );
        return result;
    }
}
