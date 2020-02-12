/*
 * This starts the program
 */
package cit360.mvc;

import cit360.mvc.view.MenuView;

/**
 *
 * @author cordeirol
 */
public class Sample {
    
    /**
    * <b>Purpose:</b> starts the MVC sample
    */
    public static void startSample(){
    
    //the View, Model and Control are all using static methods
    //start the view
    MenuView.displayMenuView();

    }        
}
