package kadoo.myecotrip.kadoo.network;



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



   /* @POST("userSignUp")
    Call<RegisterResponse> doSignUp(@Body RegisterRequest registerRequest);

    @POST("profileUpdate")
    Call<ProfileUpdateResponse> updateProfile(@Body ProfileUpdateRequest registerRequest);

    @POST("userSignIn")
    Call<LoginResponse> doSignIn(@Body LoginRequest registerRequest);

    @GET("categories")
    Call<CategoryRowData> getCategory();

    @GET("getRSAkey")
    Call<String> getRsaKey(@Query("orderId") String orderiD);

    @POST("subCategories")
    Call<SubCategoryRowData> getSubCategory(@Body SubCategoryRequest subCategoryRequest);

    @POST("ecotrailCamp")
    Call<EcoDetailsResponse> getEcoDetailsResponse(@Body CommonDetailsRequest commonDetailsRequest);

    @POST("trailList")
    Call<TrailListingRowData> getTrailList(@Body TrailRequestRowData commonDetailsRequest);

    @POST("birdSanctuary")
    Call<BirdSanacturyResponse> getBirdSnactury(@Body CommonDetailsRequest commonDetailsRequest);

    @POST("safari")
    Call<WildsafariResponse> getWildLifeSafari(@Body CommonDetailsRequest commonDetailsRequest);

    @POST("bookEcotrailStatus")
    Call<CheckVaibilityResponse> chekAvibality(@Body CheckAvailibityRequest commonDetailsRequest);

    @GET("trailDetail/{id}")
    Call<TrailDetailsResponse> getTrailDetails(@Path("id") int bookId);

    @POST("checkAvailability")
    Call<AvailableSeatBokingResponse> checkAvailableSeat(@Body AvailableSeatRuequest availableSeatRuequest);

    @POST("initiateBooking")
    Call<BookingResponse> bookingTrail(@Body BookingReuest availableSeatRuequest);

    @GET("trailBookingDetails/{path}")
    Call<PaymentResponse> getPaymentStatus(@Path("path") String thePath);

    @GET("userBookingHistory/{path}")
    Call<OrderHistoryRowData> getOrderHistory(@Path("path") String thePath);
*/


}
