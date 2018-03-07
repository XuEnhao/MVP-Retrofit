package com.lezhi.wshi.module.ui.adapter.channerl;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lezhi.wshi.R;
import com.lezhi.wshi.module.ui.adapter.RecycleItemClickListener;
import com.lezhi.wshi.utils.EventUtil;
import com.lezhi.wshi.utils.LogUtil;

import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.MasonryView> {

    private List<String> products;
    private Context mContext;

    private static PingCallBack callBack;

    public void setPingCallBack(PingCallBack click){
        this.callBack = click;
    }

    public ChannelAdapter(Context context,List<String> list) {
        mContext = context;
        products = list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_channel, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView masonryView, final int position) {
            if(position<=1){
                masonryView.textView.setEnabled(true);
                masonryView.iv_status.setVisibility(View.GONE);
                masonryView.textView.setText(products.get(position));
            }else{
                masonryView.iv_status.setVisibility(View.VISIBLE);
                masonryView.textView.setText(products.get(position));
            }
        masonryView.iv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack!=null){
                    callBack.ClickBack(position);
                }else{
                    EventUtil.showToast(mContext," 回调对象为空 -");
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class MasonryView extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView;
        private ImageView iv_status;

        public MasonryView(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.news_channel_tv);
            iv_status = (ImageView) itemView.findViewById(R.id.news_channel_iv);
            iv_status.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            itemClickListener.onItemClick(view,this.getLayoutPosition());
        }
    }

    public interface PingCallBack{
        void ClickBack(int position);
    }
}
