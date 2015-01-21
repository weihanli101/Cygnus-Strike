package com.mygdx.game;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
//Description=======================================================================================
//Handles: the orthographic camera

//Ref:https://github.com/libgdx/libgdx/wiki/A-simple-game
//==================================================================================================

public class Camera implements ApplicationListener {
    OrthographicCamera camMain;

    @Override
    public void create() {
        camMain = new OrthographicCamera();
        camMain.setToOrtho(false,1200,800);
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
