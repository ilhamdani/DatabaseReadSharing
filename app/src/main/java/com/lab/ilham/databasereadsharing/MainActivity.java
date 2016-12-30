package com.lab.ilham.databasereadsharing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    private SqlLiteManger _sqlLiteManager;
    private List<String> _listFromDB;
    private ArrayAdapter<String> _adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context sharedContext = null;
        try {
            sharedContext = this.createPackageContext("com.lab.ilham.databaseallowsharing",
                    Context.CONTEXT_INCLUDE_CODE);

            if (sharedContext == null) {
                return;
            }
        }catch (Exception e) {
            String error = e.getMessage();
            System.out.println("DB Error : " + error);
            return;
        }

        _sqlLiteManager = new SqlLiteManger(sharedContext);
        _sqlLiteManager.open();

        listView = (ListView)findViewById(R.id.listView1);

        _listFromDB = _sqlLiteManager.fetch();
        _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _listFromDB);

        listView.setAdapter(_adapter);
    }

    @Override
    protected void onPause() {
        // TODO auto-generated method stub
        super.onPause();
        _sqlLiteManager.truncateTable();
        _sqlLiteManager.close();
    }
}
