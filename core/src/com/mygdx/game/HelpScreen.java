package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class HelpScreen implements Screen {

    MyGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private TextureAtlas atlasButton;
    private Skin skinButton;
    private TextButton.TextButtonStyle tbsExit;
    private TextButton btnExit;
    private BitmapFont fontButton;
    private SpriteBatch batch;
    private Sprite spScreenBackground;
    private Texture tScreenBackground;
    private float fBtnWidth2, fBtnHeight2;
    private float stageWidth, stageHeight;
    private Sound soundButton;


    public HelpScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        spScreenBackground.draw(batch);
        batch.end();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();

        fBtnWidth2 = 189;//189
        fBtnHeight2 = 174;//174
        stageWidth = 1200;
        stageHeight = 800;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(stageWidth, stageHeight, camera));
        Gdx.input.setInputProcessor(stage);
        soundButton = Gdx.audio.newSound(Gdx.files.internal("Sounds/buttonClick.wav"));

        skinButton = new Skin();
        skinButton.addRegions(atlasButton = new TextureAtlas("Buttons/MenuButtons.pack"));
        fontButton = new BitmapFont();

        tbsExit = new TextButton.TextButtonStyle();
        tbsExit.up = skinButton.getDrawable("btnExitUp");
        tbsExit.down = skinButton.getDrawable("btnExitDown");
        tbsExit.font = fontButton;

        btnExit = new TextButton("", tbsExit);
        btnExit.setWidth(fBtnWidth2);
        btnExit.setHeight(fBtnHeight2);
        btnExit.setPosition(stageWidth, stageHeight - fBtnHeight2);
        btnExit.setTransform(true);
        btnExit.setRotation(90);
        btnExit.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                game.setScreen(game.mainMenuScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        stage.addActor(btnExit);


        spScreenBackground = new Sprite(tScreenBackground = new Texture(Gdx.files.internal("Screens/ScreenBackground.jpg")));
        spScreenBackground.setSize(1200, 800);
        spScreenBackground.rotate90(false);

    }


    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
    }


    @Override
    public void pause() {
    }


    @Override
    public void resume() {
    }


    @Override
    public void dispose() {
        // never called automatically
    }
}