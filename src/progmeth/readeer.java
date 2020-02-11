package progmeth;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class readeer {
    public static void main(String[] args){

        String path = "test.txt";
        File f = new File(path);
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            String rd = br.readLine();
            while (rd != null){
                System.out.println(rd);
                rd = br.readLine();
            }

        }catch (NoSuchFileException e){
            System.out.println("Error");
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
