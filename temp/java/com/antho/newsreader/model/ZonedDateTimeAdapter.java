package com.antho.newsreader.model;
/****/
import androidx.annotation.NonNull;
import android.util.Log;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
// Three Ten ABP Version : implementation "com.jakewharton.threetenabp:threetenabp:$threeTenVersion" /// threeTenVersion = '1.1.0'
import java.time.ZonedDateTime;
/****/
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
