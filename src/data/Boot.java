package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Евгений on 29.09.2016.
 */
public class Boot {
    public Boot(){
        Display.setTitle("Tower Defence");
        try {
            Display.setDisplayMode(new DisplayMode(600,400));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,600,0,400, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        while (!Display.isCloseRequested()){
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
    public static void main(String[] args){
        new Boot();
    }
}
