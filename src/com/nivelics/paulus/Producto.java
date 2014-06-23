package com.nivelics.paulus;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class Producto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_producto);
		
		Resources res = getResources();
		 
		TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		 
		TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Sinapsis");
		tabs.addTab(spec);
		 
		spec=tabs.newTabSpec("mitab2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Detalles");
		tabs.addTab(spec);
		
		spec=tabs.newTabSpec("mitab3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Valoración");
		tabs.addTab(spec);
		
		spec=tabs.newTabSpec("mitab4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Comentar");
		tabs.addTab(spec);
		 
		tabs.setCurrentTab(0);
	}
}
