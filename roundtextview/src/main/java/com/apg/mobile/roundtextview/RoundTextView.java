package com.apg.mobile.roundtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by X-tivity on 3/29/2017 AD.
 */

public class RoundTextView extends AppCompatTextView {

    private static final int DEFAULT_CORNER = 5;
    private static final int DEFAULT_ALL_CORNER = Integer.MIN_VALUE;

    private int tvBgColor = Color.TRANSPARENT;
    private float tvAllCorner = DEFAULT_ALL_CORNER;
    private float tvTopLeftCorner = DEFAULT_CORNER;
    private float tvTopRightCorner = DEFAULT_CORNER;
    private float tvBottomRightCorner = DEFAULT_CORNER;
    private float tvBottomLeftCorner = DEFAULT_CORNER;

    public RoundTextView(Context context) {
        super(context);
        setViewBackground();
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractAttribute(context, attrs);
        setViewBackground();
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractAttribute(context, attrs);
        setViewBackground();
    }

    private void extractAttribute(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextViewCorner, 0, 0);
        try {
            tvBgColor = ta.getColor(R.styleable.TextViewCorner_bgColor, Color.TRANSPARENT);
            tvAllCorner = ta.getDimension(R.styleable.TextViewCorner_allCorner, DEFAULT_ALL_CORNER);
            tvTopLeftCorner = ta.getDimension(R.styleable.TextViewCorner_topLeftCorner, DEFAULT_CORNER);
            tvTopRightCorner = ta.getDimension(R.styleable.TextViewCorner_topRightCorner, DEFAULT_CORNER);
            tvBottomRightCorner = ta.getDimension(R.styleable.TextViewCorner_bottomRightCorner, DEFAULT_CORNER);
            tvBottomLeftCorner = ta.getDimension(R.styleable.TextViewCorner_bottomLeftCorner, DEFAULT_CORNER);
        } finally {
            ta.recycle();
        }
    }

    private Drawable getCornerDrawable() {

        if (tvAllCorner >= 0) {
            tvTopLeftCorner = tvAllCorner;
            tvTopRightCorner = tvAllCorner;
            tvBottomRightCorner = tvAllCorner;
            tvBottomLeftCorner = tvAllCorner;
        }

        float[] outerR = new float[8];
        outerR[0] = tvTopLeftCorner;
        outerR[1] = tvTopLeftCorner;
        outerR[2] = tvTopRightCorner;
        outerR[3] = tvTopRightCorner;
        outerR[4] = tvBottomRightCorner;
        outerR[5] = tvBottomRightCorner;
        outerR[6] = tvBottomLeftCorner;
        outerR[7] = tvBottomLeftCorner;

        ShapeDrawable drawable = new ShapeDrawable();
        drawable.setShape(new RoundRectShape(outerR, null, null));
        drawable.getPaint().setColor(tvBgColor);
        return drawable;
    }

    public void setCorner(int all) {
        tvAllCorner = all;
        setViewBackground();
    }

    public void setCorner(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        tvTopLeftCorner = topLeft;
        tvTopRightCorner = topRight;
        tvBottomRightCorner = bottomRight;
        tvBottomLeftCorner = bottomLeft;
        setViewBackground();
    }

    public void setBgColor(int color) {
        tvBgColor = color;
        setViewBackground();
    }

    private void setViewBackground() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(getCornerDrawable());
        } else {
            setBackgroundDrawable(getCornerDrawable());
        }
    }
}
