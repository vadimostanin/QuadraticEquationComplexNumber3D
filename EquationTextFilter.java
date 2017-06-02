package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by vostanin on 4/13/17.
 */

public class EquationTextFilter implements TextField.TextFieldFilter
{
    @Override
    // Accepts all Characters except 'a'
    public  boolean acceptChar(TextField textField, char c) {
        if( ( c >= '0' && c <= '9' ) || c == '-' || c == '.' )
            return true;
        return false;
    }
}
