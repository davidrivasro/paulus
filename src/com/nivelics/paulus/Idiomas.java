package com.nivelics.paulus;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class Idiomas extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_idiomas);
		ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.idiomas, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new SpinnerListener());
		
	}
	
    public class SpinnerListener implements OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent,
            View view, int pos, long id) {
        	Intent iniciarSesion = new Intent(Idiomas.this, IniciarSesion.class);
			startActivity(iniciarSesion);
        }
        public void onNothingSelected(AdapterView<?> parent) {
          // Do nothing.
        }
    }
    
}
