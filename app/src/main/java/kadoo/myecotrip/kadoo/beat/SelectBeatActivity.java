package kadoo.myecotrip.kadoo.beat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.beat.rowData.BeatRowData;
import kadoo.myecotrip.kadoo.beat.rowData.CircleResponse;
import kadoo.myecotrip.kadoo.beat.rowData.CircleRowData;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.DivisionRowData;
import kadoo.myecotrip.kadoo.beat.rowData.RangeRowData;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionResponse;
import kadoo.myecotrip.kadoo.beat.rowData.SubDivisionRowData;
import kadoo.myecotrip.kadoo.network.ErrorCodes;
import kadoo.myecotrip.kadoo.network.KadooCallBack;
import kadoo.myecotrip.kadoo.network.RestClient;

public class SelectBeatActivity extends AppCompatActivity {

    private Spinner spCircle, spDivision, spSubDivision, spRange, spBeats;
    private String circleId, divisionId, subdivisionId, rangeId, pillerId;
    private List<CircleRowData> circleRowDataList;
    private List<DivisionRowData> divisionRowDataList;
    private List<SubDivisionRowData> subDivisionRowDataList;
    private List<RangeRowData> rangeRowDataList;
    private List<BeatRowData> beatRowDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_beat);
        spCircle = findViewById(R.id.spCircle);
        spDivision = findViewById(R.id.spDivision);
        spSubDivision = findViewById(R.id.spSubDivision);
        spRange = findViewById(R.id.spRange);
        spBeats = findViewById(R.id.spBeat);
        setCircleData();
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


        RestClient.getInstance().getCircle(new KadooCallBack<CircleResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(CircleResponse circleResponse) {

                ArrayAdapter<CircleRowData> arrayAdapter = new ArrayAdapter<CircleRowData>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, circleResponse.getContent());
                spCircle.setAdapter(arrayAdapter);
                spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

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
        RestClient.getInstance().getCircle(new KadooCallBack<CircleResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(CircleResponse circleResponse) {

                ArrayAdapter<CircleRowData> arrayAdapter = new ArrayAdapter<CircleRowData>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, circleResponse.getContent());
                spCircle.setAdapter(arrayAdapter);
                spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

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
        RestClient.getInstance().getCircle(new KadooCallBack<CircleResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(CircleResponse circleResponse) {

                ArrayAdapter<CircleRowData> arrayAdapter = new ArrayAdapter<CircleRowData>(SelectBeatActivity.this, android.R.layout.simple_list_item_1, circleResponse.getContent());
                spCircle.setAdapter(arrayAdapter);
                spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        setDivisiondata();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }
}
