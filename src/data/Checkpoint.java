package data;

/**
 * Created by Евгений on 01.10.2016.
 */
public class Checkpoint {
    private Tile tile;
    private int xDirection, yDirection;

    public Tile getTile() {
        return tile;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public Checkpoint(Tile tile, int xDirection, int yDirection) {

        this.tile = tile;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }
}
