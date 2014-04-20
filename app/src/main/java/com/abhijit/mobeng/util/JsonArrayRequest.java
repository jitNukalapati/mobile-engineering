package com.abhijit.mobeng.util;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * A request for retrieving a {@link com.google.gson.JsonArray} response body from a given URL
 *
 * Created by AbhijitNukalapati on 4/20/14.
 */
public class JsonArrayRequest extends Request<JsonArray> {

    private Response.Listener<JsonArray> mListener;

    public JsonArrayRequest(String url, Response.Listener<JsonArray> jsonArrayListener, Response.ErrorListener errorListener){
        this(Method.GET ,url, jsonArrayListener, errorListener);
    }

    public JsonArrayRequest(int method, String url, Response.Listener<JsonArray> jsonArrayListener, Response.ErrorListener errorListener){
        super(method, url, errorListener);
        mListener = jsonArrayListener;
    }

    @Override
    protected Response<JsonArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JsonParser().parse(jsonString).getAsJsonArray(), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JsonArray jsonArray) {
        mListener.onResponse(jsonArray);
    }
}
