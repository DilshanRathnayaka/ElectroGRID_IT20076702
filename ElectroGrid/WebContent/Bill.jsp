<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Bills.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1 class="p-3 mb-2 bg-primary text-white"><center>Electro GRID</center></h1>
<h1><center>Billing Management</center></h1>

<form  bg-color="red" id="formBill" name="formBill" method="post" action="items.jsp">
 Account No: 
 <input id="bill_Acc" name="bill_Acc" type="text" 
 class="form-control form-control-sm">
 <br> Date: 
 <input id="bill_Date" name="bill_Date" type="text" 
 class="form-control form-control-sm">
 <br> Units: 
 <input id="bill_Unit" name="bill_Unit" type="text" 
 class="form-control form-control-sm">
 <br> Per Unit Price: 
 <input id="bill_price" name="bill_price" type="text" 
 class="form-control form-control-sm">
  <br> Total: 
 <input id="bill_Total" name="bill_Total" type="text" 
 class="form-control form-control-sm">
 <br><center><input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary"></center>
 
 <input type="hidden" id="hidBillIDSave" 
 name="hidBillIDSave" value="">
</form>
</br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divBillGrid">
 <%
 	Bill itemObj = new Bill(); 
        out.print(itemObj.readBills());
 %>
</div>
</div> </div> </div> 
</body>
</html>