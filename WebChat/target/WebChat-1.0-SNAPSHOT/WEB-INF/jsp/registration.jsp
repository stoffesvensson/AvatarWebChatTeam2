<%-- 
    Document   : registration
    Created on : 2016-nov-14, 12:55:01
    Author     : Kristoffer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/Resources/CSS/style.css" rel="stylesheet" type="text/css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avatar - WebChat</title>
    </head>
    <body>
       
        
        
        <div class="section"></div>
  <main>
    <center>
   
      <div class="section"></div>

      <h5 class="teal-text " >Avatar - WebChat Registration</h5>
      <div class="section"></div>

      <div class="container">
        <div class=" grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

          <form action="#" th:action="@{/registration}" th:object="${user}" class="col s12" method="post">
            <div class='row'>
              <div class='col s12'>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                 <i class="small material-icons teal999" style=" float: left">perm_identity</i>
                <input class='validate' type='text' name='username' id='username'/>
                                <label>Username</label>
              </div>
            </div>
            
          
              
              <div class='row'>
              <div class='input-field col s12'>
                 <i class="small material-icons teal999" style=" float: left">email</i>
                <input class='validate' type='email' name='email' id='email' />
                <label>Email</label>
              </div>
            </div>

            <div class='row '>
              <div class='input-field col s12 '>
                 <i class="small material-icons teal999" style=" float: left">https</i>
                <input class='validate ' type='password' name='password' id='password' />
                <label  for='password'>Password</label>
              </div>
             
             					
            </div>

            <br />
            <center>
              <div class='row'>
                 <a href='#'> <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect teal '>Create</button></a>
              </div>
            </center>
            
            <label>Do you already have an account?</label> <a class='pink-text' href='login.htm'><b>Login Here!</b></a> 
                                                                <br>       
          </form>
        </div>
      </div>   
    </center>
    <div class="section"></div>
    <div class="section"></div>
  </main>
        
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>
    </body>
</html>
