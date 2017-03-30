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
    private float tvAllCorner;
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
            tvAllCorner = ta.getDimension(R.styleable.TextViewCorner_allCorner, Float.MIN_VALUE);
            tvTopLeftCorner = ta.getDimension(R.styleable.TextViewCorner_topLeftCorner, DEFAULT_CORNER);
            tvTopRightCorner = ta.getDimension(R.styleable.TextViewCorner_topRightCorner, DEFAULT_CORNER);
            tvBottomRightCorner = ta.getDimension(R.styleable.TextViewCorner_bottomRightCorner, DEFAULT_CORNER);
            tvBottomLeftCorner = ta.getDimension(R.styleable.TextViewCorner_bottomLeftCorner, DEFAULT_CORNER);
        } finally {
            ta.recycle();
        }
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

        Drawable drawable;

        if (tvAllCorner != Float.MIN_VALUE) {
            drawable = DrawableHelper.getCornerDrawable(
                    tvAllCorner,
                    tvAllCorner,
                    tvAllCorner,
                    tvAllCorner,
                    tvBgColor);
        } else {
            drawable = DrawableHelper.getCornerDrawable(
                    tvTopLeftCorner,
                    tvTopRightCorner,
                    tvBottomLeftCorner,
                    tvBottomRightCorner,
                    tvBgColor);
        }

        DrawableHelper.setRoundBackground(this, drawable);
    }
}
