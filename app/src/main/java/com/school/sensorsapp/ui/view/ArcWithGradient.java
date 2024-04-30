package com.school.sensorsapp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.school.sensorsapp.R;

public class ArcWithGradient extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Typeface typeface;
    private int maxValue = 500;
    private int value = 36;
    private String text = "AQI";
    public String textValue = "34";
    private int color = 0xff98ffb0;
    private int textColor = 0x000000;
    private Paint progressFillPaint;
    private RectF baseArcRect;
    int[] colors = {
            Color.GREEN,
            Color.YELLOW,
            Color.RED,
            Color.MAGENTA
    };
    float[] positions = {0.0f, 0.33f, 0.66f, 1.0f};
    private int thickness;

    public ArcWithGradient(Context context) {
        super(context);
        init(context, null);
    }

    public ArcWithGradient(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ArcWithGradient(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AQIView);
            CharSequence chars = a.getText(R.styleable.AQIView_android_text);
            text = chars != null ? chars.toString() : "AQI";

            maxValue = a.getInt(R.styleable.AQIView_maxValue, 500);
            value = a.getInt(R.styleable.AQIView_value, 25);
            color = a.getColor(R.styleable.AQIView_color, 0xff98ffb0);
            textColor = a.getColor(R.styleable.AQIView_textColor, Color.BLACK);
            chars = a.getText(R.styleable.AQIView_fontName);
            if (chars != null) {
                typeface = Typeface.createFromAsset(context.getAssets(), chars.toString());
                paint.setTypeface(typeface);
            }

            a.recycle();
        }

        thickness = dpToPx(getContext(), 25);

        //We do not want a colour for this because we will set a gradient
        progressFillPaint = makeStrokePaint(dpToPx(getContext(), 25), -1);
        baseArcRect = new RectF(0, 0, 0, 0);
        setProgressColourAsGradient(false);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        //Ensures arc is within the rectangle
        float radius = Math.min(width, height) / 2;

        //I do radius - thickness so that the arc is within the rectangle
        float baseArcLeft = ((width / 2) - (radius - thickness));
        float baseArcTop = ((height / 2) - (radius - thickness));
        float baseArcRight = ((width / 2) + (radius - thickness));
        float baseArcBottom = ((height / 2) + (radius - thickness));

        baseArcRect.set(baseArcLeft, baseArcTop, baseArcRight, baseArcBottom);
        //Recalculate the gradient
        setProgressColourAsGradient(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(baseArcRect, 135, 270, false, progressFillPaint);

        float width = getWidth();
        float height = getHeight();

        float aspect = width / height;
        final float normalAspect = 2f / 1f;
        if (aspect > normalAspect) {
            width = normalAspect * height;
        } if (aspect < normalAspect) {
            height = width / normalAspect;
        }

        canvas.save();

        canvas.translate(width / 2, height);
        canvas.scale(.5f * width, -1f * height);

        paint.setColor(0x40ffffff);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);

        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0.005f);

        canvas.restore();

        canvas.save();

        canvas.translate(width / 2, 0);

        paint.setTextSize(height / 10);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawText(text, -paint.measureText(text) /2 , height - height * 0.15f, paint);
        canvas.drawText(textValue, -paint.measureText(text) /2 , height - height * 0.3f, paint);

        canvas.restore();

        canvas.save();

        canvas.translate(width / 2, height);
        canvas.scale(.5f * width, -1f * height);
        canvas.rotate(90 - (float) 180 * (value / (float) maxValue));

        paint.setColor(0xffff8899);
        paint.setStrokeWidth(0.02f);
        canvas.drawLine(0.01f, 0, 0, 1f, paint);
        canvas.drawLine(-0.01f, 0, 0, 1f, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff88ff99);
        canvas.drawCircle(0f, 0f, .05f, paint);

        canvas.restore();
    }

    public void setProgressColourAsGradient(boolean invalidateNow) {
        SweepGradient sweepGradient = new SweepGradient(baseArcRect.centerX(), baseArcRect.centerY(), colors, positions);
        //Make the gradient start from 90 degrees
        Matrix matrix = new Matrix();
        matrix.setRotate(90,baseArcRect.centerX(), baseArcRect.centerY());
        sweepGradient.setLocalMatrix(matrix);

        progressFillPaint.setShader(sweepGradient);
        if (invalidateNow) {
            invalidate();
        }
    }

    public static int dpToPx(Context ctx, float dp) {
        return Math.round(dp * ctx.getResources().getDisplayMetrics().density);
    }

    public static Paint makeStrokePaint(int width, int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(width);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        float aspect = width / (float)height;
        final float normalAspect = 2f / 1f;
        if (aspect > normalAspect) {
            if (widthMode != MeasureSpec.EXACTLY) {
                width = Math.round(normalAspect * height);
            }
        } if (aspect < normalAspect) {
            if (heightMode != MeasureSpec.EXACTLY) {
                height = Math.round(width / normalAspect);
            }
        }
        setMeasuredDimension(width, height);
    }
}