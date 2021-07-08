package app.jw.recyclerviewproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private ArrayList<Item> listItem = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listItem.size();
    }

    void addItem(Item item) {

        listItem.add(item);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName;
        TextView textCategory;
        TextView textDate;


        public ItemViewHolder(View itemView) {
            super(itemView);

            //view 호출(findViewById) 관련 처리


        }

        void onBind(Item item)
        {
            //기타 처리(특히 View를 건드리는 처리)는 모두 여기서


        }
    }
}
