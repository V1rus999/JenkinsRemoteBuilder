package com.droidit.JenkinsBuilder.dependencyInjection;

import com.droidit.JenkinsBuilder.basicExample.BasicExampleActivity;

import dagger.Component;

/**
 * Created by JohannesC on 05-Sep-16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {NetworkModule.class, WireframeModule.class, PresenterModule.class})
public interface BasicExampleComponent {

    void inject(BasicExampleActivity basicExampleActivity);

}
