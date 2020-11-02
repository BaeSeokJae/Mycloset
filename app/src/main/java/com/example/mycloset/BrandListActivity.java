package com.example.mycloset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BrandListActivity extends AppCompatActivity {

    String a;
    int i = 0;
    int pressedTime;
    int counter = 0;
    RecyclerView recyclerView;
    ArrayList<String> listTitle = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;
    BrandRecyclerAdapter brandRecyclerAdapter;
    private ArrayList<Brand> brandList;
    private ArrayList<test1> tests1 = new ArrayList<>();
    private Button List, Memo, Map, Event;
    private ImageView imageView;
    private TextView textView;
    String b = "https://youtu.be/8L3twAgNXuw";
    String s = b.substring(17, 27);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_recyclerview);

        this.BrandListData();

        NewRunnable nr = new NewRunnable();
        Thread t = new Thread(nr);
        t.start();

        textView = findViewById(R.id.Event_Thread);
        imageView = findViewById(R.id.test);
        Glide.with(this).load(" https://img.youtube.com/vi/" + s + "/" + "0.jpg").into(imageView);

        recyclerView = findViewById(R.id.brand_recyclerview);
        int numberOfColumns = 3; // 한줄에 5개의 컬럼을 추가합니다.
        mGridLayoutManager = new GridLayoutManager(BrandListActivity.this, numberOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        brandRecyclerAdapter = new BrandRecyclerAdapter(BrandListActivity.this, brandList);
        recyclerView.setAdapter(brandRecyclerAdapter);

        List = (Button) findViewById(R.id.List);
//        Memo = (Button) findViewById(R.id.Memo);
        Map = (Button) findViewById(R.id.Map);
        Event = findViewById(R.id.Event);


//        Glide.with(this.getContext()).load(data.getEventBrandImage()).into(imageView);


        brandRecyclerAdapter.setOnItemClickListener(new BrandRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                Intent intent = new Intent(BrandListActivity.this, ProductListActivity.class);
                String brandText = brandList.get(pos).getBrandText();
                int brandImage = brandList.get(pos).getBrandImage();
                String brandSite = brandList.get(pos).getBrandSite();
                String brandSite1 = brandList.get(pos).getBrandSite1();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), brandImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("BrandSite", brandSite);
                intent.putExtra("BrandSite1", brandSite1);
                intent.putExtra("BrandImage", byteArray);
                intent.putExtra("BrandText", brandText);
                intent.putExtra("Key", pos);
                startActivity(intent);
            }
        });

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(BrandListActivity.this, SelectListActivity.class);
                startActivity(intent);
            }
        });

//        Memo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View V) {
//                Intent intent = new Intent(BrandListActivity.this, MemoListActivity.class);
//                startActivity(intent);
//            }
//        });

        Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( "https://youtu.be/8L3twAgNXuw" ));

                startActivity(intent);
//                Intent intent = new Intent(BrandListActivity.this, EventBrandListActivity.class);
//                startActivity(intent);
            }
        });

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(BrandListActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public void BrandListData() {
        brandList = new ArrayList<>();

        brandList.add(new Brand("Acne", "https://www.youtube.com/channel/UCGX5sP4ehBkihHwt5bs5wvg","https://www.youtube.com/c/DogSwellfish/videos?view=0&sort=p&flow=grid" ,R.drawable.acne));
        brandList.add(new Brand("A.P.C", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=039630&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=1467&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "http://www.apc-korea.com/main.do?referer=https://www.google.com/&null",R.drawable.apc));
        brandList.add(new Brand("adidas", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=030088&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=3340&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://shop.adidas.co.kr/adiMain.action?gclid=CjwKCAjw5vz2BRAtEiwAbcVIL35z6v51Y65zNS-0pVsSrIYRe5aak4nReCAcrQQf4KMU04qdmS5xTRoCbHAQAvD_BwE&NFN_ST=Y",R.drawable.adidas));
        brandList.add(new Brand("Alexander\nMcQUEEN", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=043872&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=2905&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.alexandermcqueen.com/kr",R.drawable.macqueen));
        brandList.add(new Brand("BALENCIAGA", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=030459&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=4978&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.balenciaga.com/kr?gclid=CjwKCAjw5vz2BRAtEiwAbcVIL7XhjkcNV6yTlkzADfUhFvPo-gXxdG9BDj-xeRamZTf9I3I2CqzwNBoCzpkQAvD_BwE&tp=62532",R.drawable.balenciaga));
        brandList.add(new Brand("BOTTEGA\nVENETA", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=030693&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=3219&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.bottegaveneta.com/kr?gclid=CjwKCAjw5vz2BRAtEiwAbcVIL3GfXsrxp298WKWoH0U2TJAx458kKv-EWh4GiVVWfGlbEpIss3AMqBoCr-0QAvD_BwE&tp=62206",R.drawable.bottegaveneta));
        brandList.add(new Brand("BURBERRY", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=001745&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=5385&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://kr.burberry.com/?ds_rl=1047545&ds_rl=1048160&ds_rl=1051053&gclid=CjwKCAjw5vz2BRAtEiwAbcVIL_c-3v1zw9T7jL6YHht1J_4V7WIg8uJOZjT5MY7c08EobMWqrcOAixoCCukQAvD_BwE&gclsrc=aw.ds", R.drawable.burberry));
        brandList.add(new Brand("converse", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=041290&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=239&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.converse.co.kr/?utm_source=google&utm_medium=cpc&utm_campaign=google_sa_pc&utm_content=01_%EB%B8%8C%EB%9E%9C%EB%93%9C_%EB%8C%80%ED%91%9C&gclid=CjwKCAjw5vz2BRAtEiwAbcVIL5jTWpy_qF8uD8MBvFI7RJBIcUe5ImrMqx1KRO1NLex-2ih1GqaNTRoCLR0QAvD_BwE", R.drawable.converse));
        brandList.add(new Brand("Golden\tgoose", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=054078&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=2872&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.goldengoosedeluxebrand.co.kr/main/main.asp",R.drawable.goldengoose));
        brandList.add(new Brand("Gucci", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=010136&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=13107&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.gucci.com/kr/ko/ca/women/womens-handbags-c-women-handbags?&utm_source=google_kr_pc&utm_medium=cpc&utm_campaign=pc_exact&utm_content=gucci_brand&utm_term=%EA%B5%AC%EC%B0%8C&gclid=CjwKCAjw5vz2BRAtEiwAbcVIL-xl4qzEpDGg9jcFePq67SaEcsXWLASs0nM3-2fF2Wv0dvCKuZ40wBoCoEMQAvD_BwE",R.drawable.gucci));
        brandList.add(new Brand("Maison\tKitsune", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=081837&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=1775&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.ssfshop.com/Maison-Kitsune/main?brandShopNo=BDMA07A22&brndShopId=BQMKT&leftBrandNM=Maison%20Kitsune_BQMKT&dspCtgryNo=",R.drawable.maisonkitsune));
        brandList.add(new Brand("Maison\tMargiera", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=090920&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=924&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "\"https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=090920&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=924&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category\"",R.drawable.maisonmargiela));
        brandList.add(new Brand("newbalance", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=067367&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=732&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.nbkorea.com/index.action?&utm_source=google&utm_medium=sa&utm_campaign=1_PC_%EC%A3%BC%EC%9A%94&utm_content=01_%EC%9E%90%EC%82%AC%EB%AA%85&utm_term=%EB%89%B4%EB%B0%9C%EB%9E%80%EC%8A%A4&_C_=925592&gclid=CjwKCAjw5vz2BRAtEiwAbcVIL67YSEVNeVnimTxmXC_wqQLqucfIQtkoOM7RqaPfSPBKXzz9UEiAKxoCte0QAvD_BwE",R.drawable.newbalance));
        brandList.add(new Brand("nike", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=007110&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=4258&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.nike.com/kr/ko_kr/?ctnakey=03-1015-5-75138&utm_source=google&utm_medium=CPC&cp=kr_pf_ps_ggkw_pc&gclid=CjwKCAjw5vz2BRAtEiwAbcVIL1yo_vx-fN9O5l33tsPU2KrvhtTHK3KtOrzjjRdwGBuAoeRqrGgzWRoCiMwQAvD_BwE",R.drawable.nike));
        brandList.add(new Brand("THOMBROWNE", "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=079477&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=3356&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category", "https://www.thombrowne.com/kr/?gclid=CjwKCAjw5vz2BRAtEiwAbcVIL-_wOxiBS4HLwW-lBpQHayHRrpMwysOJ0LdnolXgkwMZ81waAv5_UxoCIpcQAvD_BwE",R.drawable.thombrowne));

    }

    @Override
    public void onBackPressed() {
        if (pressedTime == 0) {
            Toast.makeText(BrandListActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = (int) System.currentTimeMillis();
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if (seconds > 2000) {
                Toast.makeText(BrandListActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
//                finish(); // app 종료 시키기
            }
        }
    }
//
//    class BackThread extends Thread {
//        ArrayList<String> listTitle = new ArrayList<>();
//
//        @Override
//        public void run() {
//
//            listTitle.add("a");
//            listTitle.add("b");
//            listTitle.add("c");
//            listTitle.add("d");
//            listTitle.add("e");
//            listTitle.add("f");
//
//            while (true) {
//                test1 tests = new test1();
//
//                tests.setTest(listTitle.get(5));
//
//                textView.setText("" + tests); // 메인스레드의 UI 내용 변경
//
//                // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달
//                try {
//
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } // end while
//        } // end run()
//    } // end class BackThread
//
//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {   // Message id 가 0 이면
//
//            }
//        }
//    };

    final Runnable runnable = new Runnable() {

        @Override
        public void run() {
            textView.setText(a); // 메인스레드의 UI 내용 변경
        }
    };

    // 새로운 스레드 실행 코드. 1초 단위로 현재 시각 표시 요청.
    class NewRunnable implements Runnable {
        @Override
        public void run() {
            listTitle.add("Nike SNKRS Draw 06.11 ~");
            listTitle.add("Foot Scape 기획전 06.01 ~ 06~11");
            listTitle.add("ABC 마트 기획전 04.20 ~");
            listTitle.add("MUSINSA 기획전 05.21 ~");
            while (true) {
                for (i = 0; i < listTitle.size(); i++) {

                    a = listTitle.get(i);

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 메인 스레드에 runnable 전달.
                    runOnUiThread(runnable);
                }
            }
        }
    }
}