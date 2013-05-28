package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.deerandcatgames.utils.GoogleAchivements.AchivementDefinitionList;
import com.deerandcatgames.utils.GoogleAchivements.LeaderboardDefinitionList;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.LeaderboardDefinitionCallback;

import android.os.AsyncTask;

public class LeaderboardDefinitionLoaderTask extends
		AsyncTask<Void, Void, Void> {

	
	LeaderboardDefinitionCallback callback;
	
	public LeaderboardDefinitionLoaderTask(LeaderboardDefinitionCallback cll)
	{
		callback=cll;
	}
	@Override
	protected Void doInBackground(Void... arg0) {
		try {
			Map<String,String> options=new HashMap<String, String>();
			JSONObject obj;
			obj = new ListRequest("leaderboards", options).Execute();
			LeaderboardDefinitionList.getInstance().Load(obj);
			if(callback!=null)
				callback.OnLoadComplete();
		} catch (Exception e) {
			if(callback!=null)
				callback.OnError(e);
		}
		
		return null;
	}

}
