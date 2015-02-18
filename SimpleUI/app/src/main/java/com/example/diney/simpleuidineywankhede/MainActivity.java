package com.example.diney.simpleuidineywankhede;

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
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Context;


public class MainActivity extends ActionBarActivity {
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
    public static class PlaceholderFragment extends Fragment implements OnClickListener {
        private View rootView;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button button1 = (Button) rootView.findViewById(R.id.b1);
            button1.setOnClickListener(this);
            Button button2 = (Button) rootView.findViewById(R.id.b2);
            button2.setOnClickListener(this);
            Button button3 = (Button) rootView.findViewById(R.id.b3);
            button3.setOnClickListener(this);

            return rootView;
        }
        public void onClick(View src) {
            TextView tv1 = (TextView) rootView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) rootView.findViewById(R.id.tv2);
            EditText et1 = (EditText) rootView.findViewById(R.id.et1);
            switch (src.getId()){
                case R.id.b1:
                    tv1.setText(et1.getText());

                    break;
                case R.id.b2:
                    tv2.setText(et1.getText());


                    break;
                case R.id.b3:
                    tv1.setText(et1.getText());
                    tv2.setText(et1.getText());


                    break;

            }
        }
    }
}
