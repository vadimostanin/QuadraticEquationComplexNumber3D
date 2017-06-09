package com.vadimostanin.quadratic_complex;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.vadimostanin.quadratic_complex.ScreensCache.eScreenType;

public class FunctionChooseScreen implements Screen, InputProcessor {
	private Stage mStage;
	private Table mTable;
	private Skin mSkin;
	private SelectBox mFunctionSelectBox;
//	private VisSelectBox<String> mFunctionVisSelectBox;
	private FunctionsCache mFunctionsCache = new FunctionsCache();

	public FunctionChooseScreen() {
		mStage = new Stage( new FitViewport( 1024, 576 ) );
		mTable = new Table();

		mSkin = new Skin( ResourceManager.getInstance().get( Constants.Skin_Path ) );
		mSkin.addRegions( new TextureAtlas( ResourceManager.getInstance().get( Constants.Atlas_Path ) ) );

		mTable.setSkin( mSkin );
		mTable.setSize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		mTable.setFillParent( true );
		mTable.setTransform( true );
		mTable.setOrigin( Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f );
		mTable.align( Align.top | Align.center );
		mTable.pack();

		mStage.addActor( mTable );
		createUITitle();
		createUI();
		fillFunctionsCache();
	}

	private void fillFunctionsCache()
	{
		mFunctionsCache.add( 0, new IFunction()
		{
			@Override
			public void execute( Object... o )
			{
				ScreensStack.getInstance().push( ScreensCache.getInstace().get( eScreenType.QuadraticEquationSolver ) );
				Settings.getInstance().setGraphPointsGeneratorType(eGraphPointsGeneratorType.QuadraticImSlopeX);
			}
		});
		
		mFunctionsCache.add( 1, new IFunction()
		{
			@Override
			public void execute( Object... o )
			{
				final CosGraphScreen screen = ( CosGraphScreen ) ScreensCache.getInstace().get( eScreenType.CosGraph );
				Settings.getInstance().setGraphPointsGeneratorType(eGraphPointsGeneratorType.CosImSlopeX);
				screen.updateGraphInfo( new CosGraphInputData() );
				ScreensStack.getInstance().push( screen );
			}
		});
	}

	private void createUITitle()
	{
		final String sTitle = "Choose function whose 3D\ncomplex graph you want to see";
		final BitmapFont font = FontCache.getInstance().get(20);

		Label title = new Label(sTitle, mSkin);
		final VisLabel.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
		title.setStyle(titleStyle);
		mTable.add(title).colspan(2).pad(10).fillY().align(Align.top);// .expand().align(
																		// Align.top
																		// |
																		// Align.left
																		// );
		mTable.row();
	}

	private void createUI()
	{
//		mFunctionVisSelectBox = new VisSelectBox<String>();
//		mFunctionVisSelectBox.setItems( new String[] { "Quadratic A*X^2 + B * X + C = 0", "Cos(Z)|Z = X + i*Y" } );
//		mFunctionVisSelectBox.addListener( new ChangeListener()
//		{
//			@Override
//			public void changed( ChangeEvent event, Actor actor )
//			{
//
//			}
//		});

		// SelectBoxStyle style = new SelectBoxStyle();

		mFunctionSelectBox = new SelectBox<String>( mSkin );
		mFunctionSelectBox.setItems(new String[] {"Quadratic A*X^2 + B * X + C = 0", "Cos(Z)|Z = X + i*Y"});

		mTable.add(mFunctionSelectBox).fillX();// gro width( 300 );
		
		final TextButton nextButton = new TextButton( "Next", mSkin );
		nextButton.addListener( new ClickListener()
		{
			@Override
			public void clicked( InputEvent event, float x, float y )
			{
				super.clicked( event, x, y );

				final int selectedIndex = mFunctionSelectBox.getSelectedIndex();
				mFunctionsCache.execute( selectedIndex, null );
			}
		});
		mTable.row();
		mTable.add( nextButton ).fillX().height( Gdx.graphics.getHeight() * 0.05f ).align( Align.center );
	}

	@Override
	public void show()
	{
		InputMultiplexerManager.getInstance().add( mStage ).add( this );
	}

	@Override
	public void render( float delta )
	{
		Gdx.gl.glClearColor( 0.9f, 0.9f, 0.9f, 1.0f );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mStage.act();
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide()
	{
		InputMultiplexerManager.getInstance().remove( mStage ).remove( this );
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown( int keycode )
	{
		if( keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE )
		{
			ScreensStack.getInstance().pop();

			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private class FunctionsCache {
		private Map<Integer, IFunction> mFuncs = new HashMap<Integer, IFunction>();

		public FunctionsCache() {

		}

		public void add(int index, IFunction f) {
			mFuncs.put(index, f);
		}

		public void execute(int index, Object... o) {
			final IFunction f = mFuncs.get(index);
			if (f == null) {
				return;
			}
			f.execute( o );
		}
	}
}
