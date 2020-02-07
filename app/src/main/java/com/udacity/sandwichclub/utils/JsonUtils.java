package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String ALSO_KNOW_AS = "alsoKnownAs";
        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String IMAGE = "image";
        final String INGREDIENTS = "ingredients";

        JSONObject _nameJson = jsonObject.getJSONObject(NAME);
        String mainName = _nameJson.getString(MAIN_NAME);

        JSONArray alsoKnownAsArray = _nameJson.getJSONArray(ALSO_KNOW_AS);
        ArrayList alsoKnownAsData = new ArrayList(alsoKnownAsArray.length());
        for (int i = 0; i < alsoKnownAsArray.length(); i++) {
            alsoKnownAsData.add(alsoKnownAsArray.get(i));
        }

        String placeOfOrigin = hasStringValueOrReturnEmpty(PLACE_OF_ORIGIN, jsonObject);
        String description = hasStringValueOrReturnEmpty(DESCRIPTION, jsonObject);
        String image = hasStringValueOrReturnEmpty(IMAGE, jsonObject);

        JSONArray ingredientsArray = jsonObject.getJSONArray(INGREDIENTS);
        ArrayList ingredientsData = new ArrayList(ingredientsArray.length());
        for (int i = 0; i < ingredientsArray.length(); i++) {
            ingredientsData.add(ingredientsArray.get(i));
        }

        return new Sandwich(mainName, alsoKnownAsData, placeOfOrigin, description, image, ingredientsData);
    }

    private static String hasStringValueOrReturnEmpty(String key, JSONObject jsonObject) throws JSONException {
        if (jsonObject.has(key)) {
            return jsonObject.getString(key);
        } else {
            return "";
        }
    }
}
