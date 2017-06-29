package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.Lesson;

/**
 * Created by TieuHoan on 16/05/2017.
 */

public class ListLessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Lesson> lessons;
    private Context context;
    private ProgressBar progressBar;

    public ListLessonAdapter(ArrayList<Lesson> lessons, Context context) {
        this.lessons = lessons;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_lesson, parent, false);


        return new LessonHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        LessonHolder lessonHolder = (LessonHolder) holder;
        lessonHolder.tvNameLesson.setText(lesson.getName());
        Glide.with(context)
                .load(lesson.getImage())
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.refresh)
                .into(lessonHolder.imgLesson);

//        progressBar.setVisibility(View.VISIBLE);
//        Glide.with(context)
//                .load(lesson.getImage())
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//                })
//                .into(lessonHolder.imgLesson)
//        ;
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgLesson;
        private TextView tvNameLesson;

        public LessonHolder(View itemView) {
            super(itemView);

            imgLesson = (ImageView) itemView.findViewById(R.id.imgLesson);
            tvNameLesson = (TextView) itemView.findViewById(R.id.tvNameLesson);

            itemView.setOnClickListener(this);
        }

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
}
