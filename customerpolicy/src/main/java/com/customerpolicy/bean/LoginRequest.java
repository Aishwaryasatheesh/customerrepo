package com.customerpolicy.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
private String email;
	@NotBlank(message = "Password is mandatory")
    @Size(min = 3, max = 20, message = "Password must be between 6 and 20 characters")
private String password;

}
