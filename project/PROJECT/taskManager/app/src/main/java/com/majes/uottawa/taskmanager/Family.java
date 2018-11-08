package com.majes.uottawa.taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 04-12-2017.
 */
public class Family {

    /* Attributes*/
    private String mId;
    private List<String> mUsers;
    private List<String> mTasks;
    private String mFamilyName;


    /* Constructeurs */
    public Family(String familyName) {
        this.mId = new String();
        this.mUsers = new ArrayList<>();
        this.mTasks = new ArrayList<>();
        this.mFamilyName = familyName;
    }

    public Family() {
        //SET BY DEFAULT BY ECLIPSE, CHANGE IF NECESSARY
    }



    /*
	*	getters et setters
	*/

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public List<String> getUsers() {
        return mUsers;
    }

    public List<String> getTasks() {
        return mTasks;
    }

    public void setUsers(List<String> users) {
        this.mUsers = users;
    }

    public void setTasks(List<String> tasks) {
        this.mTasks = tasks;
    }

    public String getFamilyName() {
        return mFamilyName;
    }

    public void setFamilyName(String familyName) {
        this.mFamilyName = familyName;
    }
}
