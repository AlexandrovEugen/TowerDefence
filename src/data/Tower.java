package data;

import api.Entity;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static helpers.Artist.*;
import static helpers.Clock.Delta;


public abstract class Tower implements Entity {

    private float x, y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage,range, cost;

    public Enemy getTarget() {
        return target;
    }

    public Enemy target;
    private Texture[] textures;
    private CopyOnWriteArrayList<Enemy> enemies;
    private boolean targeted;
    public ArrayList<Projectile> projectiles;
    public TowerType type;

    public Tower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies){
        this.type = type;
        this.textures = type.textures;
        this.damage = type.damage;
        this.range = type.range;
        this.cost = type.cost;
        this.firingSpeed = type.firingSpeed;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();
        this.enemies = enemies;
        this.projectiles = new ArrayList<>();
        this.targeted = false;
        this.timeSinceLastShot = 0f;
        this.angle = 0f;
    }

    private Enemy acquireTarget(){
        Enemy closest = null;
        float closestDistance = 10000;
        for (Enemy e:enemies) {
            if (isInRange(e) && findDistance(e) < closestDistance && e.getHiddenHealth() > 0){
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        if (closest != null){
            targeted = true;
        }
        return  closest;
    }

    public int getCost() {
        return cost;
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

    private void shoot() {
        timeSinceLastShot = 0;
 //       projectiles.add(new ProjectileIceball(QuickLoad("iceProjectile"),target, x + TILE_SIZE / 2 - TILE_SIZE / 4, y + TILE_SIZE / 2 - TILE_SIZE / 4, TILE_SIZE / 2, TILE_SIZE / 2, 900, 10));

    }

    public abstract void shoot(Enemy target);

    public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList){
        enemies = newList;
    }



    public void update(){
        if (!targeted || target.getHiddenHealth() < 0){
            target = acquireTarget();
        }
        else {
            angle = calculateAngle();
            if (timeSinceLastShot > firingSpeed) {
                shoot(target);
                timeSinceLastShot = 0;

            }
        }
        if (target == null || !target.isAlive()){
            targeted = false;
        }
        timeSinceLastShot += Delta();
        projectiles.forEach(Projectile::update);
        draw();
    }

    public void draw() {
        DrawQuadTex(textures[0], x, y, width, height);
        if (textures.length > 1) {
            for (int i = 1; i < textures.length; i++) {
                DrawQuadTexRot(textures[i], x, y, width, height, angle);
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
