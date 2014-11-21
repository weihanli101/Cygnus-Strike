package com.mygdx.game.android;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main implements ApplicationListener {
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    StarShip ship = new StarShip();

    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("StarShip.png");
        sprite = new Sprite(texture);
    }
    private void handleInput(){
        if(Gdx.input.isTouched()){
          ship.nX = Gdx.input.getX();
          ship.nY = 100 - Gdx.input.getY();
        }
    }
    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    //Draws everything
    public void render() {
        handleInput();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(sprite,ship.nX, ship.nY);
        batch.end();
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

}
