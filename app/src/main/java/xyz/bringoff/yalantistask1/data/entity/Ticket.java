package xyz.bringoff.yalantistask1.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_date")
    @Expose
    private long createdDate;
    @SerializedName("start_date")
    @Expose
    private long startDate;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("ticket_id")
    @Expose
    private String ticketId;
    @SerializedName("files")
    @Expose
    private List<File> files = new ArrayList<>();
    @SerializedName("performers")
    @Expose
    private List<Performer> performers = new ArrayList<Performer>();
    @SerializedName("deadline")
    @Expose
    private long deadline;
    @SerializedName("likes_counter")
    @Expose
    private int likesCounter;

    /**
     * No args constructor for use in serialization
     */
    public Ticket() {
    }

    public Ticket(int id, User user, Category category, Type type, String title, String body,
                  int createdDate, int startDate, State state, String ticketId, List<File> files,
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public int getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(int likesCounter) {
        this.likesCounter = likesCounter;
    }

    public static class Category {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * No args constructor for use in serialization
         */
        public Category() {
        }

        public Category(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class State {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * No args constructor for use in serialization
         */
        public State() {
        }

        public State(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Performer {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("organization")
        @Expose
        private String organization;
        @SerializedName("person")
        @Expose
        private String person;
        @SerializedName("deadline")
        @Expose
        private int deadline;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public int getDeadline() {
            return deadline;
        }

        public void setDeadline(int deadline) {
            this.deadline = deadline;
        }
    }

    public static class Type {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * No args constructor for use in serialization
         */
        public Type() {
        }

        public Type(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class File {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("filename")
        @Expose
        private String filename;

        /**
         * No args constructor for use in serialization
         */
        public File() {
        }

        public File(Integer id, String name, String filename) {
            this.id = id;
            this.name = name;
            this.filename = filename;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

    }
}