package kadoo.myecotrip.kadoo.network;


import kadoo.myecotrip.kadoo.beat.rowData.BeatResponse;
import kadoo.myecotrip.kadoo.beat.rowData.BeatsRequest;
import kadoo.myecotrip.kadoo.beat.rowData.CircleResponse;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.RangeResponse;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionResponse;
import kadoo.myecotrip.kadoo.login.LoginRequest;
import kadoo.myecotrip.kadoo.login.LoginResponse;

public interface INetwork {


    void doLogin(LoginRequest registerRequest, KadooCallBack<LoginResponse> responseMyEcoTripCallBack);

    void getCircle(KadooCallBack<CircleResponse> circleResponseKadooCallBack);

    void getDivision(String divisionId, KadooCallBack<DivisionResponse> circleResponseKadooCallBack);

    void getSubDivision(String subdivisionId, KadooCallBack<SubDivisionResponse> circleResponseKadooCallBack);

    void getRange(String range, KadooCallBack<RangeResponse> circleResponseKadooCallBack);

    void getBeats(BeatsRequest beatsRequest, KadooCallBack<BeatResponse> circleResponseKadooCallBack);


  /*  void doSignUp(RegisterRequest registerRequest, MyEcoTripCallBack<RegisterResponse> responseMyEcoTripCallBack);

    void doLogin(LoginRequest registerRequest, MyEcoTripCallBack<LoginResponse> responseMyEcoTripCallBack);

    void getCategory(MyEcoTripCallBack<CategoryRowData> responseMyEcoTripCallBack);

    void getTrailListing(TrailRequestRowData subCategoryRequest, MyEcoTripCallBack<TrailListingRowData> subCategoryRowDataMyEcoTripCallBack);

    void getSubCategory(SubCategoryRequest subCategoryRequest, MyEcoTripCallBack<SubCategoryRowData> subCategoryRowDataMyEcoTripCallBack);

    void getEcoTrailDetails(CommonDetailsRequest commonDetailsRequest, MyEcoTripCallBack<EcoDetailsResponse> subCategoryRowDataMyEcoTripCallBack);

    void getBirdSanctury(CommonDetailsRequest commonDetailsRequest, MyEcoTripCallBack<BirdSanacturyResponse> subCategoryRowDataMyEcoTripCallBack);

    void getWildLifeSafai(CommonDetailsRequest commonDetailsRequest, MyEcoTripCallBack<WildsafariResponse> subCategoryRowDataMyEcoTripCallBack);

    void getAvibality(CheckAvailibityRequest availibityRequest, MyEcoTripCallBack<CheckVaibilityResponse> avResponseMyEcoTripCallBack);

    void getTrailDetails(String id, MyEcoTripCallBack<TrailDetailsResponse> avResponseMyEcoTripCallBack);

    void checkVailableSeat(AvailableSeatRuequest availibityRequest, MyEcoTripCallBack<AvailableSeatBokingResponse> avResponseMyEcoTripCallBack);

    void bookTrail(BookingReuest availibityRequest, MyEcoTripCallBack<BookingResponse> avResponseMyEcoTripCallBack);

    void updateProfile(ProfileUpdateRequest registerRequest, MyEcoTripCallBack<ProfileUpdateResponse> responseMyEcoTripCallBack);

    void getPaymentStatus(String orderiD, MyEcoTripCallBack<PaymentResponse> responseMyEcoTripCallBack);

    void getOrderHistory(String userId, MyEcoTripCallBack<OrderHistoryRowData> responseMyEcoTripCallBack);*/


}