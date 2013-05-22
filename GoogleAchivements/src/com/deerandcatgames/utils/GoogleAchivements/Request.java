package com.deerandcatgames.utils.GoogleAchivements;

import java.io.StringReader;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import android.net.Uri;
import android.util.JsonReader;

public class Request {
	
	protected String mURL;
	protected String mRequest;
	protected Map<String,String> mData;
	public Request(String request,Map<String,String> data)
	{
		mRequest=request;
		mData=data;
		Uri.Builder b=Uri.parse(GoogleAchivements.GAMES_BASE_URL+request).buildUpon();
		for(Entry<String, String> entry:data.entrySet())
		{
			b.appendQueryParameter(entry.getKey(), entry.getValue());
		}
		mURL=b.build().toString();
	}
	
	public JSONObject Execute() throws JSONException
	{
		OAuthRequest request=new OAuthRequest(Verb.GET, mURL);
		GoogleAchivements.getInstance().Service.signRequest(GoogleAchivements.getInstance().AccessToken, request);
		Response resp=request.send();
		String body=resp.getBody();
		return new JSONObject(body);
	}
}
