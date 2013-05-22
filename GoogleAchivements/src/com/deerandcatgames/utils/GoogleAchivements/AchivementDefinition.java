package com.deerandcatgames.utils.GoogleAchivements;

import org.json.JSONObject;

/**
 * Basic Achivement Defnitions
 * @author Katrina Swales
 *
 */
public class AchivementDefinition {
	
	/**
	 * Types of achivements
	 * @author Katrina
	 *
	 */
	public enum AchivementTypes
	{
		/**
		 * Standard Single Unlock Achivement
		 */
		STANDARD,
		/**
		 * Achivement that unlocks after a certain count
		 */
		INCREMENTAL
	}
	/**
	 * Achivement States
	 * @author Katrina
	 *
	 */
	public enum AchivementStates
	{
		/**
		 * Hidden Achivement, Shouldnt be shown name or description, or icon of until
		 * state is changed otherwise
		 */
		HIDDEN,
		/**
		 * Revealed Achivement, not yet earnt but can see the name, description, and 
		 * revealed icon
		 */
		REVEALED,
		/**
		 * Achivement is unlocked, can now show the unlocked icon
		 */
		UNLOCKED
	}
	private String mId;
	/**
	 * @return the Id
	 */
	public String getId() {
		return mId;
	}
	/**
	 * @return the Name
	 */
	public String getName() {
		return mName;
	}
	/**
	 * @return the Description
	 */
	public String getDescription() {
		return mDescription;
	}
	/**
	 * @return the Type
	 */
	public AchivementTypes getType() {
		return mType;
	}
	/**
	 * @return the TotalSteps
	 */
	public Integer getTotalSteps() {
		return mTotalSteps;
	}
	/**
	 * @return the FormattedTotalSteps
	 */
	public String getFormattedTotalSteps() {
		return mFormattedTotalSteps;
	}
	/**
	 * @return the RevealedIconUrl
	 */
	public String getRevealedIconUrl() {
		return mRevealedIconUrl;
	}
	/**
	 * @return the UnlockedIconUrl
	 */
	public String getUnlockedIconUrl() {
		return mUnlockedIconUrl;
	}
	/**
	 * @return the InitialState
	 */
	public AchivementStates getInitialState() {
		return mInitialState;
	}
	/**
	 * @return the kind
	 */
	public static String getKind() {
		return KIND;
	}
	private String mName;
	private String mDescription;
	private AchivementTypes mType;
	private Integer mTotalSteps;
	private String mFormattedTotalSteps;
	private String mRevealedIconUrl;
	private String mUnlockedIconUrl;
	private AchivementStates mInitialState;
	public static final String KIND="games#achievementDefinition";
	
	public void Load(JSONObject obj) throws Exception
	{
			if(obj==null)
				throw new IllegalArgumentException("obj can not be null");
			if(!obj.has("kind"))
				throw new IllegalArgumentException("obj is not a google json response");
		
			if(!obj.getString("kind").equalsIgnoreCase(KIND))
				throw new IllegalArgumentException("obj is not a AchivementDefinition");
			mId=obj.getString("id");
			mName=obj.getString("name");
			mDescription=obj.getString("description");
			String achiveType=obj.getString("achievementType");
			if(achiveType.equalsIgnoreCase("STANDARD"))
				mType=AchivementTypes.STANDARD;
			else if(achiveType.equalsIgnoreCase("INCREMENTAL"))
				mType=AchivementTypes.INCREMENTAL;
			else
				throw new IllegalArgumentException("Unknown Achivement Type");
			if(mType==AchivementTypes.INCREMENTAL)
			{
				mTotalSteps=obj.getInt("totalSteps");
				mFormattedTotalSteps=obj.getString("formattedTotalSteps");
			}
			mRevealedIconUrl=obj.getString("revealedIconUrl");
			mUnlockedIconUrl=obj.getString("unlockedIconUrl");
			String achiveState=obj.getString("initialState");
			if(achiveState.equalsIgnoreCase("HIDDEN"))
				mInitialState=AchivementStates.HIDDEN;
			else if(achiveState.equalsIgnoreCase("REVEALED"))
				mInitialState=AchivementStates.REVEALED;
			else if(achiveState.equalsIgnoreCase("UNLOCKED"))
				mInitialState=AchivementStates.UNLOCKED;
			else
				throw new IllegalArgumentException("Unknown Achivement State");
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append(String.format("id: %s\r\n", this.getId()));
		builder.append(String.format("name: %s\r\n", this.getName()));
		builder.append(String.format("description: %s\r\n", this.getDescription()));
		builder.append(String.format("type: %s\r\n", this.getType().toString()));
		if(this.getType()==AchivementTypes.INCREMENTAL)
		{
			builder.append(String.format("totalSteps: %d\r\n", this.getTotalSteps()));
			builder.append(String.format("formattedTotalSteps: %s\r\n", this.getFormattedTotalSteps()));
			
		}
		builder.append(String.format("revealedIconUrl: %s\r\n", this.getRevealedIconUrl()));
		builder.append(String.format("unlockedIconUrl: %s\r\n", this.getUnlockedIconUrl()));
		builder.append(String.format("initialState: %s\r\n", this.getInitialState()));
		return builder.toString();
	}
	 
	
}
