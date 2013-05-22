package com.deerandcatgames.utils.GoogleAchivements;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GooglePlusApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import android.util.Log;

public class GoogleAchivements {

	private static GoogleAchivements instance;
	public static final String GAMES_BASE_URL="https://www.googleapis.com/games/v1/";
	public static GoogleAchivements getInstance()
	{
		if(instance==null)
		{
			instance=new GoogleAchivements();
		}
		return instance;
	}

	
	public OAuthService Service;
	public Token AccessToken;
	
	public void Initialize(String ClientID,String secret,String callbackUrl,boolean Achivements,boolean Saves) throws Exception
	{
		String scope="";
		if(!Achivements && !Saves)
			throw new Exception("Cannot use neither achivements or saves"); 
		if(Achivements)
			scope+="https://www.googleapis.com/auth/games";
		if(Saves)
			scope+=" https://www.googleapis.com/auth/appstate";
		
		Service=new ServiceBuilder().provider(GooglePlusApi.class).apiKey(ClientID).apiSecret(secret).scope(scope).callback(callbackUrl).build();
		
	}
	
	public String GetLoginURL()
	{
		return Service.getAuthorizationUrl(null);
	}
	public void Test()
	{
		HashMap<String, String> options=new HashMap<String, String>();
		//options.put("maxResults", "1");
		try {
			JSONObject obj=new ListRequest("achievements", options).Execute();
			Log.i("Results",obj.toString());
			
			AchivementDefinitionList.getInstance().Load(obj);
			for(AchivementDefinition aDef:AchivementDefinitionList.getInstance().values())
			{
				Log.i("Results",aDef.toString());
			}
			AchivementList list=new AchivementList();
			options.put("playerId", "me");
			JSONObject obj2=new ListRequest("players/me/achievements", options).Execute();
			//Log.i("Results",obj2.toString());
			list.Load(obj2);
			for(Achivement ach:list.values())
			{
				Log.i("Results", ach.toString());
			}
			Log.i("Results", AchivementDefinitionList.getInstance().toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean completeLogin(String code)
	{
		try
		{
		Verifier verifier=new Verifier(code);
		AccessToken=Service.getAccessToken(null, verifier);
		}
		catch(Exception e)
		{
			Log.e("GoogleAchivements","Couldnt login",e);
			return false;
		}
		return true;
	}
	
}
