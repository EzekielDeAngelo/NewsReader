package com.antho.newsreader.model;
/** Zoned date time adapter **/


import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import org.threeten.bp.ZonedDateTime;

import io.reactivex.annotations.NonNull;
//import java.time.ZonedDateTime;
/**  **/
public class ZonedDateTimeAdapter
{
    //
    @FromJson
    ZonedDateTime fromJson(String json)
    {
        return ZonedDateTime.parse(json);
    }
    //
    @ToJson
    String toJson(@NonNull ZonedDateTime value)
    {
        return value != null ? value.toString() : null;
    }
}
