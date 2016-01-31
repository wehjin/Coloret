package com.rubyhuntersky.columnui.presentations;

import android.util.Pair;

/**
 * @author wehjin
 * @since 1/30/16.
 */
public class PairPresentation1<C> implements Presentation1<C> {
    private final Pair<Presentation1<C>, Presentation1<C>> pair;

    public PairPresentation1(Pair<Presentation1<C>, Presentation1<C>> pair) {
        this.pair = pair;
    }

    @Override
    public float getWidth() {
        return Math.max(pair.first.getWidth(), pair.second.getWidth());
    }

    @Override
    public float getHeight() {
        return Math.max(pair.first.getHeight(), pair.second.getHeight());
    }

    @Override
    public boolean isCancelled() {
        return pair.first.isCancelled();
    }

    @Override
    public void cancel() {
        if (isCancelled()) {
            return;
        }
        pair.first.cancel();
        pair.second.cancel();
    }

    @Override
    public void rebind(C condition) {
        pair.first.rebind(condition);
        pair.second.rebind(condition);
    }
}
