<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('print.action','_self');">打印</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		出货表月统计
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="inputDate" value="2011-12"
	             			onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM'});"/>
				</td>
	        </tr>
		</table>
	</div>
</div>
 

</form>
</body>
</html>

