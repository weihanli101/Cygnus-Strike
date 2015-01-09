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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameOverScreen implements Screen {

    MyGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private TextureAtlas atlasButton;
    private Skin skinButton;
    private TextButton.TextButtonStyle tbsExit, tbsRetry;
    private TextButton btnExit, btnRetry;
    private BitmapFont fontButton;
    private SpriteBatch batch;
    private Sprite spScreenBackground;
    private Texture tScreenBackground;
    private float fBtnWidth1, fBtnHeight1;
    private float stageWidth, stageHeight;
    private Sound soundButton;
    private Table table;

    public GameOverScreen(MyGame game){
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

        fBtnWidth1 = 495;//495
        fBtnHeight1 = 173;//173
        stageWidth = 1200;
        stageHeight = 800;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(stageWidth, stageHeight, camera));
        Gdx.input.setInputProcessor(stage);
        soundButton = Gdx.audio.newSound(Gdx.files.internal("Sounds/buttonClick.wav"));

        skinButton = new Skin();
        skinButton.addRegions(atlasButton = new TextureAtlas("Buttons/OtherButtons.pack"));
        fontButton = new BitmapFont();

        tbsRetry = new TextButton.TextButtonStyle();
        tbsRetry.up = skinButton.getDrawable("btnRetryUp");
        tbsRetry.down = skinButton.getDrawable("btnRetryDown");
        tbsRetry.font = fontButton;
        btnRetry = new TextButton("", tbsRetry);
        btnRetry.setWidth(fBtnWidth1);
        btnRetry.setHeight(fBtnHeight1);
        btnRetry.setPosition(stageWidth/2 + fBtnHeight1 - 50, stageHeight/2);
        btnRetry.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                game.setScreen(game.playScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        tbsExit = new TextButton.TextButtonStyle();
        tbsExit.up = skinButton.getDrawable("btnExitUp");
        tbsExit.down = skinButton.getDrawable("btnExitDown");
        tbsExit.font = fontButton;
        btnExit = new TextButton("", tbsExit);
        btnExit.setWidth(fBtnWidth1);
        btnExit.setHeight(fBtnHeight1);
        btnExit.setPosition(stageWidth/2 + fBtnHeight1 - 50, stageHeight/2);
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

        table = new Table();
        table.add(btnRetry).width(fBtnWidth1).height(fBtnHeight1);
        table.row();
        table.add(btnExit).width(fBtnWidth1).height(fBtnHeight1);
        table.row();
        table.setTransform(true);
        table.setRotation(90);
        table.setPosition(stageWidth/2 + fBtnHeight1 - 150, stageHeight/2);
        stage.addActor(table);
        spScreenBackground = new Sprite(tScreenBackground = new Texture(Gdx.files.internal("Screens/ScreenGameOver.jpg")));
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