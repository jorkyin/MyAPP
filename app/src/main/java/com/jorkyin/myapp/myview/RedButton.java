package com.jorkyin.myapp.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jorkyin.myapp.R;

/**
 * Function:
 * 圆形背景红色按钮
 * 中间有一个白色数字
 * 数字起始为20
 * 每点击一次减少1
 * Created by YinJian on 2016/3/16.
 */
public class RedButton extends View implements View.OnClickListener {
    private static final String TAG = RedButton.class.getSimpleName();
    private Paint mPaint;
    private Rect mRect;
    private int mUnmber = 20;
    private String mText;
    private int mTextWidth;
    private int mTextHeight;
    private int mBackgrounColor;
    private int mTextSize;

    public RedButton(Context context) {
        this(context, null);
    }


    public RedButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * inti the view
     */
    private void init(Context context, AttributeSet attrs) {
        //画布
        mPaint = new Paint();
        //四边形
        mRect = new Rect();

        //注册VIew点击事件
        this.setOnClickListener(this);
        //
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RedButton);

        //
        mBackgrounColor = typedArray.getColor(R.styleable.RedButton_backgroundColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.RedButton_textSize, 18);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画布为红色
        //mPaint.setColor(Color.RED);
        mPaint.setColor(mBackgrounColor);
        //圆形按钮
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);

        //中间有一个白色的数字
        //白色的数字
        mPaint.setColor(Color.WHITE);
        //数字大小
        // mPaint.setTextSize(200);
        mPaint.setTextSize(mTextSize);

        //获得文字范围
        mText = String.valueOf(mUnmber);
        mPaint.getTextBounds(mText, 0, mText.length(), mRect);
        mTextWidth = mRect.width();
        mTextHeight = mRect.height();

        canvas.drawText(mText, getWidth() / 2 - mTextWidth / 2, getHeight() / 2 + mTextHeight / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        if (mUnmber > 0) {
            mUnmber--;

        } else {
            mUnmber = 20;
        }
        Log.i(TAG, "mUnmber:" + mUnmber);
        invalidate();
    }
}
