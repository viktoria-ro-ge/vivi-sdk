package com.viroge.utils.drag_n_drop.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DragNDropNoteModel implements Parcelable {

    final public String title;
    final public String freeText;

    public DragNDropNoteModel(final String name,
                              final String freeText) {
        this.title = name;
        this.freeText = freeText;
    }

    protected DragNDropNoteModel(Parcel in) {
        this.title = in.readString();
        this.freeText = in.readString();
    }

    public static final Creator<DragNDropNoteModel> CREATOR = new Creator<DragNDropNoteModel>() {
        @Override
        public DragNDropNoteModel createFromParcel(Parcel in) {
            return new DragNDropNoteModel(in);
        }

        @Override
        public DragNDropNoteModel[] newArray(int size) {
            return new DragNDropNoteModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(freeText);
    }
}
