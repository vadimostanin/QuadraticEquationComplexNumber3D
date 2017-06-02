package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;

public class FontUtils
{
	private static FontUtils mInstance = new FontUtils();
	public static FontUtils getInstance()
	{
		return mInstance;
	}
	private final String FONT_CHARACTERS = "\u00B0\u2220^абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	
	public BitmapFont createFont( int sizeRatio )
	{
		final FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal( "DejaVuSerif-Italic.ttf" ) );
		final FreeTypeBitmapFontData font22 = generator.generateData( Gdx.graphics.getHeight() / sizeRatio );
		final FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
	    param.size = Gdx.graphics.getHeight() / sizeRatio; // Размер шрифта. Я сделал его исходя из размеров экрана. Правда коряво, но вы сами можете поиграться, как вам угодно.
	    param.characters = FONT_CHARACTERS; // Наши символы
		param.color = Color.BLACK;

		final BitmapFont font = generator.generateFont( param, font22 ); // Генерируем шрифт
	    
	    return font;
	}
}
