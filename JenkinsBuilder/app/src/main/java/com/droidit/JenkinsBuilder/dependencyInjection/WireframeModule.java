package com.droidit.JenkinsBuilder.dependencyInjection;

import android.app.Activity;

import com.droidit.domain.basicExample.BasicExampleContract;
import com.droidit.JenkinsBuilder.basicExample.BasicExampleWireframe;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JohannesC on 07-Sep-16.
 */
@Module
public class WireframeModule {

    private final Activity activity;

    public WireframeModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @Provides
    public BasicExampleContract.WireFrame provideBasicExampleWireframe(BasicExampleWireframe basicExampleWireframe) {
        return basicExampleWireframe;
    }
}
