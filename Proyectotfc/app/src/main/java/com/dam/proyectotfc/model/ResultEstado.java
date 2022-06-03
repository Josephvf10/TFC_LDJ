
package com.dam.proyectotfc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultEstado implements Parcelable {

    @SerializedName("aliases")
    @Expose
    private Object aliases;
    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("deck")
    @Expose
    private String deck;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("expected_release_day")
    @Expose
    private Object expectedReleaseDay;
    @SerializedName("expected_release_month")
    @Expose
    private Object expectedReleaseMonth;
    @SerializedName("expected_release_quarter")
    @Expose
    private Object expectedReleaseQuarter;
    @SerializedName("expected_release_year")
    @Expose
    private Object expectedReleaseYear;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("image_tags")
    @Expose
    private List<ImageTag> imageTags = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number_of_user_reviews")
    @Expose
    private Integer numberOfUserReviews;
    @SerializedName("original_game_rating")
    @Expose
    private List<OriginalGameRating> originalGameRating = null;
    @SerializedName("original_release_date")
    @Expose
    private String originalReleaseDate;
    @SerializedName("platforms")
    @Expose
    private List<Platform> platforms = null;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;

    protected ResultEstado(Parcel in) {
        apiDetailUrl = in.readString();
        dateAdded = in.readString();
        dateLastUpdated = in.readString();
        deck = in.readString();
        guid = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            numberOfUserReviews = null;
        } else {
            numberOfUserReviews = in.readInt();
        }
        originalReleaseDate = in.readString();
        siteDetailUrl = in.readString();
    }

    public static final Creator<ResultEstado> CREATOR = new Creator<ResultEstado>() {
        @Override
        public ResultEstado createFromParcel(Parcel in) {
            return new ResultEstado(in);
        }

        @Override
        public ResultEstado[] newArray(int size) {
            return new ResultEstado[size];
        }
    };

    public Object getAliases() {
        return aliases;
    }

    public void setAliases(Object aliases) {
        this.aliases = aliases;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getExpectedReleaseDay() {
        return expectedReleaseDay;
    }

    public void setExpectedReleaseDay(Object expectedReleaseDay) {
        this.expectedReleaseDay = expectedReleaseDay;
    }

    public Object getExpectedReleaseMonth() {
        return expectedReleaseMonth;
    }

    public void setExpectedReleaseMonth(Object expectedReleaseMonth) {
        this.expectedReleaseMonth = expectedReleaseMonth;
    }

    public Object getExpectedReleaseQuarter() {
        return expectedReleaseQuarter;
    }

    public void setExpectedReleaseQuarter(Object expectedReleaseQuarter) {
        this.expectedReleaseQuarter = expectedReleaseQuarter;
    }

    public Object getExpectedReleaseYear() {
        return expectedReleaseYear;
    }

    public void setExpectedReleaseYear(Object expectedReleaseYear) {
        this.expectedReleaseYear = expectedReleaseYear;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<ImageTag> imageTags) {
        this.imageTags = imageTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfUserReviews() {
        return numberOfUserReviews;
    }

    public void setNumberOfUserReviews(Integer numberOfUserReviews) {
        this.numberOfUserReviews = numberOfUserReviews;
    }

    public List<OriginalGameRating> getOriginalGameRating() {
        return originalGameRating;
    }

    public void setOriginalGameRating(List<OriginalGameRating> originalGameRating) {
        this.originalGameRating = originalGameRating;
    }

    public String getOriginalReleaseDate() {
        return originalReleaseDate;
    }

    public void setOriginalReleaseDate(String originalReleaseDate) {
        this.originalReleaseDate = originalReleaseDate;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(apiDetailUrl);
        parcel.writeString(dateAdded);
        parcel.writeString(dateLastUpdated);
        parcel.writeString(deck);
        parcel.writeString(guid);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        if (numberOfUserReviews == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numberOfUserReviews);
        }
        parcel.writeString(originalReleaseDate);
        parcel.writeString(siteDetailUrl);
    }
}
