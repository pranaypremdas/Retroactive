package com.deco2800.game.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.deco2800.game.entities.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Box2D collision events fire globally on the physics world, not per-object. The contact listener
 * receives these events, finds the entities involved in the collision, and triggers events on them.
 *
 * <p>On contact start: evt = "collision_start", params = ({@link Fixture} thisFixture, {@link
 * Fixture} otherFixture)
 *
 * <p>On contact end: evt = "collision_end", params = ({@link Fixture} thisFixture, {@link Fixture}
 * otherFixture)
 */
public class PhysicsContactListener implements ContactListener {
  private static final Logger logger = LoggerFactory.getLogger(PhysicsContactListener.class);

  @Override
  public void beginContact(Contact contact) {
    triggerEventOn(contact.getFixtureA(), "pre_collision_start", contact.getFixtureB());
    triggerEventOn(contact.getFixtureB(), "pre_collision_start", contact.getFixtureA());
  }

  @Override
  public void endContact(Contact contact) {
    triggerEventOn(contact.getFixtureA(), "pre_collision_end", contact.getFixtureB());
    triggerEventOn(contact.getFixtureB(), "pre_collision_end", contact.getFixtureA());
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
    // Nothing to do before resolving contact
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
    // Nothing to do after resolving contact
  }

  private void triggerEventOn(Fixture fixture, String evt, Fixture otherFixture) {
    Entity userData = (Entity) fixture.getBody().getUserData();
    if (userData != null) {
      logger.debug("{} on entity {}", evt, userData);
      userData.getEvents().trigger(evt, fixture, otherFixture);
    }
  }
}