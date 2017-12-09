package kadoo.myecotrip.kadoo.nfc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;


import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.core.PillerInfo;

public class KadooTagNFCWrite extends AppCompatActivity {
    PillowNfcManager nfcManager;
    WriteTagHelper writeHelper;

    private  EditText input_piller_name,
            input_create_piller_id,input_bet_id,input_lat,input_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kadoo_tag_nfcwrite);
        init();
    }

    private void init() {
        nfcManager = new PillowNfcManager(this);
        nfcManager.onActivityCreate();

        input_piller_name = (EditText) findViewById(R.id.input_piller_name);
        input_create_piller_id = (EditText) findViewById(R.id.input_create_piller_id);
        input_bet_id = (EditText) findViewById(R.id.input_bet_id);
        input_lat = (EditText) findViewById(R.id.input_lat);
        input_lng = (EditText) findViewById(R.id.input_lng);

        writeHelper = new WriteTagHelper(this, nfcManager);
        nfcManager.setOnTagWriteErrorListener(writeHelper);
        nfcManager.setOnTagWriteListener(writeHelper);

        findViewById(R.id.btnWriteNFC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(input_piller_name.getText().toString())) {
                    if (!TextUtils.isEmpty(input_create_piller_id.getText().toString())) {
                        if (!TextUtils.isEmpty(input_bet_id.getText().toString())) {
                            PillerInfo pillerInfo = new PillerInfo();
                            pillerInfo.setPillerName(input_piller_name.getText().toString());
                            pillerInfo.setCreate_piller_id(input_create_piller_id.getText().toString());
                            pillerInfo.setBet_id(input_bet_id.getText().toString());
                            pillerInfo.setLat(LocationActivity.latitude);
                            pillerInfo.setLng(LocationActivity.longitude);
                            String jsonObject = new Gson().toJson(pillerInfo);
                            writeHelper.writeText(jsonObject);
                        }
                    } else {
                        Toast.makeText(KadooTagNFCWrite.this, "Please enter create piller id.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KadooTagNFCWrite.this, "Please enter piller name.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onNewIntent(Intent intent) {
        nfcManager.onActivityNewIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

}
