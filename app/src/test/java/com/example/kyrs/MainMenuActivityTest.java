package com.example.kyrs;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

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
public class MainMenuActivityTest extends TestCase {
    public MainMenuActivity activity;
    public SearchPhotosActivity NextActivity;
    public Button pressMeButton;
    public ShadowActivity shadowActivity;


    @Before
    public void setup() {
        //activity = new MainMenuActivity();
        //shadowActivity = Shadows.shadowOf(activity);
        activity = new MainMenuActivity();
        activity = Robolectric.buildActivity(MainMenuActivity.class).create().get();
        //activity.onCreate(null);
        //pressMeButton = (Button) activity.findViewById(R.id.button);
    }

    @Test
    public void testAddOnButton() {
        //pressMeButton.performClick();
        //assertEquals(activity, new ComponentName(activity, SearchPhotosActivity.class));
   ////////////////////////////////////////////////////////////////
        activity.findViewById(R.id.button).performClick();

        Intent expectedIntent = new Intent(activity, SearchPhotosActivity.class);

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        actualIntent.filterEquals(expectedIntent);
        assertEquals(actualIntent.getStringExtra(SearchPhotosActivity.EXTRA_MESSAGE), null);
        assertEquals(actualIntent.getAction(), ".SearchPhotosActivity");
        //assertTrue(actualIntent.filterEquals(expectedIntent));
        /////////////////////////////////////////
        /*Intent intent = shadowActivity.peekNextStartedActivityForResult().intent;
        assertEquals(intent.getStringExtra(SearchPhotosActivity.EXTRA_MESSAGE), "blebleble");
        assertEquals(intent.getComponent(), new ComponentName(activity, SearchPhotosActivity.class));*/
    }
}