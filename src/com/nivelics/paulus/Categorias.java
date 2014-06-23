package com.nivelics.paulus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Categorias extends Activity {
	
	  ListView list;
	  String[] web = {
	    "Biblia",
	      "Familia",
	      "Catequesis",
	      "Comunicación",
	      "Espiritualidad",
	      "Psicología",
	      "Superación",
	      "Duelo"
	  } ;
	  String[] cuenta = {
			    "10 libros",
			      "20 libros",
			      "5 libros",
			      "40 libros",
			      "8 libros",
			      "6 libros",
			      "70 libros",
			      "2 libros"
			  } ;
	  Integer[] imageId = {
	      R.drawable.img_categoria,
	      R.drawable.img_categoria2,
	      R.drawable.img_categoria,
	      R.drawable.img_categoria,
	      R.drawable.img_categoria,
	      R.drawable.img_categoria,
	      R.drawable.img_categoria,
	      R.drawable.img_categoria
	  };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categorias);
		CustomList adapter = new
				CustomList(Categorias.this, web, cuenta, imageId);
		list=(ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                        Intent hCategoria = new Intent(Categorias.this, HomeCategoria.class);
                        startActivity(hCategoria);
            }
        });

	}
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	  MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.menu, menu);

	        return super.onCreateOptionsMenu(menu);
	 }
}
