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

public class Main extends Game {
    Camera camera = new Camera();

    Alien alien;
    BackgroundV2 backgroundV2;
    Spaceship spaceship;
    Laser laser;
    SpriteBatch batch;
    Array<Alien> ArAlien;
    Array<Laser> ArLaser;

    long lLastSpawnTime;
    long lLastSpawnTimeLaser;


    @Override
    public void create() {
        camera.create();
        batch = new SpriteBatch();
        ArAlien = new Array<Alien>();//Array of Aliens
        ArLaser = new Array<Laser>();//Array of Lasers
        backgroundV2 = new BackgroundV2(camera.camMain, batch);
        spaceship = new Spaceship(ArAlien,camera.camMain, batch, 900, 400);
        spaceship.spaceshipCreate();
        backgroundV2.backgroundCreate();
        SpawnAlien();
        //SpawnLaser();

    }
    public void SpawnAlien(){
        alien = new Alien(-200,MathUtils.random(0, 800));
        alien.AlienCreate();
        ArAlien.add(alien);
        lLastSpawnTime = TimeUtils.millis();
    }
/*    public void SpawnLaser(){
        laser = new Laser(spaceship.SpaceshipX(),spaceship.SpaceshipY());
        laser.LaserCreate();
        ArLaser.add(laser);
        lLastSpawnTimeLaser = TimeUtils.millis();
    }*/

    @Override
    public void render() {
        backgroundV2.render();
        spaceship.spaceshipRender();
        spaceship.HandleTouch();

        if(TimeUtils.millis() - lLastSpawnTime > 1000) SpawnAlien();
        //if(TimeUtils.millis() - lLastSpawnTimeLaser >500) SpawnLaser();

        Iterator<Alien> iter = ArAlien.iterator();
        while(iter.hasNext()) {
            Alien alien1 = iter.next();
            alien1.renderAlien(camera.camMain);
            alien1.updateAlien();
            if(alien1.updateAlien() == true){
                System.out.println("REMOVED");
                iter.remove();
            }
            if(alien1.rectAlien.overlaps(spaceship.rectShip)){
                iter.remove();
            }
        }
/*        Iterator<Laser> iterLaser = ArLaser.iterator();
        while(iterLaser.hasNext()) {
            Laser laser1 = iterLaser.next();
            laser1.renderLaser(camera.camMain);
            laser1.updateLaser();
            if(laser1.updateLaser() == true){
                System.out.println("LaserRemoved");
                iter.remove();
            }
        }*/
    }
}