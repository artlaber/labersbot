package org.artlaber.adapters;

/**
 * Created by artem on 7/4/16.
 */
public interface HoroskopeGetter {

    public String getHoroskopeForSign(String sign, int type);

    public String getHoroskopeForSign(String sign);

    public String getCommonHoroskopeForAll();
}
