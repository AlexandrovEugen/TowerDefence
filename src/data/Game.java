package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

/**
 * Created by Евгений on 05.10.2016.
 */
public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;



    public Game(int[][] map) {
        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(QuickLoad("enemy64"), grid.GetTile(0,0), grid, 64, 64, 100), 2, 2);
        player = new Player(grid, waveManager);
    }

    public void update(){
        grid.Draw();
        waveManager.update();
        player.update();
    }
}
