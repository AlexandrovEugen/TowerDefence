package data;

import helpers.StateManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import ui.UI;

import static helpers.Artist.*;
import ui.UI.*;


public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI gameUI;
    private Menu towerPickerMenu;
    private Texture menuBackGround2;
    private Enemy[]  enemyTipes;

    public Game(TileGrid grid) {
        this.grid = grid;
        this.enemyTipes = new Enemy[2];
        enemyTipes[0] = new EnemyAlien(0,0, grid);
        enemyTipes[1] = new EnemyUFO(0,0, grid);
         this.menuBackGround2 = QuickLoad("menu_background2");
//        waveManager = new WaveManager(new Enemy(QuickLoad("enemyFloating_1"), grid.getTile(0,0), grid, TILE_SIZE, TILE_SIZE, 100, 25), 2, 2);
        waveManager = new WaveManager(enemyTipes,3,3);
        player = new Player(grid, waveManager);
        player.setup();
        setupUI();
    }

    private void setupUI() {
        gameUI = new UI();
        gameUI.createMenu("TowerPicker", 1280, 100, 192, 960, 2, 0);
        towerPickerMenu = gameUI.getMenu("TowerPicker");
        towerPickerMenu.quickAdd("cannon", "cannonGun");
        towerPickerMenu.quickAdd("cannonBlue", "cannonGunBlue");
    }

    private void updateUI(){
        gameUI.draw();
        gameUI.drawString(1320,700, "Lives:" + Player.Lives);
        gameUI.drawString(1320,800, "Cash:" + Player.Cash);
        gameUI.drawString(1320,600,  "Wave:" + waveManager.getWaveNumber());
        gameUI.drawString(1320,900, StateManager.framesInLastSecond + "fps");
        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (towerPickerMenu.isButtonClicked("cannon")) {
                    player.pickTower(new TowerCannonBlue(TowerType.CannonRed, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                }
                if (towerPickerMenu.isButtonClicked("cannonBlue")){
                    player.pickTower(new TowerCannonIce(TowerType.CannonBlue, grid.getTile(0,0), waveManager.getCurrentWave().getEnemyList()));
                }
            }
        }
    }

    public void update(){
        DrawQuadTex(menuBackGround2,1280, 0, 192, 960);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();
    }
}
