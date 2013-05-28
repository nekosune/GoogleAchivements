package com.deerandcatgames.utils.GoogleAchivements.callbacks;

import com.deerandcatgames.utils.GoogleAchivements.Leaderboard;

public interface LeaderboardLoadedCallback {

	
	public void Loaded(Leaderboard leaderboard);
	
	public void onError(Exception e);
}
