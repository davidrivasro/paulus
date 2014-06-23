package com.nivelics.paulus;



import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.content.*;
import android.widget.*;


import org.apache.http.util.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.util.*;

public class Home extends Activity implements AnimationListener {
	
	 public String session_id;
	 public String session_name;
	 public String user_name;

	LinearLayout faqFrame;
	LinearLayout cartFrame;
	LinearLayout sesionFrame;
	LinearLayout searchFrame;
	ToggleButton btnStart;
	ToggleButton btnCart;
	ToggleButton btnSesion;
	ToggleButton btnSearch;

	// Animation
	Animation animSideDown;
	Animation animSlideUp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Bundle extras = getIntent().getExtras();
		
        //read the session_id and session_name variables
        if (extras != null) {
            session_id = extras.getString("session_id");
            session_name = extras.getString("session_name");
            user_name = extras.getString("user_name");
        }
		
		final TextView textViewToChange = (TextView) findViewById(R.id.userName);
		textViewToChange.setText(user_name);
		
		
        //initiate the background process to fetch the latest items on Drupal site
        new FetchItems().execute();
		
		faqFrame =  (LinearLayout) findViewById(R.id.faqi);
		cartFrame =  (LinearLayout) findViewById(R.id.cartf);
		sesionFrame =  (LinearLayout) findViewById(R.id.sesionf);
		searchFrame =  (LinearLayout) findViewById(R.id.searchf);
		btnStart = (ToggleButton) findViewById(R.id.btnStart);
		btnCart = (ToggleButton) findViewById(R.id.btnCart);
		btnSesion = (ToggleButton) findViewById(R.id.btnSesion);
		btnSearch = (ToggleButton) findViewById(R.id.btnSearch);
		
		// load the animation
				animSideDown = AnimationUtils.loadAnimation(getApplicationContext(),
						R.anim.slide_down);
				animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(),
						R.anim.slide_up);
				// set animation listener
				animSideDown.setAnimationListener(this);
				animSlideUp.setAnimationListener(this);
				
				// button click event
				btnStart.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if (btnStart.isChecked()) {
							if (cartFrame.getVisibility() == View.VISIBLE) {

								cartFrame.setVisibility(View.GONE);
							} else if(sesionFrame.getVisibility() == View.VISIBLE) {

			                	sesionFrame.setVisibility(View.GONE);
							}else if (searchFrame.getVisibility() == View.VISIBLE){

			                	searchFrame.setVisibility(View.GONE);
							}
							faqFrame.setVisibility(View.VISIBLE);
							faqFrame.startAnimation(animSideDown);
		                } else {
		                	faqFrame.startAnimation(animSlideUp);
		                	faqFrame.setVisibility(View.GONE);
		                }
						
						// start the animation
						
					}
					
				});
				btnCart.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if (btnCart.isChecked()) {
							if (faqFrame.getVisibility() == View.VISIBLE) {

								faqFrame.setVisibility(View.GONE);
							} else if(sesionFrame.getVisibility() == View.VISIBLE) {
			                	sesionFrame.setVisibility(View.GONE);
							}else if (searchFrame.getVisibility() == View.VISIBLE){
			                	searchFrame.setVisibility(View.GONE);
							}
							cartFrame.setVisibility(View.VISIBLE);
							cartFrame.startAnimation(animSideDown);
		                } else {
		                	cartFrame.startAnimation(animSlideUp);
		                	cartFrame.setVisibility(View.GONE);
		                }
						
						// start the animation
						
					}
					
				});
				btnSesion.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if (btnSesion.isChecked()) {
							if (faqFrame.getVisibility() == View.VISIBLE) {
								faqFrame.setVisibility(View.GONE);
							} else if(cartFrame.getVisibility() == View.VISIBLE) {
								cartFrame.setVisibility(View.GONE);
							}else if (searchFrame.getVisibility() == View.VISIBLE){
			                	searchFrame.setVisibility(View.GONE);
							}
							sesionFrame.setVisibility(View.VISIBLE);
							sesionFrame.startAnimation(animSideDown);
		                } else {
		                	sesionFrame.startAnimation(animSlideUp);
		                	sesionFrame.setVisibility(View.GONE);
		                }
						
						// start the animation
						
					}
					
				});
				btnSearch.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if (btnSearch.isChecked()) {
							if (faqFrame.getVisibility() == View.VISIBLE) {
								faqFrame.setVisibility(View.GONE);
							} else if(cartFrame.getVisibility() == View.VISIBLE) {
								cartFrame.setVisibility(View.GONE);
							}else if (sesionFrame.getVisibility() == View.VISIBLE){;
								sesionFrame.setVisibility(View.GONE);
							} 
		                	searchFrame.setVisibility(View.VISIBLE);
							searchFrame.startAnimation(animSideDown);
		                } else {
		                	searchFrame.startAnimation(animSlideUp);
		                	searchFrame.setVisibility(View.GONE);
		                }
						
						// start the animation
						
					}
					
				});
				
	}
	
    private class FetchItems extends AsyncTask<String, Void, JSONArray> {

        protected JSONArray doInBackground(String... params) {


            HttpClient httpclient = new DefaultHttpClient();

            HttpGet httpget = new HttpGet("http://dev-app-paulus.gotpantheon.com/sp/node?parameters['type']=product_display");
            //set header to tell REST endpoint the request and response content types
            httpget.setHeader("Accept", "application/json");
            httpget.setHeader("Content-type", "application/json");

            
            JSONArray json = new JSONArray();

            try {

            	
                HttpResponse response = httpclient.execute(httpget);

                //read the response and convert it into JSON array
                json = new JSONArray(EntityUtils.toString(response.getEntity()));
                //return the JSON array for post processing to onPostExecute function
                return json;



            }catch (Exception e) {
                Log.v("Error adding article",e.getMessage());
            }



            return json;
        }


        //executed after the background nodes fetching process is complete
        protected void onPostExecute(JSONArray result) {

            //get the ListView UI element
            ListView lst = (ListView)  findViewById(R.id.listView);

            //create the ArrayList to store the titles of nodes
            ArrayList<String> listItems=new ArrayList<String>();

            //iterate through JSON to read the title of nodes
            for(int i=0;i<result.length();i++){
                try {
                    listItems.add(result.getJSONObject(i).getString("title").toString());
                } catch (Exception e) {
                    Log.v("Error adding article", e.getMessage());
                }
            }

            //create array adapter and give it our list of nodes, pass context, layout and list of items
            ArrayAdapter ad= new ArrayAdapter(Home.this, android.R.layout.simple_list_item_1,listItems);

            //give adapter to ListView UI element to render
            lst.setAdapter(ad);
        }
    }
	
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	  MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.menu, menu);

	        return super.onCreateOptionsMenu(menu);
	 }
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case R.id.bt_categorias:
	    	  Intent categorias = new Intent(Home.this, Categorias.class);
              startActivity(categorias);
	        return(true);

	    }
	    
	    return(super.onOptionsItemSelected(item));
	  }
	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
		if (animation == animSideDown) {			
		}

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}
}
