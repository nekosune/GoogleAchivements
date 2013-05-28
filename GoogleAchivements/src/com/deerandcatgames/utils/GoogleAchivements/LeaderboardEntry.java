package com.deerandcatgames.utils.GoogleAchivements;

import org.json.JSONException;
import org.json.JSONObject;

public class LeaderboardEntry {

	public static final String KIND="games#leaderboardEntry";
	protected Player player;
	protected long scoreRank;
	protected String formattedScoreRank;
	protected long scoreValue;
	protected String formattedScore;
	protected LeaderboardTimeSpan timeSpan;
	protected long writeTimestampMillis;
	
	public void Load(JSONObject obj) throws Exception
	{
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a Leaderboard Entry");
		
		player=new Player();
		player.Load(obj.getJSONObject("player"));
		scoreRank=obj.getLong("scoreRank");
		formattedScoreRank=obj.getString("formattedScoreRank");
		scoreValue=obj.getLong("scoreValue");
		formattedScore=obj.getString("formattedScore");
		String span=obj.getString("timeSpan");
		if(span.equalsIgnoreCase("ALL_TIME"))
			timeSpan=LeaderboardTimeSpan.ALL_TIME;
		else if(span.equalsIgnoreCase("WEEKLY"))
			timeSpan=LeaderboardTimeSpan.WEEKLY;
		else if(span.equalsIgnoreCase("DAILY"))
			timeSpan=LeaderboardTimeSpan.DAILY;
		else
			throw new IllegalArgumentException("Invalid Time Span");
		writeTimestampMillis=obj.getLong("writeTimestampMillis");
	}
}