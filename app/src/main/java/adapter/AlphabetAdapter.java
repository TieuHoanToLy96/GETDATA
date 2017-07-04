package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.Alphabet;

/**
 * Created by TieuHoan on 14/05/2017.
 */

public class AlphabetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Alphabet> alphabets;
    private Context context;

    private boolean isHiragana;

    public AlphabetAdapter(ArrayList<Alphabet> alphabets, Context context, boolean isHiragana) {
        this.alphabets = alphabets;
        this.context = context;
        this.isHiragana = isHiragana;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_alphabet, null, false);

        return new AlphabetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Alphabet alphabet = alphabets.get(position);
        AlphabetViewHolder aVH = (AlphabetViewHolder) holder;
        aVH.tvRomaji.setText(alphabet.getRomaji());
        if (isHiragana) {
            aVH.tvJapanese.setText(alphabet.getHiragana());
        } else {
            aVH.tvJapanese.setText(alphabet.getKatakana());
        }


//        setAnimation(aVH.itemView, position);
    }

    @Override
    public int getItemCount() {
        return alphabets.size();
    }

    public class AlphabetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout linearItemAlphbet;
        public TextView tvJapanese, tvRomaji;

        public AlphabetViewHolder(View itemView) {
            super(itemView);
            linearItemAlphbet = (LinearLayout) itemView.findViewById(R.id.linearItemAlphabet);
            tvJapanese = (TextView) itemView.findViewById(R.id.tvJapanese);
            tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);

            itemView.setOnClickListener(this);
        }

//        public void clearAnimation() {
//            linearItemAlphbet.clearAnimation();
//        }

        @Override
        public void onClick(View v) {
            onClickItemRecycleView.OnClick(v, getPosition());
        }
    }


    public void setOnClickItemRecycleView(OnClickItemRecycleView onClickItemRecycleView) {
        this.onClickItemRecycleView = onClickItemRecycleView;

    }

    public interface OnClickItemRecycleView {
        void OnClick(View view, int position);

    }

    public OnClickItemRecycleView onClickItemRecycleView;


//    private int lastPosition = -1;
//
//    private void setAnimation(View viewToAnimate, int position) {
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }

//    @Override
//    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
//        ((AlphabetViewHolder) holder).clearAnimation();
//    }
}
