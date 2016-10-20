package data;

import static helpers.Artist.*;



public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;



    public Game(int[][] map) {
        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(QuickLoad("enemy64"), grid.getTile(0,0), grid, TILE_SIZE, TILE_SIZE, 100, 25), 2, 2);
        player = new Player(grid, waveManager);
    }

    public void update(){
        grid.draw();
        waveManager.update();
        player.update();
    }
}
