package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//Description=======================================================================================
//Handles: background scrolling movement, background texture, rendering

//Ref:https://code.google.com/p/libgdx-users/wiki/ScrollingTexture
//==================================================================================================
public class Background {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite spBackground;
    private Texture imgBackground;
    float fScrollTimer = 0.0f;

    public Background(OrthographicCamera camera_, SpriteBatch batch_){
        batch = batch_;
        camera = camera_;
    }
    public void backgroundCreate(){
        imgBackground = new Texture("Textures/background.png");
        imgBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);//allows for texture to loop
        spBackground = new Sprite(imgBackground);
        spBackground.setSize(1200, 800);
        batch = new SpriteBatch();
    }
    public void render(){
        camera.update();
        spBackground.setU(fScrollTimer + 2);//incrementing position of image
        spBackground.setU2(fScrollTimer);
        fScrollTimer += Gdx.graphics.getDeltaTime();
        if (fScrollTimer > 1f) fScrollTimer = 0.0f; //Loops image by resetting timer
        Gdx.gl.glClearColor(0, 0, 0, 0);//clears background with black colour before redrawing
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        spBackground.draw(batch);//background being drawn onto the stage
        batch.end();

    }
}
