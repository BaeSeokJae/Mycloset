package com.example.mycloset;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    int pos;
    RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    EventRecyclerAdapter eventRecyclerAdapter;
    private ArrayList<Event> eventList = new ArrayList<>();
    private ImageView imageView;
    String brandSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_recyclerview);

        Intent intent = new Intent(this.getIntent());
        // 브랜드 페이지의 리사이클러뷰 포지션값을 전달 받음.
        pos = intent.getIntExtra("Key1", pos);

        recyclerView = findViewById(R.id.draw_recyclerview);
        int numberOfColumns = 2; // 한줄에 3개의 컬럼을 추가합니다.
        mGridLayoutManager = new GridLayoutManager(EventListActivity.this, numberOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        eventRecyclerAdapter = new EventRecyclerAdapter(EventListActivity.this, eventList);
        recyclerView.setAdapter(eventRecyclerAdapter);

        getData();

        eventRecyclerAdapter.setOnItemClickListener(new EventRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int product) {
                Intent intent = new Intent(EventListActivity.this, ProductSiteActivity.class);
                String Productname = eventList.get(product).getEventName();
                String ProductImage = eventList.get(product).getEventImage();
                intent.putExtra("name", Productname);
                intent.putExtra("img", ProductImage);
                intent.putExtra("Key1", product);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        EventJsoup jsoupAsyncTask = new EventJsoup();
        jsoupAsyncTask.execute();
    }

    private class EventJsoup extends AsyncTask<Void, Void, Void> {
        ArrayList<String> Eventlist = new ArrayList<>();
        ArrayList<String> EventImg = new ArrayList<>();
        ArrayList<String> EventDate = new ArrayList<>();
        ArrayList<String> EventDate1 = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            if (pos == 0) {
                try {
                    Connection connection = Jsoup.connect("https://www.nike.com/kr/launch/?type=upcoming&activeDate=date-filter:AFTER");
                    //specify user agent
                    connection.userAgent("Mozilla/5.0");
                    //get the HTML document
                    Document doc = connection.get();
                    //parse text from HTML
                    String strHTML = doc.text();
                    // 이벤트 제품 이름
                    final Elements event_name = doc.select("div.launch-list-item.upcomingItem div.info-sect p.txt-description");
                    // 이벤트 이미지
                    final Elements event_image = doc.select("div.img-sect img.img-component");
                    // 이벤트 날짜
                    final Elements event_date = doc.select("div.info-sect p.txt-subject");
                    final Elements event_date1 = doc.select("div.launch-list-item.upcomingItem  ");

                    Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //브랜드 이름 정보
                            for (Element element : event_name) {
                                Eventlist.add(element.text());
                            }
//                        // 이미지정보
                            for (Element element : event_image) {
                                EventImg.add(element.attr("data-src"));
                            }
                            //브랜드 이름 정보
                            for (Element element : event_date) {
                                EventDate.add(element.text());
                            }
                            for (Element element : event_date1) {
                                EventDate1.add(element.attr("data-active-date"));
                            }
                            for (int i = 0; i < event_image.size(); i++) {
                                Event data = new Event();
                                data.setEventName(Eventlist.get(i));
                                data.setEventImage(EventImg.get(i));
                                data.setEventDate(EventDate.get(i));
                                data.setEventDate1(EventDate1.get(i));

                                eventList.add(data);
                            }
                            eventRecyclerAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (pos == 1) {
                try {
                    Document doc = Jsoup.connect("http://atmos-seoul.com/shop/seminar.html?seminar_type=draw_list").get();
                    // 이벤트 제품 이름
                    final Elements event_name = doc.select("li.first div.release-img.draw-ended h3.draw-title");
                    // 이벤트 이미지
                    final Elements event_image = doc.select("div.Mk_draw");
                    // 이벤트 날짜
                    final Elements event_date = doc.select("li.first div.release-img.draw-ended div.available");
                    final Elements event_date1 = doc.select("li.first div.release-img.draw-ended div.draw-announced");

                    Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //브랜드 이름 정보
                            for (Element element : event_name) {
                                Eventlist.add(element.text());
                            }
//                        // 이미지정보
                            for (Element element : event_image) {
                                EventImg.add(element.attr("data-img"));
                            }
                            //브랜드 이름 정보
                            for (Element element : event_date) {
                                EventDate.add(element.text());
                            }
                            for (Element element : event_date1) {
                                EventDate1.add(element.text());
                            }
                            for (int i = 0; i < event_image.size(); i++) {
                                Event data = new Event();
                                data.setEventName(Eventlist.get(i));
                                data.setEventImage(EventImg.get(i));
                                data.setEventDate(EventDate.get(i));
                                data.setEventDate1(EventDate1.get(i));

                                eventList.add(data);
                            }
                            eventRecyclerAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}