/*
 * This is the control layer that know how to talk wioth the model layer
 */
package cit360.mvc.control;

import cit360.mvc.exceptions.UnknownUser;
import cit360.mvc.model.PersonData;

/**
 *
 * @author cordeirol
 */
public class PersonControl {
    
    
    /**
    * <b>Purpose:</b> gets all users saved on the model layer
    * 
    * @return array of users
    */
    public static String[] getAllUsers() {
        
        return PersonData.getUsers();
       
    }
    
    
    
    /**
    * <b>Purpose:</b> add a new user on the model layer
    * 
    */
    public static void addUser(String _user, String _pass) {
    
        // not that the control layer know how to deal with the model
        // an exanple is that the actual API on the model may use
        // dfferent method names. i.e. the mapping does not need to be direct
       
        PersonData.setNewUser(_user, _pass);
        
    }
    
    
    /**
    * <b>Purpose:</b> validates a password for a given user on the nodel layer
    * 
    * @return if valid true or false
    */
    public static boolean isPasswordOk(String _user, String _pass) throws UnknownUser {
        
        
        // I prefer to let the exception go up and then have the view decide what to do,
        // however it feels like the model and the view share a common object, thus have a
        // dependency.
        // I was reading about MVC best practices I could not find a one size fits all.
        return PersonData.isPasswordValid(_user, _pass); 
        
    }
    
}
