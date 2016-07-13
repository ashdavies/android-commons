package io.ashdavies.cumin.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DelegateSafeAdapter<VH extends BaseAdapter.ViewHolder<T>, T> extends DelegateAdapter<VH, T> {
    public DelegateSafeAdapter(Context context, List<T> items) {
        super(context, items);
    }

    @Override
    public void addItem(int position, T item) {
        if (hasDelegate(Collections.singletonList(item), 0)) {
            super.addItem(position, item);
        }
    }

    @Override
    public void addItems(List<T> collection) {
        super.addItems(filter(collection));
    }

    private List<T> filter(List<T> items) {
        List<T> filtered = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            if (hasDelegate(items, i)) {
                filtered.add(items.get(i));
            }
        }

        return filtered;
    }
}
