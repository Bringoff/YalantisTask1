package xyz.bringoff.yalantistask1.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("middle_name")
    @Expose
    public String middleName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("birthday")
    @Expose
    public int birthday;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("fb_registered")
    @Expose
    public int fbRegistered;

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    public User(int id, String firstName, String lastName, String middleName, String email,
                int birthday, String phone, Address address, int fbRegistered) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.fbRegistered = fbRegistered;
    }

}