package com.se.dictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Create Database and table
		db = openOrCreateDatabase("dictionarydb", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS dictionary(word VARCHAR, meaning VARCHAR, sentence VARCHAR);");
	}

	public void AddActivity(View view) {

		Intent intent = new Intent(this, AddWord.class);
		startActivity(intent);
	}
	public void DisplayActivity(View view) {

		Intent intent = new Intent(this, DisplayWords.class);
		startActivity(intent);
	}
	public void DeleteActivity(View view) {

		Intent intent = new Intent(this, DeleteWords.class);
		startActivity(intent);
	}
	public void EditActivity(View view) {

		Intent intent = new Intent(this, EditWords.class);
		startActivity(intent);
	}

}
