/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Jam
 */
public class QuizGenerator{
    Boolean reload = false;
    
    String url, urlMultiple;
    private String inline = "";
    private String multipleQuizInline = "";
    public QuizGenerator(String url, String urlMultiple) {
        this.url = url;
        this.urlMultiple = urlMultiple;
        startConnection();
    }
    
    public void reloadAnswers() {
        startConnection();
    }
   

    private void startConnection() {
        try {
            URL link = new URL(url);
            URL multipleChoiceLink = new URL(urlMultiple);
            
            HttpURLConnection conn = (HttpURLConnection)link.openConnection(); 
            HttpURLConnection conn2 = (HttpURLConnection)multipleChoiceLink.openConnection(); 
            
            conn.setRequestMethod("GET");
            conn.connect();
            
            conn2.setRequestMethod("GET");
            conn2.connect();
            
            int responsecode = conn.getResponseCode();
            int responsecode2 = conn2.getResponseCode();
            
            if(responsecode != 200 && responsecode2 != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {
                Scanner sc = new Scanner(link.openStream());
                Scanner sc2 = new Scanner(multipleChoiceLink.openStream());
                while(sc.hasNext()){
                    inline += sc.nextLine();
                }
                
                while (sc2.hasNext()) {
                    multipleQuizInline += sc2.nextLine();
                }
                
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(QuizGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuizGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TrueOrFalseModel> getTrueOrFalseQuestion() {
        JSONParser parse = new JSONParser();
        List<TrueOrFalseModel> l = new ArrayList<>();
        try {
            JSONObject jobj = (JSONObject)parse.parse(inline);
            JSONArray jsonarr_1 = (JSONArray) jobj.get("results");
            for (int i = 0 ; i < jsonarr_1.size() ; i++) {
               JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
               TrueOrFalseModel m = new TrueOrFalseModel(jsonobj_1);
               l.add(m);
            }
        } catch (ParseException ex) {
            Logger.getLogger(QuizGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public List<MultipleChoiceModel> getMultipleChoiceQuestion() {
        JSONParser parse = new JSONParser();
        List<MultipleChoiceModel> l = new ArrayList<>();
        try {
            JSONObject jobj = (JSONObject)parse.parse(multipleQuizInline);
            JSONArray jsonarr_1 = (JSONArray) jobj.get("results");
            for (int i = 0 ; i < jsonarr_1.size() ; i++) {
               JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
               MultipleChoiceModel m = new MultipleChoiceModel(jsonobj_1);
               l.add(m);
            }
        } catch (ParseException ex) {
            Logger.getLogger(QuizGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    
    
    
}
