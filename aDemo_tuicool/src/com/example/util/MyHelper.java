package com.example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper{

	public MyHelper(Context context
			//, String name, CursorFactory factory, int version
			) {
		super(context, "feedbackContent", null, 1);
		
	}

	// ±í
	@Override
	public void onCreate(SQLiteDatabase db) {
		String str="CREATE TABLE Feedback (id INTEGER PRIMARY KEY AUTOINCREMENT,info VARCHAR(20),currenttime VARCHAR(20))";
		db.execSQL(str);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
