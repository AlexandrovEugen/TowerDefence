package ui;

import org.lwjgl.input.Mouse;


import java.util.ArrayList;

import static helpers.Artist.*;

/**
 * Created by Евгений on 14.10.2016.
 */
public class UI {

    private ArrayList<Button> buttons;

    public UI() {
        buttons = new ArrayList<>();
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
        for (Button b: buttons){
            DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }



}
