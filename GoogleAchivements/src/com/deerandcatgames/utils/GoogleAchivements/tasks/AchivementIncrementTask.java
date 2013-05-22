package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;

import org.json.JSONObject;
import org.scribe.model.Verb;

import com.deerandcatgames.utils.GoogleAchivements.Achivement;
import com.deerandcatgames.utils.GoogleAchivements.AchivementDefinition.AchivementStates;
import com.deerandcatgames.utils.GoogleAchivements.AchivementList;
import com.deerandcatgames.utils.GoogleAchivements.GoogleAchivements;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;
import com.deerandcatgames.utils.GoogleAchivements.Request;

import android.os.AsyncTask;

public class AchivementIncrementTask extends AsyncTask<Integer, Void, Void> {

	public Achivement achivement;
	public AchivementIncrementTask(Achivement ach)
	{
		achivement=ach;
	}
	@Override
	protected Void doInBackground(Integer... params) {
		if(params.length!=1)
			return null;
		String id=achivement.getAchivementDefinition().getId();
		HashMap<String, String> options=new HashMap<String, String>();
		options.put("achievementId", id);
		options.put("stepsToIncrement", params[0].toString());
		JSONObject obj2;
		try {
			obj2 = new Request(String.format("achievements/%s/increment",id), options,Verb.POST).Execute();
			if(obj2.has("newlyUnlocked"))
			{
				boolean unlocked=obj2.getBoolean("newlyUnlocked");
				if(unlocked)
				{
					achivement.setAchivementState(AchivementStates.UNLOCKED);
					GoogleAchivements.getInstance().getAchivementCallback().AchivementUnlocked(achivement);
					return null;
				}
			}
			
			if(obj2.has("currentSteps"))
			{
				int cur=achivement.getCurrentSteps();
				achivement.setCurrentSteps(obj2.getInt("currentSteps"));
				if(cur!=achivement.getCurrentSteps())
				{
					GoogleAchivements.getInstance().getAchivementCallback().AchivementSteps(achivement,achivement.getCurrentSteps()-cur);
				}
			}
			
		} catch (Exception e) {
		}
		return null;
	}

}
