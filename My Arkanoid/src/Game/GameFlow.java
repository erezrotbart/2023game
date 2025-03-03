//Erez Rotbart 316080589
package Game;
import Levels.LevelInformation;
import Screens.KeyPressStoppableAnimation;
import Screens.LoseScreen;
import Screens.WinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow.
 * task 6
 * <p>
 * this class is responsible to get levels from a main class and
 * </p>
 */
public class GameFlow {
    //variables for the game flow
    private AnimationRunner ar;
    private biuoop.KeyboardSensor ks;
    private GUI gui;
    private Counter playerScore;

    /**
     * Instantiates a new Game flow.
     * <p>
     * bulder of game flow
     * </p>
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.playerScore = new Counter(0);
    }

    /**
     * Run levels.
     * <p>
     * this method is getting a list of levels and run them one by one.
     * </p>
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        //boolean flag to know if the player won or not.
        boolean win = true;
        Animation loser, winner;
        KeyPressStoppableAnimation theEnd;
        //loop that run over the list of levels end run each level.
        for (LevelInformation levelInfo : levels) {
            //crating new GameLevel and initialize the current level with it.
            GameLevel level = new GameLevel(levelInfo, this.gui, playerScore);
            level.initialize();
            //run the level until the player finished the blocks or balls
            while (level.getRemainingBlocks() != 0 && level.getRemainingBalls()
                    != 0) {
                level.run();
            }
            //checking if the level end because the player lost.
            if (level.getRemainingBalls() == 0) {
                //making a losing scree with players points - and runs it.
                loser = new LoseScreen(playerScore.getValue());
                theEnd = new KeyPressStoppableAnimation(this.ks,
                                biuoop.KeyboardSensor.SPACE_KEY, loser);
                this.ar.run(theEnd);
                win = false;
                break;
            }
        // end for (LevelInformation levelInfo : levels)
        }
        //in case the player won - making a winning screen with players points.
        if (win) {
            winner = new WinScreen(playerScore.getValue());
            theEnd = new KeyPressStoppableAnimation(this.ks,
                            biuoop.KeyboardSensor.SPACE_KEY, winner);
            this.ar.run(theEnd);
        }
        //close the program because the game was finished.
        gui.close();
    }
//end GameFlow
}

