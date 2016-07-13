package io.ashdavies.cumin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseAdapter<VH extends BaseAdapter.ViewHolder<T>, T> extends RecyclerView.Adapter<VH> implements ListAdapter<T> {
    private final LayoutInflater inflater;
    private final List<T> items;

    public BaseAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public BaseAdapter(Context context, List<T> items) {
        this(LayoutInflater.from(context), items);
    }

    public BaseAdapter(LayoutInflater inflater, List<T> items) {
        this.inflater = inflater;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public List<T> getItems() {
        return items;
    }

    @Override
    public void addItem(int position, T item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public void addItems(List<T> collection) {
        items.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(T item) {
        removeItem(items.indexOf(item));
    }

    @Override
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    protected LayoutInflater getInflater() {
        return inflater;
    }

    public static abstract class ContextViewHolder<T> extends BaseAdapter.ViewHolder<T> {
        private WeakReference<Context> context;

        public ContextViewHolder(Context context, View view) {
            super(view);
            bind(context);
        }

        private void bind(Context context) {
            this.context = new WeakReference<>(context);
        }

        public Context getContext() {
            return context.get();
        }
    }

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Unbinder unbinder;

        public ViewHolder(View view) {
            super(view);
            onAttach(view);
        }

        private void onAttach(View view) {
            unbinder = ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        protected void onDetach() {
            unbinder.unbind();
        }

        public abstract void bind(T item);

        @Override
        public void onClick(View view) {
            // Default implementation
        }
    }
}
