package com.example.jorendegoede.jorendegoede_pset5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //
    DBManager helper;
    Context context;
    ToDo todo;
    TodoAdapter adapter;

    // Global variables
    ListView taskListView;
    EditText taskEditText;
    TextView textColor;

    // Arraylists
    ArrayList<ToDo> todoList;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize variables
        taskListView = (ListView) findViewById(R.id.taskListView);
        taskEditText = (EditText) findViewById(R.id.taskEditText);

        // set listeners for listitems
        taskListView.setOnItemClickListener(new shortListener());
        taskListView.setOnItemLongClickListener(new longListener());

        // create databaseHelper
        context = this;
        helper = new DBManager(this);
        helper.open();

        loadTaskList();
    }

    // load tasklist method
    private void loadTaskList() {

        todoList = helper.read();

        // link to listview
        adapter = new TodoAdapter(this, R.layout.row, todoList);
        taskListView.setAdapter(adapter);



    }

    private void updateList() {
        // get fresh data from db
        todoList = helper.read();

        // update listview
        adapter = new TodoAdapter(getApplicationContext(), R.layout.row, todoList);
        taskListView.setAdapter(adapter);

    }

    // add item method
    public void addItem(View view){
        // initialize EditText
        taskEditText = (EditText) findViewById(R.id.taskEditText);

        // Convert input to string
        String newTask = taskEditText.getText().toString();

        // if String not empty make new todo
        if (!newTask.isEmpty()) {
            // construct new todo
            ToDo newTodo = new ToDo(newTask, false);
            // add new todo to list
            helper.create(newTodo);
            // notify the adapter the data changed so it can update
            updateList();
        }
        // clear input textView
        taskEditText.getText().clear();
    }

    // delete when long click
    private class longListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            helper.delete(todoList.get(position));

            updateList();
            return true;
        }
    }

    // task completed when short click
    public class shortListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            checkState(todoList.get(position));
            Log.d("test", "test");

            updateList();
        }
    }

    public void checkState(ToDo todo) {

        Log.d("checkSTate", "test");
        // change checkStatus
        if (todo.getChecked() == false) {
            todo.setChecked(true);
        }
        else {
            todo.setChecked(false);
        }

        helper.update(todo);
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

        String textViewValue = taskEditText.getText().toString();
        outState.putString("taskEditText", textViewValue);

    }

    @Override
    public void onRestoreInstanceState (Bundle inState) {
        super.onRestoreInstanceState(inState);

        //String textViewValueRestored = inState.getString("taskEditText");
        //taskEditText.setText(textViewValueRestored);

//        // set check state of todos
//        for (int i = 0; i < todoList.size(); i++) {
//
//            if (ToDo.getChecked() == true) {
//                textColor.setTextColor(0xff404d); //red
//            }
//            else {
//                textColor.setTextColor(0xFF000000); //black
//            }
//        }
    }

}