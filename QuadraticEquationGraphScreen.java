package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class QuadraticEquationGraphScreen implements Screen, InputProcessor
{

	private PerspectiveCamera camera;
	private CameraInputController cameraInput;
	private ShapeRenderer renderer;

	private Stage mStage;
	private Table mTable;
	private Skin mSkin;

	private Slider mSliderPointX;
	private float mSlidedPointX = 0.0f;
	private Slider mSliderOneGraphIm;
	private Slider mSliderOneGraphXSlopeIm;
	private float mSlidedOneGraphIm = 0.0f;
	private float mSlidedOneGraphXSlopeIm = 0.0f;
	private Label mPointCoordinatesLabel;
	private Label mPointXValueLabel;
	private Label mPointYValueLabel;
	private Label mSlopeXILabel;

	private QuadraticGraphInputData mGraphInputData;
	private float mXSlope;

	private GraphScreenUICreator mUiCreator;
	private QuadraticSolver mQuadraticSolver;
	private Matrix4 mRendererInitialMatrix = new Matrix4();

	private Array<IDrawShapeRenderer> mDrawers = new Array<IDrawShapeRenderer>();
	private GraphPointsManager mGraphPointsGeneratorManager;
	private GraphDrawer mGraphDrawer;
	private Vector3 mTempVector = new Vector3();
	private PropertyChangeListeners mComplexSlopeListeners = new PropertyChangeListeners();
	private ComplexSlopeChangeProperty mComplexSlopeChangeProperty = new ComplexSlopeChangeProperty();

	public QuadraticEquationGraphScreen()
	{
		renderer = new ShapeRenderer();
		mRendererInitialMatrix.set( renderer.getProjectionMatrix() );
		allocateCamera();
		initGUI();

		mDrawers.add( new AxisDrawer() );
		final QuazyXAxisDrawer drawer = new QuazyXAxisDrawer();
		mComplexSlopeListeners.add( drawer );
		mDrawers.add( drawer );
		mDrawers.add( new GridDrawer() );
		mGraphDrawer = new GraphDrawer();
		mDrawers.add( mGraphDrawer );

		batch = new SpriteBatch();
		font = FontCache.getInstance().get( 30 );
		font.setColor(Color.RED);
	}

	public void updateGraphInfo( QuadraticGraphInputData graphInputData )
	{
		mGraphInputData = graphInputData;
		mQuadraticSolver = new QuadraticSolver( mGraphInputData.getA(), mGraphInputData.getB(), mGraphInputData.getC() );
		mGraphPointsGeneratorManager = new GraphPointsManager( mGraphInputData.getA(), mGraphInputData.getB(), mGraphInputData.getC(), (float)mGraphInputData.getRoot().re(), (float)mGraphInputData.getRoot().im() );

		mSlidedPointX = ( float )mGraphInputData.getRoot().re();
		mXSlope = ( float )( mGraphInputData.getRoot().re() / mGraphInputData.getRoot().im() );

		mSlidedOneGraphIm = ( float ) mGraphInputData.getRoot().im();

		initCamera();
		clearGUI();
		fillGUI();
	}
	
	private void allocateCamera()
	{
		camera = new PerspectiveCamera( 67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		cameraInput = new CameraInputController( camera );
	}
	
	private void initCamera()
	{
		camera = new PerspectiveCamera( 67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		camera.invProjectionView.idt();
		camera.view.idt();
		camera.projection.idt();
		camera.position.set( 0.0f, 0.0f, 15.0f );
		camera.lookAt( 0.0f, 0.0f, 0.0f );
		camera.near = 0.0f;
		camera.far = 3000f;
		camera.update();
		
		cameraInput.camera = camera;
		cameraInput.reset();
		cameraInput.update();
	}

	private void createUiFullGraph( Table container )
	{
		ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

		TextureAtlas atlas = new TextureAtlas( ResourceManager.getInstance().get( "full_graph.atlas" ) );
		mSkin.addRegions( atlas );
		style.imageUp = mSkin.getDrawable( "up" );
		style.imageDown = mSkin.getDrawable( "down" );
		final ImageButton imageButton = new ImageButton( style );
		imageButton.addListener( new ClickListener()
								 {
									 public void clicked(InputEvent event, float x, float y )
									 {
										 mGraphPointsGeneratorManager.getGenerator().generateRange();
                                         if( Settings.getInstance().getGraphScreenState() == eGraphScreenState.eShowOneGraph )
                                         {
                                            Settings.getInstance().setGraphScreenState( eGraphScreenState.eShowGraphsRange );
                                         }
                                         else if( Settings.getInstance().getGraphScreenState() == eGraphScreenState.eShowGraphsRange )
                                         {
                                             Settings.getInstance().setGraphScreenState( eGraphScreenState.eShowOneGraph );
                                         }
										 System.gc();
									 }
								 }
		);

		container.add( imageButton ).align( Align.bottomLeft );
	}

	private void createUIXPointSlider()
	{
		final Table sliderTable = new Table();
		sliderTable.setFillParent( true );
		sliderTable.align( Align.left );
		{
			Label sliderTitle = new Label( "X", mSkin );
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			sliderTitle.setStyle(titleStyle);

			sliderTable.add( sliderTitle ).align( Align.left );
			sliderTable.row();

			mSliderPointX = new Slider( Settings.getInstance().getGraphXMin(), Settings.getInstance().getGraphXMax(), Settings.getInstance().getGraphXDelta(), true, mSkin );

			mSliderPointX.setValue( mSlidedPointX );
			mSliderPointX.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor)
				{
					Slider slider = (Slider) actor;

					final float x_value = slider.getValue();
					mSlidedPointX = x_value;
					final float im = MyUtils.getIAxisDisplacement( mSlidedOneGraphXSlopeIm, mSlidedPointX );
					Complex yComplex = mQuadraticSolver.getY( mSlidedPointX, im );
//					mGraphPointsGeneratorManager.getGenerator().getPoint( mSlidedPointX, mSlidedOneGraphIm, mTempVector );

					final String sCoords = String.format( "[%1$.2f x, %2$.2f y, %3$.2f i]",
							mSlidedPointX, yComplex.re(), im + yComplex.im() );
					mPointCoordinatesLabel.setText( sCoords );

					final CharSequence sX = String.format( "X = %1$.2f %2$s %3$.2f i", mSlidedPointX, im < 0 ? "-" : "+", Math.abs( im ) );
					mPointXValueLabel.setText( sX );

					final CharSequence sY = String.format( "Y = %1$.2f %2$s %3$.2f i", yComplex.re(), yComplex.im() < 0 ? "-" : "+", Math.abs( yComplex.im() ) );
					mPointYValueLabel.setText( sY );
				}
			});
			sliderTable.add( mSliderPointX ).width( Gdx.graphics.getWidth() * 0.1f ).height( Gdx.graphics.getHeight() * 0.4f ).align( Align.left );
		}
		sliderTable.row();
		{
			Label sliderTitle = new Label( "X\u2220I slope", mSkin );
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			sliderTitle.setStyle(titleStyle);

			sliderTable.add( sliderTitle ).align( Align.left );
			sliderTable.row();

			mSliderOneGraphXSlopeIm = new Slider( 0.0f, 360.0f, 0.1f, true, mSkin );
//			mSliderOneGraphXSlopeIm = new Slider( 89.999f, 90.001f, 0.00001f, true, mSkin );

			final float slope = MyUtils.recalcSlopeAngle( ( float ) mGraphInputData.getRoot().re(), ( float ) mGraphInputData.getRoot().im() );
			mSliderOneGraphXSlopeIm.setValue( slope );
			mComplexSlopeChangeProperty.set( slope );
			mComplexSlopeListeners.onChanged( mComplexSlopeChangeProperty );
			mSliderOneGraphXSlopeIm.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor)
				{
					Slider slider = ( Slider ) actor;

					final float slopeValue = slider.getValue();
					mSlidedOneGraphXSlopeIm = slopeValue;

					mGraphPointsGeneratorManager.setSlope( mSlidedOneGraphXSlopeIm );
					mGraphPointsGeneratorManager.getGenerator().generateOne();

					final float im = MyUtils.getIAxisDisplacement( mSlidedOneGraphXSlopeIm, mSlidedPointX );
					Complex yComplex = mQuadraticSolver.getY( mSlidedPointX, im );
//					mSlidedPointX = 0.000001f;

					mGraphPointsGeneratorManager.getGenerator().getPoint( mSlidedPointX, 0.0f, mTempVector );

					final CharSequence sX = String.format( "X = %1$.2f %2$s %3$.2f i", mSlidedPointX, im < 0 ? "-" : "+", Math.abs( im ) );
					mPointXValueLabel.setText( sX );

					final CharSequence sY = String.format( "Y = %1$.2f %2$s %3$.2f i", yComplex.re(), yComplex.im() < 0 ? "-" : "+", Math.abs( yComplex.im() ) );
					mPointYValueLabel.setText( sY );

					final String sCoords = String.format( "[%1$.2f x, %2$.2f y, %3$.2f i]",
							mSlidedPointX, yComplex.re(), im + yComplex.im() );
					mPointCoordinatesLabel.setText( sCoords );

					final String sLabel = String.format( "X%1$sI = %2$.1f%3$s", Constants.SymbolAngle, slopeValue, Constants.SymbolDegree );
					mSlopeXILabel.setText( sLabel );

					mComplexSlopeChangeProperty.set( slopeValue );
					mComplexSlopeListeners.onChanged( mComplexSlopeChangeProperty );
				}
			});
			sliderTable.add( mSliderOneGraphXSlopeIm ).width( Gdx.graphics.getWidth() * 0.1f ).height( Gdx.graphics.getHeight() * 0.4f ).align( Align.left );
		}
		createUiFullGraph( sliderTable );
		mUiCreator.createUiSettingIcon( sliderTable );
		mTable.add( sliderTable ).expand().align( Align.top | Align.left );
	}

	private void createGraphStatistic()
	{
		final Table legendTable = new Table();
		legendTable.setFillParent(true);
//		legendTable.setPosition( Gdx.graphics.getWidth() * 0.8f, 100);
		legendTable.align(Align.top | Align.right);
		{
			final Label legendLabelX = new Label("X", mSkin);
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			legendLabelX.setStyle(titleStyle);

			legendTable.add( legendLabelX ).align( Align.right );
		}
		legendTable.row();
		{
			final Label legendLabelX = new Label("Y", mSkin);
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			legendLabelX.setStyle(titleStyle);

			legendTable.add( legendLabelX ).align( Align.right );
		}
		legendTable.row();
		{
			final Label legendLabelX = new Label( "I", mSkin );
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			legendLabelX.setStyle(titleStyle);

			legendTable.add( legendLabelX ).align( Align.right );
		}
		legendTable.row();
		{
			final float im = (float)mGraphInputData.getRoot().im();
			final CharSequence sX = String.format( "X = %1$.2f %2$s %3$.2f i", mSlidedPointX, im < 0 ? "-" : "+", Math.abs( im ) );
			mPointXValueLabel = new Label( sX, mSkin );
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			mPointXValueLabel.setStyle(titleStyle);

			legendTable.add( mPointXValueLabel ).align( Align.right );
		}
		legendTable.row();
		{
			final float im = (float)mGraphInputData.getRoot().im();
			final Complex cY = mQuadraticSolver.getY( mSlidedPointX, im );
			final CharSequence sY = String.format( "Y = %1$.2f %2$s %3$.2f i", cY.re(), cY.im() < 0 ? "-" : "+", Math.abs( cY.im() ) );
			mPointYValueLabel = new Label( sY, mSkin );
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.BLACK);
			mPointYValueLabel.setStyle(titleStyle);

			legendTable.add( mPointYValueLabel ).align( Align.right );
		}
		legendTable.row();
		{
			final float im = (float)mGraphInputData.getRoot().im();
			final Complex cY = mQuadraticSolver.getY( mSlidedPointX, im );
			final CharSequence sCoords = String.format( "[%1$.2f x, %2$.2f y, %3$.2f i]", mSlidedPointX, cY.re(), im + cY.im() );
			mPointCoordinatesLabel = new Label(sCoords, mSkin);
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
			mPointCoordinatesLabel.setStyle(titleStyle);

			legendTable.add( mPointCoordinatesLabel ).align( Align.right );
		}
		legendTable.row();
		{
			final float slope = MyUtils.recalcSlopeAngle( ( float ) mGraphInputData.getRoot().re(), ( float ) mGraphInputData.getRoot().im() );
			final CharSequence sLabel = String.format( "X%1$sI = %2$.1f%3$s", Constants.SymbolAngle, slope, Constants.SymbolDegree );
			mSlopeXILabel = new Label( sLabel, mSkin );
			final BitmapFont font = FontCache.getInstance().get( 20 );
			final Label.LabelStyle titleStyle = new Label.LabelStyle( font, Color.BLACK );
			mSlopeXILabel.setStyle(titleStyle);

			legendTable.add( mSlopeXILabel ).align( Align.right );
		}

		mTable.row();
		mTable.addActor( legendTable );//.align( Align.topRight );
	}

	private void clearGUI()
	{
		mTable.clear();
	}

	private void fillGUI()
	{
		createGraphStatistic();
		createUIXPointSlider();
	}

	private void initGUI()
	{
		mStage = new Stage();

		mSkin = new Skin( ResourceManager.getInstance().get( Constants.Skin_Path ) );
		mSkin.addRegions( new TextureAtlas( ResourceManager.getInstance().get( Constants.Atlas_Path ) ) );

		mTable = new Table( mSkin );
		mTable.setSize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		mTable.setFillParent( true );

		mStage.addActor( mTable );

//		mStage.setDebugAll( true );
		mStage.getBatch().enableBlending();
		mStage.getBatch().setColor( 1.0f, 1.0f, 1.0f, 1.0f );

		mUiCreator = new GraphScreenUICreator( mTable );

	}

	private void drawGraphLegend()
	{
		Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Axis );
		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.RED);
		renderer.line( Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.96f, Gdx.graphics.getWidth() * 0.95f, Gdx.graphics.getHeight() * 0.96f );
		renderer.end();

		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.GREEN);
		renderer.line( Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.90f, Gdx.graphics.getWidth() * 0.95f, Gdx.graphics.getHeight() * 0.90f );
		renderer.end();

		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.BLUE);
		renderer.line( Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.84f, Gdx.graphics.getWidth() * 0.95f, Gdx.graphics.getHeight() * 0.84f );
		renderer.end();
		Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Unused );
	}

	@Override
	public void show()
	{
		InputMultiplexerManager.getInstance().add( mStage );
		InputMultiplexerManager.getInstance().add( this );
		InputMultiplexerManager.getInstance().add( cameraInput );

        switch( Settings.getInstance().getGraphScreenState() )
        {
            case eShowOneGraph:
                    mGraphPointsGeneratorManager.getGenerator().generateOne();
                break;
            case eShowGraphsRange:
                    mGraphPointsGeneratorManager.getGenerator().generateRange();
                break;
        }
	}

	@Override
	public void hide()
	{
		InputMultiplexerManager.getInstance().remove( this );
		InputMultiplexerManager.getInstance().remove( cameraInput );
		InputMultiplexerManager.getInstance().remove( mStage );
	}

	private Vector3 mVectorPoint = new Vector3();
	private BitmapFont font;
	private SpriteBatch batch;
	private Vector3 mLastVectorPoint = new Vector3();

	@Override
	public void render( float delta )
	{
		Gdx.gl.glClearColor( 1, 1, 1, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
//		Gdx.gl.glBlendFunc( GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA );

		renderer.setProjectionMatrix( camera.combined );
		renderer.begin(ShapeType.Filled);
		renderer.setColor( Color.BLUE );
//		final float xSlope = ( float )( mGraphInputData.getRoot().re() / mGraphInputData.getRoot().im() );
//		final Complex yComplex = mQuadraticSolver.getY( mXPoint, (float)( mGraphInputData.getRoot().im() ) );
		final float Im = mSlidedPointX / mXSlope;
		final Complex yComplex = mQuadraticSolver.getY( mSlidedPointX, Im );
		final float boxDimension = 0.05f;
		// renderer.box( mXPoint, (float)( yComplex.re() ), (float)( yComplex.im() + mGraphInputData.getRoot().im() ), boxDimension, boxDimension, boxDimension );
//		renderer.box( mXPoint, (float)( yComplex.re() ), (float)( yComplex.im() + Im ), boxDimension, boxDimension, boxDimension );


		mGraphPointsGeneratorManager.getGenerator().getPoint( mSlidedPointX, ( float ) mGraphInputData.getRoot().im(), mVectorPoint );


		renderer.box( mVectorPoint.x, mVectorPoint.y, mVectorPoint.z, boxDimension, boxDimension, boxDimension );

		renderer.end();

		mGraphDrawer.setGraphInfoIterable( mGraphPointsGeneratorManager.getGenerator() );
		for( IDrawShapeRenderer drawer : mDrawers )
		{
			drawer.draw( renderer );
		}

		renderer.setProjectionMatrix( mRendererInitialMatrix );
		drawGraphLegend();

//		batch.setProjectionMatrix( camera.combined );
//		batch.begin();
//		font.draw(batch, "Hello World", 0, 0);
//		batch.end();

		mStage.act();
		mStage.draw();

		camera.update(true);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

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
