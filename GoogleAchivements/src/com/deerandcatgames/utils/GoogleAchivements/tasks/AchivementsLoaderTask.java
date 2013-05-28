/**
 * 
 */
package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.AchivementList;
import com.deerandcatgames.utils.GoogleAchivements.Leaderboard;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;
import com.deerandcatgames.utils.GoogleAchivements.Request;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.AchivementsLoadedCallback;

import android.os.AsyncTask;

/**
 * @author Katrina
 *
 */
public class AchivementsLoaderTask extends AsyncTask<String, Void, AchivementList> {

	
	public AchivementsLoadedCallback callback;
	/**
	 * Constucts the task
	 * @param callback callback to call
	 */
	public AchivementsLoaderTask(AchivementsLoadedCallback callback)
	{
		this.callback=callback;
	}
	public AchivementsLoaderTask()
	{
		this.callback=null;
	}
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected AchivementList doInBackground(String... params) {
		String id=(params.length>0)?params[0]:"me";
		HashMap<String, String> options=new HashMap<String, String>();
		AchivementList list=new AchivementList();
		options.put("playerId", id);
		JSONObject obj2;
		try {
			obj2 = new Request(String.format("players/%s/achievements",id,Verb.POST), options).Execute();
			list.Load(obj2);
			if(callback!=null)
				callback.OnLoadComplete(list, id);
			return list;
		} catch (Exception e) {
			if(callback!=null)
				callback.OnError(e);
		}
		return null;
	}

}
