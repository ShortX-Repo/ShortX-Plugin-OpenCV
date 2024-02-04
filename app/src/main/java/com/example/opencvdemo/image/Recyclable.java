package com.example.opencvdemo.image;

import org.autojs.autojs.core.image.ImageWrapper;

public interface Recyclable {

    void recycle();

    boolean isRecycled();

    ImageWrapper setOneShot(boolean b);

    default ImageWrapper oneShot() {
        return setOneShot(true);
    }

    void shoot();

}
