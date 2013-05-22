package com.deerandcatgames.utils.GoogleAchivements;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GooglePlusApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.deerandcatgames.utils.GoogleAchivements.callbacks.AchivementDefinitionCallback;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.AchivementStatusChange;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.AchivementsLoadedCallback;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.LoginCompleteCallback;
import com.deerandcatgames.utils.GoogleAchivements.tasks.AchivementDefinitionsLoaderTask;
import com.deerandcatgames.utils.GoogleAchivements.tasks.AchivementsLoaderTask;

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
	public AchivementList playerAchivements;
	
	private AchivementStatusChange AchivementCallback;
	
	/**
	 * @return the accessToken
	 */
	public Token getAccessToken() {
		return AccessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(Token accessToken) {
		AccessToken = accessToken;
	}

	/**
	 * @return the playerAchivements
	 */
	public AchivementList getPlayerAchivements() {
		return playerAchivements;
	}

	/**
	 * @param playerAchivements the playerAchivements to set
	 */
	public void setPlayerAchivements(AchivementList playerAchivements) {
		this.playerAchivements = playerAchivements;
	}

	/**
	 * @return the achivementCallback
	 */
	public AchivementStatusChange getAchivementCallback() {
		return AchivementCallback;
	}

	/**
	 * @param achivementCallback the achivementCallback to set
	 */
	public void setAchivementCallback(AchivementStatusChange achivementCallback) {
		AchivementCallback = achivementCallback;
	}

	public void Initialize(String ClientID,String secret,String callbackUrl) throws Exception
	{
		String scope="https://www.googleapis.com/auth/games https://www.googleapis.com/auth/appstate";
		
		Service=new ServiceBuilder().provider(GooglePlusApi.class).apiKey(ClientID).apiSecret(secret).scope(scope).callback(callbackUrl).build();
		
	}
	
	public String GetLoginURL()
	{
		return Service.getAuthorizationUrl(null);
	}
	public void LoadAchivements(LoginCompleteCallback onComplete)
	{
		final LoginCompleteCallback callback=onComplete;
		AchivementDefinitionsLoaderTask task1=new AchivementDefinitionsLoaderTask(new AchivementDefinitionCallback() {
			
			
			@Override
			public void OnLoadComplete() {
				LoadPlayerAchivements(callback);
			}
			
			@Override
			public void OnError(Exception e) {
				callback.OnError(e);
			}
		});
		task1.execute();
	}
	public void LoadPlayerAchivements(LoginCompleteCallback onComplete)
	{
		final LoginCompleteCallback callback=onComplete;
		AchivementsLoaderTask task2=new AchivementsLoaderTask(new AchivementsLoadedCallback() {
			
			@Override
			public void OnLoadComplete(AchivementList achivements, String id) {
				playerAchivements=achivements;
				callback.OnLoginComplete();
			}
			
			@Override
			public void OnError(Exception e) {
				callback.OnError(e);
			}
		});
		task2.execute();
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
	
	public Achivement getAchivement(String id)
	{
		return playerAchivements.get(id);
	}
}
