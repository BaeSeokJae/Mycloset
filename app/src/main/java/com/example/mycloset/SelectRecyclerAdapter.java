package com.example.mycloset;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class SelectRecyclerAdapter extends RecyclerView.Adapter<SelectRecyclerAdapter.ItemViewHolder> {

    int pos, select1, test;
    private ArrayList<Select> selectData;
    private Context mContext;
    Select select;
    SelectRecyclerAdapter.ItemViewHolder itemViewHolder;

    // 커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public SelectRecyclerAdapter(Context context, ArrayList<Select> selectData) {
        this.selectData = selectData;
        setHasStableIds(true);
        mContext = context;
    }

    @NonNull
    @Override
    public SelectRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_item, viewGroup, false);
        return new SelectRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (null != selectData ? selectData.size() : 0);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectRecyclerAdapter.ItemViewHolder itemViewHolder, int Postion) {
        pos = Postion;
        final Select select = selectData.get(Postion);
        itemViewHolder.onBind(selectData.get(Postion));
        itemViewHolder.selectCheck.setOnCheckedChangeListener(null);
        itemViewHolder.selectCheck.setChecked(select.isSelected());

        itemViewHolder.selectCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                select.setSelected(isChecked);
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView selectText;
        private TextView selectSalePrice;
        private TextView selectPrice;
        private ImageView selectImg;
        private CheckBox selectCheck;
        int select = 1;

        public ItemViewHolder(@NonNull View ItemView) {
            super(ItemView);

            selectText = ItemView.findViewById(R.id.select_Text);
            selectPrice = ItemView.findViewById(R.id.select_price);
            selectPrice.setPaintFlags(selectPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            selectSalePrice = ItemView.findViewById(R.id.select_sale_price);
            selectImg = ItemView.findViewById(R.id.select_Image);
            selectCheck = ItemView.findViewById(R.id.select_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
            ItemView.setOnCreateContextMenuListener(ItemViewHolder.this); //2. OnCreateContextMenuListener 리스너를 현재 클래스에서 구현한다고 설정해둡니다.
//
//        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
//        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case 1001:
//
//                        Intent intent = new Intent(mContext,MemoChangeActivity1.class);
//                        String strMain = listdata.get(postion).getMainText();
//                        String strDate = listdata.get(postion).getSubText();
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
//                        float scale = (float) (1024 / (float) bitmap.getWidth());
//                        int image_w = (int) (bitmap.getWidth() * scale);
//                        int image_h = (int) (bitmap.getHeight() * scale);
//                        Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
//                        resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                        byteArray = stream.toByteArray();
//                        intent.putExtra("img", byteArray);
//                        intent.putExtra("subcontent",strDate);
//                        intent.putExtra("maintitle",strMain);
//                        intent.putExtra("key", postion);
//
//                        mContext.startActivity(intent);
//
////                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
////                        View view = LayoutInflater.from(mContext)
////                                .inflate(R.layout.editbox, null, false);
////                        builder.setView(view);
////
////                        final Button button = (Button) view.findViewById(R.id.button_dialog_submit);
////                        final TextView textView = (TextView) view.findViewById(R.id.edittext_dialog_id);
////                        final AlertDialog dialog = builder.create();
////
////                        textView.setText(listdata.get(getAdapterPosition()).getMainText());
////
////                        button.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                String strstr = textView.getText().toString();
////
////                                Memo memo = new Memo(strstr, null, 0);
////
////                                // 8. ListArray에 있는 데이터를 변경하고
////                                listdata.set(getAdapterPosition(), memo);
////
////                                // 9. 어댑터에서 RecyclerView에 반영하도록 합니다.
////                                notifyItemChanged(getAdapterPosition());
////
////                                dialog.dismiss();
////                            }
////                        });
////
////                        dialog.show();
////
//                        break;
//
//                    case 1002:  // 5. 삭제 항목을 선택시
//                        // 6. ArratList에서 해당 데이터를 삭제하고
//
//                        listdata.remove(getAdapterPosition());
//
//                        // 7. 어댑터에서 RecyclerView에 반영하도록 합니다.
//                        notifyItemRemoved(getAdapterPosition());
//                        notifyItemRangeChanged(getAdapterPosition(), listdata.size());
//
//                        break;
//                }
//                return true;
//            }
//        };
        }

        void onBind(Select data) {
            selectText.setText(data.getSelectName());
            selectSalePrice.setText(data.getSelectSalePrice());
            selectPrice.setText(data.getSelectPrice());

            if (select == select1) {
                selectCheck.setVisibility(View.VISIBLE);
            } else if (select != select1) {
                selectCheck.setVisibility(View.GONE);
                selectCheck.setChecked(false);
            }

            Glide.with(itemView.getContext()).load(data.getSelectImage()).into(selectImg);
        }

        // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다. ID 1001로 어떤 메뉴를 선택했는지 리스너에서 구분.
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1001, 1, "삭제");
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1001:
                        //ArratList에서 해당 데이터를 삭제하고
                        SharedPreferences sharedPreferences = getSharedPreferences("testdata", MODE_PRIVATE);
                        String readData = sharedPreferences.getString("siteList", "");
                        try {
                            //jsonArray.size()로 사이즈 확인가능
                            JSONArray jsonArray = new JSONArray(readData);
                            //삭제하고 싶은 리스트를 인덱스(itemPosition)으로 삭제한다.

                            jsonArray.remove(getAdapterPosition());

                            Log.d(TAG, jsonArray.toString() + "현재 파일 전채 내용.");
                            SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                            //인덱스로 선택된 리스트를 값을 제거 후 저장.
                            dataEditor.putString("siteList", jsonArray.toString());
                            dataEditor.apply();
                            selectData.remove(getAdapterPosition());

                            //어댑터에서 RecyclerView에 반영.
                            notifyItemRemoved(getAdapterPosition());
//                            notifyItemRangeChanged(getAdapterPosition(), selectData.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    //선택된 아이템을 SharedPreferences 에서 삭제.
                }
                return true;
            }

            private SharedPreferences getSharedPreferences(String test, int modePrivate) {
                return mContext.getSharedPreferences(test, modePrivate);
            }
        };
    }

    public ArrayList<Select> getselectData() {
        return selectData;
    }
}