package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


public class BlackScreen implements Screen {

    MyGame game;

    public BlackScreen(MyGame game) {//constructor to keep a reference to the main Game class
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // update and draw stuff

        Gdx.gl.glClearColor( 0, 0, 0, 0 );//clears background with black colour before redrawing
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );

        if (Gdx.input.justTouched())
            game.setScreen(game.whiteScreen);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
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
