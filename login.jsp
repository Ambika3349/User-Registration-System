<%
String cp = request.getParameter("cp");
if ("success".equals(cp)) {
%>
<script>
    alert("Password changed successfully");
</script>
<%
} else if ("fail".equals(cp)) {
%>
<script>
    alert("Invalid User ID or Old Password");
</script>
<%
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>

<style>

body {
  font-family: Arial;
}

/* Titles */
.title {
  text-align: center;
  text-decoration: underline;
  margin-top: 20px;
}

/* Form table alignment */
table {
  margin: auto;
}

/* Black buttons */
.black-btn {
  background-color: black;
  color: white;
  border: none;
  padding: 6px 20px;
  font-weight: bold;
  cursor: pointer;
}

/* Center button */
.center {
  text-align: center;
  margin-top: 10px;
}

/* Left & Right buttons row */
.left-right {
  display: flex;
  justify-content: space-between;
  width: 400px;
  margin: 15px auto;
}

/* Bottom note */
.note {
  text-align: center;
  font-size: 14px;
  margin-top: 15px;
}

</style>
</head>

<body>

<!-- LOGIN SECTION -->

<h2 class="title">LOGIN</h2>

<form action="LoginServlet" method="post">

<table>
<tr>
  <td>User ID:</td>
  <td><input type="text" name="userId"></td>
</tr>

<tr>
  <td>Password:</td>
  <td><input type="password" name="password"></td>
</tr>
</table>

<div class="center">
  <button type="submit" class="black-btn">LOGIN</button>
</div>

</form>

<!-- Change & Forgot Buttons -->

<div class="left-right">

  <button class="black-btn"
          onclick="location.href='login.jsp#changepwd'">
    ChangePassword
  </button>

  <button class="black-btn"
          onclick="location.href='forgotpassword.jsp'">
    ForgotPassword
  </button>

</div>


<!-- CHANGE PASSWORD SECTION -->

<h2 class="title" id="changepwd">Change Password</h2>

<form action="ChangePasswordServlet" method="post">

<table>

<tr>
  <td>User ID:</td>
  <td><input type="text" name="userId"></td>
</tr>

<tr>
  <td>Old Password:</td>
  <td><input type="password" name="oldPassword"></td>
</tr>

<tr>
  <td>New Password:</td>
  <td><input type="password" name="newPassword"></td>
</tr>

<tr>
  <td>Confirm Password:</td>
  <td><input type="password" name="confirmPassword"></td>
</tr>

</table>

<div class="center">
  <button type="submit" class="black-btn">SAVE</button>
</div>

</form>

<!-- NOTE -->

<p class="note">
The data entered in New Password and Confirm Password should be same,<br>
then only the data should get saved. Or else throw error message.
</p>

</body>
</html>