package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.gateway.IndonesiaLocationGateway;
import com.InfluencerMarketpalce.serverside.model.response.IndonesiaLocationResponse;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndonesiaLocationService {
    @Value("${url.indonesia.location}")
    private String url;

    @Autowired
    private IndonesiaLocationGateway indonesiaLocationGateway;

    public ResponseListData<IndonesiaLocationResponse> province(){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/provinsi"));
        String data = resp.optString("provinsi");
        JSONArray allData = new JSONArray(data);
        List<IndonesiaLocationResponse> listData = new ArrayList<>();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
            locationProvinceResponse.setId(temp.optLong("id"));
            locationProvinceResponse.setName(temp.optString("nama"));
            listData.add(locationProvinceResponse);
        }
        return new ResponseListData<IndonesiaLocationResponse>(listData);
    }

    public ResponseListData<IndonesiaLocationResponse> city(String id){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/kota?id_provinsi=" + id));
        String data = resp.optString("kota_kabupaten");
        JSONArray allData = new JSONArray(data);
        List<IndonesiaLocationResponse> listData = new ArrayList<>();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
            locationProvinceResponse.setId(temp.optLong("id"));
            locationProvinceResponse.setName(temp.optString("nama"));
            listData.add(locationProvinceResponse);
        }
        return new ResponseListData<IndonesiaLocationResponse>(listData);
    }

    public ResponseListData<IndonesiaLocationResponse> kecamatan(String id){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/kecamatan?id_kota=" + id));
        String data = resp.optString("kecamatan");
        JSONArray allData = new JSONArray(data);
        List<IndonesiaLocationResponse> listData = new ArrayList<>();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
            locationProvinceResponse.setId(temp.optLong("id"));
            locationProvinceResponse.setName(temp.optString("nama"));
            listData.add(locationProvinceResponse);
        }
        return new ResponseListData<IndonesiaLocationResponse>(listData);
    }

    public ResponseListData<IndonesiaLocationResponse> kelurahan(String id){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/kelurahan?id_kecamatan=" + id));
        String data = resp.optString("kelurahan");
        JSONArray allData = new JSONArray(data);
        List<IndonesiaLocationResponse> listData = new ArrayList<>();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
            locationProvinceResponse.setId(temp.optLong("id"));
            locationProvinceResponse.setName(temp.optString("nama"));
            listData.add(locationProvinceResponse);
        }
        return new ResponseListData<IndonesiaLocationResponse>(listData);
    }

    public ResponseData<IndonesiaLocationResponse> searchProvince(String name){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/provinsi"));
        String data = resp.optString("provinsi");
        JSONArray allData = new JSONArray(data);
        IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            if(temp.optString("nama").equals(name)){
                locationProvinceResponse.setId(temp.optLong("id"));
                locationProvinceResponse.setName(temp.optString("nama"));
                break;
            }
        }
        return new ResponseData<IndonesiaLocationResponse>(locationProvinceResponse);
    }

    public ResponseData<IndonesiaLocationResponse> searchCity(String id, String name){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/kota?id_provinsi=" + id));
        String data = resp.optString("kota_kabupaten");
        JSONArray allData = new JSONArray(data);
        IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            if(temp.optString("nama").equals(name)){
                locationProvinceResponse.setId(temp.optLong("id"));
                locationProvinceResponse.setName(temp.optString("nama"));
                break;
            }
        }
        return new ResponseData<IndonesiaLocationResponse>(locationProvinceResponse);
    }

    public ResponseData<IndonesiaLocationResponse> searchProvince(Long id){
        JSONObject resp = new JSONObject(indonesiaLocationGateway.callGetApi(url+ "/provinsi"));
        String data = resp.optString("provinsi");
        JSONArray allData = new JSONArray(data);
        IndonesiaLocationResponse locationProvinceResponse = new IndonesiaLocationResponse();
        for(int i = 0; i < allData.length(); i++) {
            JSONObject temp = new JSONObject(allData.optString(i));
            if(temp.optLong("id") == id){
                locationProvinceResponse.setId(temp.optLong("id"));
                locationProvinceResponse.setName(temp.optString("nama"));
                break;
            }
        }
        return new ResponseData<IndonesiaLocationResponse>(locationProvinceResponse);
    }
}
