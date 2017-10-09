package com.guyulei.gurecyclerview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView          mRecyclerView;
    private List<String>          mStringList;
    private MyRecyclerViewAdapter mMyRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.guRecyclerView);

        /*mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MyRecyclerViewDecoration(MainActivity.this, MyRecyclerViewDecoration.VERTICAL_LIST));*/
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new MyRecyclerViewGridDecoration(this, 2, this.getResources().getColor(R.color.colorAccent)));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStringList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mStringList.add("guyulei_" + i);
        }

        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter(this, mStringList);
        mRecyclerView.setAdapter(mMyRecyclerViewAdapter);
        //
        mMyRecyclerViewAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        TextView textView = (TextView) view;
        Toast.makeText(this, textView.getText().toString() + " " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, final int position) {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("确认删除吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mMyRecyclerViewAdapter.removeData(position);
                    }
                }).show();

    }
}
