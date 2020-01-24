/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cit360.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author cordeirol
 */
public class TimeDate {
    
    
    public static final String getTimestamp(){
    
        SimpleDateFormat df = new SimpleDateFormat( "HH:mm:ss.SSS" );
        
    return df.format( Calendar.getInstance().getTime() );
            
    }
    
}
