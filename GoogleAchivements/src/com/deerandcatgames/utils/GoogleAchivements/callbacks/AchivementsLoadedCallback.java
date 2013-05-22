package com.deerandcatgames.utils.GoogleAchivements.callbacks;

import com.deerandcatgames.utils.GoogleAchivements.AchivementList;

public interface AchivementsLoadedCallback {
	public void OnLoadComplete(AchivementList achivements,String id);
	public void OnError(Exception e);
}
