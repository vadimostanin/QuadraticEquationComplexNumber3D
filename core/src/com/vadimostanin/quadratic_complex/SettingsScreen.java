package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen, InputProcessor
{
	private Stage mStage;
	private Table mTable;
	private Skin mSkin;
	private TextField mXMinText;
	private TextField mXMaxText;
	private TextField mXDeltaText;

	private TextField mImMinText;
	private TextField mImMaxText;
	private TextField mImDeltaText;

	public SettingsScreen()
	{
		mStage = new Stage( new FitViewport( 1024, 576 ) );
		mTable = new Table();
		String s = Gdx.files.getLocalStoragePath();

		mSkin = new Skin( ResourceManager.getInstance().get( Constants.Skin_Path ) );

		mTable.setSize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		mTable.setFillParent( true );
		mTable.setPosition( 0, 0 );



		mStage.addActor( mTable );
//		mStage.setDebugAll( true );
		mStage.getBatch().enableBlending();
		mStage.getBatch().setColor( 1.0f, 1.0f, 1.0f, 1.0f );

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
		createXSettings();
		createImSettings();
		createGraphPointsGeneratorSettings();
		createApplyButton();
	}

	private void createUITitle()
	{
		final String sTitle = "Settings";
		final BitmapFont font = FontCache.getInstance().get( 20 );

		Label title = new Label( sTitle, mSkin );
		final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
		title.setStyle( titleStyle );
		mTable.add( title ).colspan( 10 ).pad( 10 ).fillY().align( Align.top );
		mTable.row();
	}

	private void createXSettings()
	{
		final VerticalGroup verticalGroup = new VerticalGroup();
		{
			final HorizontalGroup horizGroup = new HorizontalGroup();
			{
				final Label xMaxLabel = new Label("Xmin: ", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				xMaxLabel.setStyle( titleStyle );

				horizGroup.addActor( xMaxLabel );
			}
			{
				final String sMin = String.format("%1$.2f", Settings.getInstance().getGraphXMin()).replace(',', '.');
				mXMinText = new TextField(sMin, mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mXMinText.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mXMinText.setStyle(titleStyle);
				mXMinText.setTextFieldFilter( new EquationTextFilter() );

				horizGroup.addActor( mXMinText );
			}
			verticalGroup.align( Align.right );
			verticalGroup.addActor( horizGroup );
		}
		{
			final HorizontalGroup horizGroup = new HorizontalGroup();
			{
				final Label xMinLabel = new Label("Xmax: ", mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				xMinLabel.setStyle(titleStyle);

				horizGroup.addActor( xMinLabel );
			}
			{
				final String sMax = String.format("%1$.2f", Settings.getInstance().getGraphXMax()).replace(',', '.');
				mXMaxText = new TextField(sMax, mSkin);
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mXMaxText.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mXMaxText.setStyle(titleStyle);
				mXMaxText.setTextFieldFilter(new EquationTextFilter());

				horizGroup.addActor( mXMaxText );
			}
			verticalGroup.align( Align.right );
			verticalGroup.addActor( horizGroup );
		}
		{
			final HorizontalGroup horizGroup = new HorizontalGroup();
			{
				final Label xDelta = new Label( "Xdelta: ", mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				xDelta.setStyle(titleStyle);

				horizGroup.addActor( xDelta );
			}
			{
				final String sDelta = String.format( "%1$.2f", Settings.getInstance().getGraphXDelta() ).replace( ',', '.' );
				mXDeltaText = new TextField( sDelta, mSkin );
				final BitmapFont font = FontCache.getInstance().get( 30 );
				final TextField.TextFieldStyle prevStyle = mXDeltaText.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mXDeltaText.setStyle(titleStyle);
				mXDeltaText.setTextFieldFilter( new EquationTextFilter() );

				horizGroup.addActor( mXDeltaText );
			}
			verticalGroup.align( Align.right );
			verticalGroup.addActor( horizGroup );
		}
		mTable.add( verticalGroup ).align( Align.center );
	}

	private void createImSettings()
	{
		final VerticalGroup verticalGroup = new VerticalGroup();
		{
			final HorizontalGroup horizGroup = new HorizontalGroup();
			{
				final Label xMaxLabel = new Label( "Imin: ", mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				xMaxLabel.setStyle(titleStyle);

				horizGroup.addActor( xMaxLabel );
			}
			{
				final String sMin = String.format( "%1$.2f", Settings.getInstance().getFullGraphImMin() ).replace(',', '.');
				mImMinText = new TextField( sMin, mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mImMinText.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mImMinText.setStyle(titleStyle);
				mImMinText.setTextFieldFilter( new EquationTextFilter() );

				horizGroup.addActor( mImMinText );
			}
			verticalGroup.align( Align.right );
			verticalGroup.addActor( horizGroup );
		}
		{
			final HorizontalGroup horizGroup = new HorizontalGroup();
			{
				final Label xMinLabel = new Label( "Imax: ", mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				xMinLabel.setStyle(titleStyle);

				horizGroup.addActor( xMinLabel );
			}
			{
				final String sMax = String.format( "%1$.2f", Settings.getInstance().getFullGraphImMax() ).replace(',', '.');
				mImMaxText = new TextField( sMax, mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mImMaxText.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mImMaxText.setStyle(titleStyle);
				mImMaxText.setTextFieldFilter( new EquationTextFilter() );

				horizGroup.addActor( mImMaxText );
			}
			verticalGroup.align( Align.right );
			verticalGroup.addActor( horizGroup );
		}
		{
			final HorizontalGroup horizGroup = new HorizontalGroup();
			{
				final Label xDelta = new Label( "Idelta: ", mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
				xDelta.setStyle(titleStyle);

				horizGroup.addActor( xDelta );
			}
			{
				final String sDelta = String.format( "%1$.2f", Settings.getInstance().getFullGraphImDelta() ).replace(',', '.');
				mImDeltaText = new TextField( sDelta, mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final TextField.TextFieldStyle prevStyle = mImDeltaText.getStyle();
				final TextField.TextFieldStyle titleStyle = new TextField.TextFieldStyle(font, Color.WHITE, prevStyle.cursor, prevStyle.selection, prevStyle.background);
				mImDeltaText.setStyle(titleStyle);
				mImDeltaText.setTextFieldFilter( new EquationTextFilter() );

				horizGroup.addActor( mImDeltaText );
			}
			verticalGroup.align( Align.right );
			verticalGroup.addActor( horizGroup );
		}
		mTable.add( verticalGroup ).align( Align.center );
	}

	private void createApplyButton()
	{
		final VerticalGroup verticalGroup = new VerticalGroup();
		{
			final TextButton solveBtn = new TextButton( "Apply", mSkin );
			final BitmapFont font = FontCache.getInstance().get( 30 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
			solveBtn.getLabel().setStyle( titleStyle );

			solveBtn.addListener( new ClickListener() {
				@Override
				public void clicked( InputEvent event, float x, float y )
				{
					super.clicked( event, x, y );
					float xMin = Settings.getInstance().getGraphXMin();
					float xMax = Settings.getInstance().getGraphXMax();
					float xDelta = Settings.getInstance().getGraphXDelta();
					float imMin = Settings.getInstance().getFullGraphImMin();
					float imMax = Settings.getInstance().getFullGraphImMax();
					float imDelta = Settings.getInstance().getFullGraphImDelta();
					try
					{
						xMin = Float.parseFloat( mXMinText.getText() );
						xMax = Float.parseFloat( mXMaxText.getText() );
						xDelta = Float.parseFloat( mXDeltaText.getText() );
						imMin = Float.parseFloat( mImMinText.getText());
						imMax = Float.parseFloat( mImMaxText.getText() );
						imDelta = Float.parseFloat( mImDeltaText.getText() );
					}
					catch( Exception e )
					{
						MessageSenderStrategy.getInstance().send( "Incorrect input data" );
					}
					Settings.getInstance().setGraphXMin( xMin );
					Settings.getInstance().setGraphXMax( xMax );
					Settings.getInstance().setGraphXDelta( xDelta );
					Settings.getInstance().setFullGraphImMin( imMin );
					Settings.getInstance().setFullGraphImMax( imMax );
					Settings.getInstance().setFullGraphImDelta( imDelta );
				}
			});
			mTable.add( verticalGroup ).align( Align.center );
			mTable.add(  solveBtn ).colspan( 2 ).width( Gdx.graphics.getWidth() * 0.1f ).height( Gdx.graphics.getHeight() * 0.05f ).align( Align.left | Align.center );
		}
	}

	private void createGraphPointsGeneratorSettings()
	{
		final VerticalGroup verticalGroup = new VerticalGroup();
		{
			final Label xDelta = new Label( "Graph type: ", mSkin );
			final BitmapFont font = FontCache.getInstance().get( 30 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
			xDelta.setStyle(titleStyle);

			verticalGroup.addActor( xDelta );
		}
		final ButtonGroup<ImageButton> buttonGroup = new ButtonGroup();
		{
			final HorizontalGroup horizontalGroup = new HorizontalGroup();
			{
				final Label xDelta = new Label( "X∠I", mSkin );
				final BitmapFont font = FontCache.getInstance().get(30);
				final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
				xDelta.setStyle(titleStyle);
				horizontalGroup.addActor( xDelta );
			}
			final ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

			final TextureAtlas atlas = new TextureAtlas( ResourceManager.getInstance().get( "slope_gen.atlas" ) );

			mSkin.addRegions( atlas );
			style.imageUp = mSkin.getDrawable( "slope_up" );
			style.imageDown = mSkin.getDrawable( "slope_down" );
			style.checked = mSkin.getDrawable( "slope_down" );
			final ImageButton imageButton = new ImageButton( style );
			imageButton.addListener( new ClickListener()
									 {
										 public void clicked(InputEvent event, float x, float y )
										 {
											 Settings.getInstance().setGraphPointsGeneratorType( eGraphPointsGeneratorType.QuadraticImSlopeX );
											 System.gc();
										 }
									 }
			);
			imageButton.setChecked( Settings.getInstance().getGraphPointsGeneratorType() == eGraphPointsGeneratorType.QuadraticImSlopeX );
			buttonGroup.add( imageButton );
			horizontalGroup.addActor( imageButton );
			verticalGroup.addActor( horizontalGroup );
		}
		{
			final HorizontalGroup horizontalGroup = new HorizontalGroup();
			{
				final Label xDelta = new Label( "X||I", mSkin );
				final BitmapFont font = FontCache.getInstance().get( 30 );
				final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
				xDelta.setStyle(titleStyle);
				horizontalGroup.addActor( xDelta );
			}
			final ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

			final TextureAtlas atlas = new TextureAtlas( ResourceManager.getInstance().get( "parallel_gen.atlas" ) );

			mSkin.addRegions( atlas );
			style.imageUp = mSkin.getDrawable( "parallel_up" );
			style.imageDown = mSkin.getDrawable( "parallel_down" );
			style.checked = mSkin.getDrawable( "parallel_down" );
			final ImageButton imageButton = new ImageButton( style );
			imageButton.addListener( new ClickListener()
									 {
										 public void clicked(InputEvent event, float x, float y )
										 {
											 Settings.getInstance().setGraphPointsGeneratorType( eGraphPointsGeneratorType.CosImSlopeX );
											 System.gc();
										 }
									 }
			);
			imageButton.setChecked( Settings.getInstance().getGraphPointsGeneratorType() == eGraphPointsGeneratorType.CosImSlopeX );
			buttonGroup.add( imageButton );
			horizontalGroup.addActor( imageButton );
//			verticalGroup.addActor( horizontalGroup );
		}

		verticalGroup.align( Align.right );
		mTable.add( verticalGroup ).align( Align.center );
	}

	@Override
	public void show()
	{
		InputMultiplexerManager.getInstance().add( mStage );
		InputMultiplexerManager.getInstance().add( this );
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor( 0.9f, 0.9f, 0.9f, 1.0f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		mStage.act();
		mStage.draw();
	}

	@Override
	public void hide()
	{
		InputMultiplexerManager.getInstance().remove( mStage );
		InputMultiplexerManager.getInstance().remove( this );
	}

	@Override
	public void dispose(){}

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
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume(){}

	@Override
	public boolean keyUp(int keycode) {return false;}

	@Override
	public boolean keyTyped(char character) {return false;}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {return false;}

	@Override
	public boolean scrolled(int amount) {return false;}

}
