package data;

import api.Entity;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;


public abstract class Projectile implements Entity{


    private Texture texture;
    private  float x, y, speed, xVelocity, yVelocity;
    private int damage;
    private Enemy target;
    private boolean alive;
    private int width;
    private int height;

    public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        this.texture = type.texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = type.speed;
        this.damage = type.damage;
        this.target = target;
        this.alive = true;
        this.xVelocity = 0f;
        this.xVelocity = 0f;
        calculateDirection();

    }

    private void calculateDirection(){
        float totalAllowedMovement = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
        float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2) ;
        float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        float xPercentOfMovement = xDistanceFromTarget/totalDistanceFromTarget;
        xVelocity = xPercentOfMovement;
        yVelocity = totalAllowedMovement - xPercentOfMovement;
        if (target.getX() < x){
            xVelocity *= -1;
        }
        if (target.getY() < y){
            yVelocity *= -1;
        }
    }

    @Override
    public float getX() {
     return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    public Enemy getTarget() {
        return target;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    protected void setAlive(boolean status){
        alive = status;
    }

    protected void damage(){
        target.damage(damage);
        alive = false;
    }

    public  void update(){
        if (alive) {
            calculateDirection();
            x += xVelocity * speed * Delta();
            y += yVelocity * speed * Delta();
            if (checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
                damage();
            }
            draw();
        }

    }



    public  void  draw(){
        DrawQuadTex(texture, x, y, width, height);
    }
}
