<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/logreg-forms .css">
        <title>Login/Register Form</title>
    </head>
    <body>
        <div id="logreg-forms">
            <form action="login" method="post" class="form-signin">  
                <div class="text-center font-weight-bold display-4">Login</div><br><!-- comment -->
                <input type="user" name="user" id="inputEmail" class="form-control" placeholder="User name" required autofocus="" value="${sessionScope.acc.userName}">
                <!-- input password -->
                <div class="input-group">
                    <input type="password" name="pass" id="inputPassword1" class="form-control" placeholder="Password" required value="${sessionScope.acc.password}">                    
                </div>

                <button class="btn btn-success btn-block" type="submit" name="sign_in"><i class="fas fa-sign-in-alt"></i> Sign in</button>               
                <!-- <p>Don't have an account!</p>  -->
                <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up New Account</button>
            </form>        

            <form action="login" method="post" class="form-signup">
                <div class="text-center font-weight-bold display-4">Sign up</div><br>
                <input type="user" name="user" id="user-email" class="form-control" placeholder="User name" required autofocus="">
                <!-- input password -->
                <div class="input-group">
                    <input type="password" name="pass" id="inputPassword2" class="form-control" placeholder="Password" required="">                    
                </div>
                <button class="btn btn-primary btn-block" type="submit" name="sign_up"><i class="fas fa-user-plus"></i> Sign Up</button>
                <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
            </form>
            <br>
            <div style="color: red; text-align: center">${notice}</div>

        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="assets\js\script_login.js"></script>
        

    </body>
</html>