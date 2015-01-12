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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Iterator;

public class PlayScreen implements Screen {

    MyGame game;
    Stage stage;
    Camera camera = new Camera();
    Alien alien;
    Background background;
    Spaceship spaceship;
    Laser laser;
    Powerups powerups;
    SpriteBatch batch;
    Sound soundLaser;
    Array<Alien> ArAlien;
    Array<Laser> ArLaser;
    Array<Powerups> ArPowerups;
    BitmapFont fontScore;
    BitmapFont fontTime;
    BitmapFont fontStock;

    long lLastSpawnTime;
    long lLastSpawnTimeLaser;
    long lLastSpawnTimePowerUps;
    long lPowerUpTime;
    float fGameTime = 180f;
    public static int nScore = 0;
    int nStock = 3;
    float stageWidth, stageHeight, fBtnWidth2, fBtnHeight2;

    private TextureAtlas atlasButton;
    private Skin skinButton;
    private TextButton.TextButtonStyle tbsShoot;
    private TextButton btnShoot;
    private BitmapFont fontButton;
    private Matrix4 mx4Font = new Matrix4();
    private Sprite spHeart;
    private Texture imgHeart;

    private Music musicPlay;

    // constructor to keep a reference to the main Game class
    public PlayScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        camera.camMain.update();
        background.render();
        batch.setProjectionMatrix(camera.camMain.combined);
        mx4Font.setToRotation(new Vector3(1, 1, 0), -180);
        batch.setTransformMatrix(mx4Font);
        batch.begin();
        fontScore.draw(batch, "Score: " + nScore, 600, 0);
        fontTime.draw(batch,"Time: "+ MathUtils.round(fGameTime),350, 0);
        fontStock.draw(batch,"X " + nStock,100, 0);
        spHeart.draw(batch);
        batch.end();
        spaceship.spaceshipRender();
        spaceship.spaceshipUpdate();
        stage.draw();

        if(TimeUtils.millis() - lLastSpawnTime > 1000) SpawnAlien();
        if(TimeUtils.millis() -lLastSpawnTimePowerUps>10000)SpawnPowerups();
        fGameTime -= Gdx.graphics.getDeltaTime();

        if(MathUtils.round(fGameTime) == 0 ){//Ends game when time runs out
            nStock = 3;
            fGameTime = 180;
            //nScore = 0;
            musicPlay.stop();
            game.setScreen(game.gameOverScreen);
        }

        Iterator<Alien> iter = ArAlien.iterator();
        while(iter.hasNext()) {
            Alien alien1 = iter.next();
            alien1.renderAlien(camera.camMain);
            alien1.updateAlien();
            if(alien1.updateAlien() == true||alien1.getRectAlien().overlaps(laser.getRectLaser())||alien1.getRectAlien().overlaps(spaceship.getRectShip())){
                if(alien1.getRectAlien().overlaps(laser.getRectLaser())){
                    nScore++;
                }
                if(alien1.getRectAlien().overlaps(spaceship.getRectShip())){
                    nStock --;
                    if(nStock == 0){
                        musicPlay.stop();
                        nStock = 3;
                        fGameTime = 180;
                        //nScore = 0;
                        game.setScreen(game.gameOverScreen);
                    }
                }
                if(alien1.updateAlien() == true){
                    fGameTime -= 5.0f;
                }
                iter.remove();
            }
        }
        Iterator<Laser> iterLaser = ArLaser.iterator();
        while(iterLaser.hasNext()) {
            Laser laser1 = iterLaser.next();
            laser1.renderLaser(camera.camMain);
            laser1.updateLaser();
            if(laser1.updateLaser() == true){
                iterLaser.remove();
            }
        }
        Iterator<Powerups> iterPower = ArPowerups.iterator();
        while(iterPower.hasNext()){
            Powerups powerups1 = iterPower.next();
            powerups1.powerupsRender(camera.camMain);
            powerups1.powerupsUpdate();
            if(powerups1.powerupsUpdate() == true||laser.getRectLaser().overlaps(powerups1.getPowerupsRect())){
                if(laser.getRectLaser().overlaps(powerups1.getPowerupsRect())){
                    fGameTime += 10.0f;
                    lPowerUpTime = TimeUtils.millis();
                }
                iterPower.remove();
            }
        }
        spaceship.HandleTouch();
    }



    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();
        camera.create();
        batch = new SpriteBatch();
        fBtnWidth2 = 189;//189
        fBtnHeight2 = 174;//174
        stageWidth = 1200;
        stageHeight = 800;
        stage = new Stage(new StretchViewport(stageWidth, stageHeight, camera.camMain));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ArAlien = new Array<Alien>();
        ArLaser = new Array<Laser>();
        ArPowerups = new Array<Powerups>();
        background = new Background(camera.camMain, batch);
        spaceship = new Spaceship(camera.camMain, batch, 900, 400);
        spaceship.spaceshipCreate();
        background.backgroundCreate();
        imgHeart = new Texture("Textures/HeartStock.png");
        spHeart = new Sprite(imgHeart);
        spHeart.setSize(50,50);
        spHeart.setPosition(20,0);
        spHeart.setFlip(true, true);

        SpawnAlien();
        SpawnLaser();
        //Score Font================================================================================
        fontScore = new BitmapFont(true);
        fontScore.setScale(2);
        fontTime = new BitmapFont( true);
        fontTime.setScale(2);
        fontStock = new BitmapFont(true);
        fontStock.setScale(2);
        //==========================================================================================
        //Sounds====================================================================================
        soundLaser = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserShot.wav"));
        musicPlay = Gdx.audio.newMusic(Gdx.files.internal("Sounds/PlayMusic.mp3"));
        musicPlay.setLooping(true);
        musicPlay.play();
        //==========================================================================================
        atlasButton = new TextureAtlas("Buttons/Buttons.pack");
        skinButton = new Skin();
        skinButton.addRegions(atlasButton);
        fontButton = new BitmapFont();

        tbsShoot = new TextButton.TextButtonStyle();
        tbsShoot.up = skinButton.getDrawable("btnShootUp");
        tbsShoot.down = skinButton.getDrawable("btnShootDown");
        tbsShoot.font = fontButton;
        btnShoot = new TextButton("", tbsShoot);
        btnShoot.setWidth(fBtnWidth2);
        btnShoot.setHeight(fBtnHeight2);
        btnShoot.setPosition(stageWidth, 0);
        btnShoot.setTransform(true);
        btnShoot.setRotation(90);
        btnShoot.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               SpawnLaser();
               soundLaser.play();

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        stage.addActor(btnShoot);


    }
    public void SpawnAlien(){
        alien = new Alien(-200, MathUtils.random(0, 800));
        alien.AlienCreate();
        ArAlien.add(alien);
        lLastSpawnTime = TimeUtils.millis();
    }
    public void SpawnLaser(){
        laser = new Laser(spaceship.SpaceshipX(),spaceship.SpaceshipY());
        laser.LaserCreate();
        ArLaser.add(laser);
        lLastSpawnTimeLaser = TimeUtils.millis();
    }
    public void SpawnPowerups(){
        powerups = new Powerups(-30, MathUtils.random(0, 800));
        powerups.powerupsCreate();
        ArPowerups.add(powerups);
        lLastSpawnTimePowerUps = TimeUtils.millis();
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