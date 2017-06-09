package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class QuadraticEquationSolverScreen implements Screen, InputProcessor
{
	private Stage mStage;
	private Table mTable;
	private Skin  mSkin;
	private Table mSolveTable;
	private TextField mX2Text;
	private TextField mXText;
	private TextField mCText;
	private ScrollPane mTableScroll;
	
	public QuadraticEquationSolverScreen()
	{
//		ScreenOrientation.getInstance().changeMode( eScreenOrientation.PORTRAIT );
//		ScreenOrientation.getInstance().changeMode( eScreenOrientation.LANSCAPE );

		mStage = new Stage(new FitViewport( 1024, 576 ) );
		mTable = new Table();

		mSkin = new Skin( ResourceManager.getInstance().get( Constants.Skin_Path ) );
		mSkin.addRegions( new TextureAtlas( ResourceManager.getInstance().get( Constants.Atlas_Path ) ) );

		mTable.setSkin( mSkin );
		mTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mTable.setFillParent(true);
		mTable.setTransform(true);
		mTable.setOrigin(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f);
		mTable.align( Align.top | Align.center );
		mTable.pack();
		
//		mStage.setDebugAll( true );

		mTableScroll = new ScrollPane( mTable );
		mTableScroll.setFillParent( true );

		final Table table = new Table();
		table.setFillParent(true);
		table.add(mTableScroll).fill().expand();

		mStage.addActor( mTableScroll );

		mStage.getRoot().addCaptureListener(new InputListener() {
			public boolean keyDown (InputEvent event, int keycode) {
				if( ( event.getKeyCode() == Input.Keys.BACK || event.getKeyCode() == Input.Keys.ESCAPE ) &&
						true == (event.getTarget() instanceof TextField))
				{
					mStage.setKeyboardFocus(null);
				}
				return false;
			}
		});
		
		createUITitle();
		createUIEquationInput();
		createSolvedTable();

//		mStage.getCamera().rotate( 0.0f, 0.0f, 1.0f, 90.0f);
//		Gdx.graphics.setDisplayMode( 500, 500, false );


	}
	
	@Override
	public void show()
	{
		InputMultiplexerManager.getInstance().add( mStage );
		InputMultiplexerManager.getInstance().add( this );
	}
	
	@Override
	public void hide()
	{
		InputMultiplexerManager.getInstance().remove( mStage );
		InputMultiplexerManager.getInstance().remove( this );
	}

	@Override
	public void render( float delta )
	{
		Gdx.gl.glClearColor( 0.9f, 0.9f, 0.9f, 1.0f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		mStage.act();
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose(){}
	
	private void createUITitle()
	{
		final String sTitle = "Quadratic equation solver \nwith Complex number support";
		final BitmapFont font = FontCache.getInstance().get( 20 );

		Label title = new Label( sTitle, mSkin );
		final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
		title.setStyle( titleStyle );
		mTable.add( title ).colspan( 2 ).pad( 10 ).fillY().align( Align.top );//.expand().align( Align.top | Align.left );
		mTable.row();
	}
	
	private void createUIEquationInput()
	{
		final Table equationTable = new Table();
		equationTable.align( Align.top );
		{
			{
				mX2Text = new TextField("1.0", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mX2Text.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mX2Text.setStyle(titleStyle);
				equationTable.add(mX2Text).width( 70 );

				mX2Text.setTextFieldFilter( new EquationTextFilter() );
			}
			{
				final Label x2Label = new Label(" * X^2", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				x2Label.setStyle(titleStyle);
				equationTable.add( x2Label ).expand();
			}
			{
				final Label x2PlusLabel = new Label(" + ", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				x2PlusLabel.setStyle(titleStyle);
				equationTable.add(x2PlusLabel);
			}
		}
		{
			{
				mXText = new TextField("1.0", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mXText.getStyle();
				final TextField.TextFieldStyle x1TextStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background );
				mXText.setStyle(x1TextStyle);
				equationTable.add(mXText).width( 70 );

				mXText.setTextFieldFilter( new EquationTextFilter() );
			}
			{
				final Label x1Label = new Label(" * X", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				x1Label.setStyle(titleStyle);
				equationTable.add(x1Label);
			}
			{
				final Label x1PlusLabel = new Label(" + ", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				x1PlusLabel.setStyle(titleStyle);
				equationTable.add(x1PlusLabel);
			}
		}

		{
			mCText = new TextField("1.0", mSkin);
			final BitmapFont font = FontCache.getInstance().get(30);
			final TextField.TextFieldStyle prevStyle = mCText.getStyle();
			final TextField.TextFieldStyle x1TextStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background );
			mCText.setStyle(x1TextStyle);
			equationTable.add(mCText).width( 70 );

			mCText.setTextFieldFilter( new EquationTextFilter() );
		}
		{
			final Label cLabel = new Label(" = 0", mSkin);
			final BitmapFont font = FontCache.getInstance().get(30);
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			cLabel.setStyle(titleStyle);
			equationTable.add(cLabel);
		}

		mTable.add( equationTable ).align( Align.center );
		{
			final Button solveBtn = new TextButton( "Solve", mSkin );
			solveBtn.addListener( new ClickListener() {
				@Override
				public void clicked( InputEvent event, float x, float y )
				{
					super.clicked( event, x, y );
					try {
						final float mA = Float.parseFloat(mX2Text.getText());
						final float mB = Float.parseFloat(mXText.getText());
						final float mC = Float.parseFloat(mCText.getText());
						final QuadraticSolver quadSolver = new QuadraticSolver(mA, mB, mC);
						fillSolvedTable(quadSolver);
					}
					catch( Exception e )
					{
						MessageSenderStrategy.getInstance().send( "Incorrect input data" );
					}

				}
			});
			mTable.row();
			mTable.add( solveBtn ).fillX().height( Gdx.graphics.getHeight() * 0.05f ).align( Align.center );
		}
	}

	private void createSolvedTable()
	{
		mSolveTable = new Table();
		mTable.row();
		mTable.add( mSolveTable ).align( Align.left );
	}

	private void fillSolvedTable( final QuadraticSolver quadSolver )
	{
		mSolveTable.clear();

		Array<Complex> solutions = quadSolver.solve();

		for( int solution_i = 0 ; solution_i < solutions.size ; solution_i ++ )
		{
			final Table solutionTable = new Table();
			final Complex solution = solutions.get( solution_i );
            final QuadraticGraphInputData graphInputData = new QuadraticGraphInputData( quadSolver.getA(), quadSolver.getB(), quadSolver.getC(), solution );
			{
				final String sNumber = String.format("%1$d)", solution_i + 1 );
				final Label cLabel = new Label(sNumber, mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				cLabel.setStyle(titleStyle);
				solutionTable.add(cLabel);
			}

			{
				final String sSolution = String.format( "%1$f %2$s %3$f i", solution.re(), solution.im() < 0 ? "-" : "+", Math.abs( solution.im() ) );

				final Label cLabel = new Label( sSolution, mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				cLabel.setStyle(titleStyle);
				solutionTable.add(cLabel);
			}
			solutionTable.row();

			{
				final Button solveBtn = new TextButton( "3D Graph", mSkin );
				solveBtn.addListener( new ClickListener() {
					@Override
					public void clicked( InputEvent event, float x, float y )
					{
						super.clicked( event, x, y );

                        final QuadraticGraphInputData graphInputData = (QuadraticGraphInputData) solutionTable.getUserObject();

						final QuadraticEquationGraphScreen graphScreen = ( QuadraticEquationGraphScreen ) ScreensCache.getInstace().get( ScreensCache.eScreenType.QuadraticGraph );
						graphScreen.updateGraphInfo( graphInputData );
						ScreensStack.getInstance().push( graphScreen );
					}
				});
				solutionTable.add(  solveBtn ).expandY().fillX().height( Gdx.graphics.getHeight() * 0.05f ).align( Align.center );

			}
			solutionTable.setUserObject( graphInputData );
			mSolveTable.add( solutionTable );
			mSolveTable.row();


		}
	}

	@Override
	public boolean keyDown(int keycode)
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
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
