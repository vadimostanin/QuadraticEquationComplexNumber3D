package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.vadimostanin.quadratic_complex.IMessageSender;

/**
 * Created by vostanin on 4/14/17.
 */

public class MessageSenderDesktop implements IMessageSender
{
    public MessageSenderDesktop()
    {
    }
    @Override
    public void send( final String s )
    {
        Gdx.app.log( "", s );
    }
}
