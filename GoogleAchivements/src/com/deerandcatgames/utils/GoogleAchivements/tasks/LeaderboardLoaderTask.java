package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.AchivementList;
import com.deerandcatgames.utils.GoogleAchivements.Leaderboard;
import com.deerandcatgames.utils.GoogleAchivements.Request;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.LeaderboardLoadedCallback;

import android.os.AsyncTask;

public class LeaderboardLoaderTask extends AsyncTask<String, Void, Leaderboard> {

	
	
	LeaderboardLoadedCallback callback;
	
	public LeaderboardLoaderTask(LeaderboardLoadedCallback cll)
	{
		callback=cll;
	}
	
	public LeaderboardLoaderTask()
	{
		callback=null;
	}
	@Override
	protected Leaderboard doInBackground(String... params) {
		if(params.length<4)
			throw new IllegalArgumentException("Need all three params");
		String id=params[0];
		String Social=params[1];
		String Span=params[2];
		String window=params[3];
		String url;
		if(window=="window")
			url="leaderboards/%s/window/%s";
		else
			url="leaderboards/%s/scores/%s";
		HashMap<String, String> options=new HashMap<String, String>();
		Leaderboard leader=new Leaderboard();
		options.put("leaderboardId", id);
		options.put("collection",Social);
		options.put("timeSpan", Span);
		JSONObject obj2;
		try {
			obj2 = new Request(String.format(url,id,Social), options,Verb.GET).Execute();
			leader.Load(obj2);
			if(callback!=null)
				callback.Loaded(leader);
			return leader;
		} catch (Exception e) {
			if(callback!=null)
				callback.onError(e);
		}
		return null;
	}

}
