package com.droidit.JenkinsBuilder.basicExample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.droidit.domain.basicExample.BasicExampleContract;
import com.droidit.JenkinsBuilder.DefaultApplication;
import com.droidit.JenkinsBuilder.R;
import com.droidit.JenkinsBuilder.dependencyInjection.ApplicationComponent;
import com.droidit.JenkinsBuilder.dependencyInjection.BasicExampleComponent;
import com.droidit.JenkinsBuilder.dependencyInjection.DaggerBasicExampleComponent;
import com.droidit.JenkinsBuilder.dependencyInjection.NetworkModule;
import com.droidit.JenkinsBuilder.dependencyInjection.WireframeModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JohannesC on 30-May-16.
 * Basic activity which shows MVP, navigation, and some networking logic
 */
public class BasicExampleActivity extends AppCompatActivity implements BasicExampleContract.View {

    @BindView(R.id.activity_main_posts_tv)
    TextView activity_main_posts_tv;

    @Inject
    BasicExampleContract.Presenter basicExamplePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.initializeInjector();
        basicExamplePresenter.onCreate(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((DefaultApplication) this.getApplication()).getMainComponent();
    }

    private void initializeInjector() {
        BasicExampleComponent basicExampleComponent = DaggerBasicExampleComponent.builder()
                .applicationComponent(getApplicationComponent())
                .networkModule(new NetworkModule("http://jsonplaceholder.typicode.com/"))
                .wireframeModule(new WireframeModule(this))
                .build();
        basicExampleComponent.inject(this);
    }

    @OnClick(R.id.activity_main_launch_connection_btn)
    public void onLaunchConnectionBtnClick() {
        basicExamplePresenter.onConnectionButtonClicked();
    }

    @OnClick(R.id.activity_main_get_posts_btn)
    public void onGetPostsBtnClick() {
        basicExamplePresenter.onGetPostsBtnClicked();
    }

    @Override
    public void displaySinglePostTitle(String title) {
        activity_main_posts_tv.setTextColor(Color.BLACK);
        activity_main_posts_tv.setText(title);
    }

    @Override
    public void displayConnectionError(String message) {
        activity_main_posts_tv.setTextColor(Color.RED);
        activity_main_posts_tv.setText(message);
    }
}
