package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Main extends Game{
    Camera camera = new Camera();
    Asset asset = new Asset();

    Alien alien;
    SpriteBatch batch;
    Array<Alien> ArAlien;

    long lLastSpawnTime;

    @Override
    public void create() {
        camera.create();
        asset.create();
        batch = new SpriteBatch();
        ArAlien = new Array<Alien>();
        SpawnAlien();
    }

    public void SpawnAlien(){
        alien = new Alien(-200, MathUtils.random(0,800),asset.spAlienLoad);
        ArAlien.add(alien);
        lLastSpawnTime = TimeUtils.millis();
    }

    @Override
    public void render() {
        camera.camMain.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.camMain.combined);
        batch.begin();
        for (Alien alien1 : ArAlien) {
            batch.draw(alien1.spAlien, alien1.fX, alien1.fY);
        }
        batch.end();

        if((TimeUtils.millis()-lLastSpawnTime)>2000){
            SpawnAlien();
            System.out.println("Spawned!");
        }

        Iterator<Alien> iter = ArAlien.iterator();
        while (iter.hasNext()) {
            Alien alien1 = iter.next();
            alien1.fX += 200 * Gdx.graphics.getDeltaTime();
            if (alien1.fX>1250){
                iter.remove();
            }
        }
    }
}
