package data;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by Евгений on 21.10.2016.
 */
public class ProjectileCannonBall extends Projectile{
    public ProjectileCannonBall(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void damage(){
        super.damage();
    }
}
