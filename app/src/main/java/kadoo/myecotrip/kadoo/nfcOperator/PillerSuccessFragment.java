package kadoo.myecotrip.kadoo.nfcOperator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseFragment;
import kadoo.myecotrip.kadoo.base.CommonResponse;
import kadoo.myecotrip.kadoo.beats.AddBeatsRequest;
import kadoo.myecotrip.kadoo.beats.AddBeatsResponse;
import kadoo.myecotrip.kadoo.common.KadooAppUser;
import kadoo.myecotrip.kadoo.common.SelectedBeatData;
import kadoo.myecotrip.kadoo.core.PillerInfo;
import kadoo.myecotrip.kadoo.home.HomeActivity;
import kadoo.myecotrip.kadoo.login.KadooUser;
import kadoo.myecotrip.kadoo.network.ErrorCodes;
import kadoo.myecotrip.kadoo.network.KadooCallBack;
import kadoo.myecotrip.kadoo.network.RestClient;

/**
 * Created by Gopal kumar on 10-12-2017.
 */

public class PillerSuccessFragment extends BaseFragment {

    private String pillearDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            pillearDetails = bundle.getString(NFCOperationActivity.PILLEAR_DETAILS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piller_success, container, false);
        view.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitData();
            }
        });
        return view;
    }

    private void submitData() {
        displayProgressDialog();
        AddBeatsRequest addBeatsRequest = new AddBeatsRequest();
        Gson gson = new Gson();
        PillerInfo pillerInfo = gson.fromJson(pillearDetails, PillerInfo.class);
        addBeatsRequest.setDisplayName(pillerInfo.getPillerName());
        addBeatsRequest.setLatitude(pillerInfo.getLat());
        addBeatsRequest.setLongitude(pillerInfo.getLng());
        KadooUser kadooUser = KadooAppUser.getInstnace().getKadooAppUser();
        addBeatsRequest.setUser_id(String.valueOf(kadooUser.getId()));
        SelectedBeatData selectedBeatData = KadooAppUser.getInstnace().getSelectedBeatData();
        addBeatsRequest.setBeat_id(selectedBeatData.getBeatId());
        RestClient.getInstance().addBeats(addBeatsRequest, new KadooCallBack<AddBeatsResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

                hideProgressDialog();
            }

            @Override
            public void onSuccess(AddBeatsResponse addBeatsResponse) {

                hideProgressDialog();
                CommonResponse response = addBeatsResponse.getResponse();
                if (addBeatsResponse.getResponse().getError() != 0) {
                    Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
                    getActivity().finishAffinity();
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
            }
        });
    }
}
