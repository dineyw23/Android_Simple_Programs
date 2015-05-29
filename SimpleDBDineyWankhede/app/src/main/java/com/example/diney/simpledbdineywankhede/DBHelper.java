package com.example.diney.simpledbdineywankhede;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by diney on 3/4/15.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBname = "unique.db";
    private static final int version = 1;
    private final String MY_TABLE = "exampleTable";

    public DBHelper(Context context){

        super(context,DBname,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + MY_TABLE + " (text VARCHAR PRIMARY KEY);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertText(String s,Context context){
        try{
            SQLiteDatabase qdb = this.getWritableDatabase();
            Log.d("DB Insert: ", "INSERT OR REPLACE INTO " +
                    MY_TABLE + " (text) Values (" + s + ");");
            qdb.execSQL("INSERT INTO " +
                    MY_TABLE + " (text) Values (\""+ s + "\");");
            qdb.close();
            Toast.makeText(context, "Text Inserted", Toast.LENGTH_SHORT).show();
        }
        catch(SQLiteException se){
            Log.d("DB Insert Error: ",se.toString());
            Toast.makeText(context,"Text already in the Database", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    public String getText(){
        String toReturn = "";
        try{
            SQLiteDatabase qdb = this.getReadableDatabase();
            qdb.execSQL("CREATE TABLE IF NOT EXISTS " + MY_TABLE + " (text VARCHAR);");
            Cursor c = qdb.rawQuery("SELECT * FROM " +
                    MY_TABLE, null);
            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        String text = c.getString(c.getColumnIndex("text"));
                        toReturn += text + "\n";
                    }
                    while (c.moveToNext());
                }
            }
            qdb.close();
        }
        catch(SQLiteException se){
            Log.d("DB Select Error: ",se.toString());
            return "";
        }
        return toReturn;
    }


    public void removeText(String s,Context context) {

        try{

            Log.d("DB Delete: ", "Delete from " +
                    MY_TABLE + " where text = " + s + ";");
            SQLiteDatabase qdb = this.getWritableDatabase();
            Cursor c = qdb.rawQuery("SELECT * FROM " +
                    MY_TABLE, null);
            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        if (c.getString(c.getColumnIndex("text")).equals(s)) {
                            qdb.execSQL("DELETE FROM " + MY_TABLE + " WHERE text = '" + s + "';");
                            Toast.makeText(context, "Entry Deleted", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                        while (c.moveToNext());

                    Toast.makeText(context,"Entry does not exist",Toast.LENGTH_SHORT).show();
                qdb.close();
                }

            }}
        catch (SQLiteConstraintException e)
        {
            Log.d("Remove Fail",e.toString());
            Toast.makeText(context,"Entry does not exist",Toast.LENGTH_SHORT).show();
        }


    }


}