package helpers;

import data.Tile;
import data.TileGrid;
import data.TileType;

import java.io.*;


public class Leveler {

    public static void saveMap(String mapName, TileGrid grid){
        String mapData = "";
        for (int i = 0; i < grid.getTilesWide(); i++) {
            for (int j = 0; j < grid.getTilesHigh(); j++) {
                mapData += getTileID(grid.getTile(i,j));
            }
        }
        try {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TileType getTileType(String typeID){
        TileType type = TileType.Null;
        switch (typeID){
            case "0":
                type = TileType.Grass;
                break;
            case  "1":
                type = TileType.Dirt;
                break;
            case "2":
                type  = TileType.Water;
                break;
            case "3":
                type = TileType.Null;
                break;
        }
        return type;
    }

    public static TileGrid loadMap(String mapName){
        TileGrid tileGrid = new TileGrid();
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for (int i = 0; i < tileGrid.getTilesWide(); i++) {
                for (int j = 0; j < tileGrid.getTilesHigh(); j++) {
                    tileGrid.setTile(i,j, getTileType(data.substring(i * tileGrid.getTilesHigh() + j, i * tileGrid.getTilesHigh() + j + 1)));
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return tileGrid;
    }

    public static String getTileID(Tile t){
        String ID = "E";
        switch (t.getType()){
            case Grass:
                ID = "0";
                break;
            case Dirt:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
            case Null:
                ID = "3";
                break;
        }
        return ID;
    }
}
