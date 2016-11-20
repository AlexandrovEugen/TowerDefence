package data;


import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public enum TowerType {

    CannonRed(new Texture[]{QuickLoad("cannonBase"),QuickLoad("cannonGun")}, ProjectileType.CannonBall, 10,1000000000, 3, -20),
    CannonBlue(new Texture[]{QuickLoad("cannonBaseBlue"),QuickLoad("cannonGunBlue")},ProjectileType.IceBall, 30, 1000000000, 3, -40);

    Texture[] textures;
    ProjectileType projectileType;
    int damage, range,cost;
    float firingSpeed;

    TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, int cost) {
        this.textures = textures;
        this.projectileType = projectileType;
        this.damage = damage;
        this.range = range;
        this.firingSpeed = firingSpeed;
        this.cost = cost;
    }
}
