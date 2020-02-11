package Codewar;

import java.util.*;
import java.util.stream.Stream;

public class Inspector {
    public void receiveBulletin(String bulletin) {

       // return deny , document , crime ;
    }


    public String inspect(Map<String, String> person) {
        //////////////////////////// Create database/////////////////////////////////////////
        Map<String,Map<String,String>> database = new HashMap<>();
        for (Map.Entry<String,String> buffer1: person.entrySet()){
            Map<String,String> buffer2 = new HashMap<>();
            String[] subdata = buffer1.getValue().split("\n");
            for (int i = 0; i < subdata.length ; i++) {
                String[] subdata1 = subdata[i].split(":");
                buffer2.put(subdata1[0].strip() , subdata1[2]);
            }
            database.put(buffer1.getKey() , buffer2) ;
        }
        /////////////////////////////////////////////////////////////////////////////////////


        return "";



    }

}





/*


    public static void passport(String raw){
        Map<String , String > data = new HashMap<>();
        String[] buffer = raw.split("\n");
        for (String temp:buffer) {
            String[] sub = temp.split(":");
            data.put(sub[0],sub[1].strip());
        }
        data.forEach();
    }
*/