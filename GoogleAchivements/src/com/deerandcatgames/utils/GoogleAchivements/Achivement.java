package com.deerandcatgames.utils.GoogleAchivements;

import org.json.JSONObject;

import com.deerandcatgames.utils.GoogleAchivements.AchivementDefinition.AchivementStates;
import com.deerandcatgames.utils.GoogleAchivements.AchivementDefinition.AchivementTypes;

/**
 * Players Achivement information
 * @author Katrina
 *
 */
public class Achivement {

	private AchivementDefinition AchivementDefinition;
	private Integer currentSteps;
	private String formattedCurrentSteps;
	private AchivementStates achivementState;
	public static final String KIND="games#playerAchievement";
	/**
	 * @return the achivementDefinition
	 */
	public AchivementDefinition getAchivementDefinition() {
		return AchivementDefinition;
	}
	/**
	 * @return the currentSteps
	 */
	public Integer getCurrentSteps() {
		return currentSteps;
	}
	/**
	 * @return the formattedCurrentSteps
	 */
	public String getFormattedCurrentSteps() {
		return formattedCurrentSteps;
	}
	/**
	 * @return the achivementState
	 */
	public AchivementStates getAchivementState() {
		return achivementState;
	}
	/**
	 * @return the kind
	 */
	public static String getKind() {
		return KIND;
	}
	
	public void Load(JSONObject obj) throws Exception
	{
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a AchivementDefinition");
		String id=obj.getString("id");
		AchivementDefinition aDef=AchivementDefinitionList.getInstance().get(id);
		if(aDef==null)
			throw new IllegalArgumentException("Recieved Achivement with no definition");
		AchivementDefinition=aDef;
		if(aDef.getType()==AchivementTypes.INCREMENTAL)
		{
			if(obj.has("currentSteps"))
			{
				currentSteps=obj.getInt("currentSteps");
				formattedCurrentSteps=obj.getString("formattedCurrentStepsString");
			}
			else
			{
				currentSteps=0;
				formattedCurrentSteps="0";
			}
		}
		String achiveState=obj.getString("achievementState");
		if(achiveState.equalsIgnoreCase("HIDDEN"))
			achivementState=AchivementStates.HIDDEN;
		else if(achiveState.equalsIgnoreCase("REVEALED"))
			achivementState=AchivementStates.REVEALED;
		else if(achiveState.equalsIgnoreCase("UNLOCKED"))
			achivementState=AchivementStates.UNLOCKED;
		else
			throw new IllegalArgumentException("Unknown Achivement State");
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append(String.format("Achivement Definition: \r\n%s",this.getAchivementDefinition().toString()));
		builder.append(String.format("Current State: %s\r\n",this.getAchivementState().toString()));
		if(this.getAchivementDefinition().getType()==AchivementTypes.INCREMENTAL)
		{
			builder.append(String.format("currentSteps: %d\r\n", this.getCurrentSteps()));
			builder.append(String.format("formattedCurrentSteps: %s\r\n", this.getFormattedCurrentSteps()));
		}
		return builder.toString();
	}
}
