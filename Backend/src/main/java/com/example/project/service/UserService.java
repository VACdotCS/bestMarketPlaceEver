package com.example.project.service;


import com.example.project.dto.response.AuthenticationResponse;
import com.example.project.common.Constants;
import com.example.project.config.JwtService;
import com.example.project.dto.request.ChangeCardUserRequest;
import com.example.project.dto.request.ChangeInfoUserRequest;
import com.example.project.dto.request.ChangePasswordRequest;
import com.example.project.dto.response.*;
import com.example.project.entity.User;
import com.example.project.dto.response.UserProductStatsDTO;
import com.example.project.exception.*;
import com.example.project.repository.OrderedProductRepo;
import com.example.project.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class UserService {

    private OrderedProductRepo orderedproductRepo;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) throws UserIncorrectPasswordException, UserMismatchPasswordException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new UserIncorrectPasswordException(Constants.INCORRECT_PASSWORD);
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new UserMismatchPasswordException(Constants.MISMATCH_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);
    }


    public UserInfoDTO getInfoUser(Principal connectedUser){
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        UserProductStatsDTO stats= orderedproductRepo.findUserProductStatsByUserId(user.getUser_id());
        Double percent;
        if (stats != null) {
            Long issuedCount = stats.getIssuedProducts();
            Long refusedCount = stats.getRefusedProducts();
            percent = (double) issuedCount / (issuedCount + refusedCount) * 100;
        } else {
            percent = null;
        }
        return UserInfoDTO.builder()
                .user_discount(user.getUserDiscount())
                .amount_spent(user.getAmount_spent())
                .order_count(user.getOrders().size())
                .redemption_percent(percent)
                .username(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .card_number(user.getCard_number())
                .CVC(user.getCVC())
                .validity(user.getValidity())
                .build();
    }

    public void changeUserCard(ChangeCardUserRequest request, Principal connectedUser) throws IncorrectDateException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        user.setCard_number(request.getCard_number());
        user.setCVC(Integer.parseInt(request.getCVC()));

        try {
            user.setValidity(LocalDate.parse(request.getValidity()));
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException(Constants.INCORRECT_DATE);
        }

        userRepo.save(user);
    }

    public AuthenticationResponse changeUserInfo(ChangeInfoUserRequest request, Principal connectedUser) throws UniqueEmailException, UniquePhoneException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if((!Objects.equals(user.getEmail(), request.getEmail()))&&(userRepo.existsByEmail(request.getEmail()))) {
            throw new UniqueEmailException(Constants.UNIQUE_EMAIL);
        }
        if((!Objects.equals(user.getPhone(), request.getPhone()))&&(userRepo.existsByPhone(request.getPhone()))) {
            throw new UniquePhoneException(Constants.UNIQUE_PHONE);
        }
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .role(user.getRole())
                .build();
    }

    public List<AllUsersDTO> getAllUsers() {
        List<AllUsersDTO> users = userRepo.findAll()
                .stream()
                .map(user -> {
                    AllUsersDTO usersDTO = new AllUsersDTO();
                    usersDTO.setUsername(user.getName());
                    usersDTO.setUser_discount(user.getUserDiscount());
                    usersDTO.setRole(user.getRole());
                    usersDTO.setAmount_spent(user.getAmount_spent());
                    usersDTO.setId(user.getUser_id());
                    return usersDTO;
                })
                .toList();
        return users;
    }
}