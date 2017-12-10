package kadoo.myecotrip.kadoo.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseFragment;
import kadoo.myecotrip.kadoo.beat.SelectBeatActivity;
import kadoo.myecotrip.kadoo.nfc.LocationActivity;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class HomeFragment extends BaseFragment {

    private Button actionButton;
    private Button btnAddNextPiller;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        actionButton = view.findViewById(R.id.buttonStartAddingPiller);
        btnAddNextPiller = view.findViewById(R.id.btnAddNextPiller);
        actionButton.findViewById(R.id.buttonStartAddingPiller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleStartClick();
            }
        });
        btnAddNextPiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LocationActivity.class));
            }
        });
        setButtonText();
        return view;
    }

    private void handleStartClick() {
        if (TextUtils.isEmpty(kadooLocalUser.getSelectedCategory())) {
            startActivity(new Intent(getContext(), SelectBeatActivity.class));
        } else {
            kadooLocalUser.setSelectedCategory("");
        }
        setButtonText();
    }

    public void setButtonText() {

        if (TextUtils.isEmpty(kadooLocalUser.getSelectedCategory())) {
            actionButton.setText("START ADDING PILLEAR");
            btnAddNextPiller.setVisibility(View.GONE);
        } else {
            actionButton.setText("COMPLETE ADDING PILLEAR");
            btnAddNextPiller.setVisibility(View.VISIBLE);

        }

    }
}
