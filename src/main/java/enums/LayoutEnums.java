package enums;


/**
 * 
 * Using enums to regroup all fxml files in path strings.
 *
 */
public enum LayoutEnums{

        /**
         * Main Menu View.
         */
        MAIN_MENU("mainMenu"),

        /**
         * Options View.
         */
        OPTIONS("optionsview");

    private static final String PATH = "/layout/";
    private static final String PATH_END = ".fxml";
    private String fileName;
        
    LayoutEnums(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for the file path.
     * @return the path of the xml file of the current scene
     */
    
    public String getFilePath() {
        return PATH + fileName + PATH_END;
    }

}
