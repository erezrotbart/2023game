//erez rotbart 316080589
package Game;
import Collision.BallRemover;
import Collision.BlockRemover;
import Collision.Collidable;
import Levels.LevelInformation;
import Screens.CountdownAnimation;
import Screens.KeyPressStoppableAnimation;
import Screens.PauseScreen;
import biuoop.GUI;
import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;
import Sprite.Sprite;
import Sprite.Block;
import Sprite.Paddle;
import Sprite.SpriteCollection;
import java.awt.Color;

/**
 * The type GameLevel. constructor and it's methods.
 * task 6
 */
public class GameLevel implements Animation {
    //the gameLevel variables
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;

    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private boolean running;
    private LevelInformation level;
    /**
     * The Screen width.
     */
    static final int SCREEN_WIDTH = 800;
    /**
     * The Screen height.
     */
    static final int SCREEN_HEIGHT = 600;
    /**
     * The Up border block settings.
     */
    static final Block UP_BORDER = new Block(new Point(0, 30), SCREEN_WIDTH,
            30, Color.GRAY);
    /**
     * The Death border settings.
     */
//death-region
    static final Block DEATH_REGION = new Block(new Point(0, 615),
            SCREEN_WIDTH, 10, Color.BLUE);
    /**
     * The Right block border settings.
     */
    static final Block RIGHT_BORDER = new Block(new Point(770, 30), 30,
            600, Color.GRAY);
    /**
     * The Left block border settings.
     */
    static final Block LEFT_BORDER = new Block(new Point(0, 30), 30,
            600, Color.GRAY);
    /**
     * The Ball starting point .
     */
    static final Point BALL_START = new Point(400, 550);
    /**
     * The Radius of the balls.
     */
    static final int RADIUS = 5;
    /**
     * The constants for gaming area.
     */
    static final double GAMING_WIDTH = 740;
    /**
     * The Gaming height.
     */
    static final double GAMING_HEIGHT =
            RIGHT_BORDER.getCollisionRectangle().getHeight();
    /**
     * Instantiates a new GameLevel.
     * <p>
     * this method is the constructor of the GameLevel class .
     * </p>
     *
     * @param level the level
     * @param gui   the gui
     * @param score the score
     */
    public GameLevel(LevelInformation level, GUI gui, Counter score) {
        //game settings
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        //setting the amount of balls and blocks to the current level.
        this.blockCounter = new Counter(level.numberOfBlocksToRemove());
        this.ballCounter = new Counter(level.numberOfBalls());
        //the player's score
        this.score = score;
        this.runner = new AnimationRunner(this.gui);
        //keyboard sensor to pause the game.
        this.keyboard = gui.getKeyboardSensor();
        this.level = level;
    }
    /**
     * Gets score.
     * <p>
     * this method is returning the current score of the game.
     * </p>
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }
    /**
     * Gets remaining blocks.
     * <p>
     * this method is returning the current amount of blocks at the game
     * </p>
     * @return the remaining blocks
     */
    public int getRemainingBlocks() {
        return this.blockCounter.getValue();
    }
    /**
     * Gets remaining balls.
     * <p>
     * this method is returning the current amount of balls at the game
     * </p>
     * @return the remaining balls
     */
    public int getRemainingBalls() {
        return this.ballCounter.getValue();
    }
    /**
     * Add collidable.
     * <p>
     * this method add collodiables objects to the game
     * </p>
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Add sprite.
     * <p>
     * this method add sprites objects to the game.
     * </p>
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Instantiates a new Remove collidable.
     * <p>
     * this method is gettin a coliidiable and remove it from the game's
     * gameEnvironment
     * </p>
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.deleteCollidable(c);
    }

    /**
     * Instantiates a new Remove sprite.
     * <p>
     * this method is getting a Sprite and removing it from the game's
     * SpriteCollection
     * </p>
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.deleteSprite(s);
        //end of removeSprite
    }

    /**
     * Sets paddle.
     * <p>
     * this method is setting the paddle for the level using the
     * LevelInformation the GameLevel gets.
     * </p>
     */
    public void setPaddle() {
        //x and y for the paddle placement
        double x, y = 565;
        //calculating the x to be at the center of the gaming area.
        x = ((double) SCREEN_WIDTH / 2) - ((double) level.paddleWidth() / 2);
        Point upperLeft = new Point(x, y);
        Rectangle p = new Rectangle(upperLeft, (double) level.paddleWidth(),
                20);
        Paddle paddle = new Paddle(p, gui.getKeyboardSensor(),
                level.paddleSpeed());
        //adding the paddle to the level.
        paddle.addToGame(this);
    //end of setpaddle
    }
    /**
     * Create balls on top of paddle.
     * <p>
     * this method is creating the ball to be at the top of the paddle
     * </p>
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(BALL_START, RADIUS, Color.WHITE,
                    this.environment);
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * Create blocks to remove.
     * <p>
     * this method creates the blocks that the user should hit and make the
     * disappear.
     * </p>
     */
    public void createBlocksToRemove() {
        //setting the score on the screen.
        ScoreTrackingListener gameScore = new ScoreTrackingListener(score);
        ScoreIndicator onScreen = new ScoreIndicator(score, this);
        onScreen.addToGame(this);
        //block remover to make the blocks to move when the ball hit them.
        BlockRemover blockRemover = new BlockRemover(this, this.
                blockCounter);
        for (int i = 0; i < this.level.numberOfBlocksToRemove(); i++) {
            Block block = level.blocks().get(i);
            block.addToGame(this);
            //adding the HitListener to each block.
            block.addHitListener(blockRemover);
            block.addHitListener(gameScore);
            //increasing the number of blocks the game knows
        }
    //end of createBlocksToRemove().
    }
    /**
     * Initialize.
     * <p>
     * Initialize a new game: create the Blocks and Ball (and Paddle)and add
     * them to the game.
     * </p>
     */
    public void initialize() {
        //adding the background to Sprites from the current Level-information.
        this.addSprite(level.getBackground());
        //creating 3 balls and had them to the game.
        createBallsOnTopOfPaddle();
        //adding the correct paddle to the level
        setPaddle();
        //adding all the borders to the game.
        UP_BORDER.addToGame(this);
        LEFT_BORDER.addToGame(this);
        RIGHT_BORDER.addToGame(this);
        //setting a death region to the game.
        BallRemover ballRemover = new BallRemover(this,
                this.ballCounter);
        DEATH_REGION.addToGame(this);
        DEATH_REGION.addHitListener(ballRemover);
        //creates blocks to remove
        createBlocksToRemove();
    //end of initialize.
    }

    /**
     * Run.
     * <p>
     * The run() method is the animation loop, that will go over all the
     * sprites, and call drawOn and timePassed on each of them.
     * </p>
     */
    public void run() {
        //making a countdown animation object.
        CountdownAnimation cd = new CountdownAnimation(2,
                3, sprites);
        //showing a countdown before any level
        this.runner.run(cd);
        this.running = true;
        //run current animation
        this.runner.run(this);
    // end of run().
    }

    /**
     * Level over boolean.
     * <p>
     * this method is checking if the level is over because the player win or
     * lose during the game.
     * </p>
     *
     * @return the boolean
     */
    public boolean levelOver() {
        return this.blockCounter.getValue() == 0 || this.ballCounter.getValue()
                == 0;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //drawing all the sprites on the screen and make them move
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //in case the gamer press p the game is paused
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation pause =
                    new KeyPressStoppableAnimation(this.keyboard, biuoop.
                            KeyboardSensor.SPACE_KEY, new PauseScreen());
            this.runner.run(pause);
        }
        /*
         checking if one of the game ending conditions has happened.
         checking if all the balls fell down or the player
         successfully finished the level and hit all the blocks.
        */
        if (levelOver()) {
            //in case the player win the game extra 100 points.
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(100);
            }
            //the game or level has ended due the 2 conditions.
            this.running = shouldStop();
        }
    //end of doOneframe
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
// end of class GameLevel()
}