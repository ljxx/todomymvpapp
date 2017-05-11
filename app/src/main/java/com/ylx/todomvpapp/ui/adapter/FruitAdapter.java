package com.ylx.todomvpapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylx.todomvpapp.R;
import com.ylx.todomvpapp.bean.Fruit;

import java.util.List;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/5/11  下午12:46
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Fruit> mFruitList;

    private static final int NORMALLAYOUT = 0;
    private static final int FOOTERLAYOUT = 1;

    public FooterViewHolder mFooterAdapter;

    public FruitAdapter(List<Fruit> mList){
        this.mFruitList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        if(viewType == NORMALLAYOUT){
            View mView = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
            return new NormalViewHolder(mView);
        } else {
            View mFooterView = LayoutInflater.from(mContext).inflate(R.layout.loading_item_foot,parent,false);
            mFooterAdapter = new FooterViewHolder(mFooterView);
            return mFooterAdapter;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof NormalViewHolder){
            ((NormalViewHolder) holder).setData(position);
            if(onItemClickListener != null){
                /**
                 * 点击事件
                 */
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(holder.itemView,position);
                    }
                });

                /**
                 * 长按事件
                 */
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onItemLongClick(holder.itemView,position);
                        return false;
                    }
                });
            }
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mFruitList.size() == 0 ? 0 : mFruitList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mFruitList.size()){
            return FOOTERLAYOUT;
        } else {
            return NORMALLAYOUT;
        }
    }

    /**
     * 主体部分
     */
     class NormalViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public NormalViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }

        public void setData(int position){
            Fruit fruit = mFruitList.get(position);
            fruitName.setText(fruit.getName());
            Glide.with(mContext).load(fruit.getImageId()).into(fruitImage);
        }
    }

    /**
     * footer部分
     */
     public class FooterViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout loading_viewstub,end_viewstub,newwork_error_viewstub;

        public FooterViewHolder(View itemView) {
            super(itemView);
            loading_viewstub = (LinearLayout) itemView.findViewById(R.id.loading_viewstub);
            end_viewstub = (LinearLayout) itemView.findViewById(R.id.end_viewstub);
            newwork_error_viewstub = (LinearLayout) itemView.findViewById(R.id.newwork_error_viewstub);
        }

        /**
         * 根据传的状态，控制该状态对应的
         */
        public void setData(int status){
            setAllGone();
            switch (status){
                case 0:
                    break;
                case 1:
                    loading_viewstub.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    end_viewstub.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    newwork_error_viewstub.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

        public void setAllGone(){
            if(loading_viewstub != null){
                loading_viewstub.setVisibility(View.GONE);
            }

            if(end_viewstub != null){
                end_viewstub.setVisibility(View.GONE);
            }

            if(newwork_error_viewstub != null){
                newwork_error_viewstub.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 条目监听事件
     */
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
