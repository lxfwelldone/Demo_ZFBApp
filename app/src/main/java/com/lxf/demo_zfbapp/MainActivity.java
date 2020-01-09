package com.lxf.demo_zfbapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<AppGroup> listAppGroup = new ArrayList<>();



    /**
     * TabLayout
     */
    private TabLayout mTabLayout;
    /**
     * 内容列表
     */
    private RecyclerView mRecyclerView;
    /**
     * AppBarLayout
     */
    private AppBarLayout mAppBar;

    /**
     * LinearLayoutManager
     */
    private LinearLayoutManager mLinearLayoutManager;


    /**
     * 是否处于滚动状态，避免连锁反应
     */
    private boolean isScroll;
    /**
     * RecyclerView高度
     */
    private int mRecyclerViewHeight;
    /**
     * 平滑滚动 Scroller
     */
    private RecyclerView.SmoothScroller mSmoothScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }


    private void findViews(){
        mTabLayout = findViewById(R.id.tab_app_group);
        mRecyclerView = findViewById(R.id.recycler_all);
        mAppBar = findViewById(R.id.appbar);
        initRecyclerView();
        initTabLayout();
        addModels();

        for (AppGroup g : listAppGroup){
            mTabLayout.addTab(mTabLayout.newTab().setText(g.getName()));
        }
        AppGroupAdapter appGroupAdapter = new AppGroupAdapter(listAppGroup, this);
        mRecyclerView.setAdapter(appGroupAdapter);
    }

    private void initTabLayout() {
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
//        mTabLayout.setTabTextColors(Color.BLACK, ContextCompat.getColor(this, R.color.colorAccent));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击tab的时候，RecyclerView自动滑到该tab对应的item位置
                int position = tab.getPosition();
                if (!isScroll) {
                    // 有动画且滚动到顶部
                    mSmoothScroller.setTargetPosition(position);
                    mLinearLayoutManager.startSmoothScroll(mSmoothScroller);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initRecyclerView() {
        mSmoothScroller = new LinearSmoothScroller(this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            @Nullable
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return mLinearLayoutManager.computeScrollVectorForPosition(targetPosition);
            }
        };

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //滑动RecyclerView list的时候，根据最上面一个Item的position来切换tab
//                mTabLayout.setScrollPosition(mLinearLayoutManager.findFirstVisibleItemPosition(), 0, true);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                TabLayout.Tab tabAt = mTabLayout.getTabAt(layoutManager.findFirstVisibleItemPosition());
                if (tabAt != null && !tabAt.isSelected()) {
                    tabAt.select();
                }
            }
        });
    }


    private void addModels(){
        List<App> listApps = new ArrayList<>();
        listApps.add(new App("学工系统", false));
        listApps.add(new App("学工系统", false));
        listApps.add(new App("学工系统", false));
        listApps.add(new App("学工系统", false));
        listApps.add(new App("学工系统", false));
        listApps.add(new App("学工系统", false));

        listAppGroup.add(new AppGroup("业务系统", listApps));


        listApps = new ArrayList<>();
        listApps.add(new App("考试查询", false));
        listApps.add(new App("考试查询", false));
        listApps.add(new App("考试查询", false));
        listApps.add(new App("考试查询", false));
        listApps.add(new App("考试查询", false));
        listApps.add(new App("考试查询", false));

        listAppGroup.add(new AppGroup("学习工作", listApps));


        listApps = new ArrayList<>();
        listApps.add(new App("我的宿舍", false));
        listApps.add(new App("我的宿舍", false));
        listApps.add(new App("我的宿舍", false));
        listApps.add(new App("我的宿舍", false));
        listApps.add(new App("我的宿舍", false));
        listApps.add(new App("我的宿舍", false));

        listAppGroup.add(new AppGroup("生活娱乐", listApps));


        listApps = new ArrayList<>();
        listApps.add(new App("校务服务", false));
        listApps.add(new App("校务服务", false));
        listApps.add(new App("校务服务", false));
        listApps.add(new App("校务服务", false));
        listApps.add(new App("校务服务", false));
        listApps.add(new App("校务服务", false));

        listAppGroup.add(new AppGroup("办事大厅", listApps));


        listApps = new ArrayList<>();
        listApps.add(new App("通知公告", false));
        listApps.add(new App("通知公告", false));
        listApps.add(new App("通知公告", false));
        listApps.add(new App("通知公告", false));
        listApps.add(new App("通知公告", false));
        listApps.add(new App("通知公告", false));

        listAppGroup.add(new AppGroup("农林资讯", listApps));


        listApps = new ArrayList<>();
        listApps.add(new App("英语等级", false));
        listApps.add(new App("英语等级", false));
        listApps.add(new App("英语等级", false));
        listApps.add(new App("英语等级", false));
        listApps.add(new App("英语等级", false));
        listApps.add(new App("英语等级", false));

        listAppGroup.add(new AppGroup("常用链接", listApps));

    }



    class AppGroupAdapter extends RecyclerView.Adapter{

        public static final int VIEW_TYPE_ITEM = 1;
        public static final int VIEW_TYPE_FOOTER = 2;
        protected List<AppGroup> mData;
        /**
         * 复用同一个View对象池
         */
        private RecyclerView.RecycledViewPool mRecycledViewPool;
        /**
         * item高度
         */
        private int itemHeight;


        private Context context;
        public AppGroupAdapter(List<AppGroup> data, Context context){
            mData = data;
            this.context = context;
            mRecycledViewPool = new RecyclerView.RecycledViewPool();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(context).inflate(R.layout.app_item, parent, false);
                return new ItemVH(view);
            } else {
                //Footer是最后留白的位置，以便最后一个item能够出发tab的切换
                View view = new View(context);
                Log.e("footer", "parentHeight: " + mRecyclerViewHeight + "--" + "itemHeight: " + itemHeight);
                view.setLayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                mRecyclerViewHeight - itemHeight));
                return new FootVH(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder.getItemViewType() == VIEW_TYPE_ITEM){
                final ItemVH vh = (ItemVH) holder;
                vh.tvGroup.setText(mData.get(position).getName());
                vh.recyclerView.setRecycledViewPool(mRecycledViewPool);
                vh.recyclerView.setHasFixedSize(false);
//                vh.recyclerView.setNestedScrollingEnabled(false);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4){

                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }

                    @Override
                    public void onLayoutCompleted(RecyclerView.State state) {
                        super.onLayoutCompleted(state);
                        mRecyclerViewHeight = mRecyclerView.getHeight();
                        itemHeight = vh.itemView.getHeight();
                    }
                };
                gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(vh.recyclerView.getContext()) {
//
//                    @Override
//                    public boolean canScrollVertically() {
//                        return false;
//                    }
//
//                    @Override
//                    public void onLayoutCompleted(RecyclerView.State state) {
//                        super.onLayoutCompleted(state);
//                        mRecyclerViewHeight = mRecyclerView.getHeight();
//                        itemHeight = vh.itemView.getHeight();
//                    }
//                };
                vh.recyclerView.setLayoutManager(gridLayoutManager);

                AppsAdapter appsAdapter = new AppsAdapter(context);
                appsAdapter.list.addAll(mData.get(position).getApps());
                vh.recyclerView.setAdapter(appsAdapter);
            }
        }

        @Override
        public int getItemCount() {
            return mData.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == mData.size()) return  VIEW_TYPE_FOOTER;
            return VIEW_TYPE_ITEM;
        }

        class FootVH extends RecyclerView.ViewHolder{

            public FootVH(@NonNull View itemView) {
                super(itemView);
            }
        }

        class ItemVH extends RecyclerView.ViewHolder{

            private TextView tvGroup;
            private RecyclerView recyclerView;
            public ItemVH(@NonNull View itemView) {
                super(itemView);

                tvGroup = itemView.findViewById(R.id.tv_name);
                recyclerView = itemView.findViewById(R.id.recycler);
            }
        }
    }



}
