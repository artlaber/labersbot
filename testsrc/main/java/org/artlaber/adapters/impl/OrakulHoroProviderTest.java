package org.artlaber.adapters.impl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by artem on 7/4/16.
 */
public class OrakulHoroProviderTest {

    private OrakulHoroProvider horoProvider = new OrakulHoroProvider();

    @Test
    public void testAdapter() {
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("овен",1));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("телец",2));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("близнецы",3));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("рак",4));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("лев",1));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("дева",2));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("весы",3));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("скорпион",4));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("стрелец",1));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("козерог",2));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("водолей",0));
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("рыбы",0));
    }
}
