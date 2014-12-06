package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Asset implements ApplicationListener {
    Texture imgAlien;
    Sprite spAlienLoad;

    @Override
    public void create() {
        imgAlien = new Texture("Alien.png");
        spAlienLoad = new Sprite(imgAlien);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

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
