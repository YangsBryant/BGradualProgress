package com.bryant.progresslibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 渐变色环形进度条
 */
public class BGradualProgress extends View {

    private int roundColor; // 圆环的颜色
    private float roundWidth; // 圆环的宽度
    private float progressWidth; // 圆环进度的宽度
    private int startAngle; // 进度条起始角度

    private String text; // 文字内容
    private int textColor; // 文字内容的颜色
    private float textSize; // 文字内容的字体大小
    private boolean textShow; // 是否显示中间的文字

    private int startColor; // 渐变色起始色
    private int midColor; // 渐变色中间色
    private int endColor; // 渐变色起始色


    private Paint paint; // 画笔对象的引用
    private int max; // 最大进度
    private int progress; // 当前进度
    private boolean isfillet;

    public BGradualProgress(Context context) {
        this(context, null);
    }

    public BGradualProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BGradualProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint();

        // 读取自定义属性的值
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.BGradualProgress);

        // 获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.BGradualProgress_grp_roundColor, Color.RED);
        roundWidth = mTypedArray.getDimension(R.styleable.BGradualProgress_grp_roundWidth, 5);
        progressWidth = mTypedArray.getDimension(R.styleable.BGradualProgress_grp_progressWidth, roundWidth);
        isfillet= mTypedArray.getBoolean(R.styleable.BGradualProgress_grp_progressFillet, true);
        text = mTypedArray.getString(R.styleable.BGradualProgress_grp_text);
        textColor = mTypedArray.getColor(R.styleable.BGradualProgress_grp_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.BGradualProgress_grp_textSize, 11);
        max = mTypedArray.getInteger(R.styleable.BGradualProgress_grp_max, 100);
        startAngle = mTypedArray.getInt(R.styleable.BGradualProgress_grp_startAngle, 90);
        textShow = mTypedArray.getBoolean(R.styleable.BGradualProgress_grp_textShow, true);
        startColor = mTypedArray.getColor(R.styleable.BGradualProgress_grp_startColor, Color.GREEN);
        midColor = mTypedArray.getColor(R.styleable.BGradualProgress_grp_midColor, Color.GREEN);
        endColor = mTypedArray.getColor(R.styleable.BGradualProgress_grp_endColor, Color.GREEN);
        mTypedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2; // 获取圆心的x坐标
        int radius = (int) (centerX - roundWidth / 2); // 圆环的半径

        // step1 画最外层的大圆环
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setAntiAlias(true); // 消除锯齿
        // 设置画笔样式
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerX, radius, paint); // 画出圆环

        // step2 画圆弧-画圆环的进度
        // 锁画布(为了保存之前的画布状态)
        canvas.save();
        canvas.rotate(startAngle, centerX, centerX);
        paint.setStrokeWidth(progressWidth); // 设置画笔的宽度使用进度条的宽度
        if(isfillet) {
            paint.setStrokeCap(Paint.Cap.ROUND);
        }
        @SuppressLint("DrawAllocation")
        RectF oval = new RectF(centerX - radius, centerX - radius, centerX + radius, centerX + radius); // 用于定义的圆弧的形状和大小的界限

        int sweepAngle = 360 * progress / max; // 计算进度值在圆环所占的角度
        // 根据进度画圆弧
        paint.setShader(new SweepGradient(centerX, centerX, new int[]{startColor, midColor, endColor},null));
        canvas.drawArc(oval, 0, sweepAngle, false, paint);
        paint.setShader(null);
        canvas.rotate(-startAngle, centerX, centerX);
        canvas.restore();

        // step3 画文字
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT);
        int percent = (int) (((float) progress / (float) max) * 100);
        if (textShow && text != null && text.length() > 0 && percent >= 0) {
            float textWidth = paint.measureText(text);
            canvas.drawText(text, centerX - textWidth / 2, centerX+14, paint);
        }
    }

    /**
     * 设置进度的最大值
     */
    public synchronized void setMax(int max) {
        if (max > 0) {
            this.max = max;
        }
    }

    /**
     * 获取进度
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度
     */
    public synchronized void setProgress(int progress) {
        if (progress > 0 &&progress > max) {
            progress = max;
        }
        this.progress = progress;
        // 刷新界面调用postInvalidate()能在非UI线程刷新
        postInvalidate();
    }

    /**
     * 设置文本
     */
    public synchronized void setText(String text) {
        this.text=text;
    }


}
