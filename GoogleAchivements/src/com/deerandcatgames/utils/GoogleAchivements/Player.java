package com.deerandcatgames.utils.GoogleAchivements;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {

	private String id;
	private String name;
	private String avatarImageUrl;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the avatarImageUrl
	 */
	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}
	/**
	 * @param avatarImageUrl the avatarImageUrl to set
	 */
	public void setAvatarImageUrl(String avatarImageUrl) {
		this.avatarImageUrl = avatarImageUrl;
	}
	
	public static final String KIND="games#player";
	
	public void Load(JSONObject obj) throws Exception
	{
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a Player");

		id=obj.getString("playerId");
		name=obj.getString("displayName");
		avatarImageUrl=obj.getString("avatarImageUrl");
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append(String.format("Diplay Name : %s\r\n",this.name));
		builder.append(String.format("Player ID   : %s\r\n",this.id));
		builder.append(String.format("Display URL : %s\r\n",this.avatarImageUrl));
		return builder.toString();
	}
}
