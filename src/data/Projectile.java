package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

/**
 * Created by Евгений on 05.10.2016.
 */
public class Projectile {
    private Texture texture;
    private  float x, y, speed;
    private int damage;

    public Projectile(Texture texture, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public  void update(){
        x += Delta() * speed;
        draw();
    }



    public  void  draw(){
        DrawQuadTex(texture, x, y, 32, 32);
    }
}
