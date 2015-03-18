package com.se.dictionary;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailEdit extends Activity {
	String ds;
	SQLiteDatabase db;
	EditText wt, mt, st;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_edit);
		//Initializing
		wt = (EditText) findViewById(R.id.ew);
		mt = (EditText) findViewById(R.id.em);
		st = (EditText) findViewById(R.id.es);
		// Create Database and table
		db = openOrCreateDatabase("dictionarydb", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS dictionary(word VARCHAR, meaning VARCHAR, sentence VARCHAR);");
		

		Bundle bundle = this.getIntent().getExtras();
		wt.setText(bundle.getString("word"));
		mt.setText(bundle.getString("meaning"));
		st.setText(bundle.getString("sentence"));
		ds = bundle.getString("word");
		Toast.makeText(getBaseContext(), "Enter the new values.",
				Toast.LENGTH_SHORT).show();

	}

	public void Commit(View view) {
		db.execSQL("UPDATE dictionary SET word = '"+wt.getText()+"', meaning = '"+mt.getText()+"', sentence = '"+st.getText()+"' WHERE word = '"+ds+"';");
		this.finish();
	}
}
