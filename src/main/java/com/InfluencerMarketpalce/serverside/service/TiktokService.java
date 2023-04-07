package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.gateway.TiktokGateway;
import com.InfluencerMarketpalce.serverside.model.response.IndonesiaLocationResponse;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.model.response.TiktokGetVideoInfoReponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TiktokService {
    @Value("${url.tiktok.video.info}")
    private String url;

    @Value("${tiktok.video.info.key}")
    private String key;

    @Value("${tiktok.video.info.host}")
    private String host;

    @Autowired
    private TiktokGateway tiktokGateway;

    public ResponseData<TiktokGetVideoInfoReponse> getVideoInfo(Map<String, Object> param){
        String link = param.get("link").toString();
        String mainLink = link.substring(0, link.indexOf("?"));
        System.out.println("Main Link : " + mainLink);
        String videoId = stripNonDigits(mainLink);
        System.out.println("Video Id : " + videoId);
        JSONObject resp = new JSONObject(tiktokGateway.callGetVideoInfo(url+ videoId, key, host));
        String data = resp.optString("aweme_detail");
        JSONObject allData = new JSONObject(data);
        String stat = allData.optString("statistics");
        JSONObject statData = new JSONObject(stat);
        TiktokGetVideoInfoReponse tiktokGetVideoInfoReponse = new TiktokGetVideoInfoReponse();
        tiktokGetVideoInfoReponse.setComment_count(statData.optLong("comment_count"));
        tiktokGetVideoInfoReponse.setDigg_count(statData.optLong("digg_count"));
        tiktokGetVideoInfoReponse.setDownload_count(statData.optLong("download_count"));
        tiktokGetVideoInfoReponse.setPlay_count(statData.optLong("play_count"));
        tiktokGetVideoInfoReponse.setShare_count(statData.optLong("share_count"));
        tiktokGetVideoInfoReponse.setForward_count(statData.optLong("forward_count"));
        tiktokGetVideoInfoReponse.setWhatsapp_share_count(statData.optLong("whatsapp_share_count"));
        tiktokGetVideoInfoReponse.setCollect_count(statData.optLong("collect_count"));
        return new ResponseData<TiktokGetVideoInfoReponse>(tiktokGetVideoInfoReponse);
    }

    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */){
        final StringBuilder sb = new StringBuilder(
                input.length() /* also inspired by seh's comment */);
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
