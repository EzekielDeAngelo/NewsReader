package com.antho.newsreader.model;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.ZonedDateTime;

import io.reactivex.annotations.NonNull;
/** Return parsed date strings **/
public class ZonedDateTimeAdapter
{
    // Obtains an instance of ZonedDateTime from the text string
    @FromJson
    ZonedDateTime fromJson(String json)
    {
        return ZonedDateTime.parse(json);
    }
    // Outputs the ZonedDateTime as a string
    @ToJson
    String toJson(@NonNull ZonedDateTime value)
    {
        return value != null ? value.toString() : null;
    }
}
