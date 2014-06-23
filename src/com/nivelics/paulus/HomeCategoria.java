package com.nivelics.paulus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HomeCategoria extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_categoria);
		
		ImageView imgProducto = (ImageView) findViewById(R.id.productoThumb);
        imgProducto.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent producto = new Intent(HomeCategoria.this, Producto.class);
                startActivity(producto);
                
            }
            
            
            
        });
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
	    	  Intent categorias = new Intent(HomeCategoria.this, Categorias.class);
             startActivity(categorias);
	        return(true);

	    }
	    
	    return(super.onOptionsItemSelected(item));
	  }
}
