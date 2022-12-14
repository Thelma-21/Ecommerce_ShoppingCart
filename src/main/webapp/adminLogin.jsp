<%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 09/08/2022
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        response.sendRedirect("index.jsp");
    }
%>

<html>
<head>
    <title>Admin</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%--<%@include file="includes/navbar.jsp"%>--%>


<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form action="admin-login" method="post">
                    <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                        <p class="lead fw-normal mb-0 me-3">Admin Sign in with</p>
                        <button type="button" class="btn btn-primary btn-floating mx-1">
                            <i class="bi bi-facebook"></i>
                        </button>

                        <button type="button" class="btn btn-primary btn-floating mx-1">
                            <i class="bi bi-google"></i>
                        </button>

                        <button type="button" class="btn btn-primary btn-floating mx-1">
                            <i class="bi bi-linkedin"></i>
                        </button>
                    </div>

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Or</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="email" name="ad-login-email" id="form3Example3" class="form-control form-control-lg"
                               placeholder="Enter a valid email address" />
                        <label class="form-label" for="form3Example3">Email address</label>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <input type="password" name="ad-login-password" id="form3Example4" class="form-control form-control-lg"
                               placeholder="Enter password" />
                        <label class="form-label" for="form3Example4">Password</label>
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox"  id="form2Example3" />
                            <label class="form-check-label" for="form2Example3">
                                Remember me
                            </label>
                        </div>
                        <a href="#" class="text-body">Forgot password?</a>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                        <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="adminSignup.jsp"
                                                                                          class="link-danger">Sign Up</a></p>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <div
            class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
        <!-- Copyright -->
        <div class="text-white mb-3 mb-md-0">
            Copyright ?? 2020. All rights reserved.
        </div>
        <!-- Copyright -->

        <!-- Right -->
        <div>
            <a href="#" class="text-white me-4">
                <i class="bi bi-facebook"></i>
            </a>
            <a href="#" class="text-white me-4">
                <i class="bi bi-twitter"></i>
            </a>
            <a href="#" class="text-white me-4">
                <i class="bi bi-google"></i>
            </a>
            <a href="#" class="text-white">
                <i class="bi bi-linkedin"></i>
            </a>
        </div>
        <!-- Right -->
    </div>
</section>

</body>
</html>
