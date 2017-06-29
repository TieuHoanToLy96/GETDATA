package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import model.VocabularyMinanoLesson;

/**
 * Created by TieuHoan on 21/06/2017.
 */

public class ListVocabularyMinanoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<VocabularyMinanoLesson> listMinano;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListVocabularyMinanoAdapter(Context context, ArrayList<VocabularyMinanoLesson> listMinano) {
        this.context = context;
        this.listMinano = listMinano;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_vocabulaty_minano, parent, false);

        return new MinanoLessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VocabularyMinanoLesson minanoLesson = listMinano.get(position);
        MinanoLessonViewHolder lessonViewHolder = (MinanoLessonViewHolder) holder;

        lessonViewHolder.tvMinanoLesson.setText(minanoLesson.getNameVocMinanoLesson());
    }

    @Override
    public int getItemCount() {
        return listMinano.size();
    }

    public interface OnClickVocMinanoLesson {
        void OnClickVocMinano(View v, int position);
    }

    OnClickVocMinanoLesson onClickVocMinanoLesson;

    public void setOnClickVocMinanoLesson(OnClickVocMinanoLesson onClickVocMinanoLesson) {
        this.onClickVocMinanoLesson = onClickVocMinanoLesson;
    }

    public class MinanoLessonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMinanoLesson;

        public MinanoLessonViewHolder(View itemView) {
            super(itemView);
            tvMinanoLesson = (TextView) itemView.findViewById(R.id.tvMinanoLesson);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickVocMinanoLesson.OnClickVocMinano(v, getPosition());
        }
    }
}
