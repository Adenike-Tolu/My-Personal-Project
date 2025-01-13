package com.adenike.phone_management.dto;

import lombok.Data;

@Data
public class DefaultApiResponse {
    private String status;
    private String message;
    private Object data;
}
