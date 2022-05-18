/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.response;

import java.util.List;

/**
 *
 * @author Sendy
 * @param <E> Entity
 */
public class ResponseListData <E> {
    
    private List<E> data;

    public ResponseListData(List<E> data) {
        this.data = data;
    }

    public List<E> getData() {
        return data;
    }
}
