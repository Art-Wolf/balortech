package com.balortech.breeze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceCall {
	private final HttpClient Client = new DefaultHttpClient();
	private String Content;
	private String Error = null;
	String data = "";
	int sizeData = 0;

	public void onPreExecute(String message) {
		try {
			// Set Request parameter
			data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + message;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Call after onPreExecute method
	protected String doInBackground(String server) {
		// Send data
		try {
			// Defined URL where to send data
			URL url = new URL(server);

			// Send POST data request
			URLConnection conn = url.openConnection();

			writeToWS(conn);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			// Get the server response
			Content = readFromWS(reader);

		} catch (Exception ex) {
			Error = ex.getMessage();
		}

		return Content;
	}

	public String readFromWS(BufferedReader reader) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		String line = null;

		// Read Server Response
		try {
			while ((line = reader.readLine()) != null) {
				// Append server response in string
				sb.append(line + "");
			}
		} catch (IOException ex) {
			Error = ex.getMessage();
		} finally {
			try {
				reader.close();
			} catch (Exception ex) {
			}
		}

		// Append Server Response To Content String
		return sb.toString();
	}

	public void writeToWS(URLConnection conn) throws IOException {
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();
	}

	public String jsonConvet(String json) {
		String OutputData = "";
		JSONObject jsonResponse;

		try {

			/******
			 * Creates a new JSONObject with name/value mappings from the JSON
			 * string.
			 ********/
			jsonResponse = new JSONObject(json);

			/*****
			 * Returns the value mapped by name if it exists and is a JSONArray.
			 ***/
			/******* Returns null otherwise. *******/
			JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

			/*********** Process each JSON Node ************/

			int lengthJsonArr = jsonMainNode.length();

			for (int i = 0; i < lengthJsonArr; i++) {
				/****** Get Object for each JSON node. ***********/
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

				/******* Fetch node values **********/
				String name = jsonChildNode.optString("name").toString();
				String number = jsonChildNode.optString("number").toString();
				String date_added = jsonChildNode.optString("date_added")
						.toString();

			}
			/****************** End Parse Response JSON Data *************/

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return OutputData;
	}
}
