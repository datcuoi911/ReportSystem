package dxc.technology.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;

public interface ModuleService {

	public void getModule(String linkAPIHead, String linkAPIFoot) throws ParseException, IOException, JSONException;

	public ArrayList<Integer> getIdModule();

	public int findId(String key);
}
