package com.bikash.student;

/**
 * Created by root on 10/30/16.
 */

public class EmailProcess {
    public static String ProcessEmail(String email){
        char[] mail = email.toCharArray();
        int n = email.length();
        String newMail = new String();

        for(int i=0; i<n; i++){
            if(mail[i]=='@' || mail[i]=='.'){
                newMail+='_';
            } else {
                newMail+=mail[i];
            }
        }
        return newMail.toString();
    }
}
