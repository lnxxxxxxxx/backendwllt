package com.challengedbackend.challengedbackend.Exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Error {

        private String message;
}
