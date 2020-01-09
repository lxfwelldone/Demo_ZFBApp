package com.lxf.demo_zfbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter {

    public List<App> list = new ArrayList<>();
    private Context context;

    private static final int type_item = 1;
    private static final int type_group = 2;

    public interface OnItemClickListener {
        void clickItem(App model, int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private OnItemClickListener itemClickListener;


    public AppsAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == type_group){
          return initGroup(parent);
        }
        return initItem(parent);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        displayItem(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        App app = list.get(position);
        return app.isCat() ? type_group : type_item;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    private void displayItem(RecyclerView.ViewHolder holder, final int position){
        final App model = list.get(position);
        if ( getItemViewType(position) == type_group){
            ItemGroup itemGroup = (ItemGroup) holder;
            itemGroup.tvGroupName.setText(model.getShortName());
        } else if (getItemViewType(position) == type_item){
            ItemHolder itemHolder = (ItemHolder) holder;
            if (model.getShortName() != null) itemHolder.tvName.setText(model.getShortName());

            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.clickItem(model, position);
                    }
                }
            });
        }

    }


    private ItemHolder initItem(ViewGroup parent){
        View inflate = LayoutInflater.from(context).inflate(R.layout.app_grid_item, parent, false);
        return new ItemHolder(inflate);
    }


    class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView imgIcon, imgOperate;
        private TextView tvName;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = itemView.findViewById(R.id.img_icon);
            imgOperate = itemView.findViewById(R.id.img_operate);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }



    private ItemGroup initGroup(ViewGroup parent){
        View inflate = LayoutInflater.from(context).inflate(R.layout.app_grid_group, parent, false);
        return new ItemGroup(inflate);
    }



    class ItemGroup extends RecyclerView.ViewHolder {

        private TextView tvGroupName;
        public ItemGroup(@NonNull View itemView) {
            super(itemView);

            tvGroupName = itemView.findViewById(R.id.tv_name);
        }
    }

}
