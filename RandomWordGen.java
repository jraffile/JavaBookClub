import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;


public class RandomWordGen{
    public static void main (String [] args){

        ArrayList<String> dictionary = new ArrayList<String>();

        try {
            FileReader dictionaryFile = new FileReader("/Users/Zeus/JavaBookClub/Dictionary.txt");
            BufferedReader reader = new BufferedReader(dictionaryFile);

        String line;
        while ((line = reader.readLine()) != null) {
            dictionary.add(line);
        }
        reader.close();

        int randomIndex = (int)(Math.random()* dictionary.size());

        System.out.println(dictionary.get(randomIndex));

        }
        catch(IOException e){
            System.err.println("IOExpection:"+ e.getMessage());}
    }


}