package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONArray alsoKnownAsJsonArray = sandwichObject.getJSONObject("name").getJSONArray("alsoKnownAs");
            JSONArray ingredientsJsonArray = sandwichObject.getJSONArray("ingredients");

            String description = sandwichObject.getString("description");
            String placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            String mainName = sandwichObject.getJSONObject("name").getString("mainName");
            String image = sandwichObject.getString("image");

            List<String> ingredients = new ArrayList<>();
            List<String> alsoKnownAs = new ArrayList<>();

            for (int i = 0; i < ingredientsJsonArray.length(); ++i) {
                ingredients.add(ingredientsJsonArray.getString(i));
            }

            for (int i = 0; i < alsoKnownAsJsonArray.length(); ++i) {
                alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
