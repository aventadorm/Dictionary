package com.se.dictionary;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DCursorAdapter extends CursorAdapter {

	public DCursorAdapter(Context context, Cursor c) {
		super(context, c, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(
				R.layout.simple_list_item_1, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView wtext = (TextView) view.findViewById(R.id.text1);
		String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
		wtext.setText(word);
	}
}
