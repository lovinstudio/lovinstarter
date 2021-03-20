package com.eelve.lovinstarter.utils;

/**
 * @ClassName CoordTransformUtil
 * @Description TODO
 * @Author zhao.zhilue
 * @Date 2021/3/20 20:57
 * @Version 1.0
 **/
public class CoordTransformUtil {

    private static double x_PI = Math.PI * 3000.0 / 180.0;
    private static double PI = Math.PI;
    private static double a = 6378245.0;
    private static double ee = 0.00669342162296594323;

    /**
     * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换
     * 即 百度 转 谷歌、高德
     *
     * @param bd_lon
     * @param bd_lat
     * @return Double[lon, lat]
     */
    public static Double[] BD09ToGCJ02(Double bd_lon, Double bd_lat) {
        double x = bd_lon - 0.0065;
        double y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_PI);
        Double[] arr = new Double[2];
        arr[0] = z * Math.cos(theta);
        arr[1] = z * Math.sin(theta);
        return arr;
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
     * 即谷歌、高德 转 百度
     *
     * @param gcj_lon
     * @param gcj_lat
     * @return Double[lon, lat]
     */
    public static Double[] GCJ02ToBD09(Double gcj_lon, Double gcj_lat) {
        double z = Math.sqrt(gcj_lon * gcj_lon + gcj_lat * gcj_lat) + 0.00002 * Math.sin(gcj_lat * x_PI);
        double theta = Math.atan2(gcj_lat, gcj_lon) + 0.000003 * Math.cos(gcj_lon * x_PI);
        Double[] arr = new Double[2];
        arr[0] = z * Math.cos(theta) + 0.0065;
        arr[1] = z * Math.sin(theta) + 0.006;
        return arr;
    }

    /**
     * WGS84转GCJ02
     *
     * @param wgs_lon
     * @param wgs_lat
     * @return Double[lon, lat]
     */
    public static Double[] WGS84ToGCJ02(Double wgs_lon, Double wgs_lat) {
        if (outOfChina(wgs_lon, wgs_lat)) {
            return new Double[]{wgs_lon, wgs_lat};
        }
        double dlat = transformlat(wgs_lon - 105.0, wgs_lat - 35.0);
        double dlng = transformlng(wgs_lon - 105.0, wgs_lat - 35.0);
        double radlat = wgs_lat / 180.0 * PI;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
        Double[] arr = new Double[2];
        arr[0] = wgs_lon + dlng;
        arr[1] = wgs_lat + dlat;
        return arr;
    }

    /**
     * GCJ02转WGS84
     *
     * @param gcj_lon
     * @param gcj_lat
     * @return Double[lon, lat]
     */
    public static Double[] GCJ02ToWGS84(Double gcj_lon, Double gcj_lat) {
        double initDelta = 0.01;
        double threshold = 0.000000001;
        double dlat = initDelta;
        double dlon = initDelta;
        double mlat = gcj_lat - dlat;
        double mlon = gcj_lon - dlon;
        double plat = gcj_lat + dlat;
        double plon = gcj_lon + dlon;
        double wgs_lat;
        double wgs_lon;
        int i = 0;
        while (true) {
            wgs_lat = (mlat + plat) / 2;
            wgs_lon = (mlon + plon) / 2;
            Double[] tmp = WGS84ToGCJ02(wgs_lon, wgs_lat);
            dlat = tmp[1] - gcj_lat;
            dlon = tmp[0] - gcj_lon;
            if ((Math.abs(dlat) < threshold) && (Math.abs(dlon) < threshold)) {
                break;
            }
            if (dlat > 0) {
                plat = wgs_lat;
            } else {
                mlat = wgs_lat;
            }
            if (dlon > 0) {
                plon = wgs_lon;
            } else {
                mlon = wgs_lon;
            }
            if (++i > 10000) {
                break;
            }
        }
        Double[] arr = new Double[2];
        arr[0] = wgs_lon;
        arr[1] = wgs_lat;
        return arr;
    }

    private static Double transformlat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static Double transformlng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * outOfChina
     *
     * @param lng
     * @param lat
     * @return {boolean}
     * @描述: 判断是否在国内，不在国内则不做偏移
     */
    private static boolean outOfChina(Double lng, Double lat) {
        return (lng < 73.66 || lng > 135.05) || ((lat < 3.86 || lat > 53.55) || false);
    }

    ;

}
