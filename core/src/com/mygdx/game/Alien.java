package com.mygdx.game;


import com.badlogic.gdx.ApplicationListener;
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

public class Alien implements ApplicationListener{
    Camera camera = new Camera();
    Texture imgAlien;
    Sprite spAlien;
    SpriteBatch batch;

    Array<Sprite> ArSpAliens;
    long lLastSpawnTime;
    float fXAlien;
    float fYAlien;

    @Override
    public void create() {
        camera.create();
        batch = new SpriteBatch();
        ArSpAliens = new Array <Sprite>();
        SpawnAlien();
    }

    public void SpawnAlien(){
        imgAlien = new Texture("Alien.png");
        ArSpAliens.add(spAlien);
        lLastSpawnTime = TimeUtils.millis();
    }

    @Override
    public void render() {
        camera.camMain.update();
        batch.setProjectionMatrix(camera.camMain.combined);
        batch.begin();
        for (int i = 0; i < ArSpAliens.size; i++) {
            batch.draw(ArSpAliens.get(i), ArSpAliens.get(i).getX(), ArSpAliens.get(i).getY());
        }
        batch.end();

        if (TimeUtils.millis() - lLastSpawnTime > 2000) {
            SpawnAlien();
        }
        Iterator<Sprite> iter = ArSpAliens.iterator();
        while (iter.hasNext()) {
            Sprite spAlienMove = iter.next();
            spAlienMove.setPosition(fXAlien,fYAlien);
            fXAlien += 200*Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
