package data;

import static helpers.Artist.QuickLoad;

/**
 * Created by Евгений on 01.11.2016.
 */
public class EnemyUFO extends Enemy {
    public EnemyUFO(int tileX, int tileY, TileGrid grid) {
        super(tileX, tileY, grid);
        this.setTexture(QuickLoad("enemy64"));
        this.setSpeed(80);

    }
}
