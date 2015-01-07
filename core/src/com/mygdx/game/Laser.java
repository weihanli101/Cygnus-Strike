package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by WL on 12/9/2014.
 */
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
        imgLaser = new Texture("LaserBall.png");
        spLaser = new Sprite(imgLaser);
        rectLaser = new Rectangle(spLaser.getBoundingRectangle());
    }
    public void renderLaser(OrthographicCamera camera_){
        camera = camera_;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spLaser, fX,fY);
        batch.end();

    }
    public boolean updateLaser(){
       spLaser.setPosition(fX,fY);
       fX += 1000*Gdx.graphics.getDeltaTime();
       rectLaser.x = fX;
       rectLaser.y = fY;
       if(fX > 1200){
           return true;
        }
       return false;
    }

}
