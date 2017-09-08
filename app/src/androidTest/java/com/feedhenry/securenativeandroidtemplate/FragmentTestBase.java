package com.feedhenry.securenativeandroidtemplate;

import android.app.Fragment;
import android.support.test.rule.ActivityTestRule;

import com.feedhenry.securenativeandroidtemplate.navigation.Navigator;

import org.junit.Rule;

/**
 * Created by weili on 04/09/2017.
 */

class FragmentTestBase {
    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);

    public void loadFragment(Fragment fragmentUnderTest) {
        Navigator navigator = new Navigator();
        navigator.loadFragment(activityRule.getActivity(), fragmentUnderTest);
    }
}
