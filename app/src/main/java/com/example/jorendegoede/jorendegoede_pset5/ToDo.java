package com.example.jorendegoede.jorendegoede_pset5;

import java.io.Serializable;

/**
 * Created by Joren de Goede on 12-5-2017.
 */

public class ToDo implements Serializable {

    // State
    private String todo;
    static boolean check;
    private int _id;

    // listview
    public ToDo(String todoName, boolean checkStatus, int id){
        todo = todoName;
        check = checkStatus;
        _id = id;
    }

    // list
    public ToDo(String todoName, boolean checkStatus){
        todo = todoName;
        check = checkStatus;
    }

    // getters
    public String getTodo() {

        return todo;
    }

    public boolean getChecked() {

        return check;
    }

    public int getID() {

        return _id;
    }

    // setters
    public void setChecked(boolean checkStatus) {
        check = checkStatus;
    }
}
