/**
 * 
 */
package com.deerandcatgames.utils.GoogleAchivements.tasks;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import com.deerandcatgames.utils.GoogleAchivements.AchivementDefinitionList;
import com.deerandcatgames.utils.GoogleAchivements.ListRequest;
import com.deerandcatgames.utils.GoogleAchivements.callbacks.AchivementDefinitionCallback;

import android.os.AsyncTask;

/**
 * Task to load the Achivement Def
 * @author Katrina
 *
 */
public class AchivementDefinitionsLoaderTask extends
		AsyncTask<Void, Void, Void> {

	
	public AchivementDefinitionCallback callback;
	
	public AchivementDefinitionsLoaderTask()
	{
		callback=null;
	}
	public AchivementDefinitionsLoaderTask(AchivementDefinitionCallback cllback)
	{
		callback=cllback;
	}
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... arg0) {
		
		try {
			Map<String,String> options=new HashMap<String, String>();
			JSONObject obj;
			obj = new ListRequest("achievements", options).Execute();
			AchivementDefinitionList.getInstance().Load(obj);
			if(callback!=null)
				callback.OnLoadComplete();
		} catch (Exception e) {
			if(callback!=null)
				callback.OnError(e);
		}
		
		return null;
	}

}
