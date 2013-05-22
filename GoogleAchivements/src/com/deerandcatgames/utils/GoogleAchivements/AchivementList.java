/**
 * 
 */
package com.deerandcatgames.utils.GoogleAchivements;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Katrina
 *
 */
public class AchivementList implements Map<String, Achivement> {

	
	Map<String,Achivement> mAchivements;
	
	
	public AchivementList()
	{
		mAchivements=new HashMap<String, Achivement>();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		mAchivements.clear();

	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return mAchivements.containsKey(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return mAchivements.containsValue(value);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<String, Achivement>> entrySet() {
		return mAchivements.entrySet();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Achivement get(Object key) {
		return mAchivements.get(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return mAchivements.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<String> keySet() {
		return mAchivements.keySet();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Achivement put(String key, Achivement value) {
		return mAchivements.put(key, value);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ? extends Achivement> arg0) {
		mAchivements.putAll(arg0);

	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Achivement remove(Object key) {
		return mAchivements.remove(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return mAchivements.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Achivement> values() {
		return mAchivements.values();
	}
	
	public static final String KIND="games#playerAchievementListResponse";
	public void Load(JSONObject obj) throws Exception
	{
		this.clear();
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		if(!obj.has("kind"))
			throw new IllegalArgumentException("obj is not a google json response");
	
		if(!obj.getString("kind").equalsIgnoreCase(KIND))
			throw new IllegalArgumentException("obj is not a AchivementDefinitionList");
		
		JSONArray items=obj.getJSONArray("items");
		for(int i=0;i<items.length();i++)
		{
			JSONObject item=items.getJSONObject(i);
			Achivement ach=new Achivement();
			ach.Load(item);
			this.put(ach.getAchivementDefinition().getId(), ach);
		}
	}

}
