package com.se.dictionary;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWord extends Activity {
	EditText WORD, MEANING, SENTENCE;
	Button add;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_word);
		// Initializing
		WORD = (EditText) findViewById(R.id.word);
		MEANING = (EditText) findViewById(R.id.meaning);
		SENTENCE = (EditText) findViewById(R.id.sentence);
		add = (Button) findViewById(R.id.addb);

		// Create Database and table
		db = openOrCreateDatabase("dictionarydb", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS dictionary(word VARCHAR, meaning VARCHAR, sentence VARCHAR);");

	}

	public void AddInfo(View view) {
		Cursor d = db.rawQuery(
				"SELECT * from dictionary WHERE word = '" + WORD.getText()
						+ "'", null);
		if (WORD.getText().toString().isEmpty()
				|| WORD.getText().toString().trim().length() == 0) {
			Toast.makeText(getBaseContext(), "Please insert a word.",
					Toast.LENGTH_SHORT).show();
		} else if (MEANING.getText().toString().isEmpty()
				|| MEANING.getText().toString().trim().length() == 0) {
			Toast.makeText(getBaseContext(), "Please insert the meaning.",
					Toast.LENGTH_SHORT).show();
		} else if (SENTENCE.getText().toString().isEmpty()
				|| SENTENCE.getText().toString().trim().length() == 0) {
			Toast.makeText(getBaseContext(), "Please insert a sentence.",
					Toast.LENGTH_SHORT).show();
		} else if (d.getCount() != 0)
			Toast.makeText(getBaseContext(), "Word already exists.",
					Toast.LENGTH_SHORT).show();
		else {
			db.execSQL("INSERT INTO dictionary VALUES('" + WORD.getText()
					+ "','" + MEANING.getText() + "','" + SENTENCE.getText()
					+ "');");
			Toast.makeText(getBaseContext(), "Word has been added.",
					Toast.LENGTH_SHORT).show();
		}

	}
}