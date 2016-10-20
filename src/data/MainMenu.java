package data;

import helpers.StateManager;
import helpers.StateManager.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import ui.UI;

import static helpers.Artist.*;

public class MainMenu {
    private Texture background;
    private UI mainUI;

    public MainMenu(){
        background = QuickLoad("mainmenu");
        mainUI = new UI();
        mainUI.addButton("Play", "playButton", WIDTH/2 - 128, (int)(HEIGHT* 0.45f));
        mainUI.addButton("Editor", "editorButton", WIDTH/2 - 128, (int)(HEIGHT* 0.55f));
        mainUI.addButton("Quit", "quitButton", WIDTH/2 - 128, (int)(HEIGHT* 0.65f));
    }

    private void updateButtons(){
        if (Mouse.isButtonDown(0)){
            if (mainUI.isButtonClicked("Play")){
                StateManager.setState(GameState.GAME);
            }
            if (mainUI.isButtonClicked("Editor")){
                StateManager.setState(GameState.EDITOR);
            }
            if (mainUI.isButtonClicked("Quit")){
                System.exit(0);
            }
        }
    }

    public void update(){
        DrawQuadTex(background,0,0, 2048, 1024);
        mainUI.draw();
        updateButtons();
    }

}
