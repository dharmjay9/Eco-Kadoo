package kadoo.myecotrip.kadoo.nfcOperator;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseActivity;
import kadoo.myecotrip.kadoo.base.CommonResponse;
import kadoo.myecotrip.kadoo.beats.AddBeatsRequest;
import kadoo.myecotrip.kadoo.beats.AddBeatsResponse;
import kadoo.myecotrip.kadoo.common.KadooAppUser;
import kadoo.myecotrip.kadoo.common.SelectedBeatData;
import kadoo.myecotrip.kadoo.core.PillerInfo;
import kadoo.myecotrip.kadoo.home.HomeActivity;
import kadoo.myecotrip.kadoo.login.KadooUser;
import kadoo.myecotrip.kadoo.network.ErrorCodes;
import kadoo.myecotrip.kadoo.network.KadooCallBack;
import kadoo.myecotrip.kadoo.network.RestClient;

public class NFCOperationActivity extends BaseActivity implements Listener, OperationListener {

    public static final String TAG = NFCOperationActivity.class.getSimpleName();
    public static final String PILLEAR_DETAILS = "pillear_data";

    //private EditText mEtMessage;
    private Button mBtWrite;
    private Button mBtRead;

    private NFCWriteFragment mNfcWriteFragment;
    private NFCReadFragment mNfcReadFragment;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;
    private String pillearDetails;

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_operation);

        initViews();
        initNFC();
    }

    private void initViews() {

        //mEtMessage = (EditText) findViewById(R.id.et_message);
        pillearDetails = getIntent().getExtras().getString(PILLEAR_DETAILS);
        mBtWrite = (Button) findViewById(R.id.btn_write);
        mBtRead = (Button) findViewById(R.id.btn_read);
        showWriteFragment();

        mBtWrite.setOnClickListener(view -> showWriteFragment());
        mBtRead.setOnClickListener(view -> showReadFragment());
    }

    private void initNFC() {

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }


    private void showWriteFragment() {

        isWrite = true;

        mNfcWriteFragment = (NFCWriteFragment) getFragmentManager().findFragmentByTag(NFCWriteFragment.TAG);

        if (mNfcWriteFragment == null) {

            mNfcWriteFragment = NFCWriteFragment.newInstance();
        }
        mNfcWriteFragment.show(getFragmentManager(), NFCWriteFragment.TAG);

    }

    private void showReadFragment() {

        mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);

        if (mNfcReadFragment == null) {

            mNfcReadFragment = NFCReadFragment.newInstance();
        }
        mNfcReadFragment.show(getFragmentManager(), NFCReadFragment.TAG);

    }

    @Override
    public void onDialogDisplayed() {

        isDialogDisplayed = true;
    }

    @Override
    public void onDialogDismissed() {

        isDialogDisplayed = false;
        isWrite = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected, tagDetected, ndefDetected};

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        Log.d(TAG, "onNewIntent: " + intent.getAction());

        if (tag != null) {
          //  Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
            Ndef ndef = Ndef.get(tag);

            if (isDialogDisplayed) {

                if (isWrite) {


                    mNfcWriteFragment = (NFCWriteFragment) getFragmentManager().findFragmentByTag(NFCWriteFragment.TAG);
                    mNfcWriteFragment.onNfcDetected(ndef, pillearDetails);

                } else {

                    mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);
                }
            }
        }
    }

    @Override
    public void onSuccess() {
        // Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        PillerSuccessFragment pillerSuccessFragment = new PillerSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PILLEAR_DETAILS, pillearDetails);
        pillerSuccessFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, pillerSuccessFragment).commit();
    }

    @Override
    public void onFailure() {
       // Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show();

    }
}
