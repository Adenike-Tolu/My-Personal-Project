package com.adenike.phone_management.services.impl;

import com.adenike.phone_management.dto.DefaultApiResponse;
import com.adenike.phone_management.dto.PhoneRequestDto;
import com.adenike.phone_management.dto.PhoneResponseDto;
import com.adenike.phone_management.repository.PhoneRepository;
import com.adenike.phone_management.model.Phone;
import com.adenike.phone_management.services.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;


    @Override
    public List<PhoneResponseDto> getAllPhone() {
            List<Phone> phones = phoneRepository.findAll(); // Fetch all phones from the database

            // Convert each Phone entity to PhoneResponseDto
            return phones.stream()
                    .map(phone -> new PhoneResponseDto(
                            phone.getId(),
                            phone.getModel(),
                            phone.getBrand(),
                            phone.getPrice()))
                    .collect(Collectors.toList());
        }

    @Override
    public ResponseEntity<DefaultApiResponse> getPhoneById(Long id) {
        DefaultApiResponse responseDto = new DefaultApiResponse();

        Optional<Phone> phone = phoneRepository.findById(id);

        Optional<PhoneResponseDto> phoneResponse = Optional.of(new PhoneResponseDto());
        if (phone.isPresent()) {
            phoneResponse.get().setId(phone.get().getId());
            phoneResponse.get().setModel(phone.get().getModel());
            phoneResponse.get().setBrand(phone.get().getBrand());
            phoneResponse.get().setPrice(phone.get().getPrice());

            responseDto.setStatus("00");
            responseDto.setMessage("Data retrieved successfully");
            responseDto.setData(phoneResponse.get());
        } else {
            responseDto.setStatus("00");
            responseDto.setMessage("No data found");
        }
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<DefaultApiResponse> updatePhone(Long id, PhoneRequestDto phoneDetails) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        Optional<Phone> phoneOptional = phoneRepository.findById(id);

        if (phoneOptional.isPresent()) {
            Phone phone = phoneOptional.get();

            // Update the phone entity with the new details
            phone.setModel(phoneDetails.getModel());
            phone.setBrand(phoneDetails.getBrand());
            phone.setPrice(phoneDetails.getPrice());

            // Save the updated phone back to the repository
            Phone updatedPhone = phoneRepository.save(phone);

            // create a responseDTO
            PhoneResponseDto phoneResponseDto = new PhoneResponseDto(
                    updatedPhone.getId(),
                    updatedPhone.getModel(),
                    updatedPhone.getBrand(),
                    updatedPhone.getPrice()
            );
            // Set the API response
            defaultApiResponse.setStatus("00");
            defaultApiResponse.setMessage("Data updated successfully");
            defaultApiResponse.setData(defaultApiResponse);

            // Return the response with HTTP 200 OK status
            return ResponseEntity.ok(defaultApiResponse);

        } else {
            // Handle the case where the phone with the given ID does not exist
            defaultApiResponse.setStatus("01");
            defaultApiResponse.setMessage("No data found with the given ID");

            // Return the response with HTTP 404 Not Found status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(defaultApiResponse);
        }
    }

    @Override
    public ResponseEntity<DefaultApiResponse> createPhone(PhoneRequestDto phoneDto) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        //convert DTO to phone entity
        Phone phone = new Phone();
        phone.setModel(phoneDto.getModel());
        phone.setBrand(phoneDto.getBrand());
        phone.setPrice(phoneDto.getPrice());
        //save the entity
        Phone savedPhone = phoneRepository.save(phone);

        PhoneResponseDto responseDto = new PhoneResponseDto();
        responseDto.setId(savedPhone.getId());
        responseDto.setModel(savedPhone.getModel());
        responseDto.setBrand(savedPhone.getBrand());
        responseDto.setPrice(savedPhone.getPrice());

        defaultApiResponse.setStatus("00");
        defaultApiResponse.setMessage("Data saved successfully");
        defaultApiResponse.setData(responseDto);
        return ResponseEntity.ok(defaultApiResponse);
    }


    @Override
    public DefaultApiResponse deletePhoneById(Long id) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        Optional<Phone> phoneOptional = phoneRepository.findById(id);

        if (phoneOptional.isPresent()) {
            // If phone exists, delete it
            phoneRepository.deleteById(id);

            defaultApiResponse.setStatus("00"); // Indicating success
            defaultApiResponse.setMessage("Phone deleted successfully");
        } else {
            // If the phone doesn't exist
            defaultApiResponse.setStatus("01"); // Indicating failure
            defaultApiResponse.setMessage("Phone not found with the given ID");
        }

        return defaultApiResponse;
        }
    }



