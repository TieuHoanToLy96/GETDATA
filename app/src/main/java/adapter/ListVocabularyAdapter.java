package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import model.Vocabulary;

/**
 * Created by TieuHoan on 22/05/2017.
 */

public class ListVocabularyAdapter extends ExpandableRecyclerViewAdapter<ListVocabularyAdapter.Parent, ListVocabularyAdapter.Child> {

    private LayoutInflater layoutInflater;

    public ListVocabularyAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Parent onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_vocabulary_parent, parent, false);
        return new Parent(view);
    }

    @Override
    public Child onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_vocabulary_child, parent, false);
        return new Child(view);
    }

    @Override
    public void onBindChildViewHolder(Child holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Vocabulary vocabulary = (Vocabulary) group.getItems().get(childIndex);
        holder.setTvHiraganaVocabulary(vocabulary.getJapanese());
        holder.setTvRomanjiVocabulary(vocabulary.getRomaji());
    }

    @Override
    public void onBindGroupViewHolder(Parent holder, int flatPosition, ExpandableGroup group) {
        holder.setTvVietnameseVocabulary(group.getTitle());
    }


    public class Child extends ChildViewHolder {
        TextView tvHiraganaVocabulary, tvRomanjiVocabulary;

        public Child(View itemView) {
            super(itemView);
            tvHiraganaVocabulary = (TextView) itemView.findViewById(R.id.tvHiraganaVocabulary);
            tvRomanjiVocabulary = (TextView) itemView.findViewById(R.id.tvRomanjiVocabulary);
        }

        public void setTvHiraganaVocabulary(String name) {
            tvHiraganaVocabulary.setText(name);
        }

        public void setTvRomanjiVocabulary(String name) {
            tvRomanjiVocabulary.setText(name);
        }
    }

    public class Parent extends GroupViewHolder {
        TextView tvVietnameseVocabulary;

        public Parent(View itemView) {
            super(itemView);
            tvVietnameseVocabulary = (TextView) itemView.findViewById(R.id.tvVietnameseVocabulary);
        }

        public void setTvVietnameseVocabulary(String name) {
            tvVietnameseVocabulary.setText(name);
        }

    }


}
