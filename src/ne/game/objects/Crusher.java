package ne.game.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Crusher extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private int Speed=15;


    public Crusher(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(),this.getY());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta) {
       boolean pressed = false;
        if (input.isKeyDown(Input.KEY_W)) {
            this.setY(this.getY() - Speed);
            if (this.getY() < 0 + this.getHeight()/2) this.setY(this.getHeight()/2);
            pressed = true;
        }
        if (input.isKeyDown(Input.KEY_S)){
            this.setY(this.getY() + Speed);
            if (this.getY() > 1060 - this.getHeight()/2) this.setY(1060 - this.getHeight()/2);
            pressed = true;
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }
    public boolean intersects (Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }

}

