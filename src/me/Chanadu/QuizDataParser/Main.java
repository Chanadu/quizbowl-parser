package me.Chanadu.QuizDataParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

public class Main {


    // OuterMap(eCategories Name, Inner Map(Page/Answer, Frequency))
    static HashMap<String, Object> categories = new HashMap<>();


    public static void main(String[] args) {
        try {
            Object obj1 = new JSONParser().parse(new FileReader("QuizbowlData.json"));
            JSONObject file1 = (JSONObject) obj1;
            JSONArray questions1 = (JSONArray) file1.get("questions");
            //System.out.println(file.getClass());

            for (Object q : questions1) {
                JSONObject question = (JSONObject) q;

                if (parseQuestion(question)) {
                    System.out.println("Parse Questions Function went wrong!");
                }
            }

            Object obj = new JSONParser().parse(new FileReader("file.json"));
            JSONObject file = (JSONObject) obj;
            JSONArray questions = (JSONArray) file.get("questions");
            //System.out.println(file.getClass());

            for (Object q : questions) {
                JSONObject question = (JSONObject) q;

                if (parseQuestion(question)) {
                    System.out.println("Parse Questions Function went wrong!");
                }
            }

            for (String key: categories.keySet()){

                HashMap<String, Integer> pages = (HashMap<String, Integer>) categories.get(key);
                String highestAnswer1 = "";
                Integer highestCount1 = 0;
                String highestAnswer2 = "";
                Integer highestCount2 = 0;
                String highestAnswer3 = "";
                Integer highestCount3 = 0;
                String highestAnswer4 = "";
                Integer highestCount4 = 0;
                String highestAnswer5 = "";
                Integer highestCount5 = 0;


                for (String key1: pages.keySet()) {
                    if (pages.get(key1) > highestCount1) {
                        highestAnswer1 = key1;
                        highestCount1 = pages.get(key1);
                    } else if (pages.get(key1) > highestCount2){
                        highestAnswer2 = key1;
                        highestCount2 = pages.get(key1);
                    } else if (pages.get(key1) > highestCount3) {
                        highestAnswer3 = key1;
                        highestCount3 = pages.get(key1);
                    } else if (pages.get(key1) > highestCount4) {
                        highestAnswer4 = key1;
                        highestCount4 = pages.get(key1);
                    } else if (pages.get(key1) > highestCount5) {
                        highestAnswer5 = key1;
                        highestCount5 = pages.get(key1);
                    }
                }

                System.out.println(key + ": " + highestAnswer1 + " = " + highestCount1 + ", " + highestAnswer2 + " = " + highestCount2 + ", " + highestAnswer3 + " = " + highestCount3 + ", " + highestAnswer4 + " = " + highestCount4  + ", " + highestAnswer5 + " = " + highestCount5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean parseQuestion(JSONObject question) {
        String category = (String) question.get("category");

        if (!categories.containsKey((category))) {
            HashMap<String, Integer> innerHashMap = new HashMap<>();
            categories.put((String) question.get("category"), innerHashMap);
        }
        String page = (String) question.get("page");

        if (!( (HashMap) categories.get(category) ).containsKey(page)) {
            ( (HashMap) categories.get(category) ).put(page, 0);
        }

        //noinspection unchecked
        ((HashMap) categories.get(category)).replace(page,
                ((Integer) ((HashMap) categories.get(category)).get(page))  + 1);

        return false;
    }
}

        /*
        OuterMap(Categories Name(String), Inner Map(Page/Answer(String), Frequency(Integer)))

        If OuterMap contains Category Name(String):
            If Inner Map Contains Page/Answer(String) of Question:
                increase Frequency(Integer)
            Else:
                Add Answer(String) and Frequency(Integer) = 1 to Inner Map
        Else:
            Add Category Name(String) and an Empty HashMap with ("String", Integer) to Outer Map

        categories(String, Object (Map(String, Integer))
         */
