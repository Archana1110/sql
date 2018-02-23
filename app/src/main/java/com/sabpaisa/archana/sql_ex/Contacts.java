package com.sabpaisa.archana.sql_ex;

/**
 * Created by archana on 20/11/17.
 */

public class Contacts {
    int id;
    String number;
    String name;

    public Contacts(){

    }
    public Contacts(int id) {

        this.id = id;
    }

    public Contacts(String number) {

        this.number = number;
    }

    public Contacts( String number, String name) {


        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
