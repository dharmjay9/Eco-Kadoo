package kadoo.myecotrip.kadoo.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseFragment;
import kadoo.myecotrip.kadoo.beat.SelectBeatActivity;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class HomeFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.buttonStartAddingPiller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SelectBeatActivity.class));
            }
        });
        return view;
    }
}
