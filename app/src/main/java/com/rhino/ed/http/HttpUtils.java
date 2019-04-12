package com.rhino.ed.http;

/**
 * @author LuoLin
 * @since Create on 2019/2/19.
 */
public class HttpUtils extends com.rhino.libretrofit2okhttp3.HttpUtils {

    private static ApiService mApiService = null;

    public static void init() {
        BASE_URL = ApiService.BASE_URL;
        mApiService = instance().getService(ApiService.class);
    }

    public static ApiService getApiService() {
        if (mApiService == null) {
            init();
        }
        return mApiService;
    }

}
