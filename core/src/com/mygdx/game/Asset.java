package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Asset implements ApplicationListener {
    Texture imgAlien;
    Texture imgSpaceship;
    Texture imgLaz_Bullet;
    Texture imgBackground;
    Sprite spLaz_BulletLoad;
    Sprite spAlienLoad;
    Sprite spSpaceshipLoad;



    @Override
    public void create() {
        imgAlien = new Texture("Alien.png");
        spAlienLoad = new Sprite(imgAlien);
        imgSpaceship = new Texture("StarShip.png");
        spSpaceshipLoad = new Sprite(imgSpaceship);
        imgLaz_Bullet = new Texture("LaserBall.png");
        spLaz_BulletLoad = new Sprite(imgLaz_Bullet);
        imgBackground = new Texture("background.png");

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
