package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.Achivement;
import com.deerandcatgames.utils.GoogleAchivements.GoogleAchivements;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;
import com.deerandcatgames.utils.GoogleAchivements.AchivementDefinition.AchivementStates;
import com.deerandcatgames.utils.GoogleAchivements.Request;

import android.os.AsyncTask;

public class AchivementRevealTask extends AsyncTask<Void, Void, Void> {

	Achivement achivement;
	
	public AchivementRevealTask(Achivement ach)
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
			obj2 = new Request(String.format("achievements/%s/reveal",id), options,Verb.POST).Execute();
			if(obj2.has("currentState"))
			{
				String state=obj2.getString("currentState");
				if(state.equalsIgnoreCase("REVEALED"))
				{
					if(GoogleAchivements.getInstance().getAchivementCallback()!=null)
						GoogleAchivements.getInstance().getAchivementCallback().AchivementRevealed(achivement);
				}
			}
			
			
			
		} catch (Exception e) {
		}
		return null;
	}

}
