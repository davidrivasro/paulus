package com.nivelics.paulus;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class IniciarSesion extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iniciar_sesion);
		
		Button btRegistrar = (Button) findViewById(R.id.registrarButton);
		btRegistrar.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent producto = new Intent(IniciarSesion.this, Registrarse.class);
                startActivity(producto);  
            }
        });
		
	}
	String user_name;
	String session_name;
	String session_id;
	
	 private class doLogin extends AsyncTask<String, Integer, Integer> {
		 
	     protected Integer doInBackground(String... params) {
	    	 
	    	 HttpClient httpClient=new DefaultHttpClient();
	    	 
	    	 HttpPost httpPost= new HttpPost("http://dev-app-paulus.gotpantheon.com/sp/user/login");
	    	 
	    	 try{
	    		 
	    		 EditText username = (EditText) findViewById(R.id.editUsername);
	    		 EditText password = (EditText) findViewById(R.id.editPassword);
	    		 
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
	    	 Intent intent = new Intent(IniciarSesion.this,Home.class);
	    	 
	    	 intent.putExtra("session_name", session_name);
	    	 intent.putExtra("session_id", session_id);
	    	 intent.putExtra("user_name", user_name);
	    	 
	    	 startActivity(intent);
	    	 
	     }
	 }
	
	public void doLoginButton_click(View view){
		
	new doLogin().execute();	
	}	
}
