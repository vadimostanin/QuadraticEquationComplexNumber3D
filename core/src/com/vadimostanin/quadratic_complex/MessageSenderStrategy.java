package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 4/14/17.
 */

public class MessageSenderStrategy {
    private static final MessageSenderStrategy ourInstance = new MessageSenderStrategy();

    public static MessageSenderStrategy getInstance() {
        return ourInstance;
    }

    private IMessageSender mSender;

    private MessageSenderStrategy(){}

    public void setSender( IMessageSender sender )
    {
        mSender = sender;
    }

    public void send( String s )
    {
        mSender.send( s );
    }
}
