package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.Achivement;
import com.deerandcatgames.utils.GoogleAchivements.GoogleAchivements;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;

import android.os.AsyncTask;

public class AchivementUnlockTask extends AsyncTask<Void, Void, Void> {

	
	Achivement achivement;
	
	public AchivementUnlockTask(Achivement ach)
	{
		achivement=ach;
	}
	@Override
	protected Void doInBackground(Void... params) {
		String id=achivement.getAchivementDefinition().getId();
		HashMap<String, String> options=new HashMap<String, String>();
		options.put("achievementId", id);
		JSONObject obj2;
		try {
			obj2 = new ListRequest(String.format("achievements/%s/unlock",id), options,Verb.POST).Execute();
			if(obj2.has("newlyUnlocked"))
			{
				boolean newlyUnlocked=obj2.getBoolean("newlyUnlocked");
				if(newlyUnlocked)
				{
					if(GoogleAchivements.getInstance().getAchivementCallback()!=null)
						GoogleAchivements.getInstance().getAchivementCallback().AchivementUnlocked(achivement);
				}
			}
			
			
			
		} catch (Exception e) {
		}
		return null;
	}

}
