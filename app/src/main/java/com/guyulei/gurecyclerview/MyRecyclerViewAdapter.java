package com.guyulei.gurecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2017/10/9.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> {
    private Context      mContext;
    private List<String> mList;
    private List<Integer> mIntegers = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MyRecyclerViewAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        for (int i = 0; i < mList.size(); i++) {
            mIntegers.add((int) (Math.random() * 300 + 100));
        }


    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.myrecycleviewitem, parent, false);
        MyRecyclerViewHolder myRecyclerViewHolder = new MyRecyclerViewHolder(inflate);
        return myRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.mTextView.getLayoutParams();
        layoutParams.height = mIntegers.get(position);
        holder.mTextView.setLayoutParams(layoutParams);
        holder.mTextView.setText(mList.get(position));
        if (mOnItemClickListener != null) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(holder.mTextView, adapterPosition);
                }
            });

            holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int adapterPosition = holder.getAdapterPosition();
                    mOnItemClickListener.onItemLongClick(holder.mTextView, adapterPosition);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
