package com.example.mycloset;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoChangeActivity extends Activity {

    private static final String TAG = "";
    String TEXT, TEXT1, TEXT2;
    private EditText editText, editText1, editText2;
    byte[] byteArray;
    private ArrayList<ProductSite> productSites;

    String name, img, str, maindate, substr, siteName, siteSalePrice, sitePrice, siteImg, siteLink, memoName, memoDate, memoContent;
    Date date;
    int Key;

    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int Image_Capture_Code = 1;
    String mCurrentPhotoPath;
    public ImageView imageView;
    public Bitmap bitmap;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 메모 수정할 수 있는 레이아웃 실행.
        setContentView(R.layout.memo_dialog);

        editText = (EditText) findViewById(R.id.edit_memo_title_content);

        editText2 = (EditText) findViewById(R.id.edit_memo_content_content1);

        // 인텐트 객체 선언.
        Intent intent = new Intent(this.getIntent());

        siteName = intent.getStringExtra("name");
        siteSalePrice = intent.getStringExtra("salePrice");
        sitePrice = intent.getStringExtra("price");
        siteImg = intent.getStringExtra("img");
        siteLink = intent.getStringExtra("link");
        memoName = intent.getStringExtra("memoName");
        memoDate = intent.getStringExtra("memoDate");
        memoContent = intent.getStringExtra("memoContent");
        Key = intent.getIntExtra("Key1", Key);

        productSites = new ArrayList<>();

        final ProductSite productSite = new ProductSite(siteName, siteSalePrice, sitePrice, siteImg, siteLink, memoName, memoDate,memoContent);
        productSites.add(productSite);

//        readData();

        // 에디트텍스트뷰에 추가될 문자열 값을 받는 함수
        TEXT = intent.getStringExtra("main");
        TEXT1 = intent.getStringExtra("memo_date_content");
        TEXT2 = intent.getStringExtra("sub");

        Key = intent.getIntExtra("Key", Key);

        // 문자열 값을 받을 에디트텍스트 주소 설정.
        editText = (EditText) findViewById(R.id.edit_memo_title_content);
        // getStringExtra으로 받은 문자열 값 설정.
        editText.setText(memoName);

        // 문자열 값을 받을 에디트텍스트 주소 설정.
        editText1 = (EditText) findViewById(R.id.edit_memo_date_content);
        // getStringExtra으로 받은 문자열 값 설정.
        editText1.setText(memoDate);

        editText2 = (EditText) findViewById(R.id.edit_memo_content_content1);
        editText2.setText(memoContent);

        // 완료버튼 객체 선언 및 주소 설정.
        Button done = (Button) findViewById(R.id.done);
        //카메라버튼 객체 선언 및 id 설정.
//        ImageButton camera = (ImageButton) findViewById(R.id.camera);

        findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str = editText.getText().toString();
                substr = editText2.getText().toString();

                if (str.length() > 0) {
                    date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    maindate = sdf.format(date);

                    ProductSite productSites1 = new ProductSite(siteName, siteSalePrice, sitePrice, siteImg, siteLink, str, maindate, substr);

                    maindate = editText1.getText().toString();

                    modifyData(productSites1, Key);
                    Toast.makeText(MemoChangeActivity.this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
//                    Intent intent = new Intent(MemoChangeActivity.this, MemoListActivity.class);
//                    intent.putExtra("main", str);
//                    intent.putExtra("maindate", maindate);
//                    intent.putExtra("sub", substr);
//
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//                    float scale = (float) (1024 / (float) bitmap.getWidth());
//                    int image_w = (int) (bitmap.getWidth() * scale);
//                    int image_h = (int) (bitmap.getHeight() * scale);
//                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
//                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                    byteArray = stream.toByteArray();
//                    intent.putExtra("image", byteArray);
//                    setResult(0, intent);
//                    finish();
                }
            }
        });
        // 완료버튼 클릭시 실행 될 리스너.
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 버튼 클릭시 해당 액티비티에서 다음으로 실행될 액티비티 설정.
//                Intent intent = new Intent(MemoChangeActivity.this, MemoListActivity.class);
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                // 제목 내용을 포함한 에디트 텍스트 주소 설정.
//                editText = (EditText) findViewById(R.id.memo_title_content);
//                // 에디트 텍스트에 입력된 문자열 값을 받는 TEXT 변수.
//                TEXT = editText.getText().toString();
//                //TEXT 변수 값을 putExtra로 인텐트.
//                intent.putExtra("memo_title_content", TEXT);
//
//                editText1 = (EditText) findViewById(R.id.memo_date_content1);
//                // 에디트 텍스트에 입력된 문자열 값을 받는 TEXT 변수.
//                TEXT1 = editText1.getText().toString();
//                //TEXT 변수 값을 putExtra로 인텐트.
//                intent.putExtra("memo_date_content", TEXT1);
//
//                editText2 = (EditText) findViewById(R.id.memo_content_content1);
//                // 에디트 텍스트에 입력된 문자열 값을 받는 TEXT 변수.
//                TEXT2 = editText2.getText().toString();
//                //TEXT 변수 값을 putExtra로 인텐트.
//                intent.putExtra("memo_content_content", TEXT2);
//                imageView = (ImageView) findViewById(R.id.imageView2);
//
//                if (bitmap != null) {
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//                    float scale = (float) (1024 / (float) bitmap.getWidth());
//                    int image_w = (int) (bitmap.getWidth() * scale);
//                    int image_h = (int) (bitmap.getHeight() * scale);
//                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
//                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                    byteArray = stream.toByteArray();
//                    intent.putExtra("image", byteArray);
//                }
//
//                // 액티비티 시작.
//                startActivityForResult(intent, REQUEST_TAKE_ALBUM);
//                // 현재 액티비티 종료.
//                finish();
//            }
//        });

//        // 삭제 버튼을 누르면 실행시킬 클릭 리스너
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MemoChangeActivity.this, MemoListActivity.class);
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                startActivityForResult(intent, Image_Capture_Code);
//                finish();
//            }
//        });

        // 카메라 버튼을 누르면 실행시킬 클릭 리스트너
//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu pop = new PopupMenu(getApplicationContext(), view);
//                getMenuInflater().inflate(R.menu.main_menu, pop.getMenu());
//
//                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//                            case R.id.one:
//                                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(cInt, Image_Capture_Code);
//                                break;
//
//                            case R.id.two:
//                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                intent.setType("image/*");
//                                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//                                startActivityForResult(intent, REQUEST_TAKE_ALBUM);
//                                break;
//                        }
//                        return true;
//                    }
//                });
//                pop.show();
////                checkPermission();
//            }
//        });
    }

    //    private void checkPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
//                    (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))) {
//                new AlertDialog.Builder(this).setTitle("알림").setMessage("저장소 권한이 거부되었습니다.").setNeutralButton("설정", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        intent.setData(Uri.parse("package: " + getPackageName()));
//                        startActivity(intent);
//                    }
//                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                }).setCancelable(false).create().show();
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
//            }
//        }
//    }

    //SharedPreferences 에 Data 저장 flow
    //JSONObject > JSONArray > SharedPreferences 저장
    //활동계획 게시글을 등록하고 SharedPreferences 에 저장.
    public void saveData(MemoEdit data) {
        JSONObject jsonObject = new JSONObject();
        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //현재 저장되어있는 데이터를 읽는다.
        try {
            //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
            if (sharedPreferences.getString("siteList", "").equals("")) {
                JSONArray jsonArray = new JSONArray();
                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                jsonObject.put("memoName", data.getEditText());
                jsonObject.put("memoDate", data.getEditDate());
                jsonObject.put("memoContent", data.getEditcontent());
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
                jsonObject.put("memoName", data.getEditText());
                jsonObject.put("memoDate", data.getEditDate());
                jsonObject.put("memoContent", data.getEditcontent());
                jsonArray.put(jsonObject);
                dataEditor.putString("siteList", jsonArray.toString());
                dataEditor.apply();
                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
//    public void saveMemo(MemoEdit data) {
//        JSONObject jsonObject = new JSONObject();
//        SharedPreferences sharedPreferences = getSharedPreferences("memo", MODE_PRIVATE);
//        //현재 저장되어있는 데이터를 읽는다.
//        try {
//            //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
//            if (sharedPreferences.getString("memoList", "").equals("")) {
//                JSONArray jsonArray = new JSONArray();
//                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
//                jsonObject.put("memoTitle", data.getEditText());
//                jsonObject.put("memoDate", data.getEditDate());
//                jsonObject.put("memoContent", data.getEditcontent());
//                jsonArray.put(jsonObject);
//                dataEditor.putString("memoList", jsonArray.toString());
//                dataEditor.apply();
//                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
//                //"셀렉리스트" : [{"이미지유알엘":" ","제품명":" ","사이트링크":""}, == index = 0
//                //              {"이미지유알엘":" ","제품명":" ","사이트링크":""}, == index = 1
//                //              {"planDate":"","time":"","contents":"85285285285","maxMemberNumber":""}] == index = 2
//                //"planList"라는 키 값으로 저장되어있는 데이터가 있을때
//            } else if (!sharedPreferences.getString("memoList", "").equals("")) {
//                String readData = sharedPreferences.getString("memoList", "");
//                JSONArray jsonArray = new JSONArray(readData);
//                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
//                jsonObject.put("memoTitle", data.getEditText());
//                jsonObject.put("memoDate", data.getEditDate());
//                jsonObject.put("memoContent", data.getEditcontent());
//                jsonArray.put(jsonObject);
//                dataEditor.putString("memoList", jsonArray.toString());
//                dataEditor.apply();
//                Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

        @SuppressLint("HandlerLeak")
    public void readData() {
            SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
            //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
            String readData = sharedPreferences.getString("siteList", "");
            //SharedPreferences 에 "planLsit"에 저장되어 있는 데이터를 모두 읽어온다.
            //데이터 형식은 Array
            try {
                JSONArray jsonArray = new JSONArray(readData);
                //SharedPreferences 에서 불러온 데이터를 JSONArray 형싱으로 읽는다.
                for (int i = 0; i < jsonArray.length(); i++) {
                    //jsonArray 에 포함되어 있는 리스트의 갯수를 파악하여 객체형태로 변경한다.
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    productSites.add(new ProductSite(
                            jsonObject.getString("siteName"),
                            jsonObject.getString("sitePrice"),
                            jsonObject.getString("siteSalePrice"),
                            jsonObject.getString("siteImg"),
                            jsonObject.getString("siteLink"),
                            jsonObject.getString("memoName"),
                            jsonObject.getString("memoDate"),
                            jsonObject.getString("memoContent")
                    ));
                    Log.d(TAG, jsonObject.toString() + "제이슨 리스트 > 오브젝트 변환 값");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //쉐어드에 저장되어 있는 파일읅 읽는 함수.
    public void modifyData(ProductSite data, int position) {
        int checkedItemPosition = position;
        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
        String readData = sharedPreferences.getString("siteList", "");
            try {
                //jsonArray.size()로 사이즈 확인가능
                JSONArray jsonArray = new JSONArray(readData);
                JSONObject jsonObject = new JSONObject();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                    jsonObject.put("siteName", data.getProductSiteName());
                    jsonObject.put("siteSalePrice", data.getProductSiteSalePrice());
                    jsonObject.put("sitePrice", data.getProductSitePrice());
                    jsonObject.put("siteImg", data.getProductSiteImage());
                    jsonObject.put("siteLink", data.getProductSiteLink());
                    jsonObject.put("memoName", data.getMemoName());
                    jsonObject.put("memoDate", data.getMemoDate());
                    jsonObject.put("memoContent", data.getMemoContent());
                    jsonArray.put(checkedItemPosition, jsonObject);
                    dataEditor.putString("siteList", jsonArray.toString());
                    dataEditor.apply();
                    Log.d(TAG, jsonArray.toString() + "메모 저장 확인");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_ALBUM) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                    bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {

                }
            }
        } else if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 1) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}