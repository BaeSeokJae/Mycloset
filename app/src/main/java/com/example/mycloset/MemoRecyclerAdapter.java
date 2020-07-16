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
import java.util.ArrayList;

public class MemoRecyclerAdapter extends RecyclerView.Adapter<MemoRecyclerAdapter.ItemViewHolder> {

    int postion;
    private ArrayList<Memo> listdata;
    private Context mContext;
    MemoChangeActivity memoChangeActivity;
    Memo memo;

    // 커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public MemoRecyclerAdapter(Context context, ArrayList<Memo> listdata) {
        this.listdata = listdata;
        mContext = context;
    }

    @NonNull
    @Override
    public MemoRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.memo_item, viewGroup, false);
        return new MemoRecyclerAdapter.ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (null != listdata ? listdata.size() : 0);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoRecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        postion = itemViewHolder.getAdapterPosition();
        itemViewHolder.img.setImageBitmap(listdata.get(i).getBitmap());
        itemViewHolder.mainText.setText(listdata.get(i).getMainText());
        itemViewHolder.mainDate.setText(listdata.get(i).getMainDate());
        itemViewHolder.subText.setText(listdata.get(i).getSubText());
    }

    void addItem(Memo memo) {
        listdata.add(memo);
    }

    void removeItem(int postion) {
        listdata.remove(postion);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView mainText;
        private TextView mainDate;
        private TextView subText;
        private ImageView img;

        public ItemViewHolder(@NonNull View ItemView) {
            super(ItemView);

            mainText = ItemView.findViewById(R.id.memo_title_content);
            mainDate = ItemView.findViewById(R.id.memo_date_content);
            subText = ItemView.findViewById(R.id.memo_content_content);
            img = ItemView.findViewById(R.id.Memo_Image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
            itemView.setOnCreateContextMenuListener(ItemViewHolder.this); //2. OnCreateContextMenuListener 리스너를 현재 클래스에서 구현한다고 설정해둡니다.
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
//                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                        View view = LayoutInflater.from(mContext)
//                                .inflate(R.layout.editbox, null, false);
//                        builder.setView(view);
//
//                        final Button button = (Button) view.findViewById(R.id.button_dialog_submit);
//                        final TextView textView = (TextView) view.findViewById(R.id.edittext_dialog_id);
//                        final AlertDialog dialog = builder.create();
//
//                        textView.setText(listdata.get(getAdapterPosition()).getMainText());
//
//                        button.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                String strstr = textView.getText().toString();
//
//                                Memo memo = new Memo(strstr, null, 0);
//
//                                // 8. ListArray에 있는 데이터를 변경하고
//                                listdata.set(getAdapterPosition(), memo);
//
//                                // 9. 어댑터에서 RecyclerView에 반영하도록 합니다.
//                                notifyItemChanged(getAdapterPosition());
//
//                                dialog.dismiss();
//                            }
//                        });
//
//                        dialog.show();
//
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

        // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다. ID 1001로 어떤 메뉴를 선택했는지 리스너에서 구분.
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1001, 1, "삭제");
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1001:
                        //ArratList에서 해당 데이터를 삭제하고
                        listdata.remove(getAdapterPosition());

                        //어댑터에서 RecyclerView에 반영.
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), listdata.size());

                        break;
                }
                return true;
            }
        };
    }
}