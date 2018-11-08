package com.majes.uottawa.taskmanager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 11-21-2017.
 */

public class User {

    /* Attributes */
    private String mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private boolean isParent;
    private int mPoints;
    private List<String> mAssignedTasks;
    private String mFamily;


    /* Constructeurs */
    public User(String mId, String mFirstName, String mLastName, String mEmail, boolean isParent) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.isParent = isParent;

        this.mPoints = 0;
        this.mAssignedTasks = new ArrayList<>();

        this.mFamily = null;
    }

    public User() {
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

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }


    public void setIsParent(Boolean isParent){
        this.isParent = isParent;
    }

    public Boolean getIsParent(){
        return isParent;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        this.mPoints = points;
    }

    public List<String> getAssignedTasks() {
        return mAssignedTasks;
    }

    public void setAssignedTasks(List<String> assignedTasks) {
        this.mAssignedTasks = assignedTasks;
    }

    public String getFamily() {
        return mFamily;
    }

    public void setFamily(String family) {
        this.mFamily = family;
    }

}

