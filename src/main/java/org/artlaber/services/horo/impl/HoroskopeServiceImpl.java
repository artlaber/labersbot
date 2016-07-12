package org.artlaber.services.horo.impl;

import org.artlaber.adapters.HoroskopeGetter;
import org.artlaber.adapters.impl.OrakulHoroProvider;
import org.artlaber.services.horo.HoroskopeService;

/**
 * Created by artem on 7/4/16.
 */
public class HoroskopeServiceImpl implements HoroskopeService {

    private HoroskopeGetter horoProvider = new OrakulHoroProvider();

    @Override
    public String getCommonDailyHoro(String sign) {
        return horoProvider.getHoroskopeForSign(sign);
    }

    @Override
    public String getWorkingDailyHoro(String sign) {

        return null;
    }

    @Override
    public void rememberMe(String name) {
        // TODO:
    }

    @Override
    public String whoAmI(String name) {
        return null;
    }
}
