package org.artlaber.services.horo;

/**
 * Created by artem on 7/4/16.
 */
public interface HoroskopeService {

    public String getCommonDailyHoro(String sign, int type);

    public String getWorkingDailyHoro(String sign);

    public void rememberMe(String name);

    public String whoAmI(String name);
}
