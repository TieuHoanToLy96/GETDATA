package view;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tieuhoan.getdata.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Alphabet;

/**
 * Created by TieuHoan on 16/05/2017.
 */

public class WriteAlphabetFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Alphabet> alphabets;
    private int position;
    private ImageView imgWriteAlphabet;
    private Context context;
    private boolean isHiragana;
    private MediaPlayer player;

    public WriteAlphabetFragment(int position, ArrayList<Alphabet> alphabets, boolean isHiragana) {
        this.position = position;
        this.alphabets = alphabets;
        this.isHiragana = isHiragana;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.write_alphabet_fragment, null, false);
        bindView(view);


        return view;

    }

    public void bindView(View view) {
        imgWriteAlphabet = (ImageView) view.findViewById(R.id.imgWriteAlphabet);
        InputStream ims = null;
        try {
            if (!alphabets.get(position).getRomaji().equals("")) {
                if (isHiragana) {
                    ims = context.getAssets().open("image/hiragana/" + alphabets.get(position).getRomaji() + ".PNG");
                } else {
                    ims = context.getAssets().open("image/katakana/" + alphabets.get(position).getRomaji() + ".PNG");
                }
                Drawable d = Drawable.createFromStream(ims, null);
                imgWriteAlphabet.setImageDrawable(d);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        imgWriteAlphabet.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgWriteAlphabet: {
                eventPlayAudio();
                break;
            }
        }
    }


    public void eventPlayAudio() {
        AssetFileDescriptor afd = null;
        try {
            afd = getActivity().getAssets().openFd("audio/alphabet/" + alphabets.get(position).getRomaji() + ".mp3");

            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.setVolume(50, 50);
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.release();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
