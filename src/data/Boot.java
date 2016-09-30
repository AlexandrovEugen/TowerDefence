package data;

import helpers.Clock;
import org.lwjgl.opengl.Display;


import static helpers.Artist.*;


/**
 * Created by Евгений on 29.09.2016.
 */
public class Boot {
    public Boot(){
        BeginSession();
        int[][] map = {
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        TileGrid grid = new TileGrid(map);
        grid.SetTile(3,4,grid.GetTile(10,2).getType());
        Enemy e = new Enemy(QuickLoad("enemy64"), grid.GetTile(10,10), 64, 64, 1);
        while (!Display.isCloseRequested()){
            Clock.update();
            e.Update();
            grid.Draw();
            e.Draw();

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }



    public static void main(String[] args){
        new Boot();
    }
}
