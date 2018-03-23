<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">
	<input type="hidden" name="id" value="${obj.id}" />
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('update.action','_self');">确定</a></li>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		浏览购销合同信息
    </div> 
    </div>
    </div>
<div>

    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent">${obj.customName}</td>
				<td class="columnTitle_mustbe">收购方：</td>
				<td class="tableContent">${obj.offeror}</td>
	        </tr>
			<tr>
				<td class="columnTitle_mustbe">合同号：</td>
				<td class="tableContent">${obj.contractNo}</td>
				<td class="columnTitle_mustbe">打印版式：</td>
				<td class="tableContentAuto">
					<c:if test="${obj.printStyle=='2'}" >两款</c:if>
					<c:if test="${obj.printStyle=='1'}" >一款</c:if>
				</td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">签单日期：</td>
				<td class="tableContent">
					<fmt:formatDate value="${obj.signingDate}" pattern="yyyy-MM-dd"  />
				</td>
				<td class="columnTitle_mustbe">重要程度：</td>
				<td class="tableContentAuto">
					<c:if test="${obj.importNum==3}">★★★</c:if>
					<c:if test="${obj.importNum==2}">★★</c:if>
					<c:if test="${obj.importNum==1}">★</c:if>
				</td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">交货日期：</td>
				<td class="tableContent">
					<fmt:formatDate value="${obj.deliveryPeriod}" pattern="yyyy-MM-dd"  />
				</td>
				<td class="columnTitle_mustbe">船期：</td>
				<td class="tableContent">
					<fmt:formatDate value="${obj.shipTime}" pattern="yyyy-MM-dd"  />
				</td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">贸易条款：</td>
				<td class="tableContent">${obj.tradeTerms}</td>
				<td class="columnTitle_mustbe">验货员：</td>
				<td class="tableContent">${obj.inspector}</td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">制单人：</td>
				<td class="tableContent">${obj.inputBy}</td>
				<td class="columnTitle_mustbe">审单人：</td>
				<td class="tableContent">${obj.checkBy}</td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">要求：</td>
				<td class="tableContent"><pre>${obj.crequest}</pre></td>
				<td class="columnTitle_mustbe">说明：</td>
				<td class="tableContent"><pre>${obj.remark}</pre></td>
			</tr>


		</table>
	</div>
</div>
</div>
	<div class="textbox" id="centerTextbox">
		<div class="textbox-header">
			<div class="textbox-inner-header">
				<div class="textbox-title">
					货物列表
				</div>
			</div>
		</div>

		<div>

			<div class="eXtremeTable" >
				<table id="ec_table" class="tableRegion" width="98%" >
					<thead>
					<tr>
						<td class="tableHeader">序号</td>
						<td class="tableHeader">厂家名称</td>
						<td class="tableHeader">货号</td>
						<td class="tableHeader">数量</td>
						<td class="tableHeader">包装单位</td>
						<td class="tableHeader">装率</td>
						<td class="tableHeader">箱数</td>
						<td class="tableHeader">单价</td>
						<td class="tableHeader">总金额</td>
					</tr>
					</thead>
					<tbody class="tableBody" >

					<c:forEach items="${obj.contractProducts}" var="cp" varStatus="status">
						<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
							<td>${status.index+1}</td>
							<td>${cp.factory.factoryName}</td>
							<td>${cp.productNo}</td>
							<td>${cp.cnumber}</td>
							<td>${cp.packingUnit}</td>
							<td>${cp.loadingRate}</td>
							<td>${cp.boxNum}</td>
							<td>${cp.price}</td>
							<td>${cp.amount}</td>
						</tr>
						<c:forEach items="${cp.extCproducts}" var="ext" varStatus="status">
							<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
								<td><font color="blue">附件：${status.index+1}</font></td>
								<td>${ext.factory.factoryName}</td>
								<td>${ext.productNo}</td>
								<td>${ext.cnumber}</td>
								<td>${ext.packingUnit}</td>
								<td></td>
								<td></td>
								<td>${ext.price}</td>
								<td>${ext.amount}</td>
							</tr>
						</c:forEach>
					</c:forEach>

					</tbody>
				</table>
			</div>

		</div>


	</div>
</form>
</body>
</html>

