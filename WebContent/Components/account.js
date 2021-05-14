
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
 		{
 			$("#alertSuccess").hide();
 		}
 			$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
 		$("#alertSuccess").text("");
 		$("#alertSuccess").hide();
 		$("#alertError").text("");
 		$("#alertError").hide();
 		
	// Form validation-------------------
		var status = validateItemForm();
	if (status != true)
 		{
 			$("#alertError").text(status);
 			$("#alertError").show();
 			return;
 		}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
 	$.ajax(
		 {
 			url : "AccountAPI",
			 type : type,
 			data : $("#formAccount").serialize(),
 			dataType : "text",
 			complete : function(response, status)
 		{
 			onItemSaveComplete(response.responseText, status);
 		}
 	});
});


$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
 			url : "AccountAPI",
 			type : "DELETE",
 			data : "acc_Id=" + $(this).data("accountid"),
 			dataType : "text",
 			complete : function(response, status)
 		{
 		onItemDeleteComplete(response.responseText, status);
 		}
 	});
});

$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).data("acc_id"));
 	$("#accountId").val($(this).closest("tr").find('td:eq(0)').text());
 	$("#firstName").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#lastName").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#gender").val($(this).closest("tr").find('td:eq(3)').text());
 	$("#mobile").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#email").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#password").val($(this).closest("tr").find('td:eq(3)').text());
});

function onItemSaveComplete(response, status)
{
	if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 				if (resultSet.status.trim() == "success")
 					{
 						$("#alertSuccess").text("Successfully saved.");
 						$("#alertSuccess").show();
 						$("#table").html(resultSet.data);
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
	$("#hidItemIDSave").val("");
 	$("#formAccount")[0].reset();
}


//validation
function validateItemForm()
{
	//ID
		var id = $("#accountId").val().trim();
		if (!$.isNumeric(id))
 			{
 				return "Insert a numerical value for Mobile NO.";
 			}	
	// firstName
		if ($("#firstName").val().trim() == "")
			 {
				 return "Insert FirstName";
 			} 
	// lastName-------------------------------
		if ($("#lastName").val().trim() == "")
 			{
 				return "Insert LastName";
 			}
 	// gender-------------------------------
		if ($("#gender").val().trim() == "")
 			{
 				return "Insert Gender";
 			}
	// is numerical value
		var mob = $("#mobile").val().trim();
		if (!$.isNumeric(mob))
 			{
 				return "Insert a valid Mobile NO.";
 			}	
	// email------------------------
		if ($("#email").val().trim() == "")
			 {
 				return "Insert Item Email.";
 				}
 				// email------------------------
		if ($("#password").val().trim() == "")
			 {
 				return "Insert Item Password.";
 				}
	return true;
}
 			