package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Alien{
    private float fX;
    private float fY;
    private Texture imgAlien;
    private Sprite spAlien;
    private Rectangle rectAlien;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private float fVelX;

    public Alien(float fX_, float fY_){
        fX = fX_;
        fY = fY_;
    }
    public void AlienCreate(){
        imgAlien = new Texture("Textures/Alien.png");
        spAlien = new Sprite(imgAlien);
        batch = new SpriteBatch();
        fVelX = MathUtils.random(200,800);
    }

    public boolean updateAlien(){
        spAlien.setPosition(fX,fY);
        fX += Gdx.graphics.getDeltaTime()*fVelX;
        rectAlien = new Rectangle(spAlien.getBoundingRectangle());
        rectAlien.x = fX;
        rectAlien.y = fY;
        if(fX > 1200){
            return true;
        }
        return false;
    }
    public void renderAlien(OrthographicCamera camera_){
        camera = camera_;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spAlien, fX,fY);
        batch.end();
    }
    public Rectangle getRectAlien(){
        return(rectAlien);
    }

}
