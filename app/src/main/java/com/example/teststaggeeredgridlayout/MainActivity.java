package com.example.teststaggeeredgridlayout;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.FocusHighlight;
import android.support.v17.leanback.widget.FocusHighlightHelper;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.ShadowOverlayContainer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {
    private ItemBridgeAdapter.Wrapper mWrapper = new ItemBridgeAdapter.Wrapper() {
        @Override
        public View createWrapper(View root) {
            ShadowOverlayContainer wrapper = new ShadowOverlayContainer(root.getContext());
            wrapper.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            wrapper.initialize(true, true, true);
            return wrapper;
        }
        @Override
        public void wrap(View wrapper, View wrapped) {
            ((ShadowOverlayContainer) wrapper).wrap(wrapped);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 10;
                outRect.top = 10;
                outRect.bottom = 10;
                outRect.right = 10;
            }
        });

        ArrayObjectAdapter adapter = new ArrayObjectAdapter();
        adapter.add(new MyModel("test1", MyModel.LARGE));
        adapter.add(new MyModel("test2", MyModel.MEDIUM));
        adapter.add(new MyModel("test3", MyModel.MEDIUM));
        adapter.add(new MyModel("test4", MyModel.SMALL));
        adapter.add(new MyModel("test5", MyModel.SMALL));
        adapter.add(new MyModel("test6", MyModel.MEDIUM));
        adapter.add(new MyModel("test7", MyModel.MEDIUM));

        ItemBridgeAdapter bridgeAdapter = new ItemBridgeAdapter(adapter, new PresenterSelector() {
            private MyPresenter mPresenter = new MyPresenter();
            @Override
            public Presenter getPresenter(Object item) {
                return mPresenter;
            }
        });
        bridgeAdapter.setWrapper(mWrapper);
        ShadowOverlayContainer.prepareParentForShadow(recyclerView);
        recyclerView.setClipChildren(false);
        recyclerView.setClipToPadding(false);
        FocusHighlightHelper.setupBrowseItemFocusHighlight(bridgeAdapter, FocusHighlight.ZOOM_FACTOR_MEDIUM, false);

        recyclerView.setAdapter(bridgeAdapter);
    }
}
