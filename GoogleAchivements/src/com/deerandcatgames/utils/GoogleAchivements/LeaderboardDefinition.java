package com.deerandcatgames.utils.GoogleAchivements;

import org.json.JSONException;
import org.json.JSONObject;

public class LeaderboardDefinition {

	public enum LeaderboardOrder
	{
		LARGER_IS_BETTER,
		SMALLER_IS_BETTER
	}
	protected String name;
	protected String iconUrl;
	protected LeaderboardOrder order;
	protected String id;
	protected final static String KIND="games#leaderboard";
	
	public void Load(JSONObject obj) throws Exception
	{
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a LeaderboardDefinition");
		id=obj.getString("id");
		name=obj.getString("name");
		iconUrl=obj.getString("iconUrl");
		String orderString=obj.getString("order");
		if(orderString.equalsIgnoreCase("LARGER_IS_BETTER"))
			order=LeaderboardOrder.LARGER_IS_BETTER;
		else if(orderString.equalsIgnoreCase("SMALLER_IS_BETTER"))
			order=LeaderboardOrder.SMALLER_IS_BETTER;
		else
			throw new IllegalArgumentException("Order is invalid");
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append(String.format("Id: %s \r\n", id) );
		builder.append(String.format("Name: %s \r\n", name) );
		builder.append(String.format("IconURL: %s \r\n", iconUrl) );
		builder.append(String.format("Order: %s \r\n", order.toString()) );
		return builder.toString();
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
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return the order
	 */
	public LeaderboardOrder getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(LeaderboardOrder order) {
		this.order = order;
	}

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
	 * @return the kind
	 */
	public static String getKind() {
		return KIND;
	}
}
