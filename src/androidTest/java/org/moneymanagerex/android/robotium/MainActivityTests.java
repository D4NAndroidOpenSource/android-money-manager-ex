/*
 * Copyright (C) 2012-2015 The Android Money Manager Ex Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.moneymanagerex.android.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.money.manager.ex.home.MainActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moneymanagerex.android.testhelpers.UiTestHelpers;

/**
 * Robotium tests for the Main Activity.
 * Created by Alen Siljak on 24/09/2015.
 */
public class MainActivityTests
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private MainActivity testObject;
    private UiTestHelpers helper;

    public MainActivityTests() {
        super(MainActivity.class);

    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());

        this.testObject = getActivity();
        this.helper = new UiTestHelpers(solo);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        if (solo != null) {
            solo.finishOpenedActivities();
        }
    }

    @Test
    public void testCreation() {
        MainActivity activity = new MainActivity();

        assertNotNull(activity);
    }

    @Test
    public void welcomeViewDisplayed() {
        solo.waitForText("Welcome to MoneyManagerEx");

        //assert
    }

}
