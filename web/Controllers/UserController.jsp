<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="jakarta.servlet.ServletException" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="Business.Services.UserService" %>
<%@ page import="Domain.Model.User" %>
<%@ page import="Business.Exceptions.UserNotFoundException" %>
<%@ page import="Business.Exceptions.DuplicateUserException" %>

<%
    UserService userService = new UserService();
    String action = request.getParameter("action");
    if (action == null) {
        action = "list";
    }
    switch (action) {
        case "login":
            handleLogin(request, response, session);
            break;
        case "authenticate":
            handleAuthenticate(request, response, session, userService);
            break;
        case "showCreateForm":
            showCreateUserForm(request, response);
            break;
        case "create":
            handleCreateUser(request, response, userService);
            break;
        case "showFindForm":
            showFindForm(request, response, session, userService);
            break;
        case "search":
            handleSearch(request, response, session, userService);
            break;
        case "update":
            handleUpdateUser(request, response, session, userService);
            break;
        case "delete":
            handleDeleteUser(request, response, session, userService);
            break;
//        case "deletefl":
//            handleDeleteUserFromList(request, response, session, userService);
//            break;
        case "listAll":
            handleListAllUsers(request, response, userService);
            break;
        case "logout":
            handleLogout(request, response, session);
            break;
        default:
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            break;
    }
%>

<%!
    private void handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/Views/Forms/Users/login.jsp");
    }

private void handleAuthenticate(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserService userService)
        throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    try {
        User loggedInUser = userService.loginUser(email, password);
        session.setAttribute("loggedInUser", loggedInUser); // Guardamos el usuario en la sesión
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    } catch (UserNotFoundException e) {
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("/Views/Forms/Users/login.jsp").forward(request, response);
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime el stack trace completo para depuración
        request.setAttribute("errorMessage", "Error de base de datos. Inténtelo de nuevo. Detalles: " + e.getMessage());
        request.getRequestDispatcher("/Views/Forms/Users/login.jsp").forward(request, response);
    }
}


    private void showCreateUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Views/Forms/Users/create.jsp");
    }

    private void handleCreateUser(HttpServletRequest request, HttpServletResponse response, UserService userService)
        throws ServletException, IOException {
    String cedula = request.getParameter("cedula");
    String nombre = request.getParameter("nombre");
    String apellidos = request.getParameter("apellidos");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String rol = request.getParameter("rol");

    try {
        // Llama al servicio para crear el usuario con todos los parámetros
        userService.createUser(cedula, nombre, apellidos, username, email, password, rol);
        request.setAttribute("successMessage", "Usuario creado exitosamente.");
        handleListAllUsers(request, response, userService); // Redirige a la lista de usuarios después de crear
    } catch (DuplicateUserException e) {
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("/Views/Forms/Users/create.jsp").forward(request, response);
    } catch (SQLException e) {
        request.setAttribute("errorMessage", "Error de base de datos. Intentelo de nuevo.");
        request.getRequestDispatcher("/Views/Forms/Users/create.jsp").forward(request, response);
    }
}


    private void showFindForm(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserService userService)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserService userService)
            throws ServletException, IOException {
        String searchCedula = request.getParameter("cedula");
        try {
            User user = userService.getUserByCedula(searchCedula);
            session.setAttribute("searchedUser", user);
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        } catch (UserNotFoundException e) {
            session.removeAttribute("searchedUser");
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error de base de datos.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        }
    }

    private void handleUpdateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserService userService)
            throws ServletException, IOException {
        User searchedUser = (User) session.getAttribute("searchedUser");
        if (searchedUser == null) {
            request.setAttribute("errorMessage", "Primero debe buscar un usuario para editar.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
            return;
        }
        String cedula = searchedUser.getCedula();
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            userService.updateUser(cedula, nombre, email, password);
            request.setAttribute("successMessage", "Usuario actualizado exitosamente.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        } catch (UserNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error de base de datos.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        }
    }

    private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserService userService)
            throws ServletException, IOException {
        User searchedUser = (User) session.getAttribute("searchedUser");
        if (searchedUser == null) {
            request.setAttribute("errorMessage", "Primero debe buscar un usuario para eliminar.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
            return;
        }
        String cedula = searchedUser.getCedula();
        try {
            userService.deleteUser(cedula);
            session.removeAttribute("searchedUser");
            request.setAttribute("successMessage", "Usuario eliminado exitosamente.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        } catch (UserNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error de base de datos.");
            request.getRequestDispatcher("/Views/Forms/Users/find_edit_delete.jsp").forward(request, response);
        }
    }

    private void handleListAllUsers(HttpServletRequest request, HttpServletResponse response, UserService userService)
            throws ServletException, IOException {
        try {
            List<User> users = userService.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/Views/Forms/Users/list_all.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error de base de datos al listar usuarios.");
            request.getRequestDispatcher("/Views/Forms/Users/list_all.jsp").forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/Views/Forms/Users/login.jsp");
    }
%>
