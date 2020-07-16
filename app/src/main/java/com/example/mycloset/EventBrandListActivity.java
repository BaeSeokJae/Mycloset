package com.example.mycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventBrandListActivity extends AppCompatActivity {

    int pos;
    RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    EventBrandRecyclerAdapter eventBrandRecyclerAdapter;
    private ArrayList<EventBrand> eventBrandList = new ArrayList<>();
    String url = "https://www.nike.com/kr/launch/?type=upcoming&activeDate=date-filter:AFTER";
    private ImageView imageView;
    String brandSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_recyclerview);

        Intent intent = new Intent(this.getIntent());
        // 브랜드 페이지의 리사이클러뷰 포지션값을 전달 받음.
        pos = intent.getIntExtra("Key", pos);

        this.BrandListData();

        recyclerView = findViewById(R.id.event_recyclerview);
        int numberOfColumns = 2; // 한줄에 3개의 컬럼을 추가합니다.
        mGridLayoutManager = new GridLayoutManager(EventBrandListActivity.this, numberOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        eventBrandRecyclerAdapter = new EventBrandRecyclerAdapter(EventBrandListActivity.this, eventBrandList);
        recyclerView.setAdapter(eventBrandRecyclerAdapter);

//        Button draw = (Button)findViewById(R.id.draw);
//        Button sale = (Button)findViewById(R.id.sale);

        eventBrandRecyclerAdapter.setOnItemClickListener(new EventBrandRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int product) {
                if (eventBrandList.get(product).getEventBrandLink() == null) {
                    Intent intent = new Intent(EventBrandListActivity.this, EventListActivity.class);
                    String EventBrandname = eventBrandList.get(product).getEventBrandName();
                    int ProductImage = eventBrandList.get(product).getEventBrandImage();
                    intent.putExtra("name", EventBrandname);
                    intent.putExtra("img", ProductImage);
                    intent.putExtra("Key1", product);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(EventBrandListActivity.this, ProductSiteActivity1.class);
                    String EventBrandname = eventBrandList.get(product).getEventBrandName();
                    int ProductImage = eventBrandList.get(product).getEventBrandImage();
                    String EventBrandLink = eventBrandList.get(product).getEventBrandLink();
                    intent.putExtra("name", EventBrandname);
                    intent.putExtra("img", ProductImage);
                    intent.putExtra("link", EventBrandLink);
                    intent.putExtra("Key1", product);
                    startActivity(intent);
                }
            }
        });

//        draw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (eventBrandList.get(eventBrandRecyclerAdapter.pos).getEventBrandDate() == "draw") {
//                    BrandRecyclerAdapter.ItemViewHolder.setVisibility(View.GONE);
//                }
//
//            }
//        });
    }

//    private void getData() {
//        EventJsoup jsoupAsyncTask = new EventJsoup();
//        jsoupAsyncTask.execute();
//    }
//
//    private class EventJsoup extends AsyncTask<Void, Void, Void> {
//        ArrayList<String> Eventlist = new ArrayList<>();
//        ArrayList<String> EventImg = new ArrayList<>();
//        ArrayList<String> EventDate = new ArrayList<>();
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//                Connection connection = Jsoup.connect(url);
//                //specify user agent
//                connection.userAgent("Mozilla/5.0");
//
//                //get the HTML document
//                Document doc = connection.get();
//
//                //parse text from HTML
//                String strHTML = doc.text();
//                final Elements event_name = doc.select("div.info-sect p.txt-description");
//                final Elements event_image = doc.select("div.img-sect img.img-component");
//                final Elements event_date = doc.select("div.info-sect p.txt-subject");
////                final Elements product_price = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.price.sales a");
////                final Elements product_site = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.goods h4 a");
//
//                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //브랜드 이름 정보
//                        for (Element element : event_name) {
//                            Eventlist.add(element.text());
//                        }
//                        //브랜드 이름 정보
//                        for (Element element : event_date) {
//                            EventDate.add(element.text());
//                        }
////                        // 이미지정보
//                        for (Element element : event_image) {
//                            EventImg.add(element.attr("data-src"));
//                        }
//                        for (int i = 0; i < event_image.size(); i++) {
//                            EventBrand data = new EventBrand();
//                            data.setEventBrandName(Eventlist.get(i));
//                            data.setEventBrandImage(EventImg.get(i));
//                            data.setEventBrandDate(EventDate.get(i));
//
//                            eventBrandList.add(data);
//                        }
//                        eventBrandRecyclerAdapter.notifyDataSetChanged();
//                    }
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
    public void BrandListData() {
        eventBrandList = new ArrayList<>();

        eventBrandList.add(new EventBrand("SNKRS", "The Draw",null, null, R.drawable.snkrs));
        eventBrandList.add(new EventBrand("Foot Scape", "기획전",null,  "http://footscape.com/product/list.html?cate_no=155", R.drawable.footscape));
        eventBrandList.add(new EventBrand("ABC마트", "기획전",null,  "https://www.a-rt.com/promotion/planning-display", R.drawable.abcmart));
        eventBrandList.add(new EventBrand("Musinsa","기획전",null,  "https://store.musinsa.com/app/plan/lists", R.drawable.musinsa));
    }
}