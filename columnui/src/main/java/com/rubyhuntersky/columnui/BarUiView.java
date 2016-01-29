package com.rubyhuntersky.columnui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.rubyhuntersky.columnui.bars.Bar;
import com.rubyhuntersky.columnui.basics.Frame;
import com.rubyhuntersky.columnui.basics.TextSize;
import com.rubyhuntersky.columnui.basics.TextStyle;
import com.rubyhuntersky.columnui.displays.DelayDisplay;

/**
 * @author wehjin
 * @since 1/23/16.
 */

public class BarUiView extends UiView<Bar> {

    public static final String TAG = BarUiView.class.getSimpleName();
    private Bar bar;

    public BarUiView(Context context) {
        super(context);
        init();
    }

    public BarUiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarUiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bar = new Bar(0, 0, 0) {

            @NonNull
            @Override
            public Patch addPatch(Frame frame, Shape shape) {
                return BarUiView.this.addPatch(frame, shape);
            }

            @NonNull
            @Override
            public TextSize measureText(String text, TextStyle textStyle) {
                return BarUiView.this.measureText(text, textStyle);
            }
        };
    }

    @NonNull
    @Override
    public Bar asType() {
        return bar;
    }

    @NonNull
    @Override
    public Bar withFixedDimension(float fixedDimension) {
        return bar.withFixedDimension(fixedDimension);
    }

    @NonNull
    @Override
    public Bar withElevation(int elevation) {
        return bar.withElevation(elevation);
    }

    @NonNull
    @Override
    public DelayDisplay<Bar> withDelay() {
        return bar.withDelay();
    }

    @Override
    protected int getFixedDimensionMeasureSpec(int widthMeasureSpec, int heightMeasureSpec) {
        return heightMeasureSpec;
    }

    @Override
    protected void setMeasuredDimensionFromDisplayDimensions(float fixed, float variable) {
        setMeasuredDimension((int) variable, (int) fixed);
    }

    @Override
    protected float getVariableDimension(Presentation presentation) {
        return presentation.getWidth();
    }

    @Override
    protected float getFixedDimension(int width, int height) {
        return height;
    }
}