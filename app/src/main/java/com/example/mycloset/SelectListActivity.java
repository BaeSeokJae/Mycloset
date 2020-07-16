package com.example.mycloset;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectListActivity extends AppCompatActivity {

    private static final String TAG = "";
    ImageView imageView;
    Button edit, editDone, delete, deleteAll;
    byte[] byteArray;
    Bitmap bitmap;
    private static final int send = 1;
    RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;

    private ArrayList<Select> selectList = new ArrayList<>();
    Select select;
    int pos;
    SelectRecyclerAdapter selectRecyclerAdapter = new SelectRecyclerAdapter(SelectListActivity.this, selectList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_recyclerview);

        readData();

        imageView = findViewById(R.id.select_Image);
        TextView textView = (TextView) findViewById(R.id.select_Text);
        edit = findViewById(R.id.edit);
        editDone = findViewById(R.id.edit_done);
        delete = findViewById(R.id.delete);
        deleteAll = findViewById(R.id.delete_All);

        recyclerView = findViewById(R.id.select_recyclerview);
        int numberOfColumns = 3; // 한줄에 3개의 컬럼을 추가합니다.
        mGridLayoutManager = new GridLayoutManager(SelectListActivity.this, numberOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        recyclerView.setHasFixedSize(true);

        selectRecyclerAdapter = new SelectRecyclerAdapter(SelectListActivity.this, selectList);
        recyclerView.setAdapter(selectRecyclerAdapter);
        selectRecyclerAdapter.notifyDataSetChanged();

        selectRecyclerAdapter.setOnItemClickListener(new SelectRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int product) {
                pos = product;
                PopupMenu pop = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.main_menu, pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.one:
                                Intent intent = new Intent(SelectListActivity.this, MemoChangeActivity.class);
                                String Productname = selectList.get(pos).getSelectName();
                                String ProductImage = selectList.get(pos).getSelectImage();
                                String ProductSalePrice = selectList.get(pos).getSelectSalePrice();
                                String ProductPrice = selectList.get(pos).getSelectPrice();
                                String ProductLink = selectList.get(pos).getSelectLink();
                                String memoName = selectList.get(pos).getMemoName();
                                String memoDate = selectList.get(pos).getMemoData();
                                String memoContent = selectList.get(pos).getMemoContent();
                                intent.putExtra("memoName", memoName);
                                intent.putExtra("memoDate", memoDate);
                                intent.putExtra("memoContent", memoContent);
                                intent.putExtra("name", Productname);
                                intent.putExtra("img", ProductImage);
                                intent.putExtra("salePrice", ProductSalePrice);
                                intent.putExtra("price", ProductPrice);
                                intent.putExtra("link", ProductLink);
                                intent.putExtra("Key1", pos);
                                startActivity(intent);
                                break;

                            case R.id.two:
                                Intent intents = new Intent(SelectListActivity.this, ProductSiteActivity.class);
                                String Productname1 = selectList.get(pos).getSelectName();
                                String ProductImage1 = selectList.get(pos).getSelectImage();
                                String ProductSalePrice1 = selectList.get(pos).getSelectSalePrice();
                                String ProductPrice1 = selectList.get(pos).getSelectPrice();
                                String ProductLink1 = selectList.get(pos).getSelectLink();
                                intents.putExtra("name", Productname1);
                                intents.putExtra("img", ProductImage1);
                                intents.putExtra("salePrice", ProductSalePrice1);
                                intents.putExtra("price", ProductPrice1);
                                intents.putExtra("link", ProductLink1);
                                intents.putExtra("Key1", pos);
                                startActivity(intents);
                                break;
                        }
                        return true;
                    }
                });
                pop.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRecyclerAdapter.select1 = 1;
                edit.setVisibility(View.GONE);
                editDone.setVisibility(View.VISIBLE);
                deleteAll.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);

                selectRecyclerAdapter.notifyDataSetChanged();
            }
        });

        editDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRecyclerAdapter.select1 = 0;
                edit.setVisibility(View.VISIBLE);
                editDone.setVisibility(View.GONE);
                deleteAll.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);

                selectRecyclerAdapter.notifyDataSetChanged();
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRecyclerAdapter.select1 = 0;
                edit.setVisibility(View.VISIBLE);
                editDone.setVisibility(View.GONE);
                deleteAll.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                DialogClick(v);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRecyclerAdapter.select1 = 0;
                edit.setVisibility(View.VISIBLE);
                editDone.setVisibility(View.GONE);
                deleteAll.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);

                ArrayList<String> selectName = new ArrayList<>();
                ArrayList<String> selectPrice = new ArrayList<>();
                ArrayList<String> selectSalePrice = new ArrayList<>();
                ArrayList<String> selectImg = new ArrayList<>();
                ArrayList<String> selectLink = new ArrayList<>();
                ArrayList<String> selectMemo = new ArrayList<>();
                ArrayList<String> selectMemodata = new ArrayList<>();
                ArrayList<String> selectMemoContent = new ArrayList<>();
                ArrayList<Select> stList = (selectRecyclerAdapter).getselectData();

                for (int i = 0; i < stList.size(); i++) {
                    select = stList.get(i);
                    if (!select.isSelected()) {
                        if (select.getMemoName() != "") {
                            selectName.add(select.getSelectName());
                            selectPrice.add(select.getSelectPrice());
                            selectSalePrice.add(select.getSelectSalePrice());
                            selectImg.add(select.getSelectImage());
                            selectLink.add(select.getSelectLink());
                            selectMemo.add(select.getMemoName());
                            selectMemodata.add(select.getMemoData());
                            selectMemoContent.add(select.getMemoContent());
                        }
                        else {
                            selectName.add(select.getSelectName());
                            selectPrice.add(select.getSelectPrice());
                            selectSalePrice.add(select.getSelectSalePrice());
                            selectImg.add(select.getSelectImage());
                            selectLink.add(select.getSelectLink());
                            selectMemo.add("");
                            selectMemodata.add("");
                            selectMemoContent.add("");
                        }
                    }
                }
                deleteData();
                for (int i = 0; i < selectName.size(); i++) {
                    Select test = new Select("","","","", "","","","");
                    test.setSelectName(selectName.get(i));
                    test.setSelectPrice(selectPrice.get(i));
                    test.setSelectSalePrice(selectSalePrice.get(i));
                    test.setSelectImage(selectImg.get(i));
                    test.setSelectLink(selectLink.get(i));
                    test.setMemoName(selectMemo.get(i));
                    test.setMemoData(selectMemodata.get(i));
                    test.setMemoContent(selectMemoContent.get(i));

                    saveData(test);

                }
                readData();
                selectRecyclerAdapter.notifyDataSetChanged();
                Toast.makeText(SelectListActivity.this, "선택하신 항목이 삭제되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    //SharedPreferences 에 저장 되어 있는 데이터를 확인하여 리스트뷰의 리스트에 대입.
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
                selectList.add(new Select(jsonObject.getString("siteName"),
                        jsonObject.getString("sitePrice"), jsonObject.getString("siteSalePrice"), jsonObject.getString("siteImg"),
                        jsonObject.getString("siteLink"), jsonObject.getString("memoName"), jsonObject.getString("memoDate"), jsonObject.getString("memoContent")
                ));
                Log.d(TAG, jsonObject.toString() + "제이슨 리스트 > 오브젝트 변환 값");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //선택된 아이템을 SharedPreferences 에서 삭제.
    public void deleteData() {
        //PlanListAdapter 로 부터 받아온 item position 값을 Int 데이터 타입으로 변경.
        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
        String readData = sharedPreferences.getString("siteList", "");
        try {
            //jsonArray.size()로 사이즈 확인가능
            JSONArray jsonArray = new JSONArray(readData);
            SharedPreferences.Editor dataEditor = sharedPreferences.edit();
            //인덱스로 선택된 리스트를 값을 제거 후 저장.
            dataEditor.clear();
            dataEditor.apply();
            selectList.clear();
            selectRecyclerAdapter.notifyDataSetChanged();
            Log.d(TAG, jsonArray.toString() + "현재 파일 전채 내용.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void DialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전체 삭제");
        builder.setMessage("해당 내용들을 전체 삭제 하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "전체 삭제 되었습니다", Toast.LENGTH_LONG).show();
                deleteData();
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //선택된 아이템을 SharedPreferences 에서 삭제.
    public void deleteData1(int position) {
        //TODO : 만약 2라면.
        //TODO : 이렇게 지워야한 sp.edit().remove("clientToken").commit();
        //PlanListAdapter 로 부터 받아온 item position 값을 Int 데이터 타입으로 변경.
        //position은 0부터시작
        int checkedItemPosition = position;
        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //planListBackup 폴더에서 "planList"로 저장되어있는 리스트항목을 읽는다.
        String readData = sharedPreferences.getString("siteList", "");
        try {
            //jsonArray.size()로 사이즈 확인가능
            JSONArray jsonArray = new JSONArray(readData);
            //삭제하고 싶은 리스트를 인덱스(itemPosition)으로 삭제한다.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                jsonArray.remove(checkedItemPosition);
            }
            Log.d(TAG, jsonArray.toString() + "현재 파일 전채 내용.");
            SharedPreferences.Editor dataEditor = sharedPreferences.edit();
            //인덱스로 선택된 리스트를 값을 제거 후 저장.
            dataEditor.putString("stieList", jsonArray.toString());
            dataEditor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveData(Select data) {
        JSONObject jsonObject = new JSONObject();
        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
        //현재 저장되어있는 데이터를 읽는다.
        try {
            //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
            if (sharedPreferences.getString("siteList", "").equals("")) {
//                Toast.makeText(context, "이미저장", Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = new JSONArray();
                SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                jsonObject.put("siteName", data.getSelectName());
                jsonObject.put("siteSalePrice", data.getSelectSalePrice());
                jsonObject.put("sitePrice", data.getSelectPrice());
                jsonObject.put("siteImg", data.getSelectImage());
                jsonObject.put("siteLink", data.getSelectLink());
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
                jsonObject.put("siteName", data.getSelectName());
                jsonObject.put("siteSalePrice", data.getSelectSalePrice());
                jsonObject.put("sitePrice", data.getSelectPrice());
                jsonObject.put("siteImg", data.getSelectImage());
                jsonObject.put("siteLink", data.getSelectLink());
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