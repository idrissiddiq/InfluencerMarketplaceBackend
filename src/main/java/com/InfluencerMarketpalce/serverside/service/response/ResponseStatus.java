/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Sendy
 */
public class ResponseStatus {
     public ResponseStatusException dataAlreadyExist() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Data Sudah Terdaftar");
    }

    public ResponseStatusException dataNotFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan");
    }
    
    public ResponseStatusException passwordNotMatch() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Password tidak cocok");
    }
    
    public ResponseStatusException passwordNotExist() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Harap masukkan password");
    }
    
    public ResponseStatusException persentaseIsExceed() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Persentase Sudah Mencapai 100%");
    }
    
    
}
