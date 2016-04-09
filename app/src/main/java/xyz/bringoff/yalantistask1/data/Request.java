package xyz.bringoff.yalantistask1.data;

public class Request {

    private RequestType mRequestType;
    private String mAddress;
    private long mCreatedDate;
    private long mSolveToDate;
    private int mLikes;

    public Request() {
    }

    public Request(RequestType requestType, String address, long createdDate, long solveToDate, int likes) {
        mRequestType = requestType;
        mAddress = address;
        mCreatedDate = createdDate;
        mSolveToDate = solveToDate;
        mLikes = likes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        if (mCreatedDate != request.mCreatedDate) return false;
        if (mSolveToDate != request.mSolveToDate) return false;
        if (mLikes != request.mLikes) return false;
        if (mRequestType != request.mRequestType) return false;
        return mAddress != null ? mAddress.equals(request.mAddress) : request.mAddress == null;

    }

    @Override
    public int hashCode() {
        int result = mRequestType != null ? mRequestType.hashCode() : 0;
        result = 31 * result + (mAddress != null ? mAddress.hashCode() : 0);
        result = 31 * result + (int) (mCreatedDate ^ (mCreatedDate >>> 32));
        result = 31 * result + (int) (mSolveToDate ^ (mSolveToDate >>> 32));
        result = 31 * result + mLikes;
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "mRequestType=" + mRequestType +
                ", mAddress='" + mAddress + '\'' +
                ", mCreatedDate=" + mCreatedDate +
                ", mSolveToDate=" + mSolveToDate +
                ", mLikes=" + mLikes +
                '}';
    }

    public enum RequestType {
        UTILITY_SECTOR,
        BUILDING,
        OTHER
    }
}
