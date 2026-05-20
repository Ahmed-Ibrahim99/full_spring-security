package com.amaghrabi.Security.service;

import com.amaghrabi.Security.model.Customer;
import com.amaghrabi.Security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }

    public void createCustomer(Customer customer) {
        String password = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
        customerRepository.save(customer);
    }
}
