package com.deerandcatgames.utils.GoogleAchivements;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GooglePlusApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class GoogleAchivements {

	private static GoogleAchivements instance;
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
	
	public boolean completeLogin(String code)
	{
		try
		{
		Verifier verifier=new Verifier(code);
		AccessToken=Service.getAccessToken(null, verifier);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
}
