package com.rubyhuntersky.columnui;

import com.rubyhuntersky.columnui.bars.Bar;
import com.rubyhuntersky.columnui.bars.FrameShiftBar;
import com.rubyhuntersky.columnui.basics.Sizelet;
import com.rubyhuntersky.columnui.conditions.Human;
import com.rubyhuntersky.columnui.presentations.ResizePresentation;
import com.rubyhuntersky.columnui.tiles.TileUi;

/**
 * @author wehjin
 * @since 1/27/16.
 */

abstract public class BarUi extends BaseUi<Bar> {

    @Override
    abstract public Presentation present(Human human, Bar bar, Observer observer);

    public BarUi expandStart(final TileUi startUi) {
        return expandStart(startUi.toBar());
    }

    public BarUi expandStart(final BarUi startUi) {
        final BarUi ui = this;
        return create(new OnPresent<Bar>() {
            @Override
            public void onPresent(Presenter<Bar> presenter) {
                final Bar bar = presenter.getDisplay();
                final FrameShiftBar frameShiftBar = bar.withFrameShift();
                final Human human = presenter.getHuman();
                final Presentation endPresentation = ui.present(human, frameShiftBar, presenter);
                final Bar startBar = bar.withRelatedWidth(endPresentation.getWidth());
                final Presentation startPresentation = startUi.present(human, startBar, presenter);
                final float startWidth = startPresentation.getWidth();
                frameShiftBar.setShift(startWidth, 0);
                final float combinedWidth = startWidth + endPresentation.getWidth();
                presenter.addPresentation(startPresentation);
                presenter.addPresentation(new ResizePresentation(combinedWidth, bar.fixedHeight, endPresentation));
            }
        });
    }

    public BarUi padStart(final Sizelet padlet) {
        final BarUi ui = this;
        return create(new OnPresent<Bar>() {
            @Override
            public void onPresent(Presenter<Bar> presenter) {
                final Bar bar = presenter.getDisplay();
                final FrameShiftBar frameShiftBar = bar.withFrameShift();
                final Human human = presenter.getHuman();
                final Presentation presentation = ui.present(human, frameShiftBar, presenter);
                final float padding = padlet.toFloat(human, presentation.getWidth());
                frameShiftBar.setShift(padding, 0);
                presenter.addPresentation(
                      new ResizePresentation(padding + presentation.getWidth(), presentation.getHeight(),
                            presentation));
            }
        });
    }

    public static BarUi create(final OnPresent<Bar> onPresent) {
        return new BarUi() {
            @Override
            public Presentation present(Human human, final Bar bar, Observer observer) {
                final BasePresenter<Bar> presenter = new BasePresenter<Bar>(human, bar, observer) {
                    @Override
                    public float getWidth() {
                        float union = 0;
                        for (Presentation presentation : presentations) {
                            union = Math.max(union, presentation.getWidth());
                        }
                        return union;
                    }

                    @Override
                    public float getHeight() {
                        return display.fixedHeight;
                    }
                };
                onPresent.onPresent(presenter);
                return presenter;
            }
        };
    }
}

