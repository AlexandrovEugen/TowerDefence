package data;


import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.*;


/**
 * Created by Евгений on 30.09.2016.
 */
public class Enemy {

    private int width, height, health, currentCheckPoint;
    private float x,y, speed;
    private Texture texture;
    private Tile startTile;
    private boolean first = true;
    private TileGrid grid;
    private int[] directions;
    private ArrayList<Checkpoint> checkpoints;


    public Enemy(Texture texture,Tile startTile, TileGrid grid, int width, int height, float speed) {
        this.texture = texture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
        this.grid = grid;
        this.checkpoints = new ArrayList<>();
        this.directions = new int[2];
        //x direction
        this.directions[0] = 0;
        //y direction
        this.directions[1] = 0;
        directions = FindNextD(startTile);
        this.currentCheckPoint = 0;
        PopulateCheckpointList();
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
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
//    private boolean pathContinues(){
//        boolean answer = true;
//        Tile myTile = grid.GetTile((int)(x/64),(int)(y/ 64));
//        Tile nextTile = grid.GetTile((int)(x/64) + 1,(int)(y/ 64));
//        if (myTile.getType() !=  nextTile.getType()){
//            answer = false;
//        }
//        return  answer;
//    }

    public void Update(){
        if (first)
            first = false;
        else {
            if (CheckPointReached()) {
                currentCheckPoint++;
            } else {
                x += Delta() * checkpoints.get(currentCheckPoint).getxDirection() * speed;
                y += Delta() * checkpoints.get(currentCheckPoint).getyDirection() * speed;
            }
        }
    }

    private boolean CheckPointReached(){
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

    private Checkpoint FindNextC(Tile s, int[] dir){
        Tile next = null;
        Checkpoint c = null;

        //Boolean to decide if next checkpoint is found
        boolean found = false;
        //Integer to increment each loop
        int counter = 1;
        while (!found){
            if (s.getType() != grid.GetTile(s.getXPlace() + dir[0]*counter, s.getYPlace() + dir[1]*counter).getType()){
                found  = true;
                //ove counter back 1 to find tile before new tiletype
                counter -= 1;
                next = grid.GetTile(s.getXPlace() + dir[0]*counter, s.getYPlace() + dir[1]*counter);
            }
            counter++;
        }
        c = new Checkpoint(next, dir[0], dir[1]);
        return c;
    }
    private void PopulateCheckpointList(){
        checkpoints.add(FindNextC(startTile, directions = FindNextD(startTile)));
        int counter = 0;
        boolean cont = true;
        while (cont){
            int[] currentD = FindNextD(checkpoints.get(counter).getTile());
            //Check if next direction/checkpoint1 exists, end after 20 checkpoints(arbitrary)
            if (currentD[0] == 2 || counter == 20){
                cont= false;
            }
            else {
                checkpoints.add(FindNextC(checkpoints.get(counter).getTile(), directions = FindNextD(checkpoints.get(counter).getTile())));
            }
            counter++;
        }
    }

    private int[] FindNextD(Tile s){
        int[] dir =  new int[2];
        Tile u = grid.GetTile(s.getXPlace(), s.getYPlace() -1);
        Tile r = grid.GetTile(s.getXPlace() + 1, s.getYPlace());
        Tile l = grid.GetTile(s.getXPlace() - 1, s.getYPlace());
        Tile d = grid.GetTile(s.getXPlace(), s.getYPlace() + 1);

        if (s.getType() == u.getType()){
            dir[0] = 0;
            dir[1] = -1;
        }
        else if (s.getType() == r.getType()){
            dir[0] = 1;
            dir[1] = 0;
        }
        else if (s.getType() == d.getType()){
            dir[0] = 0;
            dir[1] = 1;
        }
        else if (s.getType() == l.getType()){
            dir[0] = -1;
            dir[1] = 0;
        }
        else {
            dir[0] = 2;
            dir[1] = 2;
            System.out.println("NO WAY");
        }
        return dir;
    }

    public void Draw(){
        DrawQuadTex(texture,x, y, width, height);
    }


}
