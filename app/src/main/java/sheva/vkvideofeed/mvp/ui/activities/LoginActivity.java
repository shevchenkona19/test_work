package sheva.vkvideofeed.mvp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.vkvideofeed.R;
import sheva.vkvideofeed.mvp.datamanager.DataManager;
import sheva.vkvideofeed.mvp.presenter.activities.LoginActivityPresenter;
import sheva.vkvideofeed.mvp.ui.interfaces.ILoginActivityView;

public class LoginActivity extends AppCompatActivity implements ILoginActivityView{
    String[] scope = new String[]{
            "video",
            "wall",
            "friends"
    };
    @BindView(R.id.btnLoginVk)
    Button btnLoginVk;
    private final LoginActivityPresenter presenter = new LoginActivityPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.bind(this);
        presenter.checkLogged();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLoginVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.login(LoginActivity.this, scope);
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                presenter.onVkAuthorised(res.accessToken);
            }

            @Override
            public void onError(VKError error) {
                Toast.makeText(LoginActivity.this, "Error in vk login", Toast.LENGTH_SHORT).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onLoggedSuccessfully() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }
}
