package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.QuickLoad;

/**
 * Created by Евгений on 21.10.2016.
 */
public enum ProjectileType {

    CannonBall(QuickLoad("bullet"),10, 500),
    IceBall(QuickLoad("iceProjectile"),6,450);

    Texture texture;
    int damage;
    float speed;

    ProjectileType(Texture texture, int damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}
