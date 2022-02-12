package com.elegantmedia.test.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.elegantmedia.test.R;

public class Helper {

    /**
     * @param uri = Url of the image
     * @param imageView = Image that set to view
     */
    public static void setCircleImage(String uri, ImageView imageView) {

        if(uri == null || uri.isEmpty()){
            return;
        }

        Glide.with(imageView.getContext()).load(uri).load(R.drawable.ic_broken).apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }

    /**
     * @param uri = Url of the image
     * @param imageView = Image that set to view
     */
    public static void setImage(String uri, ImageView imageView) {

        if(uri == null || uri.isEmpty()){
            return;
        }

        Glide.with(imageView.getContext()).load(uri).load(R.drawable.ic_broken)
                .into(imageView);
    }
}
