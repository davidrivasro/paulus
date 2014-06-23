package com.nivelics.paulus;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;




import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;




public class Registrarse extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrarse);
		
	}
	String user_name;
	String session_name;
	String session_id;
	
	 private class doLoginRegister extends AsyncTask<String, Integer, Integer> {
		 
	     protected Integer doInBackground(String... params) {
	    	 
	    	 HttpClient httpClient=new DefaultHttpClient();
	    	 
	    	 HttpPost httpPost= new HttpPost("http://dev-app-paulus.gotpantheon.com/sp/user/login");
	    	 
	    	 try{
	    		 
	    		 EditText username = (EditText) findViewById(R.id.userRegister);
	    		 EditText password = (EditText) findViewById(R.id.password1);
	    		 
	    		 
	    		 JSONObject json = new JSONObject ();
	    		 json.put("username",username.getText().toString().trim());
	    		 json.put("password",password.getText().toString().trim());
	    		 
	    		 StringEntity se = new StringEntity(json.toString());
	    		 se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
	    		 httpPost.setEntity(se);
	    		 
	    		 HttpResponse response = httpClient.execute(httpPost);
	    		 
	    		 String jsonResponse = EntityUtils.toString(response.getEntity());
	    		 
	    		 JSONObject jsonObj= new JSONObject(jsonResponse);
	    		 session_name= jsonObj.getString("session_name");
	    		 session_id= jsonObj.getString("sessid");
	    		 user_name= jsonObj.getJSONObject("user").getString("name");
	    		 
	    	 }catch(Exception e){
	    		 Log.v("Error iniciando sesion en:", e.getMessage());
	    	 }
	    	 
	         return 0;
	     }

	     protected void onPostExecute(Integer result) {
	    	 Intent intent = new Intent(Registrarse.this,Home.class);
	    	 
	    	 intent.putExtra("session_name", session_name);
	    	 intent.putExtra("session_id", session_id);
	    	 intent.putExtra("user_name", user_name);
	    	 
	    	 startActivity(intent);
	    	 
	     }
	 }
	
	 private class doRegister extends AsyncTask<String, Integer, Integer> {
		 
	     protected Integer doInBackground(String... params) {
	    	 
	    	 HttpClient httpClient=new DefaultHttpClient();
	    	 
	    	 HttpPost httpPost= new HttpPost("http://dev-app-paulus.gotpantheon.com/sp/user/register");
	    	 
	    	 try{
	    		 
	    		 EditText emailRegister = (EditText) findViewById(R.id.emailRegister);
	    		 EditText userRegister = (EditText) findViewById(R.id.userRegister);
	    		 EditText password1 = (EditText) findViewById(R.id.password1);
	    		 EditText password2 = (EditText) findViewById(R.id.password2);
	    		 EditText paisRegister = (EditText) findViewById(R.id.paisRegister);
	    		 EditText ciudadRegister = (EditText) findViewById(R.id.ciudadRegister);
	    		 
	    		 JSONObject json = new JSONObject ();
	    		 json.put("name",userRegister.getText().toString().trim());
	    		 json.put("pass",password1.getText().toString().trim());
	    		 json.put("mail",emailRegister.getText().toString().trim());
	    		 /*json.put("field_pais",paisRegister.getText().toString().trim());
	    		 json.put("field_ciudad",ciudadRegister.getText().toString().trim());*/
	    		 
	    		 StringEntity se = new StringEntity(json.toString());
	    		 se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
	    		 httpPost.setEntity(se);
	    		 
	    		 HttpResponse response = httpClient.execute(httpPost);
	    		 
	    		 String jsonResponse = EntityUtils.toString(response.getEntity());
	    		 
	    		 JSONObject jsonObj= new JSONObject(jsonResponse);
	    		 session_name= jsonObj.getString("session_name");
	    		 session_id= jsonObj.getString("sessid");
	    		 user_name= jsonObj.getJSONObject("user").getString("name");
	    	 }catch(Exception e){
	    		 Log.v("Error iniciando sesion en:", e.getMessage());
	    	 }
	    	 
	         return 0;
	     }

	     protected void onPostExecute(Integer result) {
	    	 
	    	 new doLoginRegister().execute();	
	    	 
	     }
	 }
	
	public void doRegisterButton_click(View view){
		
	new doRegister().execute();	
	}
	

}
