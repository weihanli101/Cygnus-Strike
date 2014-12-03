package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Main extends Game{
    Camera camera = new Camera();
    Alien alien;
    Texture imgAlien;
    Sprite spAlien;
    SpriteBatch batch;

    @Override
    public void create() {
        camera.create();
        batch = new SpriteBatch();
        alien = new Alien(-200, MathUtils.random(0, 800));
        imgAlien = new Texture("Alien.png");
        spAlien = new Sprite(imgAlien);
    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.camMain.combined);
        batch.begin();
        batch.draw(spAlien,alien.fX,alien.fY);
        batch.end();

        alien.MoveAlien();

    }
}
