package com.deerandcatgames.utils.GoogleAchivements;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class LeaderboardDefinitionList implements
		Map<String, LeaderboardDefinition> {

	Map<String,LeaderboardDefinition> mLeaderboardDefintions;
	protected static LeaderboardDefinitionList instance;
	protected boolean isInitialized=false;
	public static LeaderboardDefinitionList getInstance()
	{
		if(instance==null)
			instance=new LeaderboardDefinitionList();
		return instance;
	}
	private LeaderboardDefinitionList()
	{
		mLeaderboardDefintions=new HashMap<String, LeaderboardDefinition>();
	
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		mLeaderboardDefintions.clear();

	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object arg0) {
		return mLeaderboardDefintions.containsKey(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object arg0) {
		return mLeaderboardDefintions.containsValue(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<String, LeaderboardDefinition>> entrySet() {
		return mLeaderboardDefintions.entrySet();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public LeaderboardDefinition get(Object arg0) {
		return mLeaderboardDefintions.get(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return mLeaderboardDefintions.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<String> keySet() {
		return mLeaderboardDefintions.keySet();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public LeaderboardDefinition put(String arg0, LeaderboardDefinition arg1) {
		return mLeaderboardDefintions.put(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(
			Map<? extends String, ? extends LeaderboardDefinition> arg0) {
		mLeaderboardDefintions.putAll(arg0);

	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public LeaderboardDefinition remove(Object arg0) {
		return mLeaderboardDefintions.remove(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return mLeaderboardDefintions.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<LeaderboardDefinition> values() {
		return mLeaderboardDefintions.values();
	}
	
	public static final String KIND="games#leaderboardListResponse";
	public void Load(JSONObject obj) throws Exception
	{
		this.clear();
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a LeaderboardDefinitionList");
		
		JSONArray items=obj.getJSONArray("items");
		for(int i=0;i<items.length();i++)
		{
			JSONObject item=items.getJSONObject(i);
			LeaderboardDefinition aDef=new LeaderboardDefinition();
			aDef.Load(item);
			this.put(aDef.getId(), aDef);
		}
		isInitialized=true;
	}

}
