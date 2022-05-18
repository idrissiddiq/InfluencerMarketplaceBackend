/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.response;

/**
 *
 * @author Sendy
 * @param <E> Entity
 */
public class ResponseData <E> {
    private E data;
    
    public ResponseData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }
}
