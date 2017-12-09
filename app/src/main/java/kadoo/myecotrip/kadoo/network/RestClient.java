package kadoo.myecotrip.kadoo.network;


import java.io.IOException;

import kadoo.myecotrip.kadoo.beat.rowData.BeatResponse;
import kadoo.myecotrip.kadoo.beat.rowData.BeatsRequest;
import kadoo.myecotrip.kadoo.beat.rowData.CircleResponse;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.RangeResponse;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionResponse;
import kadoo.myecotrip.kadoo.login.LoginRequest;
import kadoo.myecotrip.kadoo.login.LoginResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Use to handle Network Call
 */


public class RestClient implements INetwork {

    ApiCall iNetwork;
    private static RestClient restClient;

    public RestClient() {
        String url = "http://35.154.239.203/kaadoo/public/index.php/api/v1/";
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iNetwork = retrofit.create(ApiCall.class);
    }

    public static RestClient getInstance() {
        if (restClient == null) {
            restClient = new RestClient();
        }
        return restClient;
    }


    @Override
    public void doLogin(LoginRequest registerRequest, KadooCallBack<LoginResponse> responseMyEcoTripCallBack) {

    }

    @Override
    public void getCircle(KadooCallBack<CircleResponse> circleResponseKadooCallBack) {

        Call<CircleResponse> circleResponseCall = iNetwork.getAllCircle();
        circleResponseCall.enqueue(new NetWorkCallBack<CircleResponse>(circleResponseKadooCallBack));

    }

    @Override
    public void getDivision(String circleId, KadooCallBack<DivisionResponse> circleResponseKadooCallBack) {

        Call<DivisionResponse> circleResponseCall = iNetwork.getDivision(circleId);
        circleResponseCall.enqueue(new NetWorkCallBack<>(circleResponseKadooCallBack));
    }

    @Override
    public void getSubDivision(String divisionId, KadooCallBack<SubDivisionResponse> circleResponseKadooCallBack) {

        Call<SubDivisionResponse> subDivisionResponseCall = iNetwork.getSubDivision(divisionId);
        subDivisionResponseCall.enqueue(new NetWorkCallBack<>(circleResponseKadooCallBack));
    }

    @Override
    public void getRange(String subDivisionId, KadooCallBack<RangeResponse> circleResponseKadooCallBack) {

        Call<RangeResponse> subDivisionResponseCall = iNetwork.getRange(subDivisionId);
        subDivisionResponseCall.enqueue(new NetWorkCallBack<>(circleResponseKadooCallBack));
    }

    @Override
    public void getBeats(BeatsRequest beatsRequest, KadooCallBack<BeatResponse> circleResponseKadooCallBack) {

        Call<BeatResponse> call = iNetwork.getBeats(beatsRequest);
        call.enqueue(new NetWorkCallBack<>(circleResponseKadooCallBack));
    }

}
