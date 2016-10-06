package helpers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glMatrixMode;

/**
 * Created by Евгений on 29.09.2016.
 */
public class Artist {

    public static final int WIDTH = 1280, HEIGHT = 960;

    public static void BeginSession(){
        Display.setTitle("Tower Defence");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,WIDTH,HEIGHT,0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }

    public static  void  DrawQuad(float x, float y, float width, float height){
        glBegin(GL_QUADS);
        glVertex2f(x,y); //Top left corner
        glVertex2f(x + width,y); //Top right corner
        glVertex2f(x + width ,y +height); //Bottom right corner
        glVertex2f(x,y + height);// Bottom left corner
        glEnd();
    }
    public static void DrawQuadTex(Texture tex, float x, float y, float width, float height){
        tex.bind();
        glTranslatef(x,y,0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width,0);
        glTexCoord2f(1,1);
        glVertex2f(width, height);
        glTexCoord2f(0,1);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }


    public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float angle){
        tex.bind();
        glTranslatef(x + width/2,y + height/2,0);
        glRotatef(angle,0,0,1);
        glTranslatef(-width/2, -height/2, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width,0);
        glTexCoord2f(1,1);
        glVertex2f(width, height);
        glTexCoord2f(0,1);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }


    public static Texture LoadTexture(String path, String filetype){
        Texture tex  = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(filetype, in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  tex;
    }

    public static Texture QuickLoad(String name){
        Texture tex = null;
        tex = LoadTexture("res/" +  name + ".png", "PNG");
        return  tex;
    }
}
