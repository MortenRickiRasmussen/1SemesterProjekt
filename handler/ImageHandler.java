package handler;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Morten Ricki Rasmussen 
 */
public class ImageHandler {
    
    public static void draw(Image image, Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
