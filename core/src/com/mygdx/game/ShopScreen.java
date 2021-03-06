package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
//Description=======================================================================================
//Handles: The shop Screen's exit button and switching, rendering the screen, importing screen's
//textures

//Ref:https://code.google.com/p/libgdx-users/wiki/ScreenAndGameClasses
//==================================================================================================

public class ShopScreen implements Screen {

    MyGame game;
    Camera camera = new Camera();
    private Stage stage;
    private TextureAtlas atlasButton;
    private Skin skinButton;
    private TextButton.TextButtonStyle tbsX;
    private TextButton btnX;
    private BitmapFont fontButton;
    private SpriteBatch batch;
    private Sprite spScreenBackground;
    private Texture tScreenBackground;
    private float fBtnWidth2, fBtnHeight2;
    private float stageWidth, stageHeight;
    private Sound soundButton;


    public ShopScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        camera.camMain.update();
        batch.setProjectionMatrix(camera.camMain.combined);
        batch.begin();
        spScreenBackground.draw(batch);
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {//The create function, for for screens
        // called when this screen is set as the screen with game.setScreen();
        fBtnWidth2 = 189;
        fBtnHeight2 = 174;
        stageWidth = 1200;
        stageHeight = 800;
        camera.create();
        batch = new SpriteBatch();

        stage = new Stage(new StretchViewport(stageWidth, stageHeight, camera.camMain));
        Gdx.input.setInputProcessor(stage);//Setting the stage to the current InputProcessor
        soundButton = Gdx.audio.newSound(Gdx.files.internal("Sounds/buttonClick.wav"));

        skinButton = new Skin();
        skinButton.addRegions(atlasButton = new TextureAtlas("Buttons/MenuButtons.pack"));
        fontButton = new BitmapFont();

        tbsX = new TextButton.TextButtonStyle();//Allows setting of textures on button
        tbsX.up = skinButton.getDrawable("btnXUp");
        tbsX.down = skinButton.getDrawable("btnXDown");
        tbsX.font = fontButton;
        //Button Orientation========================================================================
        btnX = new TextButton("", tbsX);
        btnX.setWidth(fBtnWidth2);
        btnX.setHeight(fBtnHeight2);
        btnX.setPosition(stageWidth, stageHeight - fBtnHeight2);
        btnX.setTransform(true);
        btnX.setRotation(90);
        btnX.addListener(new InputListener() {
        //==========================================================================================
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
        stage.addActor(btnX);
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