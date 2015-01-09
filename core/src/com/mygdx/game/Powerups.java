package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


//Ref: https://github.com/libgdx/libgdx/wiki/2D-Animation
//Naming: Ani = Animation

public class Powerups {
    private float fX;
    private float fY;
    private static int nSheetCols = 8;         // #1
    private static int nSheetRows = 6;
    private Texture imgPowerupSheet;
    private TextureRegion[] imgFrames;
    private Animation AniPowerUp;
    private SpriteBatch batch;
    private float fStateTime;
    private OrthographicCamera camera;
    private TextureRegion currentFrame;
    private Rectangle rectPowerup;
    private Sprite spCurrentFrame;

    public Powerups(float fX_, float fY_){
        fX = fX_;
        fY = fY_;
    }
    public void powerupsCreate(){
        imgPowerupSheet = new Texture("Textures/powerpad.png");
        TextureRegion[][] tmp = TextureRegion.split(imgPowerupSheet,
                imgPowerupSheet.getWidth()/nSheetCols, imgPowerupSheet.getHeight()/nSheetRows);
        imgFrames = new TextureRegion[nSheetCols * nSheetRows];
        int nIndex = 0;
        for (int i = 0; i < nSheetRows; i++) {
            for (int j = 0; j < nSheetCols; j++) {
                imgFrames[nIndex++] = tmp[i][j];
            }
        }
        AniPowerUp = new Animation(0.040f, imgFrames);
        batch = new SpriteBatch();
        fStateTime = 0f;
    }
    public void powerupsRender(OrthographicCamera camera_){
        camera = camera_;
        camera.update();
        fStateTime += Gdx.graphics.getDeltaTime();
        currentFrame = AniPowerUp.getKeyFrame(fStateTime, true);
        spCurrentFrame = new Sprite(currentFrame);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(spCurrentFrame, fX, fY);
        batch.end();
    }
    public boolean powerupsUpdate(){
        fX += 300*Gdx.graphics.getDeltaTime();
        rectPowerup = new Rectangle(spCurrentFrame.getBoundingRectangle());
        rectPowerup.x = fX;
        rectPowerup.y = fY;
        if(fX>1200){
            return(true);
        }
        return(false);
    }
    public Rectangle getPowerupsRect(){
        return(rectPowerup);
    }

}
