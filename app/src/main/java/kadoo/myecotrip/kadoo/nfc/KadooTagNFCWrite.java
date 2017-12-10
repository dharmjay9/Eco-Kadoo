package kadoo.myecotrip.kadoo.nfc;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseActivity;
import kadoo.myecotrip.kadoo.common.CommonUtils;
import kadoo.myecotrip.kadoo.common.KadooAppUser;
import kadoo.myecotrip.kadoo.common.SelectedBeatData;
import kadoo.myecotrip.kadoo.core.PillerInfo;
import kadoo.myecotrip.kadoo.home.HomeActivity;
import kadoo.myecotrip.kadoo.nfcOperator.NFCOperationActivity;

public class KadooTagNFCWrite extends BaseActivity {
    private PillowNfcManager nfcManager;
    private WriteTagHelper writeHelper;
    private EditText input_piller_name, etLat, etLong;
    private TextInputLayout tilLat, tilLong;
    private boolean isManual;
    private TextView tvPillearId;
    private SelectedBeatData selectedBeatData;
    private String pillearId;
    private String lat, longi;

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kadoo_tag_nfcwrite);
        init();
    }

    private void init() {
        isManual = getIntent().getBooleanExtra(LocationActivity.IS_MANUAL, false);
        lat = getIntent().getStringExtra(LocationActivity.LAT);
        longi = getIntent().getStringExtra(LocationActivity.LONG);
        selectedBeatData = KadooAppUser.getInstnace().getSelectedBeatData();
        pillearId = CommonUtils.getModifyString(selectedBeatData.getCircleName()) + "-" + CommonUtils.getModifyString(selectedBeatData.getDivisionName()) +
                "-" + CommonUtils.getModifyString(selectedBeatData.getSubDivisionName()) + "-" + CommonUtils.getModifyString(selectedBeatData.getRangeName()) +
                "-" + CommonUtils.getModifyString(selectedBeatData.getBeatName()) + System.currentTimeMillis();
        nfcManager = new PillowNfcManager(this);
        nfcManager.onActivityCreate();
        input_piller_name = findViewById(R.id.input_piller_name);
        etLat = findViewById(R.id.etLat);
        etLong = findViewById(R.id.etLong);
        tilLat = findViewById(R.id.tilLat);
        tilLong = findViewById(R.id.tilLong);
        tvPillearId = findViewById(R.id.tvPillerId);
        tvPillearId.setText(pillearId);
        if (isManual) {
            tilLong.setVisibility(View.VISIBLE);
            tilLat.setVisibility(View.VISIBLE);
        }

        writeHelper = new WriteTagHelper(this, nfcManager);
        nfcManager.setOnTagWriteErrorListener(writeHelper);
        nfcManager.setOnTagWriteListener(writeHelper);

        findViewById(R.id.btnWriteNFC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(input_piller_name.getText().toString())) {
                    if (isManual) {
                        if (TextUtils.isEmpty(etLat.getText().toString())) {
                            Toast.makeText(KadooTagNFCWrite.this, "Lat is empty", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (TextUtils.isEmpty(etLong.getText().toString())) {
                            Toast.makeText(KadooTagNFCWrite.this, "Long is empty", Toast.LENGTH_LONG).show();

                            return;
                        }
                    }
                    PillerInfo pillerInfo = new PillerInfo();
                    pillerInfo.setPillerName(input_piller_name.getText().toString());
                    if (isManual) {
                        pillerInfo.setLat(etLat.getText().toString());
                        pillerInfo.setLng(etLong.getText().toString());
                    } else {
                        pillerInfo.setLat(lat);
                        pillerInfo.setLng(longi);
                    }

                    pillerInfo.setCreate_piller_id(pillearId);
                    String jsonObject = new Gson().toJson(pillerInfo);
                    Intent intent = new Intent(KadooTagNFCWrite.this, NFCOperationActivity.class);
                    intent.putExtra(NFCOperationActivity.PILLEAR_DETAILS, jsonObject);
                    startActivity(intent);
                    // finishAffinity();
                    // startActivity(new Intent(KadooTagNFCWrite.this, HomeActivity.class));
                    // writeHelper.writeText(jsonObject);

                } else {
                    Toast.makeText(KadooTagNFCWrite.this, "Please enter piller name.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setToolBar("Pillear Details");

    }

    @Override
    public void onNewIntent(Intent intent) {
        nfcManager.onActivityNewIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
