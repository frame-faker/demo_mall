package com.sanbangzi.common.utils.okhttp;

import java.io.File;
import java.util.List;
import java.util.Map;

public class OkHttpUtil {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";


    /**
     * get请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpGet(String url, CallBackUtil callBack) {
        okHttpGet(url, null, null, callBack);
    }


    /**
     * get同步请求
     *
     * @param url：url
     */
    public static String okHttpSynGet(String url) {
        return okHttpSynGet(url, null);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpGet(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpGet(url, paramsMap, null, callBack);
    }

    /**
     * get同步请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String okHttpSynGet(String url, Map<String, String> paramsMap) {
        return okHttpSynGet(url, paramsMap, null);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_GET, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * get同步请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_GET, url, paramsMap, headerMap, null).newCall();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPost(String url, CallBackUtil callBack) {
        okHttpPost(url, null, callBack);
    }


    /**
     * post同步请求
     *
     * @param url：url
     */
    public static String okHttpSynPost(String url) {
        return okHttpSynPost(url, null);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPost(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpPost(url, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String okHttpSynPost(String url, Map<String, String> paramsMap) {
        return okHttpSynPost(url, paramsMap, null);
    }


    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPost(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post 同步请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynPost(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_POST, url, paramsMap, headerMap, null).newCall();
    }

    /**
     * put请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPut(String url, CallBackUtil callBack) {
        okHttpPut(url, null, callBack);
    }


    /**
     * put同步请求
     *
     * @param url：url
     */
    public static String okHttpSynPut(String url) {
        return okHttpSynPut(url, null);
    }

    /**
     * put请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPut(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpPut(url, paramsMap, null, callBack);
    }

    /**
     * put同步请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String okHttpSynPut(String url, Map<String, String> paramsMap) {
        return okHttpSynPut(url, paramsMap, null);
    }

    /**
     * put请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPut(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_PUT, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * put同步请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynPut(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_PUT, url, paramsMap, headerMap, null).newCall();
    }

    /**
     * delete请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpDelete(String url, CallBackUtil callBack) {
        okHttpDelete(url, null, callBack);
    }


    /**
     * delete请求
     *
     * @param url：url
     */
    public static String okHttpSynDelete(String url) {
        return okHttpSynDelete(url, null);
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpDelete(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpDelete(url, paramsMap, null, callBack);
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String okHttpSynDelete(String url, Map<String, String> paramsMap) {
        return okHttpSynDelete(url, paramsMap, null);
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpDelete(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_DELETE, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynDelete(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_DELETE, url, paramsMap, headerMap, null).newCall();
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPostJson(String url, String jsonStr, CallBackUtil callBack) {
        okHttpPostJson(url, jsonStr, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPostJson(String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, jsonStr, headerMap, callBack).execute();
    }


    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     */
    public static String okHttpSynPostJson(String url, String jsonStr) {
        return okHttpSynPostJson(url, jsonStr, null);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynPostJson(String url, String jsonStr, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_POST, url, jsonStr, headerMap, null).newCall();
    }

    /**
     * post请求，可以传递参数,获取返回流
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static byte[] okHttpSynPostByte(String url, String jsonStr, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_POST, url, jsonStr, headerMap, null).newByteImg();
    }


    /**
     * put请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPutJson(String url, String jsonStr, CallBackUtil callBack) {
        okHttpPutJson(url, jsonStr, null, callBack);
    }

    /**
     * put请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpPutJson(String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_PUT, url, jsonStr, headerMap, callBack).execute();
    }


    /**
     * put请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     */
    public static String okHttpSynPutJson(String url, String jsonStr) {
        return okHttpSynPutJson(url, jsonStr, null);
    }

    /**
     * put请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynPutJson(String url, String jsonStr, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_PUT, url, jsonStr, headerMap, null).newCall();
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的键参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpDeleteJson(String url, String jsonStr, CallBackUtil callBack) {
        okHttpDeleteJson(url, jsonStr, null, callBack);
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpDeleteJson(String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_DELETE, url, jsonStr, headerMap, callBack).execute();
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的键参数
     */
    public static String okHttpSynDeleteJson(String url, String jsonStr) {
        return okHttpSynDeleteJson(url, jsonStr, null);
    }

    /**
     * delete请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpSynDeleteJson(String url, String jsonStr, Map<String, String> headerMap) {
        return new RequestUtil(METHOD_DELETE, url, jsonStr, headerMap, null).newCall();
    }


    /**
     * post请求，上传单个文件
     *
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void okHttpUploadFile(String url, File file, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadFile(url, file, fileKey, fileType, null, callBack);
    }

    /**
     * post请求，上传单个文件
     *
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void okHttpUploadFile(String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpUploadFile(url, file, fileKey, fileType, paramsMap, null, callBack);
    }

    /**
     * post请求，上传单个文件
     *
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void okHttpUploadFile(String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, file, fileKey, fileType, headerMap, callBack).execute();
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     *
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpUploadListFile(String url, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadListFile(url, null, fileList, fileKey, fileType, callBack);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     *
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpUploadListFile(String url, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadListFile(url, paramsMap, fileList, fileKey, fileType, null, callBack);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     *
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpUploadListFile(String url, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, fileList, fileKey, fileType, headerMap, callBack).execute();
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     *
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpUploadMapFile(String url, Map<String, File> fileMap, String fileType, CallBackUtil callBack) {
        okHttpUploadMapFile(url, fileMap, fileType, null, callBack);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     *
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpUploadMapFile(String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpUploadMapFile(url, fileMap, fileType, paramsMap, null, callBack);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     *
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void okHttpUploadMapFile(String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, fileMap, fileType, headerMap, callBack).execute();
    }

    /**
     * 下载文件,不带参数
     */
    public static void okHttpDownloadFile(String url, CallBackUtil.CallBackFile callBack) {
        okHttpDownloadFile(url, null, callBack);
    }

    /**
     * 下载文件,带参数
     */
    public static void okHttpDownloadFile(String url, Map<String, String> paramsMap, CallBackUtil.CallBackFile callBack) {
        okHttpGet(url, paramsMap, null, callBack);
    }


}
