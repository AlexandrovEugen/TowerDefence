package data;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by Евгений on 19.10.2016.
 */
public class ProjectileIceball extends Projectile {

    public ProjectileIceball(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void damage(){
        super.getTarget().setSpeed(4f);
        super.damage();
    }
}
