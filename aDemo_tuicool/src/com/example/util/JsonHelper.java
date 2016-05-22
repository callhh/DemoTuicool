package com.example.util;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import android.support.annotation.NonNull;

public class JsonHelper {

    private static final String JSON_PARSE_ERROR = "Unable to parse JSON: ";
    private static Gson sGson;
    private static Gson sExposeGson;

    static {
        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
//        builder.registerTypeAdapter(BlogCategoryDto.class, new BlogCategorySerializer());

        sGson = builder.create();

        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        builder.excludeFieldsWithoutExposeAnnotation();
        sExposeGson = builder.create();
    }

    private static Gson getGson() {
        return sGson;
    }

    private static Gson getsExposeGsonGson() {
        return sExposeGson;
    }

    private static String toJson(@NonNull Object object) {
        return sGson.toJson(object);
    }

    private static String toJson(@NonNull Object object, @NonNull Type type) {
        return sGson.toJson(object, type);
    }

    private static String toExposeJson(@NonNull Object object) {
        return sExposeGson.toJson(object);
    }

    private static String toExposeJson(@NonNull Object object, @NonNull Type type) {
        return sExposeGson.toJson(object, type);
    }

    public static <T> T fromJson(@NonNull String content, @NonNull Class<T> clazz)
            throws IllegalStateException {
        try {
            return sGson.fromJson(content, clazz);
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException(JSON_PARSE_ERROR + content, e);
        }
    }
    public static <T> T fromExposeJson(@NonNull String content, @NonNull Class<T> clazz)
    		throws IllegalStateException {
    	try {
    		return sExposeGson.fromJson(content, clazz);
    	} catch (JsonSyntaxException e) {
    		throw new IllegalStateException(JSON_PARSE_ERROR + content, e);
    	}
    }

    public static <T> T fromJson(@NonNull String content, @NonNull Type type)
            throws IllegalStateException {
        try {
            return sGson.fromJson(content, type);
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException(JSON_PARSE_ERROR + content, e);
        }
    }
}