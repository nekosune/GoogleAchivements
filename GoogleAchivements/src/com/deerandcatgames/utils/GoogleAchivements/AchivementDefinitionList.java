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
 * Basic class to hold  and load Achivement Definitions
 * @author Katrina
 *
 */
public class AchivementDefinitionList implements Map<String,AchivementDefinition>
{

	Map<String,AchivementDefinition> mAchivementDefinitions;

	protected static AchivementDefinitionList instance;
	protected boolean isInitialized=false;
	public static AchivementDefinitionList getInstance()
	{
		if(instance==null)
			instance=new AchivementDefinitionList();
		return instance;
	}
	
	private AchivementDefinitionList()
	{
		mAchivementDefinitions=new HashMap<String, AchivementDefinition>();
	}
	
	/**
	 * @return the if the Lisyt has been loaded yet
	 */
	public boolean isInitialized() {
		return isInitialized;
	}
	/**
	 * Removes all of the mappings from this map (optional operation). The map will be empty after this call returns.
	 */
	@Override
	public void clear() {
		mAchivementDefinitions.clear();
		
	}

	/**
	 * Returns <code>true</code> if this map contains a mapping for the specified key. 
	 * More formally, returns true if and only if this map contains a mapping for a key k such that (key==null ? k==null : key.equals(k)). 
	 * (There can be at most one such mapping.)
	 * @param key key whose presence in this map is to be tested
	 * @return <code>true</code>  if this map contains a mapping for the specified key
	 * @throws ClassCastException if the key is of an inappropriate type for this map (optional)
	 * @throws NullPointerException  if the specified key is null and this map does not permit null keys (optional)
	 */
	@Override
	public boolean containsKey(Object key) {
		return mAchivementDefinitions.containsKey(key);
	}

	/**
	 * Returns <code>true</code> if this map maps one or more keys to the specified value.
	 *  More formally, returns <code>true</code> if and only if this map contains at least one mapping to a value v such that <code>(value==null ? v==null : value.equals(v))</code>.
	 *  This operation will probably require time linear in the map size for most implementations of the {@link Map} interface.
	 *  @param value value whose presence in this map is to be tested
	 *  @return <code>true</code>  if this map maps one or more keys to the specified value
	 */
	@Override
	public boolean containsValue(Object value) {
		return mAchivementDefinitions.containsValue(value);
	}

	/**
	 * Returns a {@link Set} view of the mappings contained in this map. 
	 * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
	 * 
	 * @return a set of the mappings
	 */
	@Override
	public Set<java.util.Map.Entry<String, AchivementDefinition>> entrySet() {
		return mAchivementDefinitions.entrySet();
	}

	/**
	 * Returns the value of the mapping with the specified key.
	 * @param key the key.
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 */
	@Override
	public AchivementDefinition get(Object key) {
		return mAchivementDefinitions.get(key);
	}

	/**
	 * Returns whether this map is empty.
	 * @return <code>true</code> if this map has no elements, <code>false</code> otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return mAchivementDefinitions.isEmpty();
	}

	/**
	 * Returns a set of the keys contained in this Map. 
	 * The {@link Set} is backed by this Map so changes to one are reflected by the other. 
	 * The Set does not support adding.
	 * @return a set view of the keys contained in this map
	 */
	@Override
	public Set<String> keySet() {
		return mAchivementDefinitions.keySet();
	}

	/**
	 * Maps the specified key to the specified value 
	 * Shouldn't be needed or used, left just in case. Use <code>Load</code> instead
	 * @param key the key.
	 * @param value the value.
	 * @return the value of any previous mapping with the specified key or null if there was no mapping.
	 */
	@Override
	public AchivementDefinition put(String key, AchivementDefinition value) {
		return mAchivementDefinitions.put(key, value);
	}

	/**
	 * Copies all of the mappings from the specified map to this map (optional operation). The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map. The behavior of this operation is undefined if the specified map is modified while the operation is in progress.
	 * @param arg0 mappings to be stored in this map
	 */
	@Override
	public void putAll(
			Map<? extends String, ? extends AchivementDefinition> arg0) {
		mAchivementDefinitions.putAll(arg0);
		
	}

	/**
	 * Removes a mapping with the specified key from this Map.
	 * @param key the key of the mapping to remove
	 * @return value of the removed mapping or null if no mapping for the specified key was found.
	 */
	@Override
	public AchivementDefinition remove(Object key) {
		return mAchivementDefinitions.remove(key);
	}

	/**
	 * Returns the number of mappings in this Map.
	 * @return the number of mappings in this Map.
	 */
	@Override
	public int size() {
		return mAchivementDefinitions.size();
	}

	/**
	 * Returns a Collection of the values contained in this Map. The Collection is backed by this Map so changes to one are reflected by the other. The Collection supports remove(Object), removeAll(Collection), retainAll(Collection), and clear() operations, and it does not support add(E) or addAll(Collection) operations. 
	 * <p>
	 * This method returns a Collection which is the subclass of AbstractCollection. The iterator() method of this subclass returns a "wrapper object" over the iterator of this Map's entrySet(). The size() method wraps this Map's size() method and the contains(Object) method wraps this Map's containsValue(Object) method. 
	 * <p>
	 * The collection is created when this method is called at first time and returned in response to all subsequent calls. This method may return different Collection when multiple calls to this method, since it has no synchronization performed.
	 * @return a collection of all the values in this map
	 */
	@Override
	public Collection<AchivementDefinition> values() {
		return mAchivementDefinitions.values();
	}
	public static final String KIND="games#achievementDefinitionsListResponse";
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
			AchivementDefinition aDef=new AchivementDefinition();
			aDef.Load(item);
			this.put(aDef.getId(), aDef);
		}
		isInitialized=true;
	}

}
