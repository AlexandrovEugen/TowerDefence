package data;

import static helpers.Artist.*;

/**
 * Created by Евгений on 29.09.2016.
 */
public class TileGrid {

    public Tile[][] map;
    private int tilesWide, tilesHigh;
    public TileGrid(){
        map = new Tile[20][15];
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length ; j++) {
                map[i][j] = new Tile(i*64, j* 64, 64, 64,  TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newMap){
        this.tilesWide = newMap[0].length;
        this.tilesHigh = newMap.length;
        map = new Tile[tilesWide][tilesHigh];
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j <  map[i].length; j++) {
                if (newMap[j][i] == 0){
                    map[i][j] = new Tile(i*64, j* 64, 64, 64,  TileType.Grass);
                }
                else {
                    map[i][j] = new Tile(i*64, j* 64, 64, 64,  TileType.Dirt);
                }
                switch (newMap[j][i]){
                    case 0:
                        map[i][j] = new Tile(i*64, j* 64, 64, 64,  TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i*64, j* 64, 64, 64,  TileType.Dirt);
                        break;
                    case 2:
                        map[i][j] = new Tile(i*64, j* 64, 64, 64,  TileType.Water);
                        break;

                }
            }
        }
    }
    public void SetTile(int xCoord, int yCoord, TileType type){
        map[xCoord][yCoord] =  new Tile(xCoord* 64, yCoord*64, 64, 64, type);
    }

    public int getTilesWide() {
        return tilesWide;
    }

    public void setTilesWide(int tilesWide) {
        this.tilesWide = tilesWide;
    }

    public int getTilesHigh() {
        return tilesHigh;
    }

    public void setTilesHigh(int tilesHigh) {
        this.tilesHigh = tilesHigh;
    }

    public Tile GetTile(int xPlace, int yPlace){
        if (xPlace <  tilesWide && yPlace < tilesHigh && xPlace > -1 &&yPlace > -1){
            return  map[xPlace][yPlace];
        }
        else {
            return  new Tile(0,0,0,0,TileType.Null);

        }

    }

    public void Draw(){
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length ; j++) {
                map[i][j].Draw();
            }
        }
    }

}
