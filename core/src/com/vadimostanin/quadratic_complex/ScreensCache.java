package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Screen;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vostanin on 4/13/17.
 */

public class ScreensCache
{
    public enum eScreenType
    {
    	FunctionChoose,
    	QuadraticEquationSolver,
        QuadraticGraph,
        CosGraph,
        Settings,
    }

    private static ScreensCache mInstance = new ScreensCache();
    public static ScreensCache getInstace()
    {
        return mInstance;
    }

    private Map<eScreenType, Screen> mCache = new HashMap<eScreenType, Screen>();

    public Screen get( eScreenType type )
    {
        Screen screen = mCache.get( type );
        if( null != screen)
        {
            return screen;
        }
        switch( type )
        {
        	case FunctionChoose:
        			screen = new FunctionChooseScreen();
        		break;
            case QuadraticEquationSolver:
                    screen = new EquationSolverScreen();
                break;
            case QuadraticGraph:
                    screen = new QuadraticEquationGraphScreen();
                break;
            case CosGraph:
            		screen = new CosGraphScreen();
            	break;
            case Settings:
                    screen = new SettingsScreen();
                break;
        }
        mCache.put( type, screen );
        return screen;
    }
}
