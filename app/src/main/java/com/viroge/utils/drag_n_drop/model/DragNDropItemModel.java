package com.viroge.utils.drag_n_drop.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DragNDropItemModel implements Parcelable {
    public boolean isHidden;

    final public String title;
    final public String subtitle;
    final public String date;

    public DragNDropItemModel(final boolean isHidden,
                              final String title,
                              final String subtitle,
                              final String date) {
        this.isHidden = isHidden;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    protected DragNDropItemModel(Parcel in) {
        this.isHidden = in.readByte() != 0;
        this.title = in.readString();
        this.subtitle = in.readString();
        this.date = in.readString();
    }

    public static final Creator<DragNDropItemModel> CREATOR = new Creator<DragNDropItemModel>() {
        @Override
        public DragNDropItemModel createFromParcel(Parcel in) {
            return new DragNDropItemModel(in);
        }

        @Override
        public DragNDropItemModel[] newArray(int size) {
            return new DragNDropItemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isHidden ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.date);
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public String toString() {
        return "DragNDropItemModel{" +
                "\n isHidden=" + isHidden +
                "\n title='" + title + '\'' +
                "\n subtitle='" + subtitle + '\'' +
                "\n date='" + date + '\'' +
                '}';
    }
}
