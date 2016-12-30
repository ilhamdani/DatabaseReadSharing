package com.lab.ilham.databasereadsharing;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by ILHAM on 08/12/2016.
 */

public class SqlLiteManger {
    private SqlLiteDbHelper _dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SqlLiteManger(Context c) {
        context = c;
        _dbHelper = new SqlLiteDbHelper(context);
    }

    public SqlLiteManger open() throws SQLException {
        database = _dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        _dbHelper.close();
    }

    public ArrayList<String> fetch() {
        ArrayList<String> list = new ArrayList<>();
        String[] columns = new String[] { SqlLiteDbHelper._ID, SqlLiteDbHelper.VERSION_NAME, SqlLiteDbHelper.VERSION_NO};
        Cursor cursor = database.query(SqlLiteDbHelper.TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null & cursor.moveToFirst()) {
            do {
                String _versionName = cursor.getString(cursor.getColumnIndex(SqlLiteDbHelper.VERSION_NAME));
                String _versionNo = cursor.getString(cursor.getColumnIndex(SqlLiteDbHelper.VERSION_NO));
                list.add("Version Name : " + _versionName + ", \nVersion No : " + _versionNo);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void truncateTable() {
        database.delete(SqlLiteDbHelper.TABLE_NAME, null, null);
    }
}
