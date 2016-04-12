package xyz.bringoff.yalantistask1.data;

import java.util.UUID;

public class Request {

    private final String mId;
    private RequestType mRequestType;
    private String mAddress;
    private String mStatus;
    private long mCreatedDate;
    private long mRegisteredDate;
    private long mSolveToDate;
    private String mResponsible;
    private String mDescription;
    private int mLikes;

    public Request() {
        mId = UUID.randomUUID().toString();
    }

    public Request(RequestType requestType, String address, String status, long createdDate,
                   long registeredDate, long solveToDate, String responsible,
                   String description, int likes) {
        this();
        mRequestType = requestType;
        mAddress = address;
        mStatus = status;
        mCreatedDate = createdDate;
        mRegisteredDate = registeredDate;
        mSolveToDate = solveToDate;
        mResponsible = responsible;
        mDescription = description;
        mLikes = likes;
    }

    public String getId() {
        return mId;
    }

    public RequestType getRequestType() {
        return mRequestType;
    }

    public void setRequestType(RequestType requestType) {
        mRequestType = requestType;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public long getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(long createdDate) {
        mCreatedDate = createdDate;
    }

    public long getSolveToDate() {
        return mSolveToDate;
    }

    public void setSolveToDate(long solveToDate) {
        mSolveToDate = solveToDate;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setLikes(int likes) {
        mLikes = likes;
    }

    public long getRegisteredDate() {
        return mRegisteredDate;
    }

    public void setRegisteredDate(long registeredDate) {
        mRegisteredDate = registeredDate;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String responsible) {
        mResponsible = responsible;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public enum RequestType {
        UTILITY_SECTOR,
        BUILDING,
        OTHER
    }
}
