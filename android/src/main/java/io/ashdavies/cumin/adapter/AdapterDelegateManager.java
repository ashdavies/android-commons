package io.ashdavies.cumin.adapter;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

public final class AdapterDelegateManager<VH, T> {
    private final SparseArrayCompat<AdapterDelegate<VH, T>> delegates = new SparseArrayCompat<>();

    public AdapterDelegateManager<VH, T> addDelegate(@NonNull AdapterDelegate<VH, T> delegate) {
        return addDelegate(delegate, false);
    }

    public AdapterDelegateManager<VH, T> addDelegate(@NonNull AdapterDelegate<VH, T> delegate, boolean replace) {
        int viewType = delegate.getItemViewType();

        if (!replace && delegates.get(viewType) != null) {
            throw new IllegalArgumentException("An AdapterDelegate is already registered for viewType + " + viewType);
        }

        delegates.put(viewType, delegate);
        return this;
    }

    public boolean hasDelegate(@NonNull T items, int position) {
        int count = delegates.size();

        for (int i = 0; i < count; i++) {
            AdapterDelegate<VH, T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return true;
            }
        }

        return false;
    }

    public AdapterDelegateManager<VH, T> removeDelegate(@NonNull AdapterDelegate<VH, T> delegate) {
        int viewType = delegate.getItemViewType();

        AdapterDelegate<VH, T> queried = delegates.get(viewType);
        if (queried != null && queried == delegate) {
            removeDelegate(viewType);
        }

        return this;
    }

    public AdapterDelegateManager<VH, T> removeDelegate(int viewType) {
        delegates.remove(viewType);
        return this;
    }

    public int getItemViewType(@NonNull T items, int position) {
        int count = delegates.size();

        for (int i = 0; i < count; i++) {
            AdapterDelegate<VH, T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return delegate.getItemViewType();
            }
        }

        throw new IllegalArgumentException("No AdapterDelegate for position " + position);
    }

    @NonNull
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<VH, T> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate for viewType " + viewType);
        }

        return delegate.onCreateViewHolder(parent);
    }

    public void onBindViewHolder(@NonNull T items, int position, @NonNull VH viewHolder) {
        int viewType = getItemViewType(items, position);

        AdapterDelegate<VH, T> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate for viewType " + viewType);
        }

        delegate.onBindViewHolder(items, position, viewHolder);
    }
}
