package com.antho.newsreader.model;

import android.support.annotation.NonNull;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.ZonedDateTime;

// Three Ten ABP Version
    /*implementation "com.jakewharton.threetenabp:threetenabp:$threeTenVersion"
            threeTenVersion = '1.1.0'*/


public class ZonedDateTimeAdapter
{
    @FromJson
    ZonedDateTime fromJson(String json) {
        return ZonedDateTime.parse(json);
    }

    @ToJson
    String toJson(@NonNull ZonedDateTime value) {
        return value != null ? value.toString() : null;
    }
}
