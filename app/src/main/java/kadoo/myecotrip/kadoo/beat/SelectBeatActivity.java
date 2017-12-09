package kadoo.myecotrip.kadoo.beat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.beat.rowData.CircleRowData;

public class SelectBeatActivity extends AppCompatActivity {

    private Spinner spCircle, spDivision, spSubDivision, spRange, spBeats;


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

        ArrayAdapter<CircleRowData> arrayAdapter=new ArrayAdapter<CircleRowData>(this,R.l)
        spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setDivisiondata() {
        spDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSubDivisionData() {
        spSubDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setRangeData() {
        spRange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setBeats() {
        spBeats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
