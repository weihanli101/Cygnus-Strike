package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
//Description=======================================================================================
//Handles: Laser's X&Y, collision detection, bounds checking, importing laser textures, moves laser
//hit-box

//Ref:Matt Brock
//Scott Grondin
//https://github.com/libgdx/libgdx/wiki/A-simple-game
//==================================================================================================
public class Laser {
    private Sprite spLaser;
    private Texture imgLaser;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private float fX;
    private float fY;
    private Rectangle rectLaser;

    public Laser(float fX_, float fY_){
        fX = fX_;
        fY = fY_;
    }
    public void LaserCreate(){
        imgLaser = new Texture("Textures/LaserBall.png");
        spLaser = new Sprite(imgLaser);
        rectLaser = new Rectangle(spLaser.getBoundingRectangle());
        batch = new SpriteBatch();
    }
    public void renderLaser(OrthographicCamera camera_){
        camera = camera_;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spLaser, fX-100,fY);
        batch.end();

    }
    public Rectangle getRectLaser(){
        return (rectLaser);
    }
    public boolean updateLaser(){
       spLaser.setPosition(fX,fY);
       fX -= 2000*Gdx.graphics.getDeltaTime();
       rectLaser.x = fX-100;
       rectLaser.y = fY;
       if(fX < 0){
           return true;
        }
       return false;
    }

}
