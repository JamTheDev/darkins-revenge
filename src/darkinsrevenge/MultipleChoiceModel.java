/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jam
 */
public class MultipleChoiceModel {
    String question;
    String correct_answer;
    String incorrect_answer;
    JSONObject object;
    

    MultipleChoiceModel(JSONObject object) {
        this.object = object;
    }
    
    public String getQuestion() {
        return String.valueOf(object.get("question"));
    }
    
    public String getCorrectAnswer() {
        return String.valueOf(object.get("correct_answer"));
    }
    
    public List<String> getChoices() {
        JSONArray o = (JSONArray) object.get("incorrect_answers");
        List<String> choices = new ArrayList<>();
        String correct = String.valueOf(object.get("correct_answer"));
        o.forEach((item) -> {
            choices.add(item.toString());
        });
        choices.add(correct);
        Collections.shuffle(choices);
        return choices;
    }
    
    
}
