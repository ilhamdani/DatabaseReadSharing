package com.lab.ilham.databasereadsharing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ILHAM on 08/12/2016.
 */

public class SqlLiteDbHelper extends SQLiteOpenHelper {

    public static final String TAG = "ReadDatabase";

    //Table Name
    public static final String TABLE_NAME = "ANDROID_OS_LIST";

    //Table columns
    public static final String _ID = "_id";
    public static final String VERSION_NAME = "version_name";
    public static final String VERSION_NO = "version_no";

    //Database information
    static final String DB_NAME = "android_os.db";

    //Database version
    static final int DB_VERSION = 1;

    public SqlLiteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d(TAG, "onCreate ExternalDbManager");
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade ExternalDbManager");
    }
}
