package com.example.mycloset;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ItemViewHolder> {

    int pos, test1;
    private ArrayList<Product> productData;
    Product product;
    private Context mContext;

    public ProductRecyclerAdapter(Context context, ArrayList<Product> productData) {
        this.productData = productData;
        setHasStableIds(true);
        mContext = context;
    }

    // 커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, viewGroup, false);
        return new ProductRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return productData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ItemViewHolder itemViewHolder, int Postion) {
        pos = itemViewHolder.getAdapterPosition();
        final Product product = productData.get(Postion);
        itemViewHolder.onBind(productData.get(Postion));
        itemViewHolder.productCheck.setOnCheckedChangeListener(null);
        itemViewHolder.productCheck.setChecked(product.isSelected());

        itemViewHolder.productCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setSelected(isChecked);
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView productText;
        private TextView productSalePrice;
        private TextView productPrice;
        private ImageView productImg;
        private CheckBox productCheck;
        int test = 1;

        public ItemViewHolder(@NonNull View ItemView) {
            super(ItemView);

            productText = ItemView.findViewById(R.id.product_Text);
            productSalePrice = ItemView.findViewById(R.id.product_sale_price);
            productSalePrice.setPaintFlags(productSalePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            productPrice = ItemView.findViewById(R.id.product_price);
            productImg = ItemView.findViewById(R.id.product_Image);
            productCheck = ItemView.findViewById(R.id.check);

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
//        }
        }

        void onBind(Product data) {
            productText.setText(data.getProductName());
            productSalePrice.setText(data.getProductSalePrice());
            productPrice.setText(data.getProductPrice());

            if (test == test1) {
                productCheck.setVisibility(View.VISIBLE);
            }
            else if (test != test1) {
                productCheck.setVisibility(View.GONE);
                productCheck.setChecked(false);
            }

            Glide.with(itemView.getContext()).load(data.getProductImage()).into(productImg);
        }

        // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다. ID 1001로 어떤 메뉴를 선택했는지 리스너에서 구분.
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1002:
                        //ArratList에서 해당 데이터를 삭제하고
                        productData.remove(getAdapterPosition());

                        //어댑터에서 RecyclerView에 반영.
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), productData.size());

                        break;
                }
                return true;
            }
        };

        public void saveData(Product data) {
            JSONObject jsonObject = new JSONObject();
            SharedPreferences sharedPreferences = getSharedPreferences("test11", MODE_PRIVATE);
            //현재 저장되어있는 데이터를 읽는다.
            try {
                //"planList"라는 키 값으로 저장되어있는 데이터가 없을때.
                if (sharedPreferences.getString("siteList", "").equals("")) {
//                Toast.makeText(context, "이미저장", Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = new JSONArray();
                    SharedPreferences.Editor dataEditor = sharedPreferences.edit();
                    jsonObject.put("siteName", data.getProductName());
                    jsonObject.put("siteSalePrice", data.getProductSalePrice());
                    jsonObject.put("sitePrice", data.getProductPrice());
                    jsonObject.put("siteImg", data.getProductImage());
                    jsonObject.put("siteLink", data.getProductLink());
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
                    jsonArray.put(jsonObject);
                    dataEditor.putString("siteList", jsonArray.toString());
                    dataEditor.apply();
                    Log.d(TAG, jsonArray.toString() + "데이터 저장 완료");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        private SharedPreferences getSharedPreferences(String test, int modePrivate) {
            return mContext.getSharedPreferences(test, modePrivate);
        }
    }
    public ArrayList<Product> getproductlist() {
        return productData;
    }
}