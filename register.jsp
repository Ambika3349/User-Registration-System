<!DOCTYPE html>
<html>
<head>
<title>User Registration</title>

<style>
body {
    font-family: Arial;
}

.container {
    width: 350px;
    margin: auto;
}

h2 {
    text-align: center;
}

.center-btn {
    text-align: center;
    margin-top: 20px;
}

.black-btn {
    background-color: black;
    color: white;
    padding: 10px 40px;
    border: none;
    font-size: 16px;
    cursor: pointer;
}

.black-btn:hover {
    background-color: #333;
}
</style>

<script>
function validateForm()
{
  let msg = "";

  if(document.getElementById("userId").value.trim() == "")
    msg += "User ID is required\n";

  if(document.getElementById("userName").value.trim() == "")
    msg += "Username is required\n";

  if(document.getElementById("dob").value == "")
    msg += "DOB is required\n";

  if(document.getElementById("password").value.trim() == "")
    msg += "Password is required\n";

  if(document.getElementById("a1").value.trim() == "")
    msg += "Answer 1 is required\n";

  if(document.getElementById("a2").value.trim() == "")
    msg += "Answer 2 is required\n";

  if(document.getElementById("pwdDate").value == "")
    msg += "Password Change Date is required\n";

  if(msg != "")
  {
    alert(msg);
    return false;
  }

  return true;
}
</script>
</head>

<body>

<div class="container">

<h2>User Registration</h2>

<form action="RegisterServlet" method="post" onsubmit="return validateForm();">

User ID:<br>
<input type="text" id="userId" name="userId"><br><br>

Username:<br>
<input type="text" id="userName" name="userName"><br><br>

Date of Birth:<br>
<input type="date" id="dob" name="dob"><br><br>

Password:<br>
<input type="password" id="password" name="password"><br><br>

Question 1:<br>
<input type="text" name="q1" value="What is your pet name?" readonly><br><br>

Answer 1:<br>
<input type="text" id="a1" name="a1"><br><br>

Question 2:<br>
<input type="text" name="q2" value="What is your favourite colour?" readonly><br><br>

Answer 2:<br>
<input type="text" id="a2" name="a2"><br><br>

Password Change Date:<br>
<input type="date" id="pwdDate" name="pwdDate"><br><br>

<div class="center-btn">
    <input type="submit" value="SAVE" class="black-btn">
</div>

</form>

</div>

</body>
</html>