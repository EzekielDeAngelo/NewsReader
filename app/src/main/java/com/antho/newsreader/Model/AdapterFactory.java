package com.antho.newsreader.model;
/** Adapter factory **/
import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;
/**  **/
@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory
{
    // Static factory method to access the package
    public static JsonAdapter.Factory create()
    {
        return new AutoValueMoshi_AdapterFactory();
    }
}