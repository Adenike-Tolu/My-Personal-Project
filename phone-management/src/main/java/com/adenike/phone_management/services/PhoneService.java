package com.adenike.phone_management.services;

import  com.adenike.phone_management.dto.DefaultApiResponse;
import com.adenike.phone_management.dto.PhoneRequestDto;
import com.adenike.phone_management.dto.PhoneResponseDto;
import com.adenike.phone_management.model.Phone;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface    PhoneService {


    List<PhoneResponseDto>  getAllPhone();

    ResponseEntity<DefaultApiResponse> getPhoneById(Long id);

    ResponseEntity<DefaultApiResponse> createPhone(PhoneRequestDto phoneDto);

    ResponseEntity<DefaultApiResponse> updatePhone(Long id, PhoneRequestDto phoneDetails);

    DefaultApiResponse deletePhoneById(Long id);


}
