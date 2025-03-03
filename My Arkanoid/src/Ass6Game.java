//Erez Rotbart 316080589
import Game.AnimationRunner;
import Game.GameFlow;
import Levels.DirectHit;
import Levels.Green3;
import Levels.LevelInformation;
import Levels.WideEasy;
import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass6Game.
 * task 6.
 */
public class Ass6Game {
    /**
     * The Screen width.
     */
    static final int SCREEN_WIDTH = 800;
    /**
     * The Screen height.
     */
    static final int SCREEN_HEIGHT = 600;
    /**
     * The Game gui.
     */
    static final GUI GAME_GUI = new GUI("GAME", SCREEN_WIDTH,
            SCREEN_HEIGHT);
    static final int FIX_INPUT = 0;

    /**
     * What level information.
     * <p>
     * this method is getting a number of a level and returning the correct
     * level.
     * </p>
     * @param i the
     * @return the level information
     */
    public static LevelInformation addLevel(int i) {
        //direct hit is level 1
        if (i == 1) {
            return new DirectHit();
        }
        //WideEasy is level 2
        if (i == 2) {
            return new WideEasy();
        }
        //Green 3 is level 3.
        return  new Green3();
    }

    /**
     * The entry point of application.
     * <p>
     * this main is running the game as required in the task.
     * </p>
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //free values that the game flow needs to make a game.
        biuoop.KeyboardSensor keyboard = GAME_GUI.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(GAME_GUI);
        //making a game flow.
        GameFlow makeGame = new GameFlow(ar, keyboard, GAME_GUI);
        //a list for all the levels the user chose.
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        /*
        in the following lines the program is converting the input to a list
        of integers in order to convert those integers to an order of levels
        that the user chose.
         */
        int level;
        for (String arg : args) {
            try {
                //apply if the input is correct (Integer).
                level = Integer.parseInt(arg);
                // in case the user inserts wrong input
            } catch (Exception e) {
                //makes the wrong input to a non-important number.
                level = FIX_INPUT;
            }
            /*
            adding the correct level values (1-3 in case the user made a
            mistake) to the list with a helping method that converts the level
            number to correspond LevelAnimation object .
             */
            if (level > 0 && level < 4) {
                levels.add(addLevel(level));
            }
        }
        /*
        sending the list of levels to a method that will run them at "make
        game" so the GameFlow object will run them.
         */
        makeGame.runLevels(levels);
    // end of public static void main
    }
// end of Ass3Game.
}
