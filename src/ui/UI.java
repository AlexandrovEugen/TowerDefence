package ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;


import java.awt.*;
import java.util.ArrayList;

import static helpers.Artist.*;

/**
 * Created by Евгений on 14.10.2016.
 */
public class UI {

    private ArrayList<Button> buttons;
    private ArrayList<Menu> menuList;
    private TrueTypeFont font;
    private Font awtFont;

    public UI() {
        buttons = new ArrayList<>();
        menuList = new ArrayList<>();
        awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
    }
    public void drawString(int x, int y, String text){
        font.drawString(x,y,text);
    }

    public void  addButton(String name, String textureName, int x, int y){
        buttons.add(new Button(name,QuickLoad(textureName), x,y));
    }

    public boolean isButtonClicked(String buttonName){
        Button b = getButton(buttonName);
        float mouseY = HEIGHT - Mouse.getY() - 1;
        if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY() && mouseY < b.getY() + b.getHeight()){
            return true;
        }
        return false;
    }

    private Button getButton(String buttonName){
        for (Button b:buttons) {
            if (b.getName().equals(buttonName)){
                return  b;
            }
        }
        return null;
    }

    public void draw(){
        for (Button b:buttons) {
            DrawQuadTex(b.getTexture(),b.getX(),b.getY(),b.getWidth(),b.getHeight());
            }

        menuList.forEach(Menu::draw);
    }

    public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight){
        menuList.add(new Menu(name,x,y,width, height, optionsWidth, optionsHeight));
    }

    public Menu getMenu(String name){
        for (Menu m:menuList) {
            if (name.equals(m.getName())){
                return m;
            }
        }
        return null;
    }

    public class Menu{
        private ArrayList<Button> menuButtons;
        private int x,y,width, height, buttonsAmount, optionsWidth, optionsHeight, padding;
        String name;

        public Menu(String name,int x, int y,int width, int height, int optionsWidth, int optionsHeight) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.optionsWidth = optionsWidth;
            this.optionsHeight = optionsHeight;
            this.padding = (width - (optionsWidth * TILE_SIZE))/(optionsWidth + 1);
            this.buttonsAmount = 0;
            this.menuButtons = new ArrayList<>();
        }
        public void addButton(Button b){
            setButton(b);
        }

        public void quickAdd(String buttonName, String buttonTextureName){
            Button b = new Button(buttonName, QuickLoad(buttonTextureName), 0, 0);
            setButton(b);
        }
        private void setButton(Button b){
            if (optionsWidth != 0){
                b.setY(y + (buttonsAmount / optionsWidth) * TILE_SIZE);
            }
            b.setX(x + (buttonsAmount % 2) * (padding + TILE_SIZE) + padding);

            buttonsAmount++;
            menuButtons.add(b);
        }

         public void draw(){
             for (Button b: menuButtons){
                 DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
             }
         }
        public boolean isButtonClicked(String buttonName){
            Button b = getButton(buttonName);
            float mouseY = HEIGHT - Mouse.getY() - 1;
            if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY() && mouseY < b.getY() + b.getHeight()){
                return true;
            }
            return false;
        }

        private Button getButton(String buttonName){
            for (Button b:menuButtons) {
                if (b.getName().equals(buttonName)){
                    return  b;
                }
            }
            return null;
        }
         public String getName(){
             return name;
         }
    }
}
