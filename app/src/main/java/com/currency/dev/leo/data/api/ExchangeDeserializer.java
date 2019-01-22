package com.currency.dev.leo.data.api;

import com.currency.dev.leo.data.model.ExchangeResponse;
import com.currency.dev.leo.data.model.Rate;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExchangeDeserializer implements JsonDeserializer<ExchangeResponse> {
    @Override
    public ExchangeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ExchangeResponse response = new ExchangeResponse();
        response.setMessage(json.getAsJsonObject().get("message").getAsString());
        response.setStatus(json.getAsJsonObject().get("status").getAsInt());

        List<Rate> rateList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> x : json.getAsJsonObject().get("data").getAsJsonObject().entrySet()){
            rateList.add(new Rate(x.getKey(), x.getValue().getAsDouble()));
        }

        response.setRateList(rateList);
        return response;
    }
}
