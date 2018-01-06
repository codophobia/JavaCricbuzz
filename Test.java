
package test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import cricbuzz.Cricbuzz;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException{
        Cricbuzz c = new Cricbuzz();
        Vector<HashMap<String,String>> matches = c.matches();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(matches);
        //System.out.println(json);
        
        Integer i = 0;
        for(i = 0; i < matches.size(); i++) {
            String id = matches.get(i).get("id");
            Map<String,Map> score = c.livescore(id);
            json = gson.toJson(score);
            //System.out.println(json);
            
            Map<String,Map> comm = c.commentary(id);
            json = gson.toJson(comm);
            System.out.println(json);
            
            break;
        }
        
        
        
       
    }
}
    

