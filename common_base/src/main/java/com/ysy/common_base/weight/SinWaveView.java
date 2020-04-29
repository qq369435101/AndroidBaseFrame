package com.ysy.common_base.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ysy.common_base.R;

/**
 * Created by YuShengyang on 2020/1/20
 * Email       ：18210490506@163.com
 * Description ：
 */
public class SinWaveView extends View {
    private PaintFlagsDrawFilter mDrawFilter;
    private Paint mWavePaint;
    private float mOffset1 = 0.0f;
    private float mOffset2 = 0.0f;
    //第一波的速度
    private float mSpeed1 = 0.05f;
    //第二波的速度
    private float mSpeed2 = 0.07f;
    private boolean isWaveUpward = true;

    public SinWaveView(Context context) {
        super(context);
        init();
    }

    public SinWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SinWaveView);
        isWaveUpward = array.getBoolean(R.styleable.SinWaveView_wave_upward, true);
        mSpeed1 = array.getFloat(R.styleable.SinWaveView_wave_speed, 0.05f);
        mSpeed2 = mSpeed1 * array.getFloat(R.styleable.SinWaveView_wave_speed_radio, 1.05f);
        init();
    }


    public void init() {
        // 初始绘制波纹的画笔
        mWavePaint = new Paint();
        // 去除画笔锯齿
        mWavePaint.setAntiAlias(true);
        // 设置风格为实线
        mWavePaint.setStyle(Paint.Style.FILL);
        // 设置画笔颜色
        mWavePaint.setColor(getContext().getResources().getColor(R.color.app_theme));
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 从canvas层面去除绘制时锯齿
        canvas.setDrawFilter(mDrawFilter);
        for (int i = 0; i < getWidth(); i++) {
            int h = isWaveUpward ? getHeight() / 4 : 3 * getHeight() / 4;
            // y = A * sin( wx + b) + h ; A： 浪高； w：周期；b：初相；
            float endY = (float) (getHeight() / 8 * Math.sin(2 * Math.PI / getWidth() * i + mOffset1) + h);
            //画第一条波浪
            canvas.drawLine(i, isWaveUpward ? getHeight() : endY, i, isWaveUpward ? endY : 0, mWavePaint);
            //画第二条波浪
            float endY2 = (float) (getHeight() / 8 * Math.sin(2 * Math.PI / getWidth() * i + mOffset2) + h);
            canvas.drawLine(i, isWaveUpward ? getHeight() : endY2, i, isWaveUpward ? endY2 : 0, mWavePaint);
        }

        if (mOffset1 > Float.MAX_VALUE - 1) {//防止数值超过浮点型的最大值
            mOffset1 = 0;
        }
        mOffset1 += mSpeed1;

        if (mOffset2 > Float.MAX_VALUE - 1) {//防止数值超过浮点型的最大值
            mOffset2 = 0;
        }
        mOffset2 += mSpeed2;
        //刷新
        postInvalidate();
    }
}