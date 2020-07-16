package com.example.mycloset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MemoListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<Memo> memoList;
    MemoRecyclerAdapter memoRecyclerAdapter;
    private ImageButton btnAdd;
    TextView textView;
    private TextView title_content, title_content1, title_content2;
    private static String TEXT, TEXT1, TEXT2;
    static final String KEY_DATA = "KEY_DATA";
    private ImageView imageView;
    private static final int Image_Capture_Code = 1;
    Bitmap bitmap;
    byte[] byteArray;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_recyclerview);

        memoList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MemoListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        memoRecyclerAdapter = new MemoRecyclerAdapter(MemoListActivity.this, memoList);
        recyclerView.setAdapter(memoRecyclerAdapter);

//        if (intent.getByteArrayExtra("image") != null) {
//            postion = intent.getIntExtra("key", postion);
//            String strMain = intent.getStringExtra("main");
//            String strsub = intent.getStringExtra("sub");
//            String mainDate = intent.getStringExtra("maindate");
//            byteArray = getIntent().getByteArrayExtra("image");
//            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//            Memo memo = new Memo(strMain, mainDate, strsub, bitmap, 0);
////            recyclerViewAdapter.addItem(memo);
//            memoList.set(postion, memo); //RecyclerView의 첫 줄에 삽입
////            memoRecyclerAdapter.notifyDataSetChanged();
//        }

        btnAdd = findViewById(R.id.btnAdd);

//        imageView = findViewById(R.id.item_image);

//        // 인텐트 객체 생성.
//        Intent intent = new Intent(this.getIntent());
//
//        // 문자열 값을 받을 TEXT 변수 선언.
//        TEXT = intent.getStringExtra("memo_title_content");
//        TEXT1 = intent.getStringExtra("memo_date_content");
//        TEXT2 = intent.getStringExtra("memo_content_content");
//        /*
//         이전에 실행됬던 MemoChangeActivity에 입력된 값을 textview로 받기위함.
//         textView id값 설정,
//         */
//        title_content = findViewById(R.id.memo_title_content);
//        title_content1 = findViewById(R.id.memo_date_content);
//        title_content2 = findViewById(R.id.memo_content_content);
//        imageView = findViewById(R.id.Memo_Image);
//
//        // textView에 값 설정.
//        title_content.setText(TEXT);
//        title_content1.setText(TEXT1);
//        title_content2.setText(TEXT2);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //새로운 메모 작성
                Intent intent = new Intent(MemoListActivity.this, MemoChangeActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        memoRecyclerAdapter.setOnItemClickListener(new MemoRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(MemoListActivity.this, MemoChangeActivity1.class);
                String strMain = memoList.get(pos).getMainText();
                String strsub = memoList.get(pos).getSubText();
                if (byteArray != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap = memoList.get(pos).getBitmap();
                    float scale = (float) (1024 / (float) bitmap.getWidth());
                    int image_w = (int) (bitmap.getWidth() * scale);
                    int image_h = (int) (bitmap.getHeight() * scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byteArray = stream.toByteArray();
                    intent.putExtra("image", byteArray);
                }
                intent.putExtra("sub", strsub);
                intent.putExtra("main", strMain);
                intent.putExtra("key", pos);

                startActivityForResult(intent, 1);
            }
        });
// 일정 삭제(롱클릭)
//        textAdapter.setOnItemLongClickListener(new TextAdapter.OnItemLongClickListener()
//        {
//            @Override
//            public void onItemLongClick(View v, int pos)
//            {
//                //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Dialog);
//
//                String[] params = {mTime};
//                Cursor cursor = (Cursor) dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME, null, "date=?", params, null, null, null);
//                cursor.moveToPosition(pos);
//                final int id = cursor.getInt(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID));
//
//                builder.setTitle("일정 삭제");
//                builder.setMessage("일정을 삭제하시겠습니까?");
//                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        SQLiteDatabase db = dbHelper.getWritableDatabase();
//                        int deletedCount = db.delete(MemoContract.MemoEntry.TABLE_NAME,MemoContract.MemoEntry._ID+"="+id,null);
//
//                        if(deletedCount==0)
//                        {
//                            Toast.makeText(getActivity(), "삭제 실패", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            getMemoCursor();
//                            Toast.makeText(getActivity(), "일정이 삭제되었습니다", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                builder.setNegativeButton("취소",null);
//                builder.show();
//            }
//        });

//        title_content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MemoListActivity.this, MemoChangeActivity.class);
//                // 제목 내용을 포함한 에디트 텍스트 주소 설정.
//                title_content = findViewById(R.id.memo_title_content);
//                // 에디트 텍스트에 입력된 문자열 값을 받는 TEXT 변수.
//                TEXT = title_content.getText().toString();
//                //TEXT 변수 값을 putExtra로 인텐트.
//                intent.putExtra("memo_title_content", TEXT);
//
//                // 제목 내용을 포함한 에디트 텍스트 주소 설정.
//                title_content1 = findViewById(R.id.memo_date_content);
//                // 에디트 텍스트에 입력된 문자열 값을 받는 TEXT 변수.
//                TEXT1 = title_content1.getText().toString();
//                //TEXT 변수 값을 putExtra로 인텐트.
//                intent.putExtra("memo_date_content", TEXT1);
//
//                // 제목 내용을 포함한 에디트 텍스트 주소 설정.
//                title_content2 = findViewById(R.id.memo_content_content);
//                // 에디트 텍스트에 입력된 문자열 값을 받는 TEXT 변수.
//                TEXT2 = title_content2.getText().toString();
//                //TEXT 변수 값을 putExtra로 인텐트.
//                intent.putExtra("memo_content_content", TEXT2);
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
//                startActivityForResult(intent, Image_Capture_Code);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (data != null) {
                String strMain = data.getStringExtra("main");
                String strsub = data.getStringExtra("sub");
                String mainDate = data.getStringExtra("maindate");
                byteArray = data.getByteArrayExtra("image");
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                Memo memo = new Memo(strMain, mainDate, strsub, bitmap, 0);
                memoRecyclerAdapter.notifyDataSetChanged();
                memoList.add(0, memo); //RecyclerView의 첫 줄에 삽입
            }
        }
        else if (requestCode == 1) {
            if (data != null) {
                String strMain = data.getStringExtra("main");
                String strsub = data.getStringExtra("sub");
                String mainDate = data.getStringExtra("maindate");
                pos = data.getIntExtra("key", pos);

                byteArray = data.getByteArrayExtra("image");
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                Memo memo = new Memo(strMain, mainDate, strsub, bitmap, 0);
                // 아래 주석을 사용하게되면 수정된 메모 내용이 최상단에 노출되게 할 수 있음( 최신 수정 날짜 순으로 정렬되는 느낌)
//                memoList.remove(pos); //RecyclerView의 첫 줄에 삽입
//                memoList.add(0, memo);
//                memoRecyclerAdapter.notifyDataSetChanged();
                memoList.set(pos, memo);
                memoRecyclerAdapter.notifyDataSetChanged();
            }
        } else if (resultCode == RESULT_CANCELED) {
        }
    }
}