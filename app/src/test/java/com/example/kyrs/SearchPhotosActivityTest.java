package com.example.kyrs;

import android.content.Intent;
import android.os.Build;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;
import org.robolectric.shadows.ShadowActivity;

import static org.robolectric.annotation.LooperMode.Mode.PAUSED;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
@LooperMode(PAUSED)
public class SearchPhotosActivityTest extends TestCase {
    private SearchPhotosActivity activity;
    
    @Before
    public void setup(){
        activity = new SearchPhotosActivity();
        activity = Robolectric.buildActivity(SearchPhotosActivity.class).create().get();
    }

    @Test
    public void testAddOnButton() {
        activity.findViewById(R.id.buttonIm6).performClick();
        activity.findViewById(R.id.buttonIm5).performClick();
        activity.findViewById(R.id.buttonIm4).performClick();
        activity.findViewById(R.id.buttonIm3).performClick();
        activity.findViewById(R.id.buttonIm2).performClick();

        Intent expectedIntent1 = new Intent(activity, Information.class);
        Intent expectedIntent2 = new Intent(activity, Information_area.class);
        Intent expectedIntent3 = new Intent(activity, Information_downsizing.class);
        Intent expectedIntent4 = new Intent(activity, Information_number.class);
        Intent expectedIntent5 = new Intent(activity, Information_security.class);

        ShadowActivity shadowActivity1 = Shadows.shadowOf(activity);
        ShadowActivity shadowActivity2 = Shadows.shadowOf(activity);
        ShadowActivity shadowActivity3 = Shadows.shadowOf(activity);
        ShadowActivity shadowActivity4 = Shadows.shadowOf(activity);
        ShadowActivity shadowActivity5 = Shadows.shadowOf(activity);

        Intent actualIntent1 = shadowActivity1.getNextStartedActivity();
        Intent actualIntent2 = shadowActivity2.getNextStartedActivity();
        Intent actualIntent3 = shadowActivity3.getNextStartedActivity();
        Intent actualIntent4 = shadowActivity4.getNextStartedActivity();
        Intent actualIntent5 = shadowActivity5.getNextStartedActivity();

        actualIntent1.filterEquals(expectedIntent1);
        actualIntent2.filterEquals(expectedIntent2);
        actualIntent3.filterEquals(expectedIntent3);
        actualIntent4.filterEquals(expectedIntent4);
        actualIntent5.filterEquals(expectedIntent5);

        assertEquals(actualIntent1.getStringExtra(Information_number.EXTRA_MESSAGE), null);
        assertEquals(actualIntent1.getAction(), ".Information_number");

        assertEquals(actualIntent2.getStringExtra(Information_security.EXTRA_MESSAGE), null);
        assertEquals(actualIntent2.getAction(), ".Information_security");

        assertEquals(actualIntent3.getStringExtra(Information_area.EXTRA_MESSAGE), null);
        assertEquals(actualIntent3.getAction(), ".Information_area");

        assertEquals(actualIntent4.getStringExtra(Information_downsizing.EXTRA_MESSAGE), null);
        assertEquals(actualIntent4.getAction(), ".Information_downsizing");

        assertEquals(actualIntent5.getStringExtra(Information.EXTRA_MESSAGE), null);
        assertEquals(actualIntent5.getAction(), ".Information");
        
    }
}