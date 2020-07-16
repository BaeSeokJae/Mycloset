package com.example.mycloset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private static final String TAG = "";
    int i;
    int pos;
    ArrayList<String> listTitle = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();
    RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    ProductRecyclerAdapter productRecyclerAdapter;
    private ArrayList<Product> productList = new ArrayList<>();
    private ImageView imageView, loadingimage, scrollbackground;
    private TextView textView1;
    String brandSite, a, brandSite1;
    Thread t;
    Button choice, choice1, clear, brand, event;
    private boolean condition = true;
    LottieAnimationView animationView, swipeanimation;
    Product singleStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_recyclerview);

        byte[] byteArray = getIntent().getByteArrayExtra("BrandImage");
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView = (ImageView) findViewById(R.id.Brand_image);
        imageView.setImageBitmap(image);

        Intent intent = new Intent(this.getIntent());
        String s = intent.getStringExtra("BrandText");
        TextView textView = (TextView) findViewById(R.id.Brand_Text);
        textView.setText(s);
        // 브랜드 페이지의 리사이클러뷰 포지션값을 전달 받음.
        pos = intent.getIntExtra("Key", pos);
        brandSite = intent.getStringExtra("BrandSite");
        brandSite1 = intent.getStringExtra("BrandSite1");

        recyclerView = findViewById(R.id.product_recyclerview);
        int numberOfColumns = 3; // 한줄에 3개의 컬럼을 추가합니다.
        mGridLayoutManager = new GridLayoutManager(ProductListActivity.this, numberOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        recyclerView.setHasFixedSize(true);

        productRecyclerAdapter = new ProductRecyclerAdapter(ProductListActivity.this, productList);
        recyclerView.setAdapter(productRecyclerAdapter);

        textView1 = findViewById(R.id.loading);

        choice = findViewById(R.id.choice);
        choice1 = findViewById(R.id.select_choice);
        clear = findViewById(R.id.choice_clear);
        brand = findViewById(R.id.brand_site);
        event = findViewById(R.id.event_site);

        animationView = (LottieAnimationView) findViewById(R.id.loading_image);
        swipeanimation = (LottieAnimationView) findViewById(R.id.swipe_image);
        animationView.loop(true);
        animationView.playAnimation(); //Play the animation

        NewRunnable nr = new NewRunnable();
        t = new Thread(nr);
        t.start();

        getData();
            productRecyclerAdapter.setOnItemClickListener(new ProductRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int product) {
                    Intent intent = new Intent(ProductListActivity.this, ProductSiteActivity.class);
                    String Productname = productList.get(product).getProductName();
                    String ProductImage = productList.get(product).getProductImage();
                    String ProductSalePrice = productList.get(product).getProductSalePrice();
                    String ProductPrice = productList.get(product).getProductPrice();
                    String ProductLink = productList.get(product).getProductLink();
                    intent.putExtra("name", Productname);
                    intent.putExtra("img", ProductImage);
                    intent.putExtra("salePrice", ProductSalePrice);
                    intent.putExtra("price", ProductPrice);
                    intent.putExtra("link", ProductLink);
                    intent.putExtra("Key1", product);
                    startActivity(intent);
                }
            });

        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, ProductSiteActivity1.class);
                intent.putExtra("link", brandSite1);
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, ProductSiteActivity1.class);
                intent.putExtra("link", "https://www.nike.com/kr/launch/?type=upcoming&activeDate=date-filter:AFTER");
                startActivity(intent);
//                Toast.makeText(ProductListActivity.this, "현재 준비중 입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRecyclerAdapter.test1 = 1;
                //사라지는 버튼들.
                choice.setVisibility(View.GONE);
                brand.setVisibility(View.GONE);
                event.setVisibility(View.GONE);
                //생성되는 버튼들.
                choice1.setVisibility(View.VISIBLE);
                clear.setVisibility(View.VISIBLE);

                productRecyclerAdapter.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRecyclerAdapter.test1 = 0;
                //사라지는 버튼들.
                choice.setVisibility(View.VISIBLE);
                brand.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                //생성되는 버튼들.
                choice1.setVisibility(View.GONE);
                clear.setVisibility(View.GONE);

                productRecyclerAdapter.notifyDataSetChanged();
            }
        });

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRecyclerAdapter.test1 = 0;
                //생성되는 버튼들.
                choice.setVisibility(View.VISIBLE);
                brand.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                //사라지는 버튼들.
                choice1.setVisibility(View.GONE);
                clear.setVisibility(View.GONE);

                productRecyclerAdapter.notifyDataSetChanged();

                ArrayList<String> selectName = new ArrayList<>();
                ArrayList<String> selectPrice = new ArrayList<>();
                ArrayList<String> selectSalePrice = new ArrayList<>();
                ArrayList<String> selectImg = new ArrayList<>();
                ArrayList<String> selectLink = new ArrayList<>();
                ArrayList<String> selectMemo = new ArrayList<>();
                ArrayList<String> selectMemodata = new ArrayList<>();
                ArrayList<String> selectMemoContent = new ArrayList<>();
                ArrayList<Product> stList = (productRecyclerAdapter).getproductlist();

                for (int i = 0; i < stList.size(); i++) {
                    singleStudent = stList.get(i);
                    if (singleStudent.isSelected()) {
                        selectName.add(singleStudent.getProductName());
                        selectPrice.add(singleStudent.getProductPrice());
                        selectSalePrice.add(singleStudent.getProductSalePrice());
                        selectImg.add(singleStudent.getProductImage());
                        selectLink.add(singleStudent.getProductLink());
                        selectMemo.add("");
                        selectMemodata.add("");
                        selectMemoContent.add("");
                    }
                }
                for (int i = 0; i < selectName.size(); i++) {
                    Product test = new Product();
                    test.setProductName(selectName.get(i));
                    test.setProductPrice(selectPrice.get(i));
                    test.setProductSalePrice(selectSalePrice.get(i));
                    test.setProductImage(selectImg.get(i));
                    test.setProductLink(selectLink.get(i));
                    test.setMemoName(selectMemo.get(i));
                    test.setMemoData(selectMemodata.get(i));
                    test.setMemoContent(selectMemoContent.get(i));

                    saveData(test);
                }
                Toast.makeText(ProductListActivity.this, "찜하기 목록에 추가되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getData() {
        NikeJsoup jsoupAsyncTask = new NikeJsoup();
        jsoupAsyncTask.execute();
    }

    private class NikeJsoup extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listImg = new ArrayList<>();
        ArrayList<String> listSalePrice = new ArrayList<>();
        ArrayList<String> listPrice = new ArrayList<>();
        ArrayList<String> listLink = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(brandSite).get();
                final Elements product_name = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.goods h4");
                final Elements image_list = doc.select("ul.thumbCatalog.clearfix a.thumbnails img.thumb");
                final Elements product_sale_price = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.price.consumer");
                final Elements product_price = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.price.sales a");
                final Elements product_site = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.goods h4 a");

                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //브랜드 이름 정보
                        for (Element element : product_name) {
                            listTitle.add(element.text());
                        }  // 이미지정보
                        for (Element element : image_list) {
                            listImg.add(element.attr("src"));
                        } // 세일가격
                        for (Element element : product_sale_price) {
                            listSalePrice.add(element.text());
                        } // 원래가격
                        for (Element element : product_price) {
                            listPrice.add(element.text());
                        } // 세부상품 사이트 링크
                        for (Element element : product_site) {
                            listLink.add(element.attr("href"));
                        }
                        for (int i = 0; i < product_sale_price.size(); i++) {
                            Product data = new Product();
                            data.setProductName(listTitle.get(i));
                            data.setProductImage(listImg.get(i));
                            data.setProductSalePrice(listSalePrice.get(i));
                            data.setProductPrice(listPrice.get(i));
                            data.setProductLink(listLink.get(i));

                            productList.add(data);
                        }
                        productRecyclerAdapter.notifyDataSetChanged();
                        animationView.setVisibility(View.GONE);
                        animationView.loop(false);
                        textView1.setVisibility(View.GONE);

                        Intent intent1 = new Intent(ProductListActivity.this, swipe.class);
                        startActivity(intent1);

//                        swipeanimation.setVisibility(View.VISIBLE);
//                        swipeanimation.loop(true);
//                        swipeanimation.playAnimation();

                        condition = false;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    final Runnable runnable = new Runnable() {

        @Override
        public void run() {
            textView1.setText(a); // 메인스레드의 UI 내용 변경
//            loadingimage.setImageResource(image1);
        }
    };

    // 새로운 스레드 실행 코드. 1초 단위로 현재 시각 표시 요청.
    class NewRunnable implements Runnable {
        @Override
        public void run() {

            listTitle.add("로딩중");
            listTitle.add("로딩중");
            listTitle.add("로딩중");
            listTitle.add("로딩중");
//            image.add(R.drawable.loader);
//            image.add(R.drawable.loader1);
//            image.add(R.drawable.loader2);
//            image.add(R.drawable.loader3);
            while (condition) {
                for (i = 0; i < listTitle.size(); i++) {

                    a = listTitle.get(i);
//                    image1 = image.get(i);

                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 메인 스레드에 runnable 전달.
                    runOnUiThread(runnable);
                }
            }
        }
    }

    public void saveData(Product data) {
        JSONObject jsonObject = new JSONObject();
        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //현재 저장되어있는 데이터를 읽는다.
        try {
            //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
            if (sharedPreferences.getString("siteList", "").equals("")) {
//                Toast.makeText(context, "이미 저장", Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = new JSONArray();
                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                jsonObject.put("siteName", data.getProductName());
                jsonObject.put("siteSalePrice", data.getProductSalePrice());
                jsonObject.put("sitePrice", data.getProductPrice());
                jsonObject.put("siteImg", data.getProductImage());
                jsonObject.put("siteLink", data.getProductLink());
                jsonObject.put("memoName", data.getMemoName());
                jsonObject.put("memoDate", data.getMemoData());
                jsonObject.put("memoContent", data.getMemoContent());
                jsonArray.put(jsonObject);
                dataEditor.putString("siteList", jsonArray.toString());
                dataEditor.apply();
                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
                //"셀렉리스트" : [{"이미지유알엘":" ","제품명":" ","사이트링크":""}, == index = 0
                //              {"이미지유알엘":" ","제품명":" ","사이트링크":""}, == index = 1
                //              {"planDate":"","time":"","contents":"85285285285","maxMemberNumber":""}] == index = 2
                //"planList"라는 키 값으로 저장되어있는 데이터가 있을때
            } else if (!sharedPreferences.getString("siteList", "").equals("")) {
                String readData = sharedPreferences.getString("siteList", "");
                JSONArray jsonArray = new JSONArray(readData);
                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                jsonObject.put("siteName", data.getProductName());
                jsonObject.put("siteSalePrice", data.getProductSalePrice());
                jsonObject.put("sitePrice", data.getProductPrice());
                jsonObject.put("siteImg", data.getProductImage());
                jsonObject.put("siteLink", data.getProductLink());
                jsonObject.put("memoName", data.getMemoName());
                jsonObject.put("memoDate", data.getMemoData());
                jsonObject.put("memoContent", data.getMemoContent());
                jsonArray.put(jsonObject);
                dataEditor.putString("siteList", jsonArray.toString());
                dataEditor.apply();
                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}