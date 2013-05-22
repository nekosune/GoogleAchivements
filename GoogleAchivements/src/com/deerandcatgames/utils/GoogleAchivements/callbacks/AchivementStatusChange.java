package com.deerandcatgames.utils.GoogleAchivements.callbacks;

import com.deerandcatgames.utils.GoogleAchivements.Achivement;

public interface AchivementStatusChange {

	public void AchivementRevealed(Achivement achivement);
	public void AchivementSteps(Achivement achivement,int changedBy);
	public void AchivementUnlocked(Achivement achivement);
}
