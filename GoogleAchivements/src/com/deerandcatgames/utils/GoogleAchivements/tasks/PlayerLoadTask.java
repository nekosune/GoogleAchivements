/**
 * 
 */
package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.Player;
import com.deerandcatgames.utils.GoogleAchivements.Request;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.PlayerLoadedCallback;

import android.os.AsyncTask;

/**
 * @author Katrina
 *
 */
public class PlayerLoadTask extends AsyncTask<String, Void, Player> {

	
	public PlayerLoadedCallback callback;
	
	public PlayerLoadTask(PlayerLoadedCallback call)
	{
		callback=call;
	}
	public PlayerLoadTask()
	{
		callback=null;
	}
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Player doInBackground(String... params) {
		
		String id=(params.length>0)?params[0]:"me";
		HashMap<String, String> options=new HashMap<String, String>();
		Player player=new Player();
		options.put("playerId", id);
		JSONObject obj2;
		try {
			obj2 = new Request(String.format("players/%s",id,Verb.GET), options).Execute();
			player.Load(obj2);
			if(callback!=null)
				callback.OnLoadComplete(player);
			return player;
		} catch (Exception e) {
			if(callback!=null)
				callback.OnError(e);
		}
		return null;
	}

}
