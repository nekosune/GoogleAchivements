/**
 * 
 */
package com.deerandcatgames.utils.GoogleAchivements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Katrina
 *
 */
public class LeaderboardEntryList implements List<LeaderboardEntry> {

	
	
	protected List<LeaderboardEntry> mList;
	
	
	public LeaderboardEntryList()
	{
		mList=new ArrayList<LeaderboardEntry>();
	}
	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(LeaderboardEntry arg0) {
		return mList.add(arg0);
		// TODO Sort here
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int arg0, LeaderboardEntry arg1) {
		mList.add(arg0, arg1);
		// TODO sort here too

	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends LeaderboardEntry> arg0) {
		return mList.addAll(arg0);
		// TODO SORT
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int arg0, Collection<? extends LeaderboardEntry> arg1) {
		return mList.addAll(arg0, arg1);
		// TODO SOOOOORRT
	}

	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		mList.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object arg0) {
		return mList.contains(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return mList.containsAll(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public LeaderboardEntry get(int arg0) {
		return mList.get(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object arg0) {
		return mList.indexOf(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return mList.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<LeaderboardEntry> iterator() {
		return mList.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object arg0) {
		return mList.lastIndexOf(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<LeaderboardEntry> listIterator() {
		return mList.listIterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<LeaderboardEntry> listIterator(int arg0) {
		return mList.listIterator(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public LeaderboardEntry remove(int arg0) {
		return mList.remove(arg0);
		// TODO sort
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object arg0) {
		return mList.remove(arg0);
		// TODO sort
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return mList.removeAll(arg0);
		// TODO sort
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return mList.retainAll(arg0);
		//TODO sort
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public LeaderboardEntry set(int arg0, LeaderboardEntry arg1) {
		return mList.set(arg0,arg1);
		// TODO sort
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return mList.size();
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<LeaderboardEntry> subList(int arg0, int arg1) {
		return mList.subList(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return mList.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(T[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return mList.toArray(arg0);
	}
	
	public void Load(JSONArray obj) throws Exception
	{
		if(obj==null)
			throw new IllegalArgumentException("obj can not be null");
		LeaderboardEntry entry=new LeaderboardEntry();
		for(int i=0;i<obj.length();i++)
		{
			entry=new LeaderboardEntry();
			entry.Load(obj.getJSONObject(i));
			this.add(entry);
		}
	}
}
