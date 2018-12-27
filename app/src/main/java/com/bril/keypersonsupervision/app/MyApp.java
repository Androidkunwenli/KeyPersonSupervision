package com.bril.keypersonsupervision.app;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;

import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.MapTileIndex;

public class MyApp extends Application {
    //地图
    public static XYTileSource tianDiTuImgTileSource = new XYTileSource("Tian Di Tu Img",
            1, 22,
            256,
            ".png",
            new String[]{"http://110.249.155.242:2211/mapServer/shijiazhuang1-17/788865972"}) {
        @Override
        public String getTileURLString(final long pMapTileIndex) {
            String url = getBaseUrl() + "/" + MapTileIndex.getZoom(pMapTileIndex) + "/" + MapTileIndex.getX(pMapTileIndex)
                    + "/" + MapTileIndex.getY(pMapTileIndex) + mImageFilenameEnding;
            Log.e("url",url);
            return url;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        OkGo.getInstance().init(this);
    }
}
