<%-- 
    Document   : mainSite
    Created on : 2016-nov-16, 12:49:30
    Author     : sundi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/Resources/CSS/style.css" rel="stylesheet" type="text/css" >
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avatar - WebChat</title>
    </head>
    <body>
       
        
        
        <!-- Navbar goes here -->

         
<nav class="pink" id="navBar">
    Menu
    <a href="#!" class="center brand-logo"><i class="material-icons">cloud</i>Avatar - WebChat</a>
    
    <ul id="slide-out" class="side-nav ">
        
    <li><div class="userView">
      <div class="chip">
    <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar2.png" alt="Contact Person">
    ${user.getFirstname()} ${user.getLastname()}  
    </div>
      
    </div></li>
    
    
    <ul  class="collapsible" data-collapsible="accordion" id="innerNav">
  <li>
    <div class="collapsible-header "><span class="new badge">3</span><i class="material-icons red999">chat</i>Message</div>
    <div class="collapsible-body">
        <a href="#!" class="collection-item">Alan<span class="badge">1</span></a>
        <br>
        <a href="#!" class="collection-item">Steven<span class="badge">2</span></a>
    </div>
  </li>
  <li>
    <div class="collapsible-header"><span class="badge">4</span><i class="material-icons red999">supervisor_account</i>Friends Online</div>
    <div class="collapsible-body">
    <div class="chip">
    <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar3.png" alt="Contact Person">
    Sasha
  </div>
    <div class="chip">
    <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar4.png" alt="Contact Person">
    Steven
  </div>
    <div class="chip">
    <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar5.png" alt="Contact Person">
    Albert
  </div>
    <div class="chip">
    <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar6.png" alt="Contact Person">
    Cho
  </div>
    
    </div>
  </li>
  
   <li>
    <div class="collapsible-header"><span class="badge">2</span><i class="material-icons red999">perm_identity</i>Friend Request</div>
    <div class="collapsible-body">
    
    <ul class="collection">
    <li class="collection-item avatar">
      <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar7.png" alt="" class="circle">
      <span class="title">Bill</span>
      <p>Name: Bill Andersson<br>
         From: Canada
      </p>
      
      <div class="accept">
      <i class="left material-icons red999">thumb_up</i>
      <i class="right material-icons red999">thumb_down</i>
      </div>
    </li>
    <li class="collection-item avatar">
      <img src="${pageContext.request.contextPath}/Resources/AVATAR/avatar8.png" alt="" class="circle">
      <span class="title">Doggy</span>
      <p>Name: Snoop Dogg<br>
         From: USA
      </p>
      <div class="accept">
      <i class="left material-icons red999">thumb_up</i>
      <i class="right material-icons red999">thumb_down</i>
      </div>
    </li>
    
    </ul>
    
    </div>
  </li>
  
  <div class="col s10">
      <div class="row">
        <div class="input-field col s10 ">
          <i class="material-icons prefix red999">search</i>
          <input type="text" id="autocomplete-input" class="autocomplete ">
          <label for="autocomplete-input">_</label>
        </div>
      </div>
    </div>
  
  <div class="util">
      <i class="left material-icons red999">settings</i>
      
        <a href="<c:url value="/main/logout.htm" />">Logout</a>
        <i class="material-icons red999">power_settings_new</i></button>
      
      </div>
  
</ul> 
 
     
      
     
    
  </ul>
  <a href="#" data-activates="slide-out" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>
              
</nav>
 

    <!-- Mainpage Layout here -->

    <div class="col s14 m8 sl9 teal"> 
        <div id="mainContent">
        Main
        Goes
        Here
        </div>
    </div>
    
    <!--Footers Layout here -->
    <div class="col s16 m8 sl9 grey lighten-2"> 
        <br>
        <img border="0" src="http://vht.tradedoubler.com/file/19/468x60.gif" title="TradeDoubler Affiliateprogram">
        
        <img border="0" src="http://banner.euroads.se/banner/2/90/banner_837.gif">
    </div>
    <div class="col s16 m8 sl9 grey"> 
        © 2016 Copyright by Avatar Corporation
    </div>

        <script>
            $( document ).ready(function(){
              $('.button-collapse').sideNav({
      menuWidth: 320, // Default is 240
      edge: 'left', // Choose the horizontal origin
      closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
      draggable: true // Choose whether you can drag to open on touch screens
    }
  );
  
  $('input.autocomplete').autocomplete({
    data: {
      <c:forEach var="listValue" items="${users}">
      ${listValue.getLastname()} : null,
      </c:forEach>
      "Google": 'http://placehold.it/250x250'
    }
  });
        })
        </script>
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>
    </body>
</html>
