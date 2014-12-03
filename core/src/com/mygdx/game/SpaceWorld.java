package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


public class SpaceWorld implements ApplicationListener{
    World worldSpace;

    @Override
    public void create() {
        worldSpace = new World(new Vector2(0, 0), true);
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
