package data;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import ui.UI;
import ui.UI.*;

import static helpers.Artist.*;
import static helpers.Leveler.*;



public class Editor {

    private TileGrid grid;
    private int index;
    private TileType[] types;
    private UI editorUI;
    private Menu tilePickerMenu;
    private Texture menuBackground;

    public Editor() {
        this.grid = loadMap("mapTest");
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.index = 0;
        this.menuBackground = QuickLoad("menu_background2");
        setupUI();
    }

    private void setupUI(){
        editorUI = new UI();
        editorUI.createMenu("TilePicker", 1280, 100, 192,960, 2, 0);
        tilePickerMenu = editorUI.getMenu("TilePicker");
        tilePickerMenu.quickAdd("Grass","grass64");
        tilePickerMenu.quickAdd("Dirt", "dirt64");
        tilePickerMenu.quickAdd("Water", "water64");

    }
    public void update(){
        draw();

        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (tilePickerMenu.isButtonClicked("Grass")) {
                    index = 0;
                }else if (tilePickerMenu.isButtonClicked("Dirt")){
                    index = 1;
                }
                else if (tilePickerMenu.isButtonClicked("Water")){
                    index = 2;
                }
                else {
                    setTile();
                }
            }
        }

       while (Keyboard.next()){
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
                moveIndex();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()){
                saveMap("mapTest", grid);
            }
        }
    }

    private void draw(){
        DrawQuadTex(menuBackground, 1280, 0, 192, 960);
        grid.draw();
        editorUI.draw();
    }

    private void setTile(){
        grid.setTile((int) Math.floor(Mouse.getX()/64),(int) Math.floor((HEIGHT  - Mouse.getY() -1)/TILE_SIZE), types[index]);
    }

    private void moveIndex(){
        index++;
        if (index > types.length - 1){
            index = 0;
        }
    }

}
