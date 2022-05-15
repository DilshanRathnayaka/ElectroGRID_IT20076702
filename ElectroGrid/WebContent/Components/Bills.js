$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateBillForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "BillingAPI", 
 type : type, 
 data : $("#formBill").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onBillSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onBillSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divBillGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidBillIDSave").val(""); 
$("#formBill")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidBillIDSave").val($(this).data("billid")); 
		 $("#bill_Acc").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#bill_Date").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#bill_Unit").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#bill_price").val($(this).closest("tr").find('td:eq(3)').text()); 
	     $("#bill_Total").val($(this).closest("tr").find('td:eq(4)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "BillingAPI", 
		 type : "DELETE", 
		 data : "B_ID=" + $(this).data("billid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onBillDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onBillDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divBillGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateBillForm()
{
	// CODE
	if ($("#bill_Acc").val().trim() == "")
	{
	return "Insert Item Code.";
	}
	// NAME
	if ($("#bill_Date").val().trim() == "")
	{
	return "Insert Item Name.";
}

// PRICE-------------------------------
if ($("#bill_Unit").val().trim() == ""){
	return "Insert Item Price.";
}
		// is numerical value
		var tmpPrice = $("#bill_Unit").val().trim();
		if (!$.isNumeric(tmpPrice))
	{
	return "Insert a numerical value for Item Price.";
	}
		
// convert to decimal price
$("#bill_Unit").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#bill_price").val().trim() == ""){
	
	return "Insert Item Description.";
}
if ($("#bill_Total").val().trim() == "")
	{
	return "Insert Item Code.";
	}
	return true;
}