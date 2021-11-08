package game;

import engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An extension of the World class where the maps and display are accessible
 */
public class WorldAccessible extends World
{
    private boolean Quit = false;
    /**
     *  A list of all the worlds that has been instantiated. A list is used because although there is only one world in
     *  this game, having a list would make it easier to add more world should the need arise. Is not private because it
     *  is required to be accessed from other classes
     */
    static List<WorldAccessible> worlds = new ArrayList<>();

    /**
     * Constructor for the WorldAccessible class
     * @param display is where the game is printed on
     */
    public WorldAccessible(Display display)
    {
        super(display);
        worlds.add(this);
    }

    /**
     * Accessor for the private attribute display
     * @return the private attribute display
     */
    public Display getDisplay(){
        return this.display;
    }

    /**
     * Accessor for the private attribute maps
     * @return maps which contains a list of GameMap
     */
    public List<GameMap> getMaps(){
        return this.maps;
    }

    /**
     * Accessor for the private attribute ActorLocations
     * @return the actorLocation
     */
    public ActorLocations getActorLocations(){
        return this.actorLocations;
    }

    /**
     * Runs the game
     *
     * On each iteration the gameloop does the following:
     *  - displays the player's map
     *  - processes the actions of every Actor in the game, regardless of map
     *
     *  The gameloop will stop on three conditions:
     *  -if the player is no longer on the map, ie. the player is knocked out
     *  -if the user decides to quit the game
     *  -if the player fulfils the win condition
     *
     *  The endgame message that will be printed depends on how the gameloop was stopped
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    @Override
    public void run()
    {
        if(player == null)
            throw new IllegalStateException();

        while (stillRunning() && !isQuit() && !winGameCondition()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            for (Actor actor : actorLocations) {
                processActorTurn(actor);
            }
        }

        display.println(endGameMessage());
    }

    /**
     * Prints the end game message, depending on how the user ended the game
     * @return a String on how the game ended
     */
    @Override
    protected String endGameMessage()
    {
        if(isQuit()){
            return "\nYou have quit the game. You give up too easily!! :( \nGame Quit. ";
        }
        else if(winGameCondition()){
            return "\nCongratulations!!! You have defeated Yugo Maxx and brought him back to Earth to face justice!\nThank you for playing! :)\nGame completed.";
        }
        else{
            return "\nYour character is knocked out. \nBetter luck next time! ;) Get good, mate. \nGame Over.";
        }
    }

    /**
     * Accessor for the private attribute Quit
     * @return true or false
     */
    boolean isQuit()
    {
        return Quit;
    }

    /**
     * Mutator for the private attribute Quit. Is called when the user wants to quit the game
     * @param quit true if user wants to quit the game, false otherwise
     */
    void setQuit(boolean quit)
    {
        Quit = quit;
    }

    /**
     * A method that returns true if the win game condition is fulfilled. In this case, it is only fulfilled if the
     * player has carried the unconcious Yugo Maxx back to earth
     *
     * @return true if win game condition fulfilled, false otherwise
     */
    private boolean winGameCondition(){
        try{
            GameMap playersMap = actorLocations.locationOf(player).map();

            if(playersMap.equals(maps.get(0))){
                for(Item item : player.getInventory()){
                    if(item.toString().equals("Sleeping Yugo Maxx")){
                        return true;
                    }
                }
            }
        }
        catch (Exception NullPointerException)
        {}
        return false;
    }
}
