/*
 * This class represent my data. It provodes a way to add a user
 * plus a password. It then allows to validate the password and to
 * retrieve the list of users
 */
package cit360.mvc.model;

import cit360.mvc.exceptions.UnknownUser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cordeirol
 */
public class PersonData {
    
    private static final Map<String,String> myUserData = new HashMap<>();
   
    
    /**
    * <b>Purpose:</b> adds a new user
    * 
    */
    
    public static void setNewUser(String _userName, String _password){
        
        myUserData.put(_userName,_password );   
        
    }
        
    /**
    * <b>Purpose:</b> validates a password for a given user
    * 
    * @return if valid true or false
    */
    public static boolean isPasswordValid(String _userName, String _password) throws UnknownUser {
        
        // evaluate is user exists
        if ( !myUserData.containsKey(_userName) ) throw new UnknownUser();

        //get password
        String password = myUserData.get(_userName);
                
        //compare strings and return results;
        return password.equals(_password);
    }
    
    /**
    * <b>Purpose:</b> gets all users
    */ 
    public static String[] getUsers() {
  
       return myUserData.keySet().toArray(new String[myUserData.size()]);
        
    }
    
}
