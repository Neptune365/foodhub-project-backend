package com.project.FoodHub.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenConfirmacionService {

    private final TokenConfirmacionRepository confirmationTokenRepository;

    public void saveConfirmationToken(TokenConfirmacion token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<TokenConfirmacion> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
