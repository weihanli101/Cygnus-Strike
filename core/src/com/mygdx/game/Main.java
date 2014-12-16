package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Main extends Game{
    Camera camera = new Camera();
    Asset asset = new Asset();

    Alien alien;
    BackgroundV2 backgroundV2;
    Spaceship spaceship;
    Laser laser;
    SpriteBatch batch;
    Array<Alien> ArAlien;
    Array<Laser> ArLaser;

    long lLastSpawnTime;
    long lLastSpawnTimeBullet;

    boolean isTouchingSpaceship;


    @Override
    public void create() {
        camera.create();
        asset.create();
        batch = new SpriteBatch();
        ArAlien = new Array<Alien>();//Array of Aliens
        ArLaser = new Array<Laser>();//Array of Lasers
        spaceship = new Spaceship(900,400,camera.camMain,asset.spSpaceshipLoad);
        backgroundV2 = new BackgroundV2(camera.camMain,asset.imgBackground,batch);
        SpawnAlien();
    }

    public void SpawnAlien(){//This method spawns the alien and adds it the alien array
        alien = new Alien(-100, MathUtils.random(20,780),asset.spAlienLoad);
        ArAlien.add(alien);
        lLastSpawnTime = TimeUtils.millis();
    }
    public void SpawnLaser(){// creates a laser and adds it to the array of lasers
        laser = new Laser(spaceship.fX,spaceship.fY, asset.spLaz_BulletLoad);
        ArLaser.add(laser);
        lLastSpawnTimeBullet = TimeUtils.millis();
    }
    public void touchController() {// to prevent multi-touch but doesn't function when
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.camMain.unproject(touchPos);
            if(spaceship.rectShip.contains(touchPos.x,touchPos.y)) {//enables touch move when the touch coordinates is touching the spaceship
                touchPos.x = spaceship.rectShip.x;
                touchPos.y = spaceship.rectShip.y;
                isTouchingSpaceship = true;
            }
            else{
                isTouchingSpaceship = false;
            }

        }
    }

    @Override
    public void render() {
        touchController();
        camera.camMain.update();//Updates the camera
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backgroundV2.render();
        batch.setProjectionMatrix(camera.camMain.combined);//sets the batch to the camera's coordinates
        batch.begin();
        batch.draw(spaceship.spSpaceship, spaceship.fX, spaceship.fY);//draws the spaceship
        for (Alien alien1 : ArAlien) {// loops through the alien array and draws the alien
            batch.draw(alien1.spAlien, alien1.fX, alien1.fY);
        }
        for (Laser laser1 : ArLaser) {//loops through the laser array and draws the laser
            batch.draw(laser1.spLaser, laser1.fX, laser1.fY);
        }
        batch.end();


        if ((TimeUtils.millis() - lLastSpawnTime) > 2000) {//spawns alien every 0.2 of a second
            SpawnAlien();
        }
        if((TimeUtils.millis()-lLastSpawnTimeBullet)>200){
            SpawnLaser();
        }

        Iterator<Alien> iterAlien = ArAlien.iterator();
        while (iterAlien.hasNext()) {//loops through the array of aliens to move them
            Alien alien1 = iterAlien.next();
            alien1.fX += 200 * Gdx.graphics.getDeltaTime();//moves the alien at 200px/second
            alien1.updateAlien();//moves the hit-box for the alien sprite
            if (alien1.rectAlien.overlaps(spaceship.rectShip)||alien1.rectAlien.overlaps(laser.rectLaser)||alien1.fX > 1250){
                iterAlien.remove();
        }

        }

        Iterator<Laser> iterLaser = ArLaser.iterator();
        while (iterLaser.hasNext()) {
            Laser laser1 = iterLaser.next();
            laser1.updateLaser();//moves the laser hit-box
            laser1.fX -= 2000*Gdx.graphics.getDeltaTime();// moves the laser at 2000 px/s
            if (laser1.fX < -10||laser1.rectLaser.overlaps(alien.rectAlien)){
                iterLaser.remove();
            }
        }
        //if(isTouchingSpaceship == true){
            spaceship.HandleTouch();
        //}

        spaceship.HandleKeys();//handles arrow keys movement
        spaceship.updateSpaceship();// moves the hit-box for the spaceship

    }

}
