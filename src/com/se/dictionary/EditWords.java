package com.se.dictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class EditWords extends Activity {
	ListView l;
	SQLiteDatabase db;
	DCursorAdapter dcursoradapter;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// Cursor query to display all
		Cursor c = db.rawQuery("SELECT rowid _id,* FROM dictionary", null);
		dcursoradapter = new DCursorAdapter(this, c);
		l.setAdapter(dcursoradapter);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create Database and table
		db = openOrCreateDatabase("dictionarydb", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS dictionary(word VARCHAR, meaning VARCHAR, sentence VARCHAR);");

		// Cursor query to display all
		Cursor c = db.rawQuery("SELECT rowid _id,* FROM dictionary", null);
		setContentView(R.layout.display);
		l = (ListView) findViewById(R.id.listview1);
		dcursoradapter = new DCursorAdapter(this, c);
		l.setAdapter(dcursoradapter);

		l.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String w = ((TextView) view).getText().toString();
				Cursor d = db.rawQuery(
						"SELECT * from dictionary WHERE word = '" + w + "'",
						null);
				d.moveToFirst();
				String m = d.getString(1);
				String s = d.getString(2);

				Bundle basket = new Bundle();
				basket.putString("word", w);
				basket.putString("meaning", m);
				basket.putString("sentence", s);
				Intent displayintent = new Intent(EditWords.this,
						DetailEdit.class);
				displayintent.putExtras(basket);
				startActivity(displayintent);

			}
		});

	}

}
