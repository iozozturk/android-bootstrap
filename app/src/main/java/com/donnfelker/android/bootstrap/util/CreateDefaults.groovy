package com.donnfelker.android.bootstrap.util;

import android.util.JsonReader;

import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by ismet on 1/16/14.
 */
public class CreateDefaults {

    public static boolean initUsers() {
        JsonParser parser = new JsonParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader("C:\\androidWorkspace\\android-bootstrap\\app\\src\\main\\java\\com\\donnfelker\\android\\bootstrap\\core\\sampleUsers.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("name");
            System.out.println(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }


}
