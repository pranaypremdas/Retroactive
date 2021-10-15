package com.deco2800.game.screens.mainmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.deco2800.game.generic.ServiceLocator;
import com.deco2800.game.ui.components.UIComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.util.Random;


/**
 * A ui component for displaying the Main menu.
 */
public class MainMenuDisplay extends UIComponent {
  private static final Logger logger = LoggerFactory.getLogger(MainMenuDisplay.class);
  private static final float Z_INDEX = 2f;
  private static TextField txtUsername = new TextField("", skin);
  private Table table;
  private String[] playablecharcters = {
          "images/characters/boy_01/boy_01_menu_preview.png",
          "images/characters/girl_00/girl_00_menu_preview.png",
          "images/characters/boy_00/boy_00_menu_preview.png"

  };
  private String[] playableAtlas ={
          "images/characters/boy_01/boy_01.atlas",
          "images/characters/girl_00/girl_00.atlas",
          "images/characters/boy_00/boy_00.atlas"
  };
  int characterIndex= 0 ;

  private static int menuIndex = 0;
  private static List<TextButton> buttons = new ArrayList<>();
  private static Image menuIndicator = new Image(ServiceLocator.getResourceService()
              .getAsset("images/ui/elements/menuFrame-LONG.png", Texture.class));



  @Override
  public void create() {
    super.create();
    addActors();
  }

  private void addActors() {

    table = new Table();
    table.setFillParent(true);
    Image title =
        new Image(
            ServiceLocator.getResourceService()
                .getAsset("images/ui/title/RETROACTIVE-large.png", Texture.class));

    writeAtlas(); //Stores copy of the first character

    TextButton startBtn = new TextButton("Start", skin);
    buttons.add(startBtn);
    TextButton leaderboardBtn = new TextButton("LeaderBoard", skin);
    buttons.add(leaderboardBtn);
    TextButton settingsBtn = new TextButton("Settings", skin);
    buttons.add(settingsBtn);
    TextButton exitBtn = new TextButton("Exit", skin);
    buttons.add(exitBtn);
    TextButton changeCharacterBtn = new TextButton("Change Character", skin);
    buttons.add(changeCharacterBtn);
    txtUsername.setMessageText("Username:");


    Image character = new Image(ServiceLocator.getResourceService()
            .getAsset(playablecharcters[characterIndex], Texture.class));

    // Triggers an event when the button is pressed
    startBtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
            logger.debug("Start button clicked");
            writeUsername();
            entity.getEvents().trigger("start");
          }
        });

    leaderboardBtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
            logger.debug("LeaderBoard button clicked");
            entity.getEvents().trigger("leaderboard");
          }
        });

    settingsBtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
            logger.debug("Settings button clicked");
            entity.getEvents().trigger("settings");
          }
        });

    exitBtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {

            logger.debug("Exit button clicked");
            entity.getEvents().trigger("exit");
          }
        });

    changeCharacterBtn.addListener(
            new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    characterIndex++;
                    changeCharacterDisplay();
                    writeAtlas();
                    logger.info("Change Character button clicked. ");
                    entity.getEvents().trigger("change_character");
                }
            });


    table.add(title);
    table.row();
    table.add(startBtn).padTop(15f);
    table.row();
    table.add(leaderboardBtn).padTop(15f);
    table.row();
    table.add(settingsBtn).padTop(15f);
    table.row();
    table.add(exitBtn).padTop(15f);
    table.row();
    table.add(txtUsername).padTop(50f);
    table.row();
    table.add(character).padTop(20f);
    table.row();
    table.add(changeCharacterBtn).padTop(10f).padBottom(20f);
    stage.addActor(table);

    updateMenuFrame();
    menuIndicator.setTouchable(Touchable.disabled);
    stage.addActor(menuIndicator);


  }

  @Override
  public void draw(SpriteBatch batch) {
    // draw is handled by the stage
  }

  @Override
  public float getZIndex() {
    return Z_INDEX;
  }

  @Override
  public void dispose() {
    table.clear();
    super.dispose();
  }

    /**
     * Changes the character displayed on the main menu page and cycles the index.
     */
  public void changeCharacterDisplay(){
      if (characterIndex <= playablecharcters.length-1){
          dispose();
          create();
      } else {
          characterIndex =0;
          dispose();
          create();
      }
  }
    /**
     * Updates currentCharacterAtlas.txt
     */
    public void writeAtlas(){
        try (FileWriter writer = new FileWriter("configs/currentCharacterAtlas.txt")) {
            writer.write(this.playableAtlas[this.characterIndex]);
            logger.info("Writing new atlas to settings.");
        } catch (Exception e){

            logger.debug("Could not load the atlas after character change was made.");
        }
    }

    public void writeUsername(){
        try (FileWriter writer = new FileWriter("configs/leaderboard.txt",true)) {
            String username;

            if (txtUsername.getText().length()<2){
                username = "DirtyDefault"+ getRandomNum();
            }else{
                username = txtUsername.getText();
            }

            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            sb.append(username);
            sb.append(":");
            String s = sb.toString();
            writer.write(s);
            logger.info("Wrote username to leaderboard.");
        } catch (Exception e){
            logger.debug("Could not write username to leaderboard.");
        }
    }

    public int getRandomNum(){
        try {
            Random rand = SecureRandom.getInstanceStrong();
            return rand.nextInt(100000);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Random isn't available in this environment.");
            return 0;
        }
    }

    public static void moveUp(){
        if (notAtTop()) {
            menuIndex--;
            updateMenuFrame();
        }
        logger.info("Menu Index is {}", menuIndex);
    }


    /**
       Emulates mouse hover with keyboard
     **/
    public static void hoverMenu(Actor btn){
        InputEvent event = new InputEvent();
        event.setType(InputEvent.Type.enter);
        event.setPointer(-1);
        btn.fire(event);
    }

    /**
     Emulates mouse unhover with keyboard
     **/
    public static void unhoverMenu(Actor btn){
        InputEvent event = new InputEvent();
        event.setType(InputEvent.Type.exit);
        event.setPointer(-1);
        btn.fire(event);
    }

    private static boolean notAtTop() {
        return menuIndex > 0;
    }

    public static void moveDown(){
        if (notAtBottom()) {
            menuIndex++;
            updateMenuFrame();
        }

        logger.info("Menu Index is {}", menuIndex);
    }

    private static boolean notAtBottom() {
        return menuIndex < 5;
    }

    public static void updateMenuFrame() {
        TextButton startBtn = buttons.get(0);
        TextButton leadBtn = buttons.get(1);
        TextButton setBtn = buttons.get(2);
        TextButton exitBtn = buttons.get(3);
        TextButton charBtn = buttons.get(4);
        switch (menuIndex) {
            case 0: //Start Button (height of title image + 15f)
                menuIndicator.setPosition(500f,460);
                hoverMenu(startBtn);
                unhoverMenu(leadBtn);
                unhoverMenu(setBtn);
                unhoverMenu(exitBtn);
                unhoverMenu(charBtn);
                logger.info("How many buttons {}", buttons.size());
                break;
            case 1: //Leaderboard Button (height start btn + 15f)
                menuIndicator.setPosition(500f,402);
                unhoverMenu(startBtn);
                hoverMenu(leadBtn);
                unhoverMenu(setBtn);
                unhoverMenu(exitBtn);
                unhoverMenu(charBtn);
                break;
            case 2: //Settings Button
                menuIndicator.setPosition(500f,345);
                unhoverMenu(startBtn);
                unhoverMenu(leadBtn);
                hoverMenu(setBtn);
                unhoverMenu(exitBtn);
                unhoverMenu(charBtn);
                break;
            case 3: //Exit Button
                menuIndicator.setPosition(500f,287);
                unhoverMenu(startBtn);
                unhoverMenu(leadBtn);
                unhoverMenu(setBtn);
                hoverMenu(exitBtn);
                unhoverMenu(charBtn);
                break;
            case 4: // Enter Username
                menuIndicator.setPosition(500f,202);
                unhoverMenu(startBtn);
                unhoverMenu(leadBtn);
                unhoverMenu(setBtn);
                unhoverMenu(exitBtn);
                unhoverMenu(charBtn);
                break;
            case 5: //Character Button
                menuIndicator.setPosition(500f,8);
                unhoverMenu(startBtn);
                unhoverMenu(leadBtn);
                unhoverMenu(setBtn);
                unhoverMenu(exitBtn);
                hoverMenu(charBtn);
                break;
            default:
                logger.error("Invalid menu button or has no function.");
                break;
        }
    }

    public static void pressMenu() {
        logger.info("Enter key is pressed");
        switch (menuIndex) {
            case 0: //Start Button
                TextButton startBtn = buttons.get(0);
                buttons.clear();
                startBtn.toggle();
                break;
            case 1: //Leaderboard Button
                TextButton leadBtn = buttons.get(1);
                buttons.clear();
                leadBtn.toggle();
                break;
            case 2: //Settings Button
                TextButton setBtn = buttons.get(2);
                buttons.clear();
                setBtn.toggle();
                break;
            case 3: //Exit Button
                TextButton exitBtn = buttons.get(3);
                exitBtn.toggle();
                break;
            case 4: // Enter Username

                break;
            case 5: //Character Button
                TextButton charBtn = buttons.get(4);
                charBtn.toggle();
                break;
            default:
                logger.error("Invalid menu button or has no function.");
                break;
        }
    }
}



