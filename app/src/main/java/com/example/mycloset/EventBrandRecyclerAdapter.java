package com.example.mycloset;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EventBrandRecyclerAdapter extends RecyclerView.Adapter<EventBrandRecyclerAdapter.ItemViewHolder> {

    int pos;
    private ArrayList<EventBrand> eventBrandData;

    private Context mContext;
    Product product;

    // 커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public EventBrandRecyclerAdapter(Context context, ArrayList<EventBrand> eventBrandData) {
        this.eventBrandData = eventBrandData;
        mContext = context;
    }

    @NonNull
    @Override
    public EventBrandRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_brand_item1, viewGroup, false);
        return new EventBrandRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (null != eventBrandData ? eventBrandData.size() : 0);
    }

    @Override
    public void onBindViewHolder(@NonNull EventBrandRecyclerAdapter.ItemViewHolder itemViewHolder, int Postion) {
        pos = itemViewHolder.getAdapterPosition();
        itemViewHolder.onBind(eventBrandData.get(Postion));
//        itemViewHolder.productImg.setImageResource(productData.get(productPostion).getProductImage());
//        itemViewHolder.productText.setText(productData.get(productPostion).getProductName());
//        itemViewHolder.productPrice1.setText(productData.get(productPostion).getProductPrice1());
//        itemViewHolder.productPrice.setText(productData.get(productPostion).getProductPrice());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView eventText;
        private TextView eventContent;
        private TextView eventSubContent;
        private ImageView eventImg;

        public ItemViewHolder(@NonNull View ItemView) {
            super(ItemView);

            eventText = ItemView.findViewById(R.id.event_Brand_Text1);
            eventContent = ItemView.findViewById(R.id.event_Brand_date1);
            eventSubContent = ItemView.findViewById(R.id.event_Brand_date2);
            eventImg = ItemView.findViewById(R.id.event_Brand_Image1);

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

        void onBind(EventBrand data) {
            eventText.setText(data.getEventBrandName());
            eventContent.setText(data.getEventBrandDate());
            eventSubContent.setText(data.getEventBrandSubContent());

            Glide.with(itemView.getContext()).load(data.getEventBrandImage()).into(eventImg);
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
                        eventBrandData.remove(getAdapterPosition());

                        //어댑터에서 RecyclerView에 반영.
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), eventBrandData.size());

                        break;
                }
                return true;
            }
        };
    }
}