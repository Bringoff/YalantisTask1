package xyz.bringoff.yalantistask1.data.model;

import java.util.ArrayList;
import java.util.List;

import xyz.bringoff.yalantistask1.data.remote.ApiConstants;

public class Ticket {

    public static final String STATUS_IN_PROGRESS = "in_progress";
    public static final String STATUS_DONE = "done";
    public static final String STATUS_PENDING = "pending";

    private int mId;
    private String mStatus;
    private String mType;
    private String mDescription;
    private String mAddress;
    private String mResponsible;
    private Long mCreatingDate;
    private Long mRegisteringDate;
    private Long mDeadlineDate;
    private Integer mLikesCount;
    private List<String> mImageNames;
    private List<String> mImageUrls;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String responsible) {
        mResponsible = responsible;
    }

    public Long getCreatingDate() {
        return mCreatingDate;
    }

    public void setCreatingDate(Long creatingDate) {
        mCreatingDate = creatingDate;
    }

    public Long getRegisteringDate() {
        return mRegisteringDate;
    }

    public void setRegisteringDate(Long registeringDate) {
        mRegisteringDate = registeringDate;
    }

    public Long getDeadlineDate() {
        return mDeadlineDate;
    }

    public void setDeadlineDate(Long deadlineDate) {
        mDeadlineDate = deadlineDate;
    }

    public Integer getLikesCount() {
        return mLikesCount;
    }

    public void setLikesCount(Integer likesCount) {
        mLikesCount = likesCount;
    }

    public List<String> getImageNames() {
        return mImageNames;
    }

    public void setImageNames(List<String> imageNames) {
        mImageNames = imageNames;
        mImageUrls = generateImageUrls(mImageNames);
    }

    public List<String> getImageUrls() {
        if (mImageUrls == null) {
            mImageUrls = generateImageUrls(mImageNames);
        }
        return mImageUrls;
    }

    private List<String> generateImageUrls(List<String> imageNames) {
        List<String> urls = new ArrayList<>();
        if (mImageNames != null) {
            for (String name : mImageNames) {
                urls.add(String.format(ApiConstants.API_IMAGE_URL_FORMAT, name));
            }
        }
        return urls;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "mId=" + mId +
                ", mStatus='" + mStatus + '\'' +
                ", mType='" + mType + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mResponsible='" + mResponsible + '\'' +
                ", mCreatingDate=" + mCreatingDate +
                ", mRegisteringDate=" + mRegisteringDate +
                ", mDeadlineDate=" + mDeadlineDate +
                ", mLikesCount=" + mLikesCount +
                ", mImageNames=" + mImageNames +
                ", mImageUrls=" + mImageUrls +
                '}';
    }
}
