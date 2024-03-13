package ne.game.objects;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class EasyGame extends BasicGame {

    private ArrayList<MeinUfo> mUfoList;
    private Image background;
    private Crusher crusher;
    private Sound sound;
    private Music music;
    private int lautstaerke =0;
    private int hit=0;
    private int miss=0;
    private AngelCodeFont font;

    public EasyGame() {
        super("EasyGame");
    }
    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1900, 1060, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
        background = new Image("assets/pics/apfelshop.jpg");
        mUfoList=new ArrayList<MeinUfo>();
        for(int i=1; i<=100; i++) {
            mUfoList.add(new MeinUfo(100,199, new Image("assets/pics/refurb-iphone-13-pro-max-graphite-2023.jpg")));
        }
        crusher = new Crusher(950,530,new Image("assets/pics/Clipped_image_20240313_095649.png"),container.getInput());
        music = new Music("testdata/testloop.ogg", true);
        sound = new Sound("assets/sounds/iphone_notification.wav");
        music.loop();
        music.setVolume(10);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        if (input.isKeyPressed(Input.KEY_2)) {
            lautstaerke = lautstaerke +1;
            if (lautstaerke >= 10) lautstaerke = 10;
            music.setVolume(lautstaerke/10f);
        }
        if (input.isKeyPressed(Input.KEY_1)) {
            lautstaerke = lautstaerke - 1;
            if (lautstaerke < 1) lautstaerke = 0;
            music.setVolume(lautstaerke / 10f);
        }

        for (MeinUfo u :mUfoList) {
            if (crusher.intersects(u.getShape())){
                System.out.println("coolide");
                sound.play();
                u.setRandomPosition();
                hit ++;
            }
             if (u.getY()> 1060) {
                 miss++;
                 u.setRandomPosition();
             }

            u.update(delta);
        }
        crusher.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        for(MeinUfo u:mUfoList){
            u.draw(g);
        }
        crusher.draw(g);
        font.drawString(80, 5, "Hit: "+hit, Color.black);
        font.drawString(80, 25, "Miss: "+miss, Color.red);

    }
}