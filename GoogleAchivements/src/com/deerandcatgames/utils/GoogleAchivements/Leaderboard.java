package com.deerandcatgames.utils.GoogleAchivements;

import org.json.JSONObject;

public class Leaderboard {

	protected LeaderboardDefinition definition;
	protected long numScores;
	protected LeaderboardEntry playerScore;
	protected LeaderboardEntryList scores;
	public static final String KIND="games#leaderboardScores";
	
	
	public void Load(JSONObject obj) throws Exception
	{
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a Leaderboard");
		numScores=obj.getLong("numScores");
		JSONObject obj2=obj.getJSONObject("playerScore");
		playerScore=new LeaderboardEntry();
		playerScore.Load(obj2);
		
		this.scores=new LeaderboardEntryList();
		scores.Load(obj.getJSONArray("items"));
	}
}
