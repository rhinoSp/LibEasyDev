package com.rhino.ed;

import com.rhino.ui.base.BaseApplication;

/**
 * @author LuoLin
 * @since Create on 2019/4/12.
 */
public class AppApplication extends BaseApplication {
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
