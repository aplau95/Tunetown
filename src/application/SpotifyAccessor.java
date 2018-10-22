package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class SpotifyAccessor {
	
	private String accessToken;
	private CloseableHttpClient client;
	
	public SpotifyAccessor(String clientId, String clientSecret) throws ClientProtocolException, IOException {
		
		client = HttpClientBuilder.create().build();
		
		HttpPost httppost = new HttpPost("https://accounts.spotify.com/api/token");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", "client_credentials"));
		httppost.setEntity(new UrlEncodedFormEntity(params));
		
		String encoding = Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes());
		httppost.setHeader("Authorization", "Basic " + encoding);
		
		CloseableHttpResponse response = client.execute(httppost);
		String bodyAsString = EntityUtils.toString(response.getEntity());
		
		JSONObject obj = new JSONObject(bodyAsString);
		
		accessToken = obj.getString("access_token");
		
	}
	
	public JSONObject getSampleTrack() throws ClientProtocolException, IOException {
		
		HttpGet httpget = new HttpGet("https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V");
		httpget.setHeader("Authorization","Bearer "+accessToken);
		
		CloseableHttpResponse response = client.execute(httpget);
		String bodyAsString = EntityUtils.toString(response.getEntity());
		
		return new JSONObject(bodyAsString);
	}
	
}