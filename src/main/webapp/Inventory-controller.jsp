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
	 
    <title>Home</title>
	
	<style>
		@import url("resources/Admin_header.css");
		@import url("resources/Admin_footer.css");
		@import url("resources/responsive.css");
		@import url("resources/inventory-controller-unit.css");
		
		div.display-table {
			width: 100% !important;
			overflow: auto !important;
		}
	</style>
	
</head>

<body>
    <header id="myHeader">
		<div class="header-row">
			<div class="col-2 logo-container nav-com">
				<div class="logo">
					<a href="#Home"><img src="logo-images/automart.png" alt="Girl in a jacket" width="120px" height="120px"></a>
				</div>
			</div>
			<div class="col-8 nav nav-unq" >
				<ul>
					<!-- li class="li-com"><a href="#Home">Store</a></li-->
					<li class="li-com"><a href="<%=request.getContextPath()%>/newStock">New Stock</a></li>	
					<!-- li class="li-com"><a href="<%=request.getContextPath()%>/stockView">Out of Stock</a></li-->					  
				</ul>
			</div>
			
			<div class="col-2 nav nav-com">
				<div class="admin-tag" data-toggle="tooltip" data-placement="top" title="Admin Online">Admin</div>
				<div class="admin-active" data-toggle="tooltip" data-placement="top" title="Admin Online"></div>
			</div>
		</div>
	</header>
	
	<!----------------------------------- Body ----------------------------------->
	<div class="inventory-title" style="width:100%;">INVENTORY CONTROL SYSTEM</div>
	<div class="inventory-body">
		<div class="control-panel" style="width: 33.33%;">
			<div class="inventory-btn">
				<button type="button" id="myButton">INVENTORY CONTROLLER</button>
			</div>
			
			<div class="stock-form">
				<div class="form-title">INVENTORY CONTROLLER</div>
				<div class="form-content">
					<c:if test="${inventoryRecord == null}">
						<form action="addInventoryDB" method="get">
					</c:if>
					<c:if test="${inventoryRecord != null}">
						<form action="updateInventoryDB" method="get">
					</c:if>
							
							<c:if test="${stock != null}">
								<input type="hidden" name="id" value="" />
							</c:if>
							<c:if test="${inventoryRecord != null}">
								<input type="hidden" name="id" value="<c:out value='${inventoryRecord.invId}' />" />
							</c:if>
							
							<label class="stock-label" for="invType">Inventory Type </label>
							<input class="stock-input" type="text" id="invType" name="invType" value="<c:out value='${inventoryRecord.invType}'/>"><br>
							
							
							<label class="stock-label" for="description">Description </label>
							<textarea id="description" name="description" style="width: 60%; min-height: 30px; height: 30px;"><c:out value='${inventoryRecord.description}'/></textarea><br>
							
							
							<div class="form-submit">	
								<c:if test="${inventoryRecord == null}">
									<input id="add" type="submit" value="ADD">
								</c:if>
								<c:if test="${inventoryRecord != null}">
									<input id="update" type="submit" value="UPDATE">
								</c:if>
							</div>
						</form>
				</div>
			</div>
			
			<div class="report-btn">
				<button type="button">GENERATE A REPORT</button>
			</div>
			
		</div>
		
		<div class="table-view" style="width: 66.66%">
			<div class="table-view-frame">
				<div class="button-area">
					<button type="button" id="showStockTable">SHOW STOCK TABLE</button>
					<button class="active-btn" type="button" id="showInventoryTable">SHOW INVENTORY TABLE</button>
				</div>
				
				<div class="display-table">
					<table class="table table-bordered" style="width: 100%;">
						<thead>
						<tr>
							<th>Inventory ID</th>
							<th>Inventory Type</th>
							<th>Description</th>
							<th>Inserted Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="inventory" items="${displayInventory}">
	
							<tr>
								<td><c:out value="${inventory.invId}" /></td>
								<td><c:out value="${inventory.invType}" /></td>
								<td><c:out value="${inventory.description}" /></td>
								<td><c:out value="${inventory.insertedDate}" /></td>
								
								<td>
									<a href="editInventory?id=<c:out value='${inventory.invId}' />">EDIT</a>
									<a href="deleteInventory?id=<c:out value='${inventory.invId}' />">DELETE</a>
								</td>
							</tr>
						</c:forEach>
			
					</tbody>
					</table>
				</div>
			</div>
		</div>
	
	</div>
	
	<!----------------------------------- footer --------------------------------->
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
	
	<!----------------------Goto Inventory Controller--------------------->
	document.getElementById("myButton").onclick = function () {
        location.href = "<%=request.getContextPath()%>/inventoryView";
    };
    
    document.getElementById("showStockTable").onclick = function () {
        location.href = "<%=request.getContextPath()%>/stockView";
    };
    
    document.getElementById("showInventoryTable").onclick = function () {
        location.href = "<%=request.getContextPath()%>/inventoryView";
    };
	</script>
	
	
</body>

</html>

