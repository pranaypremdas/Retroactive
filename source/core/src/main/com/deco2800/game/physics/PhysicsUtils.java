package com.deco2800.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.deco2800.game.entities.Entity;
import com.deco2800.game.physics.components.ColliderComponent;
import com.deco2800.game.physics.components.HitboxComponent;
import com.deco2800.game.physics.components.PhysicsComponent;

public class PhysicsUtils {

  // Set the collider to the base of the entity, scaled relative to the entity size.
  public static void setScaledCollider(Entity entity, float scaleX, float scaleY) {
    Vector2 boundingBox = entity.getScale().cpy().scl(scaleX, scaleY);
    entity
        .getComponent(ColliderComponent.class)
        .setAsIsoAligned(
            boundingBox, PhysicsComponent.AlignX.CENTER, PhysicsComponent.AlignY.BOTTOM);
  }

  // Set the collider to the base of the entity, scaled relative to the entity size.
  public static void setScaledHitbox(Entity entity, float scaleX, float scaleY) {
    Vector2 boundingBox = entity.getScale().cpy().scl(scaleX, scaleY);
    entity
            .getComponent(HitboxComponent.class)
            .setAsIsoAligned(
                    boundingBox, PhysicsComponent.AlignX.CENTER, PhysicsComponent.AlignY.BOTTOM);
  }

  public static void realignScaledCollider(Entity entity, float scaleX, float scaleY, PhysicsComponent.AlignX var1,
                                           PhysicsComponent.AlignY var2 ) {
    Vector2 boundingBox = entity.getScale().cpy().scl(scaleX, scaleY);
    entity
            .getComponent(ColliderComponent.class)
            .setAsIsoAligned(
                    boundingBox, var1, var2);
  }

  public static void setColliderShape(Entity entity, float leftSides, float rightSides) {
      entity.getComponent(ColliderComponent.class)
              .setIsoShape(leftSides, rightSides);
  }

  private PhysicsUtils() {
    throw new IllegalStateException("Instantiating static util class");
  }
}
