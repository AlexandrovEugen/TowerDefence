package data;

import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import static helpers.Artist.*;


public class Player {

    private TileGrid grid;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
    private Tower tempTower;
    public static int Cash, Lives;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
        this.leftMouseButtonDown = false;
        this.rightMouseButtonDown = false;
        this.holdingTower = false;
        this.tempTower = null;
        Cash = 0;
        Lives = 0;
    }

    public void setup(){
        Cash = 500;
        Lives = 10;
    }

    public static boolean modifyCash(int amount){
        if (Cash + amount >= 0){
            Cash += amount;
            System.out.println(Cash);
            return true;
        }
        System.out.println(Cash);
        return false;
    }

    public static void modifyLives(int amount){
        Lives += amount;
    }

    public void update(){
        //Update holding tower
        if(holdingTower){
            tempTower.setX(getMouseTile().getX());
            tempTower.setY(getMouseTile().getY());
            tempTower.draw();
        }

        for (Tower t:towerList) {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        if (Mouse.isButtonDown(0) && !leftMouseButtonDown){
            placeTower();
        }
        if (Mouse.isButtonDown(1) && !rightMouseButtonDown){
            if (modifyCash(-55)) {
                towerList.add(new TowerCannonIce(TowerType.CannonBlue, grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE), waveManager.getCurrentWave().getEnemyList()));
            }
        }
        leftMouseButtonDown  = Mouse.isButtonDown(0);
        rightMouseButtonDown = Mouse.isButtonDown(1);
        while (Keyboard.next()){
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(-0.2f);
            }
        }
    }

    private void placeTower(){
        Tile currentTile = getMouseTile();
        if (holdingTower) {
            if (!currentTile.getOccupied() && modifyCash(tempTower.getCost())) {
                towerList.add(tempTower);
                currentTile.setOccupied(true);
                holdingTower = false;
                tempTower = null;
            }
        }
    }

    public void pickTower(Tower t){
        tempTower = t;
        holdingTower = true;
    }

    private  Tile getMouseTile(){
        return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
    }

}
