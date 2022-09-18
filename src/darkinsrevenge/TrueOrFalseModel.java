/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jam
 */
public class TrueOrFalseModel {
    String question;
    String correct_answer;
    String incorrect_answer;
    JSONObject object;
    

    TrueOrFalseModel(JSONObject object) {
        this.object = object;
    }
    
    public String getQuestion() {
        return String.valueOf(object.get("question"));
    }
    
    public String getCorrectAnswer() {
        return String.valueOf(object.get("correct_answer"));
    }
    
    public String getIncorrectAnswer() {
        JSONArray o = (JSONArray) object.get("incorrect_answers");
        return String.valueOf(o.get(0));
    }
}
