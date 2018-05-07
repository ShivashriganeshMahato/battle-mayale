package game;

import mayflower.Actor;
import mayflower.Picture;

/**
 * @author Shivashriganesh Mahato
 */
public class Map extends Actor {
    public Map() {
        Picture picture = new Picture("src/mislaytri.png");
        setPicture(picture);
    }

    @Override
    public void update() {

    }
}
