package com.deerandcatgames.utils.GoogleAchivements.callbacks;

import com.deerandcatgames.utils.GoogleAchivements.Player;

public interface PlayerLoadedCallback {

	void OnLoadComplete(Player player);

	void OnError(Exception e);

	
}
