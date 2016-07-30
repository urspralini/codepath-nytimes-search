package com.prabhu.codepath.nytimessearch.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pbabu on 7/30/16.
 */
public class FilterOptions implements Parcelable {
    public static final Parcelable.Creator<FilterOptions> CREATOR = new Parcelable.Creator<FilterOptions>() {
        @Override
        public FilterOptions createFromParcel(Parcel source) {
            return new FilterOptions(source);
        }

        @Override
        public FilterOptions[] newArray(int size) {
            return new FilterOptions[size];
        }
    };
    private String date;
    private SortOrder sortOrder;
    private Set<String> newsDeskValues;


    public FilterOptions() {
        sortOrder = SortOrder.Newest;
        newsDeskValues = new HashSet<>();
    }

    protected FilterOptions(Parcel in) {
        this.date = in.readString();
        int tmpSortOrder = in.readInt();
        this.sortOrder = tmpSortOrder == -1 ? null : SortOrder.values()[tmpSortOrder];
        int newDeskValueSize = in.readInt();
        if(newDeskValueSize > 0) {
            List<String> newsDeskValues = new ArrayList<>();
            in.readStringList(newsDeskValues);
            this.newsDeskValues = new HashSet<>(newsDeskValues);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<String> getNewsDeskValues() {
        return newsDeskValues;
    }

    public void setNewsDeskValues(Set<String> newsDeskValues) {
        this.newsDeskValues = newsDeskValues;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.sortOrder == null ? -1 : this.sortOrder.ordinal());
        dest.writeInt(this.newsDeskValues.size());
        if(newsDeskValues.size() > 0){
            dest.writeStringList(new ArrayList<>(this.newsDeskValues));
        }

    }

    public enum SortOrder {
        Newest,
        Oldest
    }

    public void addNewsDesk(String newsDesk) {
        newsDeskValues.add(newsDesk);
    }

    public void removeNewsDesk(String newsDesk) {
        newsDeskValues.remove(newsDesk);
    }

    public String getNewDeskValues() {
        if(newsDeskValues == null || newsDeskValues.isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        for(String newsDeskValue : newsDeskValues) {
            if(sb.length() > 0) {
                sb.append(" ");
            }
            sb.append("\"" +newsDeskValue + "\"");
        }
        return sb.toString();
    }
}
