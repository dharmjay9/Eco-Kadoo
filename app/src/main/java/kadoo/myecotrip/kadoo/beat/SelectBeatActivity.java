package kadoo.myecotrip.kadoo.beat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.List;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseActivity;
import kadoo.myecotrip.kadoo.beat.rowData.BeatResponse;
import kadoo.myecotrip.kadoo.beat.rowData.BeatRowData;
import kadoo.myecotrip.kadoo.beat.rowData.BeatsRequest;
import kadoo.myecotrip.kadoo.beat.rowData.CircleResponse;
import kadoo.myecotrip.kadoo.beat.rowData.CircleRowData;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionRowData;
import kadoo.myecotrip.kadoo.beat.rowData.RangeResponse;
import kadoo.myecotrip.kadoo.beat.rowData.RangeRowData;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionRowData;
import kadoo.myecotrip.kadoo.common.IConstant;
import kadoo.myecotrip.kadoo.common.KadooLocalUser;
import kadoo.myecotrip.kadoo.common.SelectedBeatData;
import kadoo.myecotrip.kadoo.network.ErrorCodes;
import kadoo.myecotrip.kadoo.network.KadooCallBack;
import kadoo.myecotrip.kadoo.network.RestClient;
import kadoo.myecotrip.kadoo.nfc.LocationActivity;


public class SelectBeatActivity extends BaseActivity {

    private Spinner spCircle, spDivision, spSubDivision, spRange, spBeats;
    private String circleId, divisionId, subdivisionId, rangeId, beatId;
    private List<CircleRowData> circleRowDataList;
    private List<DivisionRowData> divisionRowDataList;
    private List<SubDivisionRowData> subDivisionRowDataList;
    private List<RangeRowData> rangeRowDataList;
    private List<BeatRowData> beatRowDataList;
    private String circleName, divisionName, subdivisionName, rangeName, beatName;


    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_beat);
        spCircle = findViewById(R.id.spCircle);
        spDivision = findViewById(R.id.spDivision);
        spSubDivision = findViewById(R.id.spSubDivision);
        spRange = findViewById(R.id.spRange);
        spBeats = findViewById(R.id.spBeat);
        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                start(LocationActivity.class);

            }
        });
        setCircleData();
        setToolBar("Select Category");

    }

    private void start(Class<? extends Activity> token) {
        Intent intent = new Intent(this, token);
        startActivity(intent);
    }

    private void setCircleData() {


        RestClient.getInstance().getCircle(new KadooCallBack<CircleResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(CircleResponse circleResponse) {

                circleRowDataList = circleResponse.getContent();
                ArrayAdapter<CircleRowData> arrayAdapter = new ArrayAdapter<CircleRowData>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, circleRowDataList);
                spCircle.setAdapter(arrayAdapter);
                spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        circleId = String.valueOf(circleRowDataList.get(i).getId());
                        circleName = circleRowDataList.get(i).getName();
                        setDivisiondata();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

    }

    private void setDivisiondata() {

        RestClient.getInstance().getDivision(circleId, new KadooCallBack<DivisionResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(DivisionResponse divisionResponse) {

                divisionRowDataList = divisionResponse.getContent();
                ArrayAdapter<DivisionRowData> arrayAdapter = new ArrayAdapter(SelectBeatActivity.this, android.R.layout.simple_list_item_1, divisionRowDataList);
                spDivision.setAdapter(arrayAdapter);
                spDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        divisionId = String.valueOf(divisionRowDataList.get(i).getId());
                        divisionName = divisionRowDataList.get(i).getName();
                        setSubDivisionData();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

    }

    private void setSubDivisionData() {

        RestClient.getInstance().getSubDivision(divisionId, new KadooCallBack<SubDivisionResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(final SubDivisionResponse subDivisionResponse) {

                subDivisionRowDataList = subDivisionResponse.getContent();
                ArrayAdapter<SubDivisionRowData> arrayAdapter = new ArrayAdapter<>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, subDivisionRowDataList);
                spSubDivision.setAdapter(arrayAdapter);
                spSubDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        subdivisionId = String.valueOf(subDivisionRowDataList.get(i).getId());
                        subdivisionName = subDivisionRowDataList.get(i).getName();
                        setRangeData();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

    }

    private void setRangeData() {

        RestClient.getInstance().getRange(subdivisionId, new KadooCallBack<RangeResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(RangeResponse rangeResponse) {

                rangeRowDataList = rangeResponse.getContent();
                ArrayAdapter<RangeRowData> arrayAdapter = new ArrayAdapter<>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, rangeRowDataList);
                spRange.setAdapter(arrayAdapter);
                spRange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        rangeId = String.valueOf(rangeRowDataList.get(i).getId());
                        rangeName = rangeRowDataList.get(i).getName();
                        setBeats();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }

                });
            }
        });

    }

    private void setBeats() {

        BeatsRequest beatsRequest = new BeatsRequest();
        beatsRequest.setUsertype(IConstant.USER_TYPE);
        beatsRequest.setId(Integer.parseInt(rangeId));
        RestClient.getInstance().getBeats(beatsRequest, new KadooCallBack<BeatResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

                Log.i("", "");
            }

            @Override
            public void onSuccess(BeatResponse beatResponse) {

                beatRowDataList = beatResponse.getContent();
                ArrayAdapter<BeatRowData> arrayAdapter = new ArrayAdapter<BeatRowData>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, beatRowDataList);
                spBeats.setAdapter(arrayAdapter);
                spBeats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        beatId = String.valueOf(beatRowDataList.get(i).getId());
                        beatName = beatRowDataList.get(i).getName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }

    private void setData() {
        SelectedBeatData selectedBeatData = new SelectedBeatData();
        selectedBeatData.setCircleId(circleId);
        selectedBeatData.setCircleName(circleName);
        selectedBeatData.setDivisionId(divisionId);
        selectedBeatData.setDivisionName(divisionName);
        selectedBeatData.setSubDivisionId(subdivisionId);
        selectedBeatData.setSubDivisionName(subdivisionName);
        selectedBeatData.setRangeId(rangeId);
        selectedBeatData.setRangeName(rangeName);
        selectedBeatData.setBeatId(beatId);
        selectedBeatData.setBeatName(beatName);
        Gson gson = new Gson();
        String data = gson.toJson(selectedBeatData);
        kadooLocalUser.setSelectedCategory(data);


    }
}
