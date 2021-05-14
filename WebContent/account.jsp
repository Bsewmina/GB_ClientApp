<%@ page import="com.Account" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/account.js"></script>
</head>
<body><br>
<center><h2>Account Managment</h2></center><br>
<div class="container "> <div class="row justify-content-center"><div class="col-10">

	<form id="formAccount" name="formAccount">
		<div class="form-group row">
			<div class="col-3">
 				Account ID : <input id="accountId" name="accountId" type="number" class="form-control form-control-sm">
 			</div>
    		<div class="col">
 				First Name : <input id="firstName" name="firstName" type="text" class="form-control form-control-sm"> 
 			</div>
 			<div class="col">
 				Last Name : <input id="lastName" name="lastName" type="text" class="form-control form-control-sm"> 
 			</div>
 			<div class="col-3">
 				Gender : <input id="gender" name="gender" type="text" class="form-control form-control-sm"> 
 			</div>
 		</div>
 		<br>
 		<div class="form-group row">
			<div class="col">
				Mobile NO : <input id="mobile" name="mobile" type="number" class="form-control form-control-sm">  
				</div>
    		<div class="col">
				Email : <input id="email" name="email" type="email" class="form-control form-control-sm">
				</div>
			<div class="col">
				Password : <input id="password" name="password" type="password" class="form-control form-control-sm">
				</div>
 		 </div><br>
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
</div></div></div>

<div id="table">
<%Account acc = new Account();
	out.print(acc.viewallAccounts());
%>
</div>
</body>
</html>