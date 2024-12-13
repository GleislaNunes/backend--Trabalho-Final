package org.pucgoias.food.service;

import org.apache.coyote.BadRequestException;
import org.pucgoias.food.dao.CustomerRepository;
import org.pucgoias.food.dao.UserRepository;
import org.pucgoias.food.dto.CreateCustomerDto;
import org.pucgoias.food.dto.LoginResponseDto;
import org.pucgoias.food.model.Customer;
import org.pucgoias.food.model.User;
import org.pucgoias.food.model.UserType;
import org.pucgoias.food.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public LoginResponseDto save(CreateCustomerDto data) throws BadRequestException {
        boolean emailAlreadyExists = userRepository.existsByEmail(data.email());
        if (emailAlreadyExists) {
            throw new BadRequestException("Já existe um usuário com este email");
        }

        String cpf = data.cpf().replaceAll("[^0-9]", "");

        boolean isCpfValid = ValidationUtil.validateCPF(cpf);
        if (!isCpfValid) {
            throw new BadRequestException("CPF inválido!");
        }

        boolean cpfAlreadyExists = customerRepository.existsByCpf(cpf);
        if (cpfAlreadyExists) {
            throw new BadRequestException("Já existe um usuário com este cpf");
        }

        String phoneNumber = data.phone().replaceAll("[^0-9]", "");

        boolean phoneNumberAlreadyExists = customerRepository.existsByPhoneNumber(phoneNumber);

        if (phoneNumberAlreadyExists) {
            throw new BadRequestException("Já existe um usuário com este telefone");
        }

        User user = new User();
        user.setEmail(data.email());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.setType(UserType.CUSTOMER);
        user = userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setName(data.name());
        customer.setCpf(cpf);
        customer.setPhoneNumber(phoneNumber);
        customer = customerRepository.save(customer);

        String jwtToken = jwtService.generateToken(user);

        return new LoginResponseDto(user.getId(), user.getEmail(), jwtToken, customer);
    }
}
