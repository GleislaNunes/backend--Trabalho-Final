package org.pucgoias.food.service;

import org.pucgoias.food.dao.CustomerRepository;
import org.pucgoias.food.dto.LoginDto;
import org.pucgoias.food.dto.LoginResponseDto;
import org.pucgoias.food.model.Customer;
import org.pucgoias.food.model.User;
import org.pucgoias.food.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomerRepository customerRepository;

    public LoginResponseDto authenticate(LoginDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.email(),
                input.password()
            )
        );

        User user = userRepository.findByEmail(input.email()).orElseThrow();

        Customer customer = customerRepository.findByUserId(user.getId());

        String jwtToken = jwtService.generateToken(user);

        return new LoginResponseDto(user.getId(), user.getEmail(), jwtToken, customer);
    }
}
