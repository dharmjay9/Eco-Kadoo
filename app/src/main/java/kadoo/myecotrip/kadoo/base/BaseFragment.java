package kadoo.myecotrip.kadoo.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kadoo.myecotrip.kadoo.common.KadooLocalUser;
import kadoo.myecotrip.kadoo.network.RestClient;


public abstract class BaseFragment extends Fragment {

    protected RestClient mRestClient;
    protected ProgressDialog mProgressDialog;
    protected KadooLocalUser mConverbizUser;
    protected RestClient restClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRestClient=new RestClient();
        restClient=new RestClient();
        mConverbizUser= KadooLocalUser.getInstance(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void displayProgressDialog(){
        mProgressDialog=new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Wait...");
        mProgressDialog.show();
    }

    protected void hideProgressDialog(){
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
        }
    }


}
