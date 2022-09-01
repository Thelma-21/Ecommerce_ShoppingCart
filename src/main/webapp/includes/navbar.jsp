
<%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 07/08/2022
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand " href="../index.jsp">Ziva Stores</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cart.jsp">Cart<span class="badge bg-danger px-1">${cart_list.size()}</span></a>
                </li>
                <%
                 if(auth != null){%>
                <li class="nav-item"><a class="nav-link" href="user-logout">Logout</a></li>
                <%}else {%>
                <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                <%}
                    %>
            </ul>
        </div>
    </div>
</nav>


