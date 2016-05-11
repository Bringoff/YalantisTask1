package xyz.bringoff.yalantistask1.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("category")
    @Expose
    public Category category;
    @SerializedName("type")
    @Expose
    public Type type;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("created_date")
    @Expose
    public int createdDate;
    @SerializedName("start_date")
    @Expose
    public int startDate;
    @SerializedName("state")
    @Expose
    public State state;
    @SerializedName("ticket_id")
    @Expose
    public String ticketId;
    @SerializedName("files")
    @Expose
    public List<Object> files = new ArrayList<Object>();
    @SerializedName("performers")
    @Expose
    public List<Performer> performers = new ArrayList<Performer>();
    @SerializedName("deadline")
    @Expose
    public int deadline;
    @SerializedName("likes_counter")
    @Expose
    public int likesCounter;

    /**
     * No args constructor for use in serialization
     */
    public Ticket() {
    }

    public Ticket(int id, User user, Category category, Type type, String title, String body,
                  int createdDate, int startDate, State state, String ticketId, List<Object> files,
                  List<Performer> performers, int deadline, int likesCounter) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.type = type;
        this.title = title;
        this.body = body;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.state = state;
        this.ticketId = ticketId;
        this.files = files;
        this.performers = performers;
        this.deadline = deadline;
        this.likesCounter = likesCounter;
    }

    public static class Category {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;

        /**
         * No args constructor for use in serialization
         */
        public Category() {
        }

        public Category(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    public static class State {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;

        /**
         * No args constructor for use in serialization
         */
        public State() {
        }

        public State(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Performer {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("organization")
        @Expose
        public String organization;
        @SerializedName("person")
        @Expose
        public String person;
        @SerializedName("deadline")
        @Expose
        public int deadline;

        /**
         * No args constructor for use in serialization
         */
        public Performer() {
        }

        public Performer(int id, String organization, String person, int deadline) {
            this.id = id;
            this.organization = organization;
            this.person = person;
            this.deadline = deadline;
        }

    }

    public class Type {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;

        /**
         * No args constructor for use in serialization
         */
        public Type() {
        }

        /**
         * @param id
         * @param name
         */
        public Type(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }
}