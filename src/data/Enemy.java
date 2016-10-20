package data;


import org.newdawn.slick.opengl.Texture;
import api.*;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.*;


public class Enemy implements Entity {

    private int width, height, currentCheckPoint;
    private float x,y, speed, health, startHealth;
    private Texture texture, healthBackground, healthForeground, healthBorder;
    private Tile startTile;
    private boolean first = true, alive = true;
    private TileGrid grid;
    private int[] directions;
    private ArrayList<Checkpoint> checkpoints;


    public Enemy(Texture texture,Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
        this.texture = texture;
        this.healthBackground = QuickLoad("healthBackGround");
        this.healthForeground = QuickLoad("healthForeground");
        this.healthBorder = QuickLoad("healthBorder");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.health = health;
        this.startHealth = health;
        this.speed = speed;
        this.health = health;
        this.grid = grid;
        this.checkpoints = new ArrayList<>();
        this.directions = new int[2];
        //x direction
        this.directions[0] = 0;
        //y direction
        this.directions[1] = 0;
        directions = findNextD(startTile);
        this.currentCheckPoint = 0;
        populateCheckpointList();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public  TileGrid getTileGrid(){
        return grid;
    }

    public boolean isAlive() {
        return alive;
    }

    public void damage(int amount){
        health -=  amount;
        if (health <= 0){
            die();
        }
    }

    private void die(){
        alive = false;
    }

    public void update(){
        if (first)
            first = false;
        else {
            if (checkPointReached()) {
                if (currentCheckPoint + 1 == checkpoints.size())
                    die();
                else
                    currentCheckPoint++;
            } else {
                x += Delta() * checkpoints.get(currentCheckPoint).getxDirection() * speed;
                y += Delta() * checkpoints.get(currentCheckPoint).getyDirection() * speed;
            }
        }
    }

    private boolean checkPointReached(){
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckPoint).getTile();
        // Check if position reached tile  within variance of 3 (arbitrary)
        if (x > t.getX() -  3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3){
            reached = true;
            x = t.getX();
            y = t.getY();
        }

        return reached;
    }

    private Checkpoint findNextC(Tile s, int[] dir){
        Tile next = null;
        Checkpoint c;
        //Boolean to decide if next checkpoint is found
        boolean found = false;
        //Integer to increment each loop
        int counter = 1;
        while (!found){
            if (s.getXPlace() + dir[0]*counter == grid.getTilesWide() || s.getYPlace() + dir[1]*counter == grid.getTilesHigh() || s.getType() != grid.getTile(s.getXPlace() + dir[0]*counter, s.getYPlace() + dir[1]*counter).getType()){
                found  = true;
                //ove counter back 1 to find tile before new tiletype
                counter -= 1;
                next = grid.getTile(s.getXPlace() + dir[0]*counter, s.getYPlace() + dir[1]*counter);
            }
            counter++;
        }
        c = new Checkpoint(next, dir[0], dir[1]);
        return c;
    }
    private void populateCheckpointList(){
        checkpoints.add(findNextC(startTile, directions = findNextD(startTile)));
        int counter = 0;
        boolean cont = true;
        while (cont){
            int[] currentD = findNextD(checkpoints.get(counter).getTile());
            //Check if next direction/checkpoint1 exists, end after 20 checkpoints(arbitrary)
            if (currentD[0] == 2 || counter == 20){
                cont= false;
            }
            else {
                checkpoints.add(findNextC(checkpoints.get(counter).getTile(), directions = findNextD(checkpoints.get(counter).getTile())));
            }
            counter++;
        }
    }

    private int[] findNextD(Tile s){
        int[] dir =  new int[2];
        Tile u = grid.getTile(s.getXPlace(), s.getYPlace() -1);
        Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
        Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());
        Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);

        if (s.getType() == u.getType() && directions[1] != 1){
            dir[0] = 0;
            dir[1] = -1;
        }
        else if (s.getType() == r.getType()  && directions[0] != -1){
            dir[0] = 1;
            dir[1] = 0;
        }
        else if (s.getType() == d.getType()&& directions[1] !=  -1){
            dir[0] = 0;
            dir[1] = 1;
        }
        else if (s.getType() == l.getType()&& directions[0] != 1){
            dir[0] = -1;
            dir[1] = 0;
        }
        else {
            dir[0] = 2;
            dir[1] = 2;
        }
        return dir;
    }
    public void draw() {
        float healthPercentage = health / startHealth;
        DrawQuadTex(texture,x, y, width, height);
        DrawQuadTex(healthBackground,x,y - 2, width,8);
        DrawQuadTex(healthForeground,x,y - 2,TILE_SIZE * healthPercentage,8);
        DrawQuadTex(healthBorder,x,y - 2,width,8);
    }


}
