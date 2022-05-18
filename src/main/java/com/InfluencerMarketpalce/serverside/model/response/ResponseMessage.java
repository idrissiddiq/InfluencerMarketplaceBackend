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
public class ResponseMessage<E> extends ResponseData<E>{
    private String message;

    public ResponseMessage(String message, E data) {
        super(data);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
