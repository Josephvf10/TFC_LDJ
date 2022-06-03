
package com.dam.proyectotfc.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JuegoDetalles implements Parcelable {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("number_of_page_results")
    @Expose
    private Integer numberOfPageResults;
    @SerializedName("number_of_total_results")
    @Expose
    private Integer numberOfTotalResults;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("results")
    @Expose
    private ResultsDetalles results;
    @SerializedName("version")
    @Expose
    private String version;

    protected JuegoDetalles(Parcel in) {
        error = in.readString();
        if (in.readByte() == 0) {
            limit = null;
        } else {
            limit = in.readInt();
        }
        if (in.readByte() == 0) {
            offset = null;
        } else {
            offset = in.readInt();
        }
        if (in.readByte() == 0) {
            numberOfPageResults = null;
        } else {
            numberOfPageResults = in.readInt();
        }
        if (in.readByte() == 0) {
            numberOfTotalResults = null;
        } else {
            numberOfTotalResults = in.readInt();
        }
        if (in.readByte() == 0) {
            statusCode = null;
        } else {
            statusCode = in.readInt();
        }
        version = in.readString();
    }

    public static final Creator<JuegoDetalles> CREATOR = new Creator<JuegoDetalles>() {
        @Override
        public JuegoDetalles createFromParcel(Parcel in) {
            return new JuegoDetalles(in);
        }

        @Override
        public JuegoDetalles[] newArray(int size) {
            return new JuegoDetalles[size];
        }
    };

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumberOfPageResults() {
        return numberOfPageResults;
    }

    public void setNumberOfPageResults(Integer numberOfPageResults) {
        this.numberOfPageResults = numberOfPageResults;
    }

    public Integer getNumberOfTotalResults() {
        return numberOfTotalResults;
    }

    public void setNumberOfTotalResults(Integer numberOfTotalResults) {
        this.numberOfTotalResults = numberOfTotalResults;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ResultsDetalles getResults() {
        return results;
    }

    public void setResults(ResultsDetalles results) {
        this.results = results;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(error);
        if (limit == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(limit);
        }
        if (offset == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(offset);
        }
        if (numberOfPageResults == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numberOfPageResults);
        }
        if (numberOfTotalResults == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numberOfTotalResults);
        }
        if (statusCode == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(statusCode);
        }
        parcel.writeString(version);
    }
}
