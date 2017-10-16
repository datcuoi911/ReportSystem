package com.dxc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;

import com.dxc.model.DailyData;

public interface APIService {

	public List<String> getKeyAPI(String linkAPI) throws IOException, ParseException, JSONException;

	public JSONArray getValueAPI(String key) throws IOException, ParseException, JSONException;

	public ArrayList<DailyData> getDailyData();
}
