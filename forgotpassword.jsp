<!DOCTYPE html>
<html>
<head>
<title>Forgot Password</title>

<style>
body {
    font-family: Arial;
}

.container {
    width: 400px;
    margin: auto;
}

h2 {
    text-align: center;
}

input {
    width: 100%;
    padding: 6px;
    margin-top: 5px;
    margin-bottom: 10px;
}

button {
    padding: 8px 30px;
    background-color: black;
    color: white;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #333;
}

.note {
    font-size: 12px;
    color: gray;
}
</style>

<script>
function validateForm()
{
    let userId = document.getElementById("userId").value.trim();
    let a1 = document.getElementById("a1").value.trim();
    let a2 = document.getElementById("a2").value.trim();

    if(userId == "" || a1 == "" || a2 == "")
    {
        alert("Fields should not be empty");
        return false;
    }
    return true;
}
</script>
</head>

<body>

<div class="container">

<h2>Forgot Password</h2>

<form action="ForgotPasswordServlet" method="post" onsubmit="return validateForm();">

User ID:<br>
<input type="text" id="userId" name="userId" required><br>

Question 1:<br>
<input type="text" name="q1" value="What is your pet name?" readonly>
<div class="note">Displayed from registration</div>

Answer 1:<br>
<input type="text" id="a1" name="a1" required><br>

Question 2:<br>
<input type="text" name="q2" value="What is your favourite colour?" readonly>
<div class="note">Displayed from registration</div>

Answer 2:<br>
<input type="text" id="a2" name="a2" required><br>

<div style="text-align:center;">
    <button type="submit">SUBMIT</button>
</div>

</form>

</div>

</body>
</html>