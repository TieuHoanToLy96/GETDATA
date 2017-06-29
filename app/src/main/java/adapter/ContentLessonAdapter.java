package adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.ContentLesson;
import model.DetailLesson;
import model.Lesson;

/**
 * Created by TieuHoan on 17/05/2017.
 */

public class ContentLessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<ContentLesson> contentLessons;
    private Context context;
    private DetailLesson detailLesson;
    private Lesson lesson;
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    public ContentLessonAdapter(ArrayList<ContentLesson> contentLessons, Context context, DetailLesson detailLesson, Lesson lesson) {
        this.contentLessons = contentLessons;
        this.context = context;
        this.detailLesson = detailLesson;
        this.lesson = lesson;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        if (viewType == TYPE_HEADER) {
            view = layoutInflater.inflate(R.layout.detail_lesson_header, null, false);
            return new DetailLessonViewHolder(view);

        } else {
            view = layoutInflater.inflate(R.layout.item_content_lesson, parent, false);

            return new ContentLessonViewHolder(view);
        }

    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (isPositionHeader(position)) {
            DetailLessonViewHolder detail = (DetailLessonViewHolder) holder;
            detail.tvNameLessonDetail.setText(lesson.getName());
            detail.tvDetail.setText(detailLesson.getDetail());
            detail.tvGrammar.setText(detailLesson.getGrammar());
            Glide.with(context)
                    .load(lesson.getImage())
                    .centerCrop()
                    .crossFade()
                    .into(detail.imgLessonDetail);
        } else {
            ContentLesson contentLesson = contentLessons.get(position - 1);
            ContentLessonViewHolder content = (ContentLessonViewHolder) holder;
            content.tvKanaName.setText(contentLesson.getKana_name());
            content.tvRomajiName.setText(contentLesson.getRomaji_name());
            content.tvKana.setText(contentLesson.getKana());
            content.tvRomaji.setText(contentLesson.getRomaji());
            content.tvVN.setText(contentLesson.getVn());
        }
    }


    public interface OnClickHeaderRecycleView {
        void OnLickDownAudio(View view, int position);

        void OnClickDownPDF(View view, int position);

        void OnClickPlayAllVideo(View view, int position, ImageView imgPlayAllAudio);
    }

    public OnClickHeaderRecycleView onClickHeaderRecycleView;

    public void setOnClickHeaderRecycleView(OnClickHeaderRecycleView onClickHeaderRecycleView) {
        this.onClickHeaderRecycleView = onClickHeaderRecycleView;
    }

    public class DetailLessonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgLessonDetail, imgDownAudio, imgDownPDF, imgPlayAllAudio;
        TextView tvNameLessonDetail, tvDetail, tvGrammar;
        private MediaPlayer player;

        public ImageView getImgPlayAllAudio() {
            return imgPlayAllAudio;
        }

        public DetailLessonViewHolder(View itemView) {
            super(itemView);

            imgLessonDetail = (ImageView) itemView.findViewById(R.id.imgLessonDetail);
            imgDownAudio = (ImageView) itemView.findViewById(R.id.imgDownAudio);
            imgDownPDF = (ImageView) itemView.findViewById(R.id.imgDownPDF);
            imgPlayAllAudio = (ImageView) itemView.findViewById(R.id.imgPlayAllAudio);

            tvNameLessonDetail = (TextView) itemView.findViewById(R.id.tvNameLessonDetail);
            tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);
            tvGrammar = (TextView) itemView.findViewById(R.id.tvGrammar);

            ///set onclick
            imgDownAudio.setOnClickListener(this);
            imgDownPDF.setOnClickListener(this);
            imgPlayAllAudio.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgDownAudio: {
                    onClickHeaderRecycleView.OnLickDownAudio(v, getPosition());
                    break;
                }
                case R.id.imgDownPDF: {
                    onClickHeaderRecycleView.OnClickDownPDF(v, getPosition());
                    break;
                }
                case R.id.imgPlayAllAudio: {
                    onClickHeaderRecycleView.OnClickPlayAllVideo(v, getPosition(), getImgPlayAllAudio());
                    break;
                }

            }
        }


    }

    public interface OnClickItemRecycleViewContentLesson {
        void OnLickPlayAudio(View view, int position, ImageView imgPlayAudio);

    }

    OnClickItemRecycleViewContentLesson onClick;

    public void setOnClick(OnClickItemRecycleViewContentLesson onClick) {
        this.onClick = onClick;
    }

    public class ContentLessonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvKanaName, tvRomajiName, tvKana, tvRomaji, tvVN;
        public ImageView imgPlayAudio;


        public ContentLessonViewHolder(View itemView) {
            super(itemView);
            tvKanaName = (TextView) itemView.findViewById(R.id.tvKanaName);
            tvRomajiName = (TextView) itemView.findViewById(R.id.tvRomajiName);
            tvKana = (TextView) itemView.findViewById(R.id.tvKana);
            tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
            tvVN = (TextView) itemView.findViewById(R.id.tvVN);

            imgPlayAudio = (ImageView) itemView.findViewById(R.id.imgPlayAudio);
            imgPlayAudio.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.imgPlayAudio: {
                    onClick.OnLickPlayAudio(v, getPosition(), imgPlayAudio);
                    break;
                }


            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_HEADER) return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return contentLessons.size() + 1;
    }
}
