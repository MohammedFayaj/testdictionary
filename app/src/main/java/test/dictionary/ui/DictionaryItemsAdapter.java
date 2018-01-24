package test.dictionary.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.dictionary.R;
import test.dictionary.model.DictionaryResultItems;


public class DictionaryItemsAdapter extends RecyclerView.Adapter<DictionaryItemsAdapter.MyViewHolder> {

    private Context mContext;
    private List<DictionaryResultItems> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, thumbsUp,thumbsDown;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbsUp =  view.findViewById(R.id.tv_thumbsup);
            thumbsDown =  view.findViewById(R.id.tv_thumbsdown);

        }
    }


    public DictionaryItemsAdapter(Context mContext, List<DictionaryResultItems> itemsList) {
        this.mContext = mContext;
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        DictionaryResultItems item = itemsList.get(position);
        holder.title.setText(item.getDefinition());
        holder.thumbsUp.setText(item.getThumbsUp() + "");
        holder.thumbsDown.setText(item.getThumbsDown() + "");

    }

    public void updateList(List<DictionaryResultItems> newlist) {
        itemsList.clear();
        itemsList.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}