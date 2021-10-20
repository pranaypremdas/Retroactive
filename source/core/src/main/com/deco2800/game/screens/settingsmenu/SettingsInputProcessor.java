package com.deco2800.game.screens.settingsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.deco2800.game.screens.endgame.EndGameDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SettingsInputProcessor implements InputProcessor {
    private final Logger logger = LoggerFactory.getLogger(SettingsInputProcessor.class);

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            SettingsMenuDisplay.applySettings();
            logger.info("Enter Key Pressed");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            SettingsMenuDisplay.exitSettingsMenu();
            logger.info("Escape Key Pressed");
        }
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
