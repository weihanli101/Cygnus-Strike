package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


//Description=======================================================================================
//This is the the spaceship object class: Handles movement, rendering, hit box

//References:
//https://github.com/libgdx/libgdx/wiki/Accelerometer
//https://github.com/libgdx/libgdx/wiki/A-simple-game
//Mr. Matt Brock yay
//==================================================================================================

public class Spaceship {
    private float fX;
    private float fY;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite spSpaceship;
    private Texture imgSpaceship;
    private Rectangle rectShip;
    private static int nSheetCols = 1;
    private static int nSheetRows = 3;
    private TextureRegion[] imgFrames;
    private TextureRegion currentFrame;
    private Animation AniSpaceship;
    private float fStateTime;

    public Spaceship(OrthographicCamera camera_,SpriteBatch batch_,float fX_, float fY_){
        fX = fX_;
        fY = fY_;
        camera = camera_;
        batch = batch_;
    }

    public void spaceshipCreate(){
        imgSpaceship = new Texture("Textures/spaceship.png");
        TextureRegion[][] tmp = TextureRegion.split(imgSpaceship,
                imgSpaceship.getWidth()/nSheetCols, imgSpaceship.getHeight()/nSheetRows);
        imgFrames = new TextureRegion[nSheetCols * nSheetRows];
        int nIndex = 0;
        for (int i = 0; i < nSheetRows; i++) {
            for (int j = 0; j < nSheetCols; j++) {
                imgFrames[nIndex++] = tmp[i][j];
            }
        }
        AniSpaceship = new Animation(0.1f, imgFrames);
        batch = new SpriteBatch();
        fStateTime = 0f;
        batch = new SpriteBatch();
    }
    public void spaceshipRender(){
        camera.update();
        fStateTime += Gdx.graphics.getDeltaTime();
        currentFrame = AniSpaceship.getKeyFrame(fStateTime, true);
        spSpaceship = new Sprite(currentFrame);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spSpaceship, fX-100, fY);
        batch.end();

    }
    public float SpaceshipX(){
        return(fX);
    }
    public float SpaceshipY(){
        return(fY);
    }

    public Rectangle getRectShip(){
        return(rectShip);
    }

    public void spaceshipUpdate(){
        rectShip = new Rectangle(spSpaceship.getBoundingRectangle());
        rectShip.x = fX-100;
        rectShip.y = fY;
    }

    public void HandleTouch(){
        float fSprite_Width = spSpaceship.getWidth();
        float fSprite_Height = spSpaceship.getWidth();
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            fX = touchPos.x - (fSprite_Width/ 2);
            fY = touchPos.y - (fSprite_Height/ 2);
        }
    }
  /*  public void HandleTilt(){
        float fAccelX = Gdx.input.getAccelerometerX();
        float fAccelY = Gdx.input.getAccelerometerY();

        if(fAccelX>3){
            fY += 500*Gdx.graphics.getDeltaTime();
        }
        if(fAccelX<-3){
            fY -= 500*Gdx.graphics.getDeltaTime();
        }
        if(fAccelY<-3){
            fX -= 500*Gdx.graphics.getDeltaTime();
        }
        if(fAccelY>3){
            fX += 500 *Gdx.graphics.getDeltaTime();
        }
*/
    //}
    public void HandleKeys(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            fY -= 500*Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            fY += 500*Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            fX -= 500*Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            fX += 500*Gdx.graphics.getDeltaTime();
        }
    }
}
