package view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieuhoan.getdata.R;

import java.util.ArrayList;
import java.util.Random;

import model.Alphabet;

/**
 * Created by TieuHoan on 23/05/2017.
 */

public class CheckAlphabetFragment extends Fragment implements View.OnClickListener {
    private int position;
    private ArrayList<Alphabet> alphabets;
    private boolean isHiragana;
    private ImageView imgCheckResult, imgHelp;
    private TextView tvCorrect, tvIncorrect, tvCheckJapanese;
    private EditText edtCheckResult;
    private int correct, incorrect;
    private Boolean isHelp;

    public CheckAlphabetFragment(int position, ArrayList<Alphabet> alphabets, boolean isHiragana) {
        this.position = position;
        this.alphabets = alphabets;
        this.isHiragana = isHiragana;
        this.incorrect = 0;
        this.correct = 0;
        isHelp = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Kiểm tra chữ cái");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.check_alphabet_fragment, null, false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        tvCheckJapanese = (TextView) view.findViewById(R.id.tvCheckJapanese);
        edtCheckResult = (EditText) view.findViewById(R.id.edtCheckResult);

        imgCheckResult = (ImageView) view.findViewById(R.id.imgCheckResult);
        imgHelp = (ImageView) view.findViewById(R.id.imgHelp);

        tvCorrect = (TextView) view.findViewById(R.id.tvCorrect);
        tvIncorrect = (TextView) view.findViewById(R.id.tvIncorrect);

        imgCheckResult.setOnClickListener(this);
        imgHelp.setOnClickListener(this);


        edtCheckResult.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtCheckResult, InputMethodManager.SHOW_IMPLICIT);
        //set text
        if (isHiragana) {
            tvCheckJapanese.setText(alphabets.get(position).getHiragana());
        } else {
            tvCheckJapanese.setText(alphabets.get(position).getKatakana());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgCheckResult: {
                eventImgCheckResult();
                break;
            }
            case R.id.imgHelp: {
                isHelp = true;
                eventImgHelp();
                break;
            }
        }
    }

    private void eventImgHelp() {
        edtCheckResult.setText(alphabets.get(position).getRomaji());
    }

    private void eventImgCheckResult() {

        //get result
        String stringJapanese = alphabets.get(position).getRomaji();

        //check text result and set correct , incorrect

        if (String.valueOf(edtCheckResult.getText()).equals(stringJapanese) && !isHelp) {
            correct++;
            tvCorrect.setText(String.valueOf(correct));
        } else {
            incorrect++;
            tvIncorrect.setText(String.valueOf(incorrect));
        }

        isHelp = false;


        //random japanese
        Random random = new Random();
        position = random.nextInt(alphabets.size());

        if (isHiragana) {
            tvCheckJapanese.setText(alphabets.get(position).getHiragana());
            edtCheckResult.setText("");
        } else {
            tvCheckJapanese.setText(alphabets.get(position).getKatakana());
            edtCheckResult.setText("");
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Kiểm tra chữ cái");
    }
}
