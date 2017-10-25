package Utils;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by 白玉春 on 2017/10/25.
 */

public class CacheProvide {

    Context context;

    public CacheProvide(Context context) {
        this.context = context;
    }

    public Cache provideCache(){
        return  new Cache(context.getCacheDir() ,10240*1204);
    }
}
