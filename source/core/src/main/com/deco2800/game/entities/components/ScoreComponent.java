package com.deco2800.game.entities.components;

import com.deco2800.game.generic.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.deco2800.game.entities.Entity;
import com.deco2800.game.entities.components.player.PlayerObjectInteractions;
import com.deco2800.game.generic.Component;
import com.deco2800.game.physics.PhysicsLayer;
import com.deco2800.game.physics.components.HitboxComponent;
import com.deco2800.game.generic.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreComponent extends Component {

  private static final Logger logger = LoggerFactory.getLogger(ScoreComponent.class);
  private static String username;
  private static int score;

  public ScoreComponent(int initialscore){
      this.score = initialscore;
  }

  @Override
  public void create() {
    super.create();
    entity.getEvents().addListener("change_score", this::changeScore);
  }

  public int getScore(){
    return this.score;
  }


  public void changeScore(int change){
    this.score += change;
    System.out.println(score);
    entity.getEvents().trigger("update_score",score);
  }



  public static void tickScore() {
       score--;
  }

}