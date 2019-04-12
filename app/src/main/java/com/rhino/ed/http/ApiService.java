package com.rhino.ed.http;


import com.rhino.ed.http.result.HomeModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;


/**
 * @author LuoLin
 * @since Create on 2019/2/19.
 */
public interface ApiService {

    String BASE_URL = "http://www.51jiaji.cn:802/crm/";

    @POST("MP/GetVersion")
    Observable<BaseResult<String>> getHomeModel();


}