package com.test.studentv.security;


import com.test.studentv.config.ProdProfileCondition;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.service.UserService;
import com.test.studentv.util.CommonUtil;
import com.test.studentv.util.MacAddress;
import com.test.studentv.util.MyPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Conditional(ProdProfileCondition.class)
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
            response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
            String url = request.getRequestURI().replace(servletContext.getContextPath() + "/", "").replaceAll("(([/]+)([0-9])+)", "");
            System.out.println("Original Url: " + request.getRequestURI());
            System.out.println("Url: " + url);
            ArrayList<String> authUrlList = new ArrayList<>();
            authUrlList.add("auth");

            if (authUrlList.contains(url)) {
                System.out.println("username: " + request.getParameter("username"));
                System.out.println("Password: " + request.getParameter("password"));
                System.out.println("DoFilter: " + Boolean.TRUE);
                chain.doFilter(request, response);
            } else {
                System.out.println("ELSE URL LIST NOT CONTAINS: " + url);
                String authToken = request.getHeader("Authorization");

                LOGGER.info("Token:::" + authToken);
                System.out.println("Token   "+authToken);
                if (authToken != null && !authToken.trim().isEmpty() && !authToken.equals("Bearer")) {//AFTER
                    if (jwtTokenUtil.isTokenExpired(authToken)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
                    } else {
                        System.out.println("Not Expired");
                    }
                    System.out.println("=======================");
                    System.out.println("authToken: " + authToken);
                    System.out.println("ExpDate: " + jwtTokenUtil.getExpirationDateFromToken(authToken));
                    System.out.println("=======================");
                    MyPrint.println("Auth Token :::: " + authToken);

                    String username = jwtTokenUtil.getUsernameFromToken(authToken);

                    logger.info(jwtTokenUtil + "checking authentication für user " + username);

                    if (username != null) {
                        logger.info("checking authentication für user ::: 2" + username);
                        // It is not compelling necessary to load the use details from the database. You could also store the information
                        // in the token and read it from it. It's up to you ;)
                        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
                        LOGGER.info("IP:" + request.getRemoteAddr() + ",MAC:" + MacAddress.getMac(request.getRemoteAddr())
                                + ",DATE:" + CommonUtil.getCurrentTimestamp().toString() + ",User:" + username + ",URL:" + request.getRequestURI());
                        if (username == null && username.isEmpty()) {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User Not Found!!!");
                        }
                        UserEntity users = this.userService.findByUserName(username);
                        if (users.getStatus() == false) {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User Not Found!!!");

                        }
//
                        System.out.println("============================================");
                        System.out.println("UserName: " + username);
                        System.out.println("Status: " + users.getStatus());
                        System.out.println("Url: " + url);

                        if (!authUrlList.contains(url)) {
                            String[] urlSplitter = url.split("/");
                            if (urlSplitter.length > 1) {
                                url = urlSplitter[0] + "/" + urlSplitter[1];
                            } else {
                                url = urlSplitter[0];
                            }
                            ArrayList<String> utilPermissionList = jwtTokenUtil.getPermissionList(authToken);

                            utilPermissionList.add("user/create");
                            utilPermissionList.add("user/update");
                            utilPermissionList.add("user/delete");
                            utilPermissionList.add("user/view");
                            utilPermissionList.add("user/delete");
                            utilPermissionList.add("user/getAll");
                            utilPermissionList.add("user/views");
                            utilPermissionList.add("user/resetPassword");
                            utilPermissionList.add("user/uploadImage");
                            utilPermissionList.add("user/askForVideo");

                            utilPermissionList.add("class/create");
                            utilPermissionList.add("class/update");
                            utilPermissionList.add("class/delete");
                            utilPermissionList.add("class/view");
                            utilPermissionList.add("class/delete");
                            utilPermissionList.add("class/getAll");

                            utilPermissionList.add("category/create");
                            utilPermissionList.add("category/update");
                            utilPermissionList.add("category/delete");
                            utilPermissionList.add("category/view");
                            utilPermissionList.add("category/delete");
                            utilPermissionList.add("category/getAll");

                            utilPermissionList.add("course/create");
                            utilPermissionList.add("course/update");
                            utilPermissionList.add("course/delete");
                            utilPermissionList.add("course/view");
                            utilPermissionList.add("course/delete");
                            utilPermissionList.add("course/getAll");
                            utilPermissionList.add("course/uploadImage");
                            utilPermissionList.add("course/views");

                            utilPermissionList.add("likedCourse/likeCourse");
                            utilPermissionList.add("likedCourse/getAllRecommendedCourses");
                            utilPermissionList.add("likedCourse/findAllLikedCoursesOfUser");

                            utilPermissionList.add("video/create");
                            utilPermissionList.add("video/update");
                            utilPermissionList.add("video/delete");
                            utilPermissionList.add("video/view");
                            utilPermissionList.add("video/delete");
                            utilPermissionList.add("video/getAll");
                            utilPermissionList.add("video/videoUpload");

                            utilPermissionList.add("comments/create");
                            utilPermissionList.add("comments/update");
                            utilPermissionList.add("comments/delete");
                            utilPermissionList.add("comments/view");
                            utilPermissionList.add("comments/delete");
                            utilPermissionList.add("comments/getAll");

                            utilPermissionList.add("message/sendMessage");
                            utilPermissionList.add("message/receiveMessage");
                            utilPermissionList.add("message/delete");
                            utilPermissionList.add("message/getAllMessagesBySendId");
                            utilPermissionList.add("message/delete");
                            utilPermissionList.add("message/getAllMessagesByReceiverId");

                            utilPermissionList.add("purchase/purchaseCourse");
                            utilPermissionList.add("purchase/findAllPurchasedCoursesByUser");

                            utilPermissionList.add("cards/create");
                            utilPermissionList.add("cards/update");
                            utilPermissionList.add("cards/delete");
                            utilPermissionList.add("cards/view");
                            utilPermissionList.add("cards/delete");
                            utilPermissionList.add("cards/getAll");


                            if (utilPermissionList.contains(url)) {
                                System.out.println("Authorize:---- " + Boolean.TRUE);
                                System.out.println("============================================\n");
                                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                                    logger.info("Valid Token");
                                } else {
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                                }

                            } else {
                                System.out.println("Authorize:NOT " + Boolean.FALSE);
                                System.out.println("============================================\n");
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                            }
                        } else {
                            System.out.println("URL NOT FOUND ");
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

                        }
                    } else {
                        System.out.println("User is null");
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    }
                } else {
                    System.out.println("Token is Null");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                }
                System.out.println("============================================");
                System.out.println("LAST POINT");
                System.out.println("============================================\n");
                chain.doFilter(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
