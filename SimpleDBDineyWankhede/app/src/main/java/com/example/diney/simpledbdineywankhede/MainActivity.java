package com.example.diney.simpledbdineywankhede;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;


public class MainActivity extends ActionBarActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        context = this;


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        private View rootView;
        private DBHelper dbHelper;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
             rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button add = (Button) rootView.findViewById(R.id.add);
            Button get = (Button) rootView.findViewById(R.id.get);
            Button remove = (Button) rootView.findViewById(R.id.remove);
            add.setOnClickListener(this);
            get.setOnClickListener(this);
            remove.setOnClickListener(this);
            dbHelper = new DBHelper(getActivity());

            return rootView;
        }


        public void onClick(View src) {
            EditText editText = (EditText) rootView.findViewById(R.id.et1);
            switch (src.getId()) {

                case R.id.add:


                    if (editText.getText().toString().equals("")) {
                   Toast.makeText(getActivity(),"Please Insert Some Text",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dbHelper.insertText(editText.getText().toString(),getActivity());
                    }
                    editText.setText("");
                    break;
                case R.id.get:
                    TextView tv = (TextView) rootView.findViewById(R.id.tv1);
                    Toast.makeText(getActivity(),"Data Fetched from Database",Toast.LENGTH_SHORT).show();
                    tv.setText(dbHelper.getText());
                    editText.setText("");
                    break;

                case R.id.remove:
                    if (editText.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Please Insert Some Text",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dbHelper.removeText(editText.getText().toString(), getActivity());
                        editText.setText("");
                    }
                    break;
            }

        }

    }


}
