package com.example.diney.filemanipulatordineywankhede;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class MainActivity extends ActionBarActivity {

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        try {
            file = new File(Environment.getExternalStorageDirectory().getPath()+"/test.txt");

            if(!file.exists())
            {
                file.createNewFile();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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

        int i = 0;
        View rootView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button button1 = (Button) rootView.findViewById(R.id.b1);
            Button button2 = (Button) rootView.findViewById(R.id.b2);
            Button button3 = (Button) rootView.findViewById(R.id.b3);
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);

           return rootView;

        }
        public void clear() {
            EditText edit1 = (EditText) rootView.findViewById(R.id.et1);
            edit1.setText("");

        }

        public void appendText(String s, int i) {


            try {
                File file = new File(Environment.getExternalStorageDirectory().getPath()
                        + "/test.txt");
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter writeBuffer = new BufferedWriter(fw);

                if (i < 1)          //To avoid the first newline
                {
                    writeBuffer.newLine();
                    writeBuffer.write(s);
                    i++;
                }
                else
                {
                    writeBuffer.newLine();
                    writeBuffer.write(s);
                }
                writeBuffer.close();
            }

            catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void overWriteText(String s) {

            try {
                File file = new File(Environment.getExternalStorageDirectory().getPath()
                        +"/test.txt");
                FileWriter fw = new FileWriter(file);
                BufferedWriter writeBuffer = new BufferedWriter(fw);
                writeBuffer.write(s);
                writeBuffer.close();
            }

            catch(Exception e) {
                e.printStackTrace();

            }
        }

        public String readText() {

            String contents = "";
            try {

                File file = new File(Environment.getExternalStorageDirectory().getPath()
                        + "/test.txt");
                FileReader fr = new FileReader(file);
                BufferedReader readBuffer = new BufferedReader(fr);
                int i = 0;
                while (readBuffer.ready()) {  //To avoid the first newline
                    if (i < 1)
                    {
                        contents = contents + readBuffer.readLine();
                        i++;
                    }
                    else
                        contents = contents + '\n' + readBuffer.readLine();
                }
                readBuffer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return contents;
        }


        public void onClick(View src) {

            EditText edit1 = (EditText) rootView.findViewById(R.id.et1);
            switch (src.getId()) {

                case R.id.b1:   //Reading from file
                    TextView tv = (TextView) rootView.findViewById(R.id.tv1);
                    String read = readText();
                    if (read != null && !read.isEmpty()) {
                      tv.setText(read);
                      readText();
                      Toast.makeText(PlaceholderFragment.this.getActivity(),
                                "READ SUCCESS", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv.setText(read);
                        Toast.makeText(PlaceholderFragment.this.getActivity(),
                                "FILE IS EMPTY", Toast.LENGTH_SHORT).show();
                    }
                      break;

                case R.id.b3:   //Writing to a file
                    String write = edit1.getText().toString();
                        overWriteText(write);
                        clear();
                        Toast.makeText(PlaceholderFragment.this.getActivity(),
                                "WRITE SUCCESS", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.b2:   //Appending to a file
                    String append = edit1.getText().toString();
                    if(append !=null && !append.isEmpty()) {
                        appendText(append, i);
                        i++;
                        clear();
                        Toast.makeText(PlaceholderFragment.this.getActivity(),
                                "APPEND SUCCESS",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(PlaceholderFragment.this.getActivity(),
                                "PLEASE ENTER SOME TEXT",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
