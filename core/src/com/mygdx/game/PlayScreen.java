package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
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

    long lLastSpawnTime;
    long lLastSpawnTimeLaser;
    long lLastSpawnTimePowerUps;
    long lPowerUpTime;
    int nStock;
    int nLaserspawntime = 500;
    float stageWidth, stageHeight, fBtnWidth2, fBtnHeight2;

    private TextureAtlas atlasButton;
    private Skin skinButton;
    private TextButton.TextButtonStyle tbsShoot;
    private TextButton btnShoot;
    private BitmapFont fontButton;

    // constructor to keep a reference to the main Game class
    public PlayScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // update and draw stuff

        background.render();
        //background.backgroundRender();
        spaceship.spaceshipRender();
        spaceship.spaceshipUpdate();
        stage.draw();



        if(TimeUtils.millis() - lLastSpawnTime > 1000) SpawnAlien();
        if(TimeUtils.millis() - lLastSpawnTimeLaser >nLaserspawntime) SpawnLaser();
        if(TimeUtils.millis() -lLastSpawnTimePowerUps>10000)SpawnPowerups();
        if(TimeUtils.millis() -lPowerUpTime>10000) nLaserspawntime = 500;

        Iterator<Alien> iter = ArAlien.iterator();
        while(iter.hasNext()) {
            Alien alien1 = iter.next();
            alien1.renderAlien(camera.camMain);
            alien1.updateAlien();
            if(alien1.updateAlien() == true||alien1.getRectAlien().overlaps(laser.getRectLaser())){
                iter.remove();
            }
            else if(alien1.getRectAlien().overlaps(spaceship.getRectShip())){
                nStock = spaceship.getLife();
                System.out.println(nStock);// Debug code for life take out later!!!!!!
                if(nStock == 0){// Debug code for life take out later!!!!!!
                    game.setScreen(game.gameOverScreen);
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
                    nLaserspawntime = 100;
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
        //background = new Background(camera.camMain,batch);
        spaceship = new Spaceship(camera.camMain, batch, 900, 400);
        spaceship.spaceshipCreate();
        background.backgroundCreate();
        //background.backgroundCreate();
        soundLaser = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserShot.wav"));
        SpawnAlien();
        SpawnLaser();

        atlasButton = new TextureAtlas("Buttons/OtherButtons.pack");
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