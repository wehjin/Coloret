package com.rubyhuntersky.columnui;

import android.support.annotation.NonNull;

import com.rubyhuntersky.columnui.bars.Bar;
import com.rubyhuntersky.columnui.bars.FullBar;
import com.rubyhuntersky.columnui.basics.Frame;
import com.rubyhuntersky.columnui.basics.ShapeSize;
import com.rubyhuntersky.columnui.basics.TextSize;
import com.rubyhuntersky.columnui.basics.TextStyle;
import com.rubyhuntersky.columnui.patches.Patch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class BarUnitTest {

    private Bar bar;

    @Before
    public void setUp() throws Exception {
        bar = new FullBar(10, 20, 1) {

            @NonNull
            @Override
            public Patch addPatch(Frame frame, Shape shape) {
                return Patch.EMPTY;
            }

            @NonNull
            @Override
            public TextSize measureText(String text, TextStyle textStyle) {
                return TextSize.ZERO;
            }

            @NonNull
            @Override
            public ShapeSize measureShape(Shape shape) {
                return ShapeSize.ZERO;
            }
        };
    }

    @Test
    public void withElevation_changesElevation() throws Exception {
        final Bar bar2 = bar.withElevation(2);
        assertEquals(2, bar2.elevation);
    }

    @Test
    public void withHeight_changesHeight() throws Exception {
        final Bar bar2 = this.bar.withFixedHeight(15);
        assertEquals(15, bar2.fixedHeight, .001);
    }

    @Test
    public void withRelatedWidth_changesRelatedWidth() throws Exception {
        final Bar bar = this.bar.withRelatedWidth(105);
        assertEquals(105, bar.relatedWidth, .001);
    }
}