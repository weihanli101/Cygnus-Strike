package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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


public class MainMenuScreen implements Screen {

    MyGame game;
    Camera camera = new Camera();
    SpriteBatch batch;
    Texture tMenuBackground;
    Sprite spMenuBackground;
    private Stage stage;
    private TextureAtlas atlasButton;
    private Skin skinButton;
    private TextButton.TextButtonStyle tbsPlay, tbsProfile, tbsOption, tbsHelp, tbsX, tbsShop;
    private TextButton btnPlay, btnProfile, btnOption, btnHelp, btnX, btnShop;
    private BitmapFont fontButton;
    private float fBtnWidth1, fBtnHeight1, fBtnWidth2, fBtnHeight2;
    private Table table;
    float stageWidth, stageHeight;
    private Sound soundButton;
    private Music musicMainMenu;
    Boolean bMusic = false;

    // constructor to keep a reference to the main Game class
    public MainMenuScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // update and draw stuff

        camera.camMain.update();
        batch.setProjectionMatrix(camera.camMain.combined);

        batch.begin();
        spMenuBackground.draw(batch);
        batch.end();
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();

        soundButton = Gdx.audio.newSound(Gdx.files.internal("Sounds/buttonClick.wav"));
        musicMainMenu = Gdx.audio.newMusic(Gdx.files.internal("Sounds/MainMenuMusic.mp3"));
        musicMainMenu.setVolume(20);
        musicMainMenu.setLooping(true);

        if (!bMusic){
            musicMainMenu.play();
            bMusic= true;
        }
        batch = new SpriteBatch();
        stageWidth = 1200;
        stageHeight = 800;
        camera.create();
        stage = new Stage(new StretchViewport(stageWidth, stageHeight, camera.camMain));
        Gdx.input.setInputProcessor(stage);

        atlasButton = new TextureAtlas("Buttons/Buttons.pack");
        skinButton = new Skin();
        skinButton.addRegions(atlasButton);
        fontButton = new BitmapFont();
        fBtnWidth1 = 495;//495
        fBtnHeight1 = 173;//173
        fBtnWidth2 = 189;//189
        fBtnHeight2 = 174;//174

        tbsPlay = new TextButton.TextButtonStyle();
        tbsPlay.up = skinButton.getDrawable("btnPlayUp");
        tbsPlay.down = skinButton.getDrawable("btnPlayDown");
        tbsPlay.font = fontButton;

        btnPlay = new TextButton("", tbsPlay);
        btnPlay.setWidth(fBtnWidth1);
        btnPlay.setHeight(fBtnHeight1);
        btnPlay.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bMusic = false;
                soundButton.play();
                musicMainMenu.pause();
                game.setScreen(game.playScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        tbsProfile = new TextButton.TextButtonStyle();
        tbsProfile.up = skinButton.getDrawable("btnProfileUp");
        tbsProfile.down = skinButton.getDrawable("btnProfileDown");
        tbsProfile.font = fontButton;

        btnProfile = new TextButton("", tbsProfile);
        btnProfile.setWidth(fBtnWidth1);
        btnProfile.setHeight(fBtnHeight1);
        btnProfile.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                game.setScreen(game.profileScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        tbsOption = new TextButton.TextButtonStyle();
        tbsOption.up = skinButton.getDrawable("btnOptionUp");
        tbsOption.down = skinButton.getDrawable("btnOptionDown");
        tbsOption.font = fontButton;

        btnOption = new TextButton("", tbsOption);
        btnOption.setWidth(fBtnWidth1);
        btnOption.setHeight(fBtnHeight1);
        btnOption.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                game.setScreen(game.optionScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        tbsHelp = new TextButton.TextButtonStyle();
        tbsHelp.up = skinButton.getDrawable("btnHelpUp");
        tbsHelp.down = skinButton.getDrawable("btnHelpDown");
        tbsHelp.font = fontButton;

        btnHelp = new TextButton("", tbsHelp);
        btnHelp.setWidth(fBtnWidth1);
        btnHelp.setHeight(fBtnHeight1);
        btnHelp.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                game.setScreen(game.helpScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        tbsX = new TextButton.TextButtonStyle();
        tbsX.up = skinButton.getDrawable("btnXUp");
        tbsX.down = skinButton.getDrawable("btnXDown");
        tbsX.font = fontButton;

        btnX = new TextButton("", tbsX);
        btnX.setWidth(fBtnWidth2);
        btnX.setHeight(fBtnHeight2);
        btnX.setPosition(stageWidth, stageHeight - fBtnHeight2);
        btnX.setTransform(true);
        btnX.setRotation(90);
        btnX.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                Gdx.app.exit();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        tbsShop = new TextButton.TextButtonStyle();
        tbsShop.up = skinButton.getDrawable("btnShopUp");
        tbsShop.down = skinButton.getDrawable("btnShopDown");
        tbsShop.font = fontButton;

        btnShop = new TextButton("", tbsShop);
        btnShop.setWidth(fBtnWidth2);
        btnShop.setHeight(fBtnHeight2);
        btnShop.setPosition(stageWidth, 0);
        btnShop.setTransform(true);
        btnShop.setRotation(90);
        btnShop.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundButton.play();
                game.setScreen(game.shopScreen);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        spMenuBackground = new Sprite(tMenuBackground = new Texture(Gdx.files.internal("Screens/ScreenMainMenu.png")));
        spMenuBackground.setSize(1200, 800);
        spMenuBackground.rotate90(false);

        table = new Table();
        table.add(btnPlay).width(fBtnWidth1).height(fBtnHeight1);
        table.row();
        table.add(btnProfile).width(fBtnWidth1).height(fBtnHeight1);
        table.row();
        table.add(btnOption).width(fBtnWidth1).height(fBtnHeight1);
        table.row();
        table.add(btnHelp).width(fBtnWidth1).height(fBtnHeight1);
        table.setTransform(true);
        table.setRotation(90);
        table.setPosition(stageWidth/2 + fBtnHeight1 - 50, stageHeight/2);//y, x 163

        stage.addActor(table);
        stage.addActor(btnX);
        stage.addActor(btnShop);


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