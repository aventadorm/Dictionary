package com.se.dictionary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteWords extends Activity {
	ListView l;
	SQLiteDatabase db;
	DCursorAdapter dcursoradapter;
	String w;

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

				w = ((TextView) view).getText().toString();

				new AlertDialog.Builder(DeleteWords.this)
						.setTitle("Delete entry")
						.setMessage(
								"Are you sure you want to delete this entry?")
						.setPositiveButton(android.R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// continue with delete

										db.execSQL("DELETE from dictionary WHERE word = '"
												+ w + "'");
										Cursor d = db
												.rawQuery(
														"SELECT rowid _id,* FROM dictionary",
														null);
										dcursoradapter.changeCursor(d);
										l.setAdapter(dcursoradapter);
									}
								})
						.setNegativeButton(android.R.string.no,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// do nothing
									}
								}).setIcon(android.R.drawable.ic_dialog_alert)
						.show();

			}
		});

	}

}
