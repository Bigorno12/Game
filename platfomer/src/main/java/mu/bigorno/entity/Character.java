package mu.bigorno.entity;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Getter
public class Character {
    public float x;
    public float y;
    public int width;
    public int height;
    public Rectangle2D.Float hitBox;

    public Character(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawHitBox(Graphics graphics) {
        graphics.setColor(Color.PINK);
        graphics.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    public void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }
}
