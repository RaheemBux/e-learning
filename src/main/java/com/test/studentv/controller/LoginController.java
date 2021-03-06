package com.test.studentv.controller;

import com.test.studentv.dto.AuthenticatedUser;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.security.JwtAuthenticationRequest;
import com.test.studentv.security.JwtTokenUtil;
import com.test.studentv.security.UsersAuthenticationProvider;
import com.test.studentv.service.UserService;
import com.test.studentv.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UsersAuthenticationProvider usersAuthenticationProvider;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        logger.info("Inside Auth Service");
        // Perform the security
        Device device = DeviceUtils.getCurrentDevice(request);

        if(device!=null)
            logger.info("Device: " + device.toString());
        else
            device = new Device() {
                @Override
                public boolean isNormal() {
                    return true;
                }

                @Override
                public boolean isMobile() {
                    return false;
                }

                @Override
                public boolean isTablet() {
                    return false;
                }

                @Override
                public DevicePlatform getDevicePlatform() {
                    return null;
                }
            };

        AuthenticatedUser authenticatedUser;
        if(jwtAuthenticationRequest.getUsername()!=null && !jwtAuthenticationRequest.getUsername().isEmpty() && jwtAuthenticationRequest.getPassword()!=null && !jwtAuthenticationRequest.getPassword().isEmpty()){
            UserEntity user = userService.findByUserName(jwtAuthenticationRequest.getUsername());
            if(user == null){
                return new ResponseEntity<>(new StatusDTO(0, "Incorrect User Name or Password" ), HttpStatus.NOT_FOUND);
            }
            final Authentication authentication = usersAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtAuthenticationRequest.getUsername(),
                    jwtAuthenticationRequest.getPassword()
            ));
            logger.info("Inside Auth Service :: 1");
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Inside Auth Service :: 2");
                authenticatedUser = getAuthenticatedUserForUnblockedUser(user, jwtTokenUtil, device);
                userService.update(user);
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new StatusDTO(0, "Incorrect User Name or Password" ), HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>((new UserEntity()), HttpStatus.OK);
    }
    private AuthenticatedUser getAuthenticatedUserForUnblockedUser(UserEntity user, JwtTokenUtil jwtTokenUtil, Device device) {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        Set<String> permissionList = new HashSet<>();

        authenticatedUser.setCode(1);
        authenticatedUser.setStatus(1);

        if (user.getFirstName() != null) {
            authenticatedUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            authenticatedUser.setLastName(user.getLastName());
        }
        if(user.getMobileNo()!=null){
            authenticatedUser.setMobileNo(user.getMobileNo());
        }
        if (user.getUserName() != null) {
            authenticatedUser.setUserName(user.getUserName());
        }
        if (user.getEmail() != null) {
            authenticatedUser.setEmail(user.getEmail());
        }
        final String token = jwtTokenUtil.generateToken(user, device, permissionList);
        authenticatedUser.setMessage("Success");
        authenticatedUser.setToken(token);
        authenticatedUser.setUserId(user.getId());
        return authenticatedUser;
    }

}

