package com.spark.biben.custome.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

public class FloatlNullAdapter extends TypeAdapter<Float>{
    @Override
    public Float read(JsonReader reader) throws IOException{
        if (reader.peek() == JsonToken.STRING) {
            reader.skipValue(); //跳过当前
            return -1f;
        }
        BigDecimal bigDecimal = new BigDecimal(reader.nextString());
        return bigDecimal.floatValue();
    }

    @Override
    public void write(JsonWriter writer, Float value) throws IOException {
        writer.value(value);
    }
}