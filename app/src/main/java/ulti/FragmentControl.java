package ulti;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.tieuhoan.getdata.R;


/**
 * Created by TieuHoan on 14/05/2017.
 */

public class FragmentControl {


    public static void goToFragmentAddBackStack(int idFragment, Fragment fragment, Context context, String nameClass) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.addToBackStack(nameClass);
        transaction.replace(idFragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void goToFragmentNoAddBackStack(int idFragment, Fragment fragment, Context context) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(idFragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void goToFragmentNoAddBackStackAnimation(int idFragment, Fragment fragment, Context context) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(idFragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void removeFragmentInBackStack(Context context) {
        FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
        for (int i = 0; i < manager.getBackStackEntryCount(); ++i) {
            manager.popBackStack();
        }
    }


}
