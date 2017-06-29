package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;

import adapter.ListVocabularyAdapter;
import model.Genre;
import model.Vocabulary;
import ulti.Data;

/**
 * Created by TieuHoan on 21/05/2017.
 */

public class ListVocabularyMoreFragment extends Fragment {
    private int positionCategory;
    private Context context;
    private ArrayList<Vocabulary> vocabularies;
    private RecyclerView recyclerView;
    private ArrayList<Genre> genres;
    private ListVocabularyAdapter adapter;
    private String title;

    public ListVocabularyMoreFragment(int positionCategory, String title) {
        this.positionCategory = positionCategory;
        this.title = title;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        Data data = new Data(context);
        vocabularies = data.getVocabulary(String.valueOf(positionCategory));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_vocabulary_more_fragment, null, false);
        bindView(view);

        return view;
    }

    public void bindView(View view) {

        genres = new ArrayList<>();
        for (int i = 0; i < vocabularies.size(); i++) {
            ArrayList<Vocabulary> vocabularies1 = new ArrayList<>();
            vocabularies1.add(vocabularies.get(i));
            genres.add(new Genre(vocabularies.get(i).getVietnamese(), vocabularies1));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewListVocabulary);
        adapter = new ListVocabularyAdapter(genres, context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
