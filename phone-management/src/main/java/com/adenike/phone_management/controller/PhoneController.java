package com.adenike.phone_management.controller;

import com.adenike.phone_management.dto.DefaultApiResponse;
import com.adenike.phone_management.dto.PhoneRequestDto;
import com.adenike.phone_management.dto.PhoneResponseDto;
import com.adenike.phone_management.services.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhoneController {
    @Autowired
    private final PhoneService phoneService;

//    public PhoneController(PhoneService phoneService) {
//        this.phoneService = phoneService;
//    }

    @GetMapping("/phone") 
    public List<PhoneResponseDto> getAllPhone() {
        return phoneService.getAllPhone();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultApiResponse> getPhoneById(@PathVariable Long id) {
        return phoneService.getPhoneById(id);
    }


    @PostMapping("/phone")
    public ResponseEntity<DefaultApiResponse> createPhone(@RequestBody PhoneRequestDto phoneDto) {
        return phoneService.createPhone(phoneDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DefaultApiResponse> updatePhone(@PathVariable Long id, @RequestBody PhoneRequestDto phoneDetails) {
        DefaultApiResponse updatedPhone = phoneService.updatePhone(id, phoneDetails).getBody();
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultApiResponse> deletePhone(@PathVariable Long id) {
        // Call the service to delete the phone and get the response
        DefaultApiResponse apiResponse = phoneService.deletePhoneById(id);

        if (apiResponse.getStatus().equals("00")) {
            // If phone is successfully deleted, return HTTP 200 OK
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            // If the phone is not found or deletion failed, return HTTP 404 NOT FOUND with error message
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
        }


