package com.example.mycloset;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoChangeActivity1 extends AppCompatActivity {

    String TEXT, TEXT1, TEXT2;
    private EditText editText, editText1, editText2;
    byte[] byteArray;
    private ArrayList<Memo> memoList;
    RecyclerView recyclerView;
    MemoRecyclerAdapter memoRecyclerAdapter;
    String str;
    String maindate;
    String substr;
    Date date;
    int test;

    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int Image_Capture_Code = 1;
    public ImageView imageView;
    public Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 메모 수정할 수 있는 레이아웃 실행.
        setContentView(R.layout.memo_edit1);

        editText = (EditText) findViewById(R.id.memo_title_content);

        editText2 = (EditText) findViewById(R.id.memo_content_content1);

        // 인텐트 객체 선언.
        Intent intent = new Intent(this.getIntent());

        test = intent.getIntExtra("key", test);
        // 에디트텍스트뷰에 추가될 문자열 값을 받는 함수
        TEXT = intent.getStringExtra("main");
//        TEXT1 = intent.getStringExtra("memo_date_content");
        TEXT2 = intent.getStringExtra("sub");

        // 문자열 값을 받을 에디트텍스트 주소 설정.
        editText = (EditText) findViewById(R.id.memo_title_content);
        // getStringExtra으로 받은 문자열 값 설정.
        editText.setText(TEXT);

//        editText1 = (EditText) findViewById(R.id.memo_date_content1);
//        editText1.setText(TEXT1);

        editText2 = (EditText) findViewById(R.id.memo_content_content1);
        editText2.setText(TEXT2);

        imageView = findViewById(R.id.imageView2);

        byteArray = getIntent().getByteArrayExtra("image");
        if (byteArray != null) {
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(bitmap);
        }

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

                    Intent intent = new Intent(MemoChangeActivity1.this, MemoListActivity.class);
                    intent.putExtra("main", str);
                    intent.putExtra("maindate", maindate);
                    intent.putExtra("sub", substr);
                    intent.putExtra("key", test);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    float scale = (float) (1024 / (float) bitmap.getWidth());
                    int image_w = (int) (bitmap.getWidth() * scale);
                    int image_h = (int) (bitmap.getHeight() * scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byteArray = stream.toByteArray();
                    intent.putExtra("image", byteArray);

                    setResult(1, intent);
                    finish();
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

//        // 카메라 버튼을 누르면 실행시킬 클릭 리스트너
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
//                checkPermission();
//            }
//        });
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))) {
                new AlertDialog.Builder(this).setTitle("알림").setMessage("저장소 권한이 거부되었습니다.").setNeutralButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package: " + getPackageName()));
                        startActivity(intent);
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setCancelable(false).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
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
