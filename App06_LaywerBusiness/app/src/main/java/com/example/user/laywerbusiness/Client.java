package com.example.user.laywerbusiness;

/**
 * Created by user on 21/12/2017.
 */

public class Client {
    public int _id;
    public String _name;
    public String _start_date;
    public String _end_date;
    public String _state;

    public Client() {
    }

    public Client(int _id, String _name, String _start_date, String _end_date, String _state) {
        this._id = _id;
        this._name = _name;
        this._start_date = _start_date;
        this._end_date = _end_date;
        this._state = _state;
    }

    public Client(String _name, String _start_date, String _end_date, String _state) {
        this._name = _name;
        this._start_date = _start_date;
        this._end_date = _end_date;
        this._state = _state;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_start_date() {
        return _start_date;
    }

    public void set_start_date(String _start_date) {
        this._start_date = _start_date;
    }

    public String get_end_date() {
        return _end_date;
    }

    public void set_end_date(String _end_date) {
        this._end_date = _end_date;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }
}
