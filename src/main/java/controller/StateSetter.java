package controller;


/**
 * 
 * Using enums to manage the states of the game
 *
 */
public final class StateSetter{

    /**
     * Current state.
     */
    public static StateSetterEnum state = StateSetterEnum.MENU;
    /**
     * True if scene needs to be changed, false otherwise.
     */
    public static boolean change = false;
    /**
     * True to start a new game, false otherwise.
     */
    public static boolean init = false;
    /**
     * True to close the application, false otherwise.
     */
    public static boolean close = false;


    private StateSetter() {

    }

    /**
     * Enumeration to distinguish game states.
     */
    public static enum StateSetterEnum {

        /**
         * MenuView.
         */
        MENU("menuview"),
        /**
         * GameView.
         */
        GAME("gameview"),
        
        OPTIONS("optionsview"),
        /**
         * End.
         */
        END("end");

        private final String name;

        StateSetterEnum(final String stateName) {
            this.name = stateName;
        }

        /**
         * @return
         *      Name of the state as a String.
         */
        public final String getName() {
            return this.name;
        }

    }
}
