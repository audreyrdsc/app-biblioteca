package com.unifap.biblioteca.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditingService implements AuditorAware<String> {

    //Carrega o username do user que está logado
    @Override public Optional<String> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
            return Optional.of(((UserDetails) principal).getUsername());
        return Optional.of(principal.toString());
    }
}