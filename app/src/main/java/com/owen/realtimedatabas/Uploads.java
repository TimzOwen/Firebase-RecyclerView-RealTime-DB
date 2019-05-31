package com.owen.realtimedatabas;

public class Uploads {

    private String mName, mImageUrl;

    public Uploads()
    {

    }
    public Uploads(String name, String imageurl)
    {
        //check and makke sure there are no blank spaces
        if (name.trim().equals(""))
        {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageurl;
    }
    //getter and setter methods;

    public  String getName()
    {
        return mName;
    }

    public void  setName(String name)
    {
        name = mName;
    }

    public String setImageUrl()
    {
        return mImageUrl;
    }

    public void getImageUrl(String imageUrl)
    {
        mImageUrl=imageUrl;
    }

}