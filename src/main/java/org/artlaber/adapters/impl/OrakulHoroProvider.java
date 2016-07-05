package org.artlaber.adapters.impl;

import org.artlaber.adapters.HoroskopeGetter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by artem on 7/4/16.
 */
public class OrakulHoroProvider implements HoroskopeGetter {

    private final Map<String, String> horo2keyMap = new HashMap(){{
        put("овен", "aries");
        put("телец", "taurus");
        put("близнецы", "gemini");
        put("рак", "cancer");
        put("лев", "lion");
        put("дева", "virgo");
        put("весы", "libra");
        put("скорпион", "scorpio");
        put("стрелец", "sagittarius");
        put("козерог", "capricorn");
        put("водолей", "aquarius");
        put("рыбы", "pisces");
    }};

    public static final String BASE_URL = "http://orakul.com/horoscope/astrologic/";
    public static final String TODAY_MORE = "more/";
    public static final String LOVE = "love/";
    public static final String FAMILY = "family/";
    public static final String FLIRT = "flirt/";
    public static final String TODAY_ENDING = "/today.html";

    @Override
    public String getHoroskopeForSign(String sign, int type) {
        try {
            Document doc = Jsoup.connect(getSignURL(sign, type)).get();
            String textContents = doc.select(".horoBlock").first().text();
            return textContents;
        } catch (NullPointerException e) {
            //
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getCommonHoroskopeForAll() {
        return null;
    }

    private String getSignURL (String sign, int type) throws NullPointerException {
        StringBuilder result = new StringBuilder(BASE_URL);

        switch (type) {
            case 1:
                result.append(TODAY_MORE);
                break;
            case 2:
                result.append(LOVE);
                break;
            case 3:
                result.append(FAMILY);
                break;
            case 4:
                result.append(FLIRT);
                break;
            default:
                result.append(TODAY_MORE);
                break;
        }

        Set<String> signs = horo2keyMap.keySet();
        if (!signs.contains(sign)) {
            throw new NullPointerException("Entered sign is not found");
        }

        result.append(horo2keyMap.get(sign)).append(TODAY_ENDING);
        return result.toString();
    }
}
