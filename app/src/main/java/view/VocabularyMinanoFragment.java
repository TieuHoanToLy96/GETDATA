package view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.tieuhoan.getdata.R;

/**
 * Created by TieuHoan on 21/06/2017.
 */

public class VocabularyMinanoFragment extends Fragment {

    private WebView webViewVocMinano;
    private int position;

    public VocabularyMinanoFragment(int position) {
        this.position = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vocabulary_minano_fragment, null, false);

        webViewVocMinano = (WebView) view.findViewById(R.id.webViewVocMinano);
        webViewVocMinano.getSettings().setBuiltInZoomControls(true);
        webViewVocMinano.getSettings().setDisplayZoomControls(false);
//        webViewVocMinano.getSettings().setMinimumFontSize(18);
//        webViewVocMinano.getSettings().setUseWideViewPort(true);
//        webViewVocMinano.getSettings().setLoadWithOverviewMode(true);
        webViewVocMinano.loadUrl("file:///android_asset/voc/" + "voc_" + position + ".html");
        return view;
    }
}
