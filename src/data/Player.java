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
    private boolean leftMouseButtonDown, rightMouseButtonDown = false;


    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
        this.leftMouseButtonDown = false;
        this.rightMouseButtonDown = false;
    }


    public void update(){
        for (Tower t:towerList) {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        if (Mouse.isButtonDown(0) && !leftMouseButtonDown){
            towerList.add(new TowerCannonBlue(TowerType.CannonRed, grid.getTile(Mouse.getX()/TILE_SIZE,(HEIGHT - Mouse.getY() - 1) / TILE_SIZE), waveManager.getCurrentWave().getEnemyList()));

        }
        if (Mouse.isButtonDown(1) && !rightMouseButtonDown){
            towerList.add(new TowerIce(TowerType.CannonBlue, grid.getTile(Mouse.getX()/TILE_SIZE,(HEIGHT - Mouse.getY() - 1) / TILE_SIZE), waveManager.getCurrentWave().getEnemyList()));
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

}
