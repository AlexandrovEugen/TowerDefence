package data;

import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

/**
 * Created by Евгений on 30.09.2016.
 */
public class Enemy {

    private int width, height, health;
    private float x,y, speed;
    Texture texture;
    Tile startTile;
    private boolean first = true;

    public Enemy(Texture texture,Tile startTile, int width, int height, float speed) {
        this.texture = texture;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
    }

    public void Update(){
        if (first)
            first = false;
        else
        x += Delta()* speed;
    }

    public void Draw(){
        DrawQuadTex(texture,x, y, width, height);
    }


    public Enemy(){}
}
