package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tieuhoan.getdata.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Alphabet;

/**
 * Created by TieuHoan on 29/06/2017.
 */

public class ViewFlipperAdapter extends BaseAdapter {
    private ArrayList<Alphabet> alphabets;
    private Context context;
    private ImageView imgFlipper;
    private boolean isHiragana;

    public ViewFlipperAdapter(ArrayList<Alphabet> alphabets, Context context, Boolean isHiragana) {
        this.alphabets = alphabets;
        this.context = context;
        this.isHiragana = isHiragana;
    }

    @Override

    public int getCount() {
        return alphabets.size();
    }

    @Override
    public Object getItem(int position) {
        return alphabets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_flipper, null, false);
        imgFlipper = (ImageView) view.findViewById(R.id.imgFlipper);


        InputStream ims = null;
        try {
            if (!alphabets.get(position).getRomaji().equals("")) {
                if (isHiragana) {
                    ims = context.getAssets().open("image/hiragana/" + alphabets.get(position).getRomaji() + ".PNG");
                } else {
                    ims = context.getAssets().open("image/katakana/" + alphabets.get(position).getRomaji() + ".PNG");
                }
                Drawable d = Drawable.createFromStream(ims, null);
                imgFlipper.setImageDrawable(d);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}
