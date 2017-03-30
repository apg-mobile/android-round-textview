package com.apg.mobile.roundtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by X-tivity on 3/30/2017 AD.
 */

public class BadgeView extends RoundLayout {

    private TextView tvBadgeMessage;
    private RoundTextView rtvBadgeAmount;

    private String mainText;
    private int mainTextColor;
    private float mainTextSize;
    private int mainBgColor;
    private String subText;
    private int subTextColor;
    private float subTextSize;
    private int subBgColor;
    private int subTopLeftCorner;
    private int subTopRightCorner;
    private int subBottomRightCorner;
    private int subBottomLeftCorner;
    private int subAllCorner;
    private int subMarginLeft;
    private int subMarginTop;
    private int subMarginRight;
    private int subMarginBottom;
    private int subAllMargin;
    private int subPaddingLeft;
    private int subPaddingTop;
    private int subPaddingRight;
    private int subPaddingBottom;
    private int subAllPadding;
    private int mainTopLeftCorner;
    private int mainTopRightCorner;
    private int mainBottomRightCorner;
    private int mainBottomLeftCorner;
    private int mainAllCorner;


    public BadgeView(Context context) {
        super(context);
        initView();
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        extractAttribute(context, attrs);
        initView();
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractAttribute(context, attrs);
        initView();
    }

    private void extractAttribute(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeView, 0, 0);
        try {
            mainText = ta.getString(R.styleable.BadgeView_mainText);
            mainTextColor = ta.getColor(R.styleable.BadgeView_mainTextColor, Color.WHITE);
            mainTextSize = ta.getDimension(R.styleable.BadgeView_mainTextSize, 24f);
            mainBgColor = ta.getColor(R.styleable.BadgeView_mainBgColor, Color.parseColor("#FFAE00"));
            mainTopLeftCorner = (int) ta.getDimension(R.styleable.BadgeView_mainTopLeftCorner, 0);
            mainTopRightCorner = (int) ta.getDimension(R.styleable.BadgeView_mainTopRightCorner, 0);
            mainBottomRightCorner = (int) ta.getDimension(R.styleable.BadgeView_mainBottomRightCorner, 0);
            mainBottomLeftCorner = (int) ta.getDimension(R.styleable.BadgeView_mainBottomLeftCorner, 0);
            mainAllCorner = (int) ta.getDimension(R.styleable.BadgeView_mainAllCorner, Integer.MIN_VALUE);
            subText = ta.getString(R.styleable.BadgeView_subText);
            subTextColor = ta.getColor(R.styleable.BadgeView_subTextColor, Color.WHITE);
            subTextSize = ta.getDimension(R.styleable.BadgeView_subTextSize, 24f);
            subBgColor = ta.getColor(R.styleable.BadgeView_subBgColor, Color.parseColor("#96000000"));
            subTopLeftCorner = (int) ta.getDimension(R.styleable.BadgeView_subTopLeftCorner, 0);
            subTopRightCorner = (int) ta.getDimension(R.styleable.BadgeView_subTopRightCorner, 0);
            subBottomRightCorner = (int) ta.getDimension(R.styleable.BadgeView_subBottomRightCorner, 0);
            subBottomLeftCorner = (int) ta.getDimension(R.styleable.BadgeView_subBottomLeftCorner, 0);
            subAllCorner = (int) ta.getDimension(R.styleable.BadgeView_subAllCorner, Integer.MIN_VALUE);
            subMarginLeft = (int) ta.getDimension(R.styleable.BadgeView_subMarginLeft, 16f);
            subMarginTop = (int) ta.getDimension(R.styleable.BadgeView_subMarginTop, 0f);
            subMarginRight = (int) ta.getDimension(R.styleable.BadgeView_subMarginRight, 0f);
            subMarginBottom = (int) ta.getDimension(R.styleable.BadgeView_subMarginBottom, 0f);
            subAllMargin = (int) ta.getDimension(R.styleable.BadgeView_subMargin, Integer.MIN_VALUE);
            subPaddingLeft = (int) ta.getDimension(R.styleable.BadgeView_subPaddingLeft, 16f);
            subPaddingTop = (int) ta.getDimension(R.styleable.BadgeView_subPaddingTop, 5f);
            subPaddingRight = (int) ta.getDimension(R.styleable.BadgeView_subPaddingRight, 16f);
            subPaddingBottom = (int) ta.getDimension(R.styleable.BadgeView_subPaddingBottom, 5f);
            subAllPadding = (int) ta.getDimension(R.styleable.BadgeView_subPadding, Integer.MIN_VALUE);
        } finally {
            ta.recycle();
        }
    }

    private void initView() {
        inflate(getContext(), R.layout.view_badge, this);

        tvBadgeMessage = (TextView) findViewById(R.id.tvBadgeMessage);
        rtvBadgeAmount = (RoundTextView) findViewById(R.id.rtvBadgeSub);

        // default padding
        if (subText == null || subText.isEmpty()) {
            int dp6 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
            int dp16 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
            setPadding(dp16, dp6, dp16, dp6);
        } else {
            int dp4 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            int dp16 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
            setPadding(dp16, dp4, dp4, dp4);
        }

        // Main
        setBadgeMainText(mainText);
        setBadgeMainTextColor(mainTextColor);
        setBadgeMainTextSize(mainTextSize);
        setBadgeMainBackgroundColor(mainBgColor);

        if (mainAllCorner != Integer.MIN_VALUE) {
            setBadgeMainCorner(mainAllCorner, mainAllCorner, mainAllCorner, mainAllCorner);
        } else {
            setBadgeMainCorner(mainTopLeftCorner, mainTopRightCorner, mainBottomRightCorner, mainBottomLeftCorner);
        }

        // Sub
        setBadgeSubText(subText);
        setBadgeSubTextColor(subTextColor);
        setBadgeSubTextSize(subTextSize);
        setBadgeSubBackgroundColor(subBgColor);

        if (subAllCorner != Integer.MIN_VALUE) {
            setBadgeSubCorner(subAllCorner, subAllCorner, subAllCorner, subAllCorner);
        } else {
            setBadgeSubCorner(subTopLeftCorner, subTopRightCorner, subBottomRightCorner, subBottomLeftCorner);
        }

        if (subAllMargin != Integer.MIN_VALUE) {
            setBadgeSubMargin(subAllMargin, subAllMargin, subAllMargin, subAllMargin);
        } else {
            setBadgeSubMargin(subMarginLeft, subMarginTop, subMarginRight, subMarginBottom);
        }

        if (subAllPadding != Integer.MIN_VALUE) {
            setBadgeSubPadding(subAllPadding, subAllPadding, subAllPadding, subAllPadding);
        } else {
            setBadgeSubPadding(subPaddingLeft, subPaddingTop, subPaddingRight, subPaddingBottom);
        }
    }

    private void setBadgeMainCorner(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        setCorner(topLeft, topRight, bottomRight, bottomLeft);
    }

    private void setBadgeMainBackgroundColor(int mainBgColor) {
        setBgColor(mainBgColor);
    }

    public void setBadgeMainText(String text) {
        tvBadgeMessage.setText(text);
    }

    private void setBadgeMainTextSize(float mainTextSize) {
        tvBadgeMessage.setTextSize(mainTextSize);
    }

    public void setBadgeMainTextColor(@ColorInt int color) {
        tvBadgeMessage.setTextColor(color);
    }

    public void setBadgeSubText(String text) {

        if (text == null || text.isEmpty()) {
            rtvBadgeAmount.setVisibility(GONE);
        } else {
            rtvBadgeAmount.setVisibility(VISIBLE);
            rtvBadgeAmount.setText(text);
        }
    }

    public void setBadgeSubTextColor(@ColorInt int color) {
        rtvBadgeAmount.setTextColor(color);
    }

    private void setBadgeSubTextSize(float subTextSize) {
        rtvBadgeAmount.setTextSize(subTextSize);
    }

    public void setBadgeSubBackgroundColor(@ColorInt int color) {
        rtvBadgeAmount.setBgColor(color);
    }

    /**
     * @hide
     */
    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public void setBadgeSubVisibility(@Visibility int visibility) {
        rtvBadgeAmount.setVisibility(visibility);
    }

    public void setBadgeSubCorner(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        rtvBadgeAmount.setCorner(topLeft, topRight, bottomRight, bottomLeft);
    }

    public void setBadgeSubMargin(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                rtvBadgeAmount.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        requestLayout();
    }

    public void setBadgeSubPadding(int left, int top, int right, int bottom) {
        rtvBadgeAmount.setPadding(left, top, right, bottom);
    }
}
