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
	protected Verb verb;
	public Request(String request,Map<String,String> data)
	{
		this(request,data,Verb.GET);
	}
	public Request(String request,Map<String,String> data,Verb verb)
	{
		this.verb=verb;
		if(verb!=Verb.POST && verb!=Verb.PUT)
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
		else
		{
			mRequest=request;
			mData=data;
			Uri.Builder b=Uri.parse(GoogleAchivements.GAMES_BASE_URL+request).buildUpon();
			mURL=b.build().toString();
		}
	}
	public JSONObject Execute() throws JSONException
	{
		OAuthRequest request=new OAuthRequest(verb, mURL);
		if(verb==Verb.POST || verb==Verb.PUT)
		{
			for(Entry<String, String> entry:mData.entrySet())
			{
				request.addBodyParameter(entry.getKey(), entry.getValue());
			}
		}
		GoogleAchivements.getInstance().Service.signRequest(GoogleAchivements.getInstance().AccessToken, request);
		Response resp=request.send();
		String body=resp.getBody();
		return new JSONObject(body);
	}
}
