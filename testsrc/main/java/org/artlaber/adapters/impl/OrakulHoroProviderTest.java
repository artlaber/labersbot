package org.artlaber.adapters.impl;

import org.artlaber.updateshandlers.impl.HoroskopeHandler;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.api.objects.Update;

import static org.junit.Assert.assertNull;

/**
 * Created by artem on 7/4/16.
 */
public class OrakulHoroProviderTest {

    private OrakulHoroProvider horoProvider = new OrakulHoroProvider();

    private HoroskopeHandler horoskopeHandler = new HoroskopeHandler();

    @Test
    public void testAdapter() {
        Assert.assertNotNull(horoProvider.getHoroskopeForSign("овен",1));
        System.out.println(horoProvider.getHoroskopeForSign("водолей"));
    }
}
