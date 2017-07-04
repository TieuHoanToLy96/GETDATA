package adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.Tag;

/**
 * Created by TieuHoan on 04/07/2017.
 */

public class TagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Tag> tags;
    private Context context;
    private Tag tag;

    public TagAdapter(ArrayList<Tag> tags, Context context) {
        this.tags = tags;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tag, null, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagViewHolder viewHolder = (TagViewHolder) holder;
        tag = tags.get(position);
        viewHolder.imgBgTag.setImageResource(tag.getImageTag());
        viewHolder.tvNameTag.setText(tag.getNameTag());
        viewHolder.tvQuotation.setText(tag.getQuotation());


        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.image_flip);
        set.setTarget(viewHolder.imgBgTag);
        set.start();

//        viewHolder.tvQuotation.setMovementMethod(new ScrollingMovementMethod());
        viewHolder.tvQuotation.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgBgTag;
        public TextView tvNameTag;
        public TextView tvQuotation;


        public TagViewHolder(View itemView) {
            super(itemView);
            imgBgTag = (ImageView) itemView.findViewById(R.id.imgBgTag);
            tvNameTag = (TextView) itemView.findViewById(R.id.tvNameTag);
            tvQuotation = (TextView) itemView.findViewById(R.id.tvQuotation);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onClickItemTag.OnClickTag(v, getLayoutPosition(), imgBgTag);
        }
    }

    public interface OnClickItemTag {
        void OnClickTag(View view, int position, ImageView imgBgTag);
    }

    public void setOnClickItemTag(OnClickItemTag onClickItemTag) {
        this.onClickItemTag = onClickItemTag;
    }

    public OnClickItemTag onClickItemTag;


}
