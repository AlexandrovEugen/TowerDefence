package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.*;


public class TowerCannon{
    private float x,y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage, range;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;
    private boolean targeted;


    public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.baseTexture = baseTexture;
        cannonTexture = QuickLoad("cannonGun");
        this.startTile = startTile;
        this.damage = damage;
        this.range = range;
        this.width =  startTile.getWidth();
        this.height = startTile.getHeight();
        this.firingSpeed = 3;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
        this.enemies = enemies;
        this.targeted = false;
    }

    private Enemy acquireTarget(){
        Enemy closest = null;
        float closestDistance = 1000000;
        for (Enemy e:enemies) {
            if (isInRange(e) && findDistance(e) < closestDistance){
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        if (closest != null){
            targeted = true;
        }
        return  closest;
    }

    private boolean isInRange(Enemy e){
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        if (xDistance < range && yDistance < range){
            return true;
        }
        return  false;
    }

    private float findDistance(Enemy e){
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        return xDistance + yDistance;
    }

    private float calculateAngle(){
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(angleTemp) - 90;
    }

    public void updateEnemyList(ArrayList<Enemy> newList){
        enemies = newList;
    }

    public void update(){
        if (!targeted){
            target = acquireTarget();
        }
        if (target == null || target.isAlive() == false){
            targeted = false;
        }
        timeSinceLastShot += Delta();
        if (timeSinceLastShot > firingSpeed){
            shoot();
        }
        projectiles.forEach(Projectile::update);

        angle = calculateAngle();
        draw();
    }

    private void shoot() {
        timeSinceLastShot = 0;
        projectiles.add(new ProjectileIceball(QuickLoad("bullet"),target, x + TILE_SIZE / 2 - TILE_SIZE / 4, y + TILE_SIZE / 2 - TILE_SIZE / 4, 32, 32, 900, 10));

    }

    public void draw(){
        DrawQuadTex(baseTexture,x ,y, width, height);
        DrawQuadTexRot(cannonTexture,x,y, width,height, angle);
    }
}

