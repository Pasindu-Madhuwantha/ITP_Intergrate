<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	 
    <title>Newly Added Stocks</title>
	
	<style>
		@import url("resources/Admin_header.css");
		@import url("resources/Admin_footer.css");
		@import url("resources/inventory_ctrl_body.css");
	</style>
	
</head>

<body>
    <header>
		<div class="header-row" id="myHeader">
			<div class="col-2 logo-container nav-com">
				<div class="logo">
					<a href="#Home"><img src="logo-images/automart.png" alt="Automart-logo" width="120px" height="120px"></a>
				</div>
			</div>
			<div class="col-8 nav nav-unq" >
				<ul>
					<!-- li class="li-com"><a href="#Store">Store</a></li-->
					<li class="li-com"><a href="<%=request.getContextPath()%>/default">New Stock</a></li>
					<!-- li class="li-com"><a href="#Out-of-stock">Out of stock</a></li-->				  
				</ul>
			</div>
			
			<div class="col-2 nav nav-com">
				<div class="admin-tag" data-toggle="tooltip" data-placement="top" title="Admin Online">Admin</div>
				<div class="admin-active" data-toggle="tooltip" data-placement="top" title="Admin Online"></div>
			</div>
		</div>
	</header>
	
	<!----------------------------------- Body ----------------------------------->
	<div class="body-content">
		
		<div class="content-title">
			Newly Added Stocks
		</div>
		<div class="content-table-frame">
			<div class="content-table">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Supplier ID</th>
							<th>Supplier Item</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="supItem" items="${tempSup}">
	
							<tr>
								<td><c:out value="${supItem.supId}" /></td>
								<td><c:out value="${supItem.supItem}" /></td>
								<td><c:out value="${supItem.quantity}" /></td>
								<td><c:out value="${supItem.price}" /></td>
								<td><a href="add?id=<c:out value='${supItem.supId}' />">Add to Stock</a>
							</tr>
						</c:forEach>
			
					</tbody>
	
				</table>
			</div>
		</div>	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</div>
	
	<!------------------------------------- footer ---------------------------------->
	<footer class="footer">
        <div class="container">
            <div class="row">
                Copyrights© <div id="demo" style="margin: 0px 8px 0px 8px;"></div> AUTOMART.  All rights reserved
            </div>
        </div>
    </footer>
	
	<!----------------------------------------------- SCRIPT -------------------------------------------->
	<script>
	window.onscroll = function() {myFunction()};

	var header = document.getElementById("myHeader");
	var sticky = header.offsetTop;

	function myFunction() {
	  if (window.pageYOffset > sticky) {
		header.classList.add("sticky");
	  } else {
		header.classList.remove("sticky");
	  }
	}
	
	
	<!-------------------Tooltip---------------------->
	$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
	
	<!--Get Year-->
	const d = new Date();
	let year = d.getFullYear();
	document.getElementById("demo").innerHTML = year;

	</script>
	
	
</body>

</html>

