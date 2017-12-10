package kadoo.myecotrip.kadoo.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.common.KadooLocalUser;
import kadoo.myecotrip.kadoo.network.RestClient;


public abstract class BaseActivity extends AppCompatActivity {

    protected RestClient restClient;
    protected KadooLocalUser kadooLocalUser;
    private ProgressDialog mProgressDialog;

    private static final String HEADLESS_FRAGMENT = "head_less_fragment";
    private static final String PROGRESS_FRAGMENT = "progress_fragment";

    protected abstract void initView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        restClient = new RestClient();
        kadooLocalUser = KadooLocalUser.getInstance();
    }

    protected void setToolBar(String title) {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    protected void displayProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Wait...");
        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
