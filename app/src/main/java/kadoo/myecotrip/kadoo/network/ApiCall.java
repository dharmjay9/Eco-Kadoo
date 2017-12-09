package kadoo.myecotrip.kadoo.network;


import kadoo.myecotrip.kadoo.beat.rowData.BeatResponse;
import kadoo.myecotrip.kadoo.beat.rowData.BeatsRequest;
import kadoo.myecotrip.kadoo.beat.rowData.CircleResponse;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.RangeResponse;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionResponse;
import kadoo.myecotrip.kadoo.login.LoginRequest;
import kadoo.myecotrip.kadoo.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiCall {

    @POST
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @GET("getCircles")
    Call<CircleResponse> getAllCircle();

    ///getDivisions/{circle_id}
    @GET("getDivisions/{circle_id}")
    Call<DivisionResponse> getDivision(@Path("circle_id") String circleId);

    @GET("getSubDivisions/{division_id}")
    Call<SubDivisionResponse> getSubDivision(@Path("circle_id") String circleId);

    @GET("getRanges/{subdivision_id}")
    Call<RangeResponse> getRange(@Path("circle_id") String circleId);

    @POST("getBeats")
    Call<BeatResponse> getBeats(@Body BeatsRequest beatsRequest);

}
