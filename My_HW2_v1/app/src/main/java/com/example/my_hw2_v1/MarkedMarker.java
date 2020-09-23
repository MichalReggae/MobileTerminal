package com.example.my_hw2_v1;

import org.json.JSONException;
import org.json.JSONObject;

public class MarkedMarker {
    private double x;
    private double y;

    MarkedMarker(JSONObject object) throws JSONException {
        x = (double) object.get("x");
        y = (double) object.get("y");
    }


}
