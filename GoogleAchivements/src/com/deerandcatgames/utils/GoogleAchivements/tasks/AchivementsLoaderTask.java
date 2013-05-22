/**
 * 
 */
package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.AchivementList;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;
import com.deerandcatgames.utils.GoogleAchivements.Request;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.AchivementsLoadedCallback;

import android.os.AsyncTask;

/**
 * @author Katrina
 *
 */
public class AchivementsLoaderTask extends AsyncTask<String, Void, Void> {

	
	public AchivementsLoadedCallback callback;
	/**
	 * Constucts the task, no non callback version for this one as that makes no sense
	 * @param callback callback to call
	 */
	public AchivementsLoaderTask(AchivementsLoadedCallback callback)
	{
		this.callback=callback;
	}
	
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(String... params) {
		if(callback==null)
			throw new IllegalArgumentException("Callback may not be null");
		String id=(params.length>0)?params[0]:"me";
		HashMap<String, String> options=new HashMap<String, String>();
		AchivementList list=new AchivementList();
		options.put("playerId", id);
		JSONObject obj2;
		try {
			obj2 = new Request(String.format("players/%s/achievements",id,Verb.POST), options).Execute();
			list.Load(obj2);
			callback.OnLoadComplete(list, id);
		} catch (Exception e) {
			callback.OnError(e);
		}
		return null;
	}

}
