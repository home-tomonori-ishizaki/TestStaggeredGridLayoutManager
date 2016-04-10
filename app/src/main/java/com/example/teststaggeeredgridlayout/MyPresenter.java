package com.example.teststaggeeredgridlayout;

import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPresenter extends Presenter {
    private static class MyViewHolder extends ViewHolder {
        public ImageView image;
        public TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setFocusable(true);
            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int bgColor = hasFocus ? Color.WHITE : Color.parseColor("#1976D2");
                    int txtColor = hasFocus ? Color.BLACK : Color.WHITE;
                    title.setTextColor(txtColor);
                    title.setBackgroundColor(bgColor);
                    //v.setZ(hasFocus ? 1 : 0);
                }
            });

            image = (ImageView)itemView.findViewById(R.id.image);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_view, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        MyModel model = (MyModel)item;
        MyViewHolder vh = (MyViewHolder)viewHolder;
        Log.i("HOGE", "type:" + model.type);
        ViewGroup.LayoutParams lp = vh.image.getLayoutParams();
        lp.width = model.type == MyModel.LARGE ? 800 : 200;
        lp.height = model.type == MyModel.LARGE ? 400 : 200;
        vh.image.setLayoutParams(lp);

        vh.title.setText(model.title);

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
