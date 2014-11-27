package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*Description=======================================================================================
This Class will deal with displaying the game's background and giving it a scrolling effect

References:
-https://code.google.com/p/libgdx-users/wiki/ScrollingTexture

Naming:
- Texture will be referenced with a "t"
- Sprites will be referenced with "sp"
- Camera will be referenced with "cam" *we are only using the orthographic camera
- Shapes will be referenced with "rect" or "cir" etc. you get the idea
- ArrayLists (just called Arrays in android) will be name with a prefix of "Ar"
- Wrapper long will be referenced with a "l"
===================================================================================================*/
public class Background implements ApplicationListener {
    Camera camera = new Camera();
    Texture tBackground;
    SpriteBatch batch;
    Sprite spBackground;
    float scrollTimer = 0.0f;

    @Override
    public void create() {

        camera.create();
        tBackground = new Texture("background.png");//grabbing image of background from assets folder
        tBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);//allows for texture to loop
        batch = new SpriteBatch();//used to display image

        spBackground = new Sprite(tBackground);//created sprite from previous texture (sprites are easily rotatable and movable)

        spBackground.setSize(1280, 800);//set the size of background to fit dimensions of the orthographic camera

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        camera.camMain.update();//Updates Camera
        batch.setProjectionMatrix(camera.camMain.combined);//indicates to spritebatch to use cam coor system instead of the default one

        scrollTimer+=Gdx.graphics.getDeltaTime();//update position of image
        if(scrollTimer>1f)//loops image
            scrollTimer = 0.0f;

        //scroll from top to bottom (using .setV & .setV2 would create horizontal movement)
        spBackground.setU(scrollTimer+2);//incrementing position of image
        spBackground.setU2(scrollTimer);

        Gdx.gl.glClearColor( 0, 0, 0, 0 );//clears background with black colour before redrawing
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        spBackground.draw(batch);//background being drawn onto the stage
        batch.end();
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
