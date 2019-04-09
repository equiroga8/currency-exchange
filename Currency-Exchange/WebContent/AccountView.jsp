<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="titleHeader">
      <title>Balance</title>
    </jsp:attribute>
    <jsp:body>
        <shiro:guest>
		<h2>Account</h2>
		<table class="table">
  <thead class="thead-light" >
    <tr>
      <th scope="col">COIN</th>
      <th scope="col">AMOUNT</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>USD</td>
      <td>${client.account.wallet.usd}</td>
    </tr>
    <tr>
     <td>EUR</td>
     <td>${client.account.wallet.eur}</td>
    </tr>
    <tr>
     <td>YEN</td>
     <td>${client.account.wallet.yen}</td>
    </tr>
    <tr>
     <td>GBP</td>
     <td>${client.account.wallet.gbp}</td>
    </tr>
    <tr>
     <td>SFr</td>
     <td>${client.account.wallet.sfr}</td>
    </tr>
    <tr>
     <td>AUD</td>
     <td>${client.account.wallet.aud}</td>
    </tr>
    <tr>
     <td>CAD</td>
     <td>${client.account.wallet.cad}</td>
    </tr>
  </tbody>
</table>
	<h4>DEPOSIT</h4>
	<div class="input-group mb-3">
	  <div class="input-group-prepend">
	    <c:choose>
	        <c:when test="${client.localCurrency == 6}"><span class="input-group-text">USD</span></c:when>
	        <c:when test="${client.localCurrency == 3}"><span class="input-group-text">EUR</span></c:when>
	        <c:when test="${client.localCurrency == 7}"><span class="input-group-text">YEN</span></c:when>
	        <c:when test="${client.localCurrency == 4}"><span class="input-group-text">GBP</span></c:when>
	        <c:when test="${client.localCurrency == 5}"><span class="input-group-text">SFr</span></c:when>
	        <c:when test="${client.localCurrency == 1}"><span class="input-group-text">AUD</span></c:when>
	        <c:when test="${client.localCurrency == 2}"><span class="input-group-text">CAD</span></c:when>
	        <c:otherwise>undefined</c:otherwise>
	    </c:choose>
	    <form ction="DepositServlet" method="post">
		    <input type="text" class="form-control" aria-label="Amount" name="amount">
		    <input type="hidden" name="email" value="${client.email}" />
		    <button type="submit" class="btn btn-outline-secondary" type="button" a>Deposit</button>
	    </form>
	    
	  </div>
	</div>
	<h4>WITHDRAW</h4>
	<div class="input-group mb-3">
		 <div class="input-group-prepend">
		    <c:choose>
		        <c:when test="${client.localCurrency == 6}"><span class="input-group-text">USD</span></c:when>
		        <c:when test="${client.localCurrency == 3}"><span class="input-group-text">EUR</span></c:when>
		        <c:when test="${client.localCurrency == 7}"><span class="input-group-text">YEN</span></c:when>
		        <c:when test="${client.localCurrency == 4}"><span class="input-group-text">GBP</span></c:when>
		        <c:when test="${client.localCurrency == 5}"><span class="input-group-text">SFr</span></c:when>
		        <c:when test="${client.localCurrency == 1}"><span class="input-group-text">AUD</span></c:when>
		        <c:when test="${client.localCurrency == 2}"><span class="input-group-text">CAD</span></c:when>
		        <c:otherwise>undefined</c:otherwise>
		    </c:choose>
		    <form action="WithdrawServlet" method="post">
			    <input type="hidden" name="email" value="${client.email}" />
	  			<input type="text" class="form-control" aria-label="Amount" name="amount">
	  			<button type="submit" class="btn btn-outline-secondary" type="button" >Whitdraw</button>
		    </form>
		    
		</div>
	</div>
	</shiro:guest>
    </jsp:body>
</t:layout>