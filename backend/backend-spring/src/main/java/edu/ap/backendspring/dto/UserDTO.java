package edu.ap.backendspring.dto;

public record UserDTO (
    Integer id,
    String email,
    String role,
    String firstanme,
    String lastname
) {
}
