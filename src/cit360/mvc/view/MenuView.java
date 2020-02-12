/*
 * This creates the user view to interact with the user
 */
package cit360.mvc.view;

import cit360.mvc.control.PersonControl;
import cit360.mvc.exceptions.UnknownUser;
import java.util.Scanner;


/**
 *
 * @author cordeirol
 */
public class MenuView {

    private static final int _MAX_OPTION = 5;
    private static final String _MENU = "\n"
            + "**********************************\n"
            + "*    USER PASSWORD VALIDATION    *\n"
            + "**********************************\n"
            + " 1 - List Users\n"
            + " 2 - Add User\n"
            + " 3 - Validate Password\n"
            + " 4 - Save User Data\n"
            + " 5 - Exit\n";

    private final static Scanner keyboard = new Scanner(System.in);

    /**
     * <b>Purpose:</b> displays the menu
     */
    public static void displayMenuView() {

        int menuOption = 0;

        do {

            // Display the menu
            System.out.println(_MENU);

            // Prompt the user and get the user’s input
            menuOption = getOption();

            // Perform the desired action
            doOption(menuOption);

        } while (menuOption != _MAX_OPTION);

    }

   /**
    * <b>Purpose:</b> gets the user's input
    *
    * @return the option selected
    */
    private static int getOption() {

        int inputValue = 0;

        do {

            System.out.format("\nPlease enter an option(1 - %d):", _MAX_OPTION);
            
            //I just used nextLine so the rest of the code worked. Scanner has this delimer
            //functionlity and a nextInt has a strange behavior if the next call on Scanner is
            //a nextLine()
            
            try {
                inputValue = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException ex) {
                inputValue = 0;
            }

            if (inputValue < 1 || inputValue > _MAX_OPTION) {
                System.out.format("\nError: input value must be between 1 and %d.", _MAX_OPTION);
            }

        } while (inputValue < 1 || inputValue > _MAX_OPTION);

        return inputValue;
    }

    /**
     * <b>Purpose:</b> executes option
     *
     * @param the option
     */
    private static void doOption(int option) {

        switch (option) {

            case 1: // List Users

                String[] users = PersonControl.getAllUsers();

                System.out.print("\n******************************\n* List Users\n*\n");
                for (int i = 0; i < users.length; i++) {                    
                    System.out.print("  ->");
                    System.out.println(users[i]);
                }
                
                break;

            case 2: // Add a User

                System.out.print("\n******************************\n* Add Users\n*\n*\nUsername: ");
                String addUsername = keyboard.nextLine();

                System.out.print("Password: ");
                String addPassword = keyboard.nextLine();

                PersonControl.addUser(addUsername, addPassword);

                break;

            case 3: // Validate password

                System.out.print("\n******************************\n* Check Password\n*\n*\nUsername: ");
                String valUsername = keyboard.nextLine();

                System.out.print("Password: ");
                String valPassword = keyboard.nextLine();

                
                try {
                    
                    boolean isValid = PersonControl.isPasswordOk(valUsername, valPassword);
                    
                    if (isValid){
                        System.out.println("\nPassord is CORRECT for "+valUsername); }
                    else {
                        System.out.println("\nPassord is WRONG for "+valUsername); 
                    }
                
                } catch (UnknownUser ex) {
                   System.out.println("\nEXCEPTION \"" + valUsername + "\" does not exist. "); 
                }

                break;

            case 4: // save user data

                System.out.println("\nIt will implemented in the future, sorry.");
                break;
            case 5:

                System.out.println("\nThanks for using. Goodbye.");
                break;
            default:

                System.out.println("\nInvalid option.");

        }
    }
}
