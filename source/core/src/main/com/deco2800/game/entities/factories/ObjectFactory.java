package com.deco2800.game.entities.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.deco2800.game.chores.Chore;
import com.deco2800.game.chores.ChoreList;
import com.deco2800.game.entities.Entity;
import com.deco2800.game.entities.components.object.*;
import com.deco2800.game.entities.components.SingleUse;
import com.deco2800.game.generic.ResourceService;
import com.deco2800.game.generic.ServiceLocator;
import com.deco2800.game.physics.PhysicsLayer;
import com.deco2800.game.physics.PhysicsUtils;
import com.deco2800.game.physics.components.ColliderComponent;
import com.deco2800.game.physics.components.HitboxComponent;
import com.deco2800.game.physics.components.PhysicsComponent;
import com.deco2800.game.rendering.components.AnimationRenderComponent;
import com.deco2800.game.rendering.components.TextureRenderComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Factory to create obstacle entities.
 *
 * <p>Each obstacle entity type should have a creation method that returns a corresponding entity.
 */
@SuppressWarnings({"unused", "UnnecessaryLocalVariable"})
public class ObjectFactory {

  private static final Logger logger = LoggerFactory.getLogger(ObjectFactory.class);

    public static Entity createWall(String[] assets) {
    final float wallScale = 1f;
    Entity wall = createBaseObject(assets);
    PhysicsUtils.setScaledCollider(wall, wallScale, wallScale);
    return wall;
  }

  public static Entity createBed(String[] assets) {
    // Dummy method, eventually the intended entity will be returned from
    // createPlayerBed or createNormalBed
    return null;
  }

  public static Entity createPlayerBed(String[] assets) {
    Entity bed = createBaseInteractable(assets, BodyType.StaticBody)
            .addComponent(new BedActions());
    bed.setScale(1.5f, 1f);
    PhysicsUtils.setColliderShape(bed, 1f, 2f);
    return bed;
  }

  public static Entity createNormalBed(String[] assets) {
    Entity bed = createBaseObject(assets);
    bed.setScale(1.5f, 1f);
    PhysicsUtils.setColliderShape(bed, 1f, 2f);
    return bed;
  }

  public static Entity createDoor(String[] assets) {
//    Entity door = createBaseInteractable(assets, BodyType.StaticBody)
//            .addComponent(new DoorActions());
    return new Entity();
  }

  public static Entity createPlaceableBox(String[] assets) {
    return createBaseInteractable(assets, BodyType.StaticBody)
            .addComponent(new PlaceableBoxActions());
  }

  public static Entity createTv(String[] assets) {
    Entity tv = createBaseChore(assets, BodyType.StaticBody, ChoreList.TV)
            .addComponent(new TvActions());
    PhysicsUtils.setColliderShape(tv, 1f, 1f);
    return tv;
  }

  public static Entity createEnergyDrink(String[] assets) {
    return createBaseInteractable(assets, BodyType.DynamicBody)
            .addComponent(new DrinkActions())
            .addComponent(new SingleUse());
  }

  public static Entity createBananaPeel(String[] assets) {
    return createBaseInteractable(assets, BodyType.DynamicBody)
            .addComponent(new BananaPeelActions());
  }

  public static Entity createPuddle(String[] assets){
    Entity puddle = createBaseInteractable(assets, BodyType.KinematicBody).addComponent(new BananaPeelActions());
    puddle.setScale(1f, 0.5f);
    PhysicsUtils.setScaledCollider(puddle,1f,1f);
    return puddle;
  }

  public static Entity createBookcase(String[] assets) {
    Entity bookcase = createBaseObject(assets);
    PhysicsUtils.setColliderShape(bookcase, 1f, 1f);
    PhysicsUtils.setColliderOffset(bookcase, 0, 0.3f);
    return bookcase;
  }

  public static Entity createBath(String[] assets) {
    Entity bath = createBaseObject(assets);
    bath.setScale(1.5f,1.5f);
    PhysicsUtils.setColliderShape(bath, 2f, 1f);
    return bath;
  }

  public static Entity createLounge(String[] assets) {
    Entity lounge = createBaseObject(assets);
    lounge.setScale(2f,1f);
    PhysicsUtils.setColliderShape(lounge, 2.5f, 1f);
    PhysicsUtils.setColliderOffset(lounge, 0.5f, 0);
    return lounge;
  }

  public static Entity createDesk(String[] assets) {
    Entity desk = createBaseObject(assets);
    PhysicsUtils.setColliderShape(desk, 1f, 1f);
    return desk;
  }

  public static Entity createCoffeeTable(String[] assets) {
    Entity coffeeTable = createBaseObject(assets);
    coffeeTable.getComponent(TextureRenderComponent.class).scaleEntity();
    //coffeeTable.setScale(1.5f,1f);
    PhysicsUtils.setScaledCollider(coffeeTable,1f,1f);
    return coffeeTable;
  }

  public static Entity createLamp(String[] assets) {
    Entity lamp = createBaseObject(assets);
    lamp.setScale(0.5f,1f);
    PhysicsUtils.setColliderShape(lamp, 0.5f, 0.5f);
    return lamp;
  }

  public static Entity createChair(String[] assets) {
    Entity chair = createBaseObject(assets);
    PhysicsUtils.setColliderShape(chair, 0.5f, 0.5f);
    PhysicsUtils.setColliderOffset(chair, 0, 0.5f);
    return chair;
  }

  public static Entity createSideTable(String[] assets) {
    Entity side = createBaseObject(assets);
    side.setScale(0.5f, 0.5f);
    PhysicsUtils.setScaledCollider(side, 0.5f, 0.5f);
    return side;
  }

  public static Entity createFridge(String[] assets) {
    Entity fridge = createBaseObject(assets);
    fridge.setScale(1f, 1.5f);
    PhysicsUtils.setColliderShape(fridge, 1f, 1f);
    return fridge;
  }

  public static Entity createCabinet(String[] assets) {
    Entity cab = createBaseObject(assets);
    PhysicsUtils.setScaledCollider(cab, 1f,1f);
    return cab;
  }

  public static Entity createBin(String[] assets) {
    Entity bin = createBaseObject(assets);
    bin.setScale(0.5f, 0.5f);
    PhysicsUtils.setScaledCollider(bin, 0.5f, 0.5f);
    return bin;
  }

  /**
   * Creates the object as a chore, and registers it as a chore to the ChoreController
   * @param assets the image and atlas assets of this object
   * @param bodyType Static, kinematic or dynamic body type
   * @param object The ChoreList ID of this object
   * @return The new entity of this obstacle
   */
  public static Entity createBaseChore(String[] assets, BodyType bodyType, ChoreList object) {
    Entity entity = createBaseInteractable(assets, bodyType);
    ServiceLocator.getChoreController().addChore(entity, object);
    return entity;
  }

  public static Entity createBaseInteractable(String[] assets, BodyType bodyType) {
    // Set interactable to have a base hitbox component
    Entity interactable = createBaseObject(assets)
            .addComponent(new HitboxComponent().setLayer(PhysicsLayer.OBSTACLE));
    PhysicsUtils.setScaledHitbox(interactable, 1f, 1f);
    return interactable;
  }

  public static Entity createBaseObject(String[] assets) {
    // Set obstacle to have base physics components
    Entity obstacle = new Entity()
            .addComponent(new PhysicsComponent().setBodyType(selectBodyType(assets[1])))
            .addComponent(new ColliderComponent().setLayer(PhysicsLayer.OBSTACLE));
    PhysicsUtils.setScaledCollider(obstacle, 1f, 1f);
    //obstacle.scaleHeight(1f);
    if (assets[0] == "") {
      return obstacle;
    }
    // Set obstacle to have a base render component
    ResourceService resourceService = ServiceLocator.getResourceService();
    if (assets[0].endsWith(".png")) {
      // Asset is a texture, add a TextureRenderComponent
      Texture texture = resourceService.getAsset(assets[0], Texture.class);
      obstacle.addComponent(new TextureRenderComponent(texture));
    } else if (assets[0].endsWith(".atlas")) {
      // Asset is an atlas, add an AnimationRenderComponent
      TextureAtlas textureAtlas = resourceService.getAsset(assets[0], TextureAtlas.class);
      AnimationRenderComponent animator = new AnimationRenderComponent(textureAtlas);
      // Add all atlas regions as animations to the component
      for (TextureAtlas.AtlasRegion region : new Array.ArrayIterator<>(textureAtlas.getRegions())) {
        if (!animator.hasAnimation(region.name)) {
          if (region.name.equals("TV_on1") || region.name.equals("TV_onh1")) {
            animator.addAnimation(region.name, 0.1f, Animation.PlayMode.LOOP);
          } else {
            animator.addAnimation(region.name, 1f);
          }
        }
      }
      obstacle.addComponent(animator);
    }
    return obstacle;
  }

  private static BodyType selectBodyType(String ID) {
    switch (ID) {
      case "0":
        return BodyType.StaticBody;
      case "1":
        return BodyType.DynamicBody;
      case "2":
        return BodyType.KinematicBody;
      default:
        logger.error("No valid body type was specified");
        return BodyType.StaticBody;
    }
  }

  private static void addInteraction(String interactionID, Entity obstacle) {
    switch (interactionID) {
      case "0":
        logger.error("Interaction ID for non-interaction entity called");
      case "1":
        obstacle.addComponent(new TvActions());
        break;
      case "2":
        obstacle.addComponent(new DrinkActions())
                .addComponent(new SingleUse());
        break;
      case "3":
        obstacle.addComponent(new PlaceableBoxActions());
        break;
      case "4":
        obstacle.addComponent(new BedActions());
        break;
      case "5":
        obstacle.addComponent(new BananaPeelActions());
        break;
      case "6":
        obstacle.addComponent(new WashingDishesActions());
        break;
      case "7":
        obstacle.addComponent(new PlantActions());
        break;
      case "8":
        obstacle.addComponent(new ShrubActions());
    }
  }

  private static ChoreList getChoreType(String choreID) {
    switch (choreID) {
      case "1":
        return ChoreList.TV;
      case "2":
        return ChoreList.DRINK;
      case "3":
        return ChoreList.PUDDLE;
      case "4":
        return ChoreList.DISHWASHER;
      case "5":
        return ChoreList.PLANT;
      case "6":
        return ChoreList.SHRUB;
      default:
        logger.debug("Invalid choreID provided");
        return null;
    }
  }

  private ObjectFactory() {
    throw new IllegalStateException("Instantiating static util class");
  }
}

