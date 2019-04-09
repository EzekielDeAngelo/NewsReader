package com.antho.newsreader.model;
import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;
/** Creates an Moshi adapter **/
@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory
{
    // Return the adapter if on was created or null if this factory isn't capable of creating such an adapter
    public static JsonAdapter.Factory create()
    {
        return new AutoValueMoshi_AdapterFactory();
    }
}