package com.deerandcatgames.utils.GoogleAchivements;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class ListRequest extends Request {

	public ListRequest(String request, Map<String, String> data) {
		super(request, data);
	}

	@Override
	public JSONObject Execute() throws JSONException {
		// TODO Auto-generated method stub
		JSONObject obj=super.Execute();
		
		if(obj.has("nextPageToken"))
		{
			String pageToken=obj.getString("nextPageToken");
			Map<String,String> newData=new HashMap<String, String>();
			for(Entry<String,String> entry:mData.entrySet())
			{
				newData.put(entry.getKey(), entry.getValue());
				
			}
			newData.put("pageToken", pageToken);
			JSONObject deep=new Request(mRequest, newData).Execute();
			for(int i=0;i<deep.getJSONArray("items").length();i++)
				obj.getJSONArray("items").put(deep.getJSONArray("items").get(i));
			
			while(deep.has("nextPageToken"))
			{
				pageToken=deep.getString("nextPageToken");
				newData=new HashMap<String, String>();
				for(Entry<String,String> entry:mData.entrySet())
				{
					newData.put(entry.getKey(), entry.getValue());
					
				}
				newData.put("pageToken", pageToken);
				deep=new Request(mRequest, newData).Execute();
				for(int i=0;i<deep.getJSONArray("items").length();i++)
					obj.getJSONArray("items").put(deep.getJSONArray("items").get(i));
				
			}
		}
		
		
		return obj;
	}
	
	

}
