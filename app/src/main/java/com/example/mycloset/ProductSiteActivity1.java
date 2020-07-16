package com.example.mycloset;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductSiteActivity1 extends AppCompatActivity {

    private static final String TAG = null;
    WebView mWebView;
    private ArrayList<ProductSite> productSites;
    int pos;
    int product;
    String siteName, siteSalePrice, sitePrice, siteImg, siteLink, siteLinks;
    String http = "https://www.wizwid.com/CSW/handler/wizwid/kr/";
    Button select, memo;
    Context context;
    private SharedPreferences sharedPreferences;
    private SelectListActivity selectListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_site);

        Intent intent = new Intent(this.getIntent());
        product = intent.getIntExtra("Key1", product);
        siteName = intent.getStringExtra("name");
        siteSalePrice = intent.getStringExtra("salePrice");
        sitePrice = intent.getStringExtra("price");
        siteImg = intent.getStringExtra("img");
        siteLink = intent.getStringExtra("link");

        productSites = new ArrayList<>();

        final ProductSite productSite = new ProductSite(siteName, siteSalePrice, sitePrice, siteImg, siteLink, "", "","");
        productSites.add(productSite);

        mWebView = (WebView) findViewById(R.id.product_webView);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings mWebSettings = mWebView.getSettings();
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDisplayZoomControls(false);

        // 웹뷰 멀티 터치 가능하게 (줌기능)
        mWebSettings.setBuiltInZoomControls(true);   // 줌 아이콘 사용
        mWebSettings.setSupportZoom(true);

        // 웹뷰 사이즈에 맞게 화면 출력
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.loadUrl(siteLink);

        select = findViewById(R.id.select1);
        select.setVisibility(View.GONE);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductSiteActivity1.this, "찜하기 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                saveData(productSite);
            }
        });
        memo = findViewById(R.id.select2);
        memo.setVisibility(View.GONE);
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductSiteActivity1.this, MemoChangeActivity2.class);
                intent.putExtra("name", siteName);
                intent.putExtra("salePrice", siteSalePrice);
                intent.putExtra("price", sitePrice);
                intent.putExtra("img", siteImg);
                intent.putExtra("link",siteLink);
                startActivity(intent);
            }
        });
    }

    //SharedPreferences 에 Data 저장 flow
    //JSONObject > JSONArray > SharedPreferences 저장
    //활동계획 게시글을 등록하고 SharedPreferences 에 저장.
    public void saveData(ProductSite data) {
        JSONObject jsonObject = new JSONObject();
        sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //현재 저장되어있는 데이터를 읽는다.
        try {
            //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
            if (sharedPreferences.getString("siteList", "").equals("")) {
//                Toast.makeText(context, "이미저장", Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = new JSONArray();
                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                jsonObject.put("siteName", data.getProductSiteName());
                jsonObject.put("siteSalePrice", data.getProductSiteSalePrice());
                jsonObject.put("sitePrice", data.getProductSitePrice());
                jsonObject.put("siteImg", data.getProductSiteImage());
                jsonObject.put("siteLink", data.getProductSiteLink());
                jsonObject.put("memoName", data.getMemoName());
                jsonObject.put("memoDate", data.getMemoDate());
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
                jsonObject.put("siteName", data.getProductSiteName());
                jsonObject.put("siteSalePrice", data.getProductSiteSalePrice());
                jsonObject.put("sitePrice", data.getProductSitePrice());
                jsonObject.put("siteImg", data.getProductSiteImage());
                jsonObject.put("siteLink", data.getProductSiteLink());
                jsonObject.put("memoName", data.getMemoName());
                jsonObject.put("memoDate", data.getMemoDate());
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
//    @SuppressLint("HandlerLeak")
//    public void readData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
//        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
//        String readData = sharedPreferences.getString("siteList", "");
//        //SharedPreferences 에 "planLsit"에 저장되어 있는 데이터를 모두 읽어온다.
//        //데이터 형식은 Array
//        try {
//            JSONArray jsonArray = new JSONArray(readData);
//            //SharedPreferences 에서 불러온 데이터를 JSONArray 형싱으로 읽는다.
//            for (int i = 0; i < jsonArray.length(); i++) {
//                //jsonArray 에 포함되어 있는 리스트의 갯수를 파악하여 객체형태로 변경한다.
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                MemoList.add(new Select(jsonObject.getString("siteName"),
//                        jsonObject.getString("sitePrice"), jsonObject.getString("siteSalePrice"), jsonObject.getString("siteImg"),
//                        jsonObject.getString("siteLink")
//                ));
//                Log.d(TAG, jsonObject.toString() + "제이슨 리스트 > 오브젝트 변환 값");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
//    //SharedPreferences 에 Data 저장 flow
//    //JSONObject > JSONArray > SharedPreferences 저장
//    //활동계획 게시글을 등록하고 SharedPreferences 에 저장.
//    public void saveData(PlanListData data) {
//        JSONObject jsonObject = new JSONObject();
//        sharedPreferences = getSharedPreferences("planListBackup", MODE_PRIVATE);
//        //현재 저장되어있는 데이터를 읽는다.
//        try {
//            //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
//            if (sharedPreferences.getString("planList", "").equals("")) {
//                JSONArray jsonArray = new JSONArray();
//                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
//                jsonObject.put("planDate", data.getPlanDate());
//                jsonObject.put("time", data.getTime());
//                jsonObject.put("contents", data.getContents());
//                jsonObject.put("maxMemberNumber", data.getMaxMemberNumber());
//                jsonArray.put(jsonObject);
//                dataEditor.putString("planList", jsonArray.toString());
//                dataEditor.apply();
//                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
//                //"셀렉리스트" : [{"이미지유알엘":" ","제품명":" ","사이트링크":""}, == index = 0
//                //              {"이미지유알엘":" ","제품명":" ","사이트링크":""}, == index = 1
//                //              {"planDate":"","time":"","contents":"85285285285","maxMemberNumber":""}] == index = 2
//                //"planList"라는 키 값으로 저장되어있는 데이터가 있을때
//            } else if (!sharedPreferences.getString("planList", "").equals("")) {
//                String readData = sharedPreferences.getString("planList", "");
//                JSONArray jsonArray = new JSONArray(readData);
//                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
//                jsonObject.put("planDate", data.getPlanDate());
//                jsonObject.put("time", data.getTime());
//                jsonObject.put("contents", data.getContents());
//                jsonObject.put("maxMemberNumber", data.getMaxMemberNumber());
//                jsonArray.put(jsonObject);
//                dataEditor.putString("planList", jsonArray.toString());
//                dataEditor.apply();
//                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

//    //SharedPreferences 에 저장 되어 있는 데이터를 확인하여 리스트뷰의 리스트에 대입.
//    @SuppressLint("HandlerLeak")
//    public void readData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("planListBackup", MODE_PRIVATE);
//        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
//        String readData = sharedPreferences.getString("planList", "");
//        //SharedPreferences 에 "planLsit"에 저장되어 있는 데이터를 모두 읽어온다.
//        //데이터 형식은 Array
//        try {
//            JSONArray jsonArray = new JSONArray(readData);
//            //SharedPreferences 에서 불러온 데이터를 JSONArray 형싱으로 읽는다.
//            for (int i = 0; i < jsonArray.length(); i++) {
//                //jsonArray 에 포함되어 있는 리스트의 갯수를 파악하여 객체형태로 변경한다.
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                planDate = jsonObject.getString("planDate");
//                planItemList.add(new PlanListData(jsonObject.getString("planDate"),
//                        jsonObject.getString("time"),
//                        jsonObject.getString("contents"),
//                        jsonObject.getString("maxMemberNumber")));
//                Log.d(TAG, jsonObject.toString() + "제이슨 리스트 > 오브젝트 변환 값");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        thread = new Thread() {
//            @Override
//            public void run() {
//                Message msg = new Message();
//                msg.arg1 = planItemList.size();
//                handler.sendMessage(msg);
//            }
//        };
//        thread.start();
//
//        handler = new Handler() {
//            @SuppressLint("HandlerLeak")
//            @Override
//            //루퍼에의해 실행되는 함수.
//            //송신 쓰레드에서 전달받은 파라메터를 통해 코드가 진행됨.
//            public void handleMessage(@NonNull Message msg) {
//                int count = msg.arg1;
//                Log.d(TAG, String.valueOf(count));
//                counter = findViewById(R.id.counterText);
//                counter.setText(String.valueOf(count));
//            }
//        };
//        thread.interrupt();
//    }
//
//    //선택된 아이템을 SharedPreferences 에서 삭제.
//    public void deleteData(String position) {
//        //TODO : 만약 2라면.
//        //TODO : 이렇게 지워야한 sp.edit().remove("clientToken").commit();
//        //PlanListAdapter 로 부터 받아온 item position 값을 Int 데이터 타입으로 변경.
//        //position은 0부터시작
//        int checkedItemPosition = Integer.parseInt(position);
//        SharedPreferences sharedPreferences = getSharedPreferences("planListBackup", MODE_PRIVATE);
//        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
//        String readData = sharedPreferences.getString("planList", "");
//        try {
//            //jsonArray.size()로 사이즈 확인가능
//            JSONArray jsonArray = new JSONArray(readData);
//            //삭제하고 싶은 리스트를 인덱스(itemPosition)으로 삭제한다.
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                jsonArray.remove(checkedItemPosition);
//            }
//            Log.d(TAG, jsonArray.toString() + "현재 파일 전채 내용.");
//            SharedPreferences.Editor dataEditor = sharedPreferences.edit();
//            //인덱스로 선택된 리스트를 값을 제거 후 저장.
//            dataEditor.putString("planList", jsonArray.toString());
//            dataEditor.apply();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //쉐어드에 저장되어 있는 파일읅 읽는 함수.
//    public void modifyData(PlanListData data, String position) {
//        int checkedItemPosition = Integer.parseInt(position);
//        SharedPreferences sharedPreferences = getSharedPreferences("planListBackup", MODE_PRIVATE);
//        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
//        String readData = sharedPreferences.getString("planList", "");
//        try {
//            //jsonArray.size()로 사이즈 확인가능
//            JSONArray jsonArray = new JSONArray(readData);
//            JSONObject jsonObject = new JSONObject();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
//                jsonObject.put("planDate", data.getPlanDate());
//                jsonObject.put("time", data.getTime());
//                jsonObject.put("contents", data.getContents());
//                jsonObject.put("maxMemberNumber", data.getMaxMemberNumber());
//                jsonArray.put(checkedItemPosition, jsonObject);
//                dataEditor.putString("planList", jsonArray.toString());
//                dataEditor.apply();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
