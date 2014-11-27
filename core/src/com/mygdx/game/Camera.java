package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*Description=======================================================================================
This Class will deal with handling the orthographic camera or cameras (orthographic cameras are used
99% of the time in 2D rendering)

Camera: Width 1280px and 800px

References:
- https://github.com/libgdx/libgdx/wiki/A-simple-game *orthographic Cam + structure

Naming:
- Texture will be referenced with a "t"
- Sprites will be referenced with "sp"
- Camera will be referenced with "cam" *we are only using the orthographic camera
- Shapes will be referenced with "rect" or "cir" etc. you get the idea
- ArrayLists (just called Arrays in android) will be name with a prefix of "Ar"
- Wrapper long will be referenced with a "l"
===================================================================================================*/
public class Camera implements ApplicationListener{
    float screenWidth = 1280;// virtual width
    float screenHeight = 800;// virtual height
    OrthographicCamera camMain;

    @Override
    public void create() {

        camMain = new OrthographicCamera();
        camMain.setToOrtho(true, screenWidth,screenHeight);

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
