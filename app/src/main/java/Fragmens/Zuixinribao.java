package Fragmens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import Bean.MynewRibao;
import Myadater.MyNewRibaoadater;
import Utils.Icalls;
import Utils.Okhttp;
import okhttp3.Request;
import test.bwie.com.myruanjian1.R;

public class Zuixinribao extends Fragment {

    private RecyclerView mRecy;
    private View v;
    private LinearLayoutManager linearLayoutManager;
    private List<MynewRibao.StoriesBean> list = new ArrayList<>();
    private List<MynewRibao.TopStoriesBean> top = new ArrayList<>();
    String urls = "http://news-at.zhihu.com/api/4/news/latest";
    private SwipeRefreshLayout refreshLayout;
    private MyNewRibaoadater myNewRibaoadater;
    String newUrls = "http://news-at.zhihu.com/api/4/news/before/20131119";

    private int last ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                   MynewRibao mynewRibao =  new Gson().fromJson( msg.obj.toString(),MynewRibao.class);

                        list.addAll(mynewRibao.getStories());
                        top.addAll(mynewRibao.getTop_stories());

                        myNewRibaoadater = new MyNewRibaoadater(getActivity(),list,top);
                        mRecy.setAdapter(myNewRibaoadater);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_zuixinribao, container, false);

        initview();

        InitData();


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         Okhttp.getOkhttps().GetDatas(newUrls, new Icalls() {
                             @Override
                             public void Success(final Object o) {
                                 getActivity().runOnUiThread(new Runnable() {
                                     @Override
                                     public void run() {
                                         MynewRibao mynewRibao =  new Gson().fromJson(o.toString(),MynewRibao.class);
                                         List<MynewRibao.StoriesBean> newlist=  new ArrayList<MynewRibao.StoriesBean>();
                                         newlist.addAll(mynewRibao.getStories());

                                         myNewRibaoadater.AddSua(newlist);
                                         Toast.makeText(getContext(), "跟新了", Toast.LENGTH_SHORT).show();

                                         refreshLayout.setRefreshing(false);
                                     }
                                 });
                             }

                             @Override
                             public void Error(Exception e, Request request) {

                             }
                         });
                    }
                },2000);
            }
        });

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int i1 =  linearLayoutManager.findLastVisibleItemPosition();

                if(newState == RecyclerView.SCROLL_STATE_IDLE&&last+1== myNewRibaoadater.getItemCount()){
                     new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             Okhttp.getOkhttps().GetDatas(newUrls, new Icalls() {
                                 @Override
                                 public void Success(final Object o) {
                                     getActivity().runOnUiThread(new Runnable() {
                                         @Override
                                         public void run() {
                                             MynewRibao mynewRibao =  new Gson().fromJson(o.toString(),MynewRibao.class);
                                             List<MynewRibao.StoriesBean> newlist=  new ArrayList<MynewRibao.StoriesBean>();
                                             newlist.addAll(mynewRibao.getStories());

                                             myNewRibaoadater.Addjian(newlist);
                                             Toast.makeText(getContext(), "跟新了", Toast.LENGTH_SHORT).show();

                                             refreshLayout.setRefreshing(false);
                                         }
                                     });
                                 }

                                 @Override
                                 public void Error(Exception e, Request request) {

                                 }
                             });
                         }
                     }, 2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                last = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

        return v;
    }

    private void InitData() {
        Okhttp.getOkhttps().GetDatas(urls, new Icalls() {
            @Override
            public void Success(final Object o) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Message message = new Message();
                        message.obj = o;
                        message.what = 1;


                       handler.sendMessage(message);
                    }
                });
            }

            @Override
            public void Error(Exception e, Request request) {

            }
        });

    }

    private void initview() {

        mRecy  = v.findViewById(R.id.newribao);
        refreshLayout = v.findViewById(R.id.swipe);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecy.setLayoutManager(linearLayoutManager);

    }

}
