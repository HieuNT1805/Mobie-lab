package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class History implements Parcelable {
    String data;
    String result;

    public History(String data, String result){
        this.data=data;
        this.result=result;
    }

    public String getData(){
        return data;
    }

    public String getResult(){
        return result;
    }
    public History(Parcel in){
        data = in.readString();
        result= in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int i)
    {
        //this is the order you should read in your contructor
        out.writeString(data);
        out.writeString(result);
//      writing some custom object: out.writeTypedList(someCustomObjectArrayList);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    //for arrays and creating from a parcel
    public static final Parcelable.Creator<History> CREATOR = new Parcelable.Creator<History>() {
        public History createFromParcel(Parcel in) {
            return new History(in);
        }
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
