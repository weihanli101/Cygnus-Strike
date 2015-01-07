package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BackgroundV2 {

    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite spBackground;
    Texture imgBackground;
    float fScrollTimer = 0.0f;


    public BackgroundV2(OrthographicCamera camera_, SpriteBatch batch_){
        batch = batch_;
        camera = camera_;
    }
    public void backgroundCreate(){
        imgBackground = new Texture("background.png");
        imgBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);//allows for texture to loop
        spBackground = new Sprite(imgBackground);
        spBackground.setSize(1200, 800);
    }
    public void render(){
        camera.update();
        spBackground.setU(fScrollTimer + 2);//incrementing position of image
        spBackground.setU2(fScrollTimer);
        Gdx.gl.glClearColor(0, 0, 0, 0);//clears background with black colour before redrawing
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        fScrollTimer += Gdx.graphics.getDeltaTime();
        if (fScrollTimer > 1f) fScrollTimer = 0.0f; //Loops image by resetting timer

        batch.begin();
        spBackground.draw(batch);//background being drawn onto the stage
        batch.end();

    }
}
