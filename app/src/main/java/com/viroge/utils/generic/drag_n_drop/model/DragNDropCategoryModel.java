package com.viroge.utils.generic.drag_n_drop.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.internal.ParcelableSparseArray;

import java.util.Arrays;
import java.util.List;

public class DragNDropCategoryModel implements Parcelable {

    final public String title;
    final public List<Parcelable> items;

    public DragNDropCategoryModel(final String name,
                                  final List<Parcelable> items) {
        this.title = name;
        this.items = items;
    }

    protected DragNDropCategoryModel(Parcel in) {
        this.title = in.readString();
        this.items = Arrays.asList(in.readParcelableArray(ParcelableSparseArray.class.getClassLoader()));
    }

    public static final Creator<DragNDropCategoryModel> CREATOR = new Creator<DragNDropCategoryModel>() {
        @Override
        public DragNDropCategoryModel createFromParcel(Parcel in) {
            return new DragNDropCategoryModel(in);
        }

        @Override
        public DragNDropCategoryModel[] newArray(int size) {
            return new DragNDropCategoryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        final Parcelable accountsArray[] = new Parcelable[items.size()];
        dest.writeParcelableArray(items.toArray(accountsArray), flags);
    }
}
