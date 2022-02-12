package com.elegantmedia.test.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elegantmedia.test.R;
import com.elegantmedia.test.databinding.ItemHotelsBinding;
import com.elegantmedia.test.model.Datum;
import com.elegantmedia.test.utils.Helper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HotelsAdaptor<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HOTELS = R.layout.item_hotels;


    private Context context;
    private CustomerAdaptorCallback callback;
    private final List<T> data;
    private int viewType = 0;

    public HotelsAdaptor(Context context, List<T> data, CustomerAdaptorCallback callback) {
        if(data == null)
            data = new ArrayList<>();

        viewType = VIEW_TYPE_HOTELS;
        this.context = context;
        this.data = data;
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case VIEW_TYPE_HOTELS:
                view = LayoutInflater.from(parent.getContext()).inflate(VIEW_TYPE_HOTELS, parent, false);
                return new HotelsVH(view);
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.error_view_type + " " + viewType));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        T t = data.get(position);

        switch (viewType) {
            case VIEW_TYPE_HOTELS:
                HotelsVH vh = (HotelsVH) holder;
                vh.onBindComments((Datum) t, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void add(T t) {
        data.add(t);
        notifyDataSetChanged();
    }

    public void remove(T t) {
        data.remove(t);
        notifyDataSetChanged();
    }

    public void update(int position, T t) {
        data.add(position, t);
        notifyDataSetChanged();
    }

    public void setItems(List<T> t) {
        if (t == null) return;

        this.data.clear();
        this.data.addAll(t);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }


    public class HotelsVH extends RecyclerView.ViewHolder {

        int position;
        ItemHotelsBinding binding;

        public HotelsVH(@NotNull View itemView) {
            super(itemView);
            binding = ItemHotelsBinding.bind(itemView);
        }

        public void onBindComments(Datum item, int position) {
            this.position = position;

            binding.tvTitle.setText(item.getTitle());
            binding.tvAddress.setText(item.getAddress());

            Helper.setCircleImage(item.getImage().getSmall(), binding.ivProfile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(callback != null)
                        callback.onView(getItemViewType(), position);
                }
            });
        }
    }

    public interface CustomerAdaptorCallback {

        void onView(int viewType, int position);
        void onEdit(int viewType, int position);
        void onDelete(int viewType, int position);

    }
}
