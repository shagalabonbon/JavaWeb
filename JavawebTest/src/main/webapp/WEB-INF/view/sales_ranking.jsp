<%@ page import="javaweb.model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Ranking</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/javaweb/css/buttons.css">
		
		<!-- google 圓餅圖 --> <!-- 複製 script  -->
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
     	google.charts.load('current', {'packages':['corechart']});
      	google.charts.setOnLoadCallback(drawChart);

      	function drawChart() {
		
      	
      	
      	var data = google.visualization.arrayToDataTable([  // 更改圓餅圖顯示資訊
                ['Task', 'Hours per Day'],
                ['Work',     11],
                ['Eat',      2],
                ['Commute',  2],
                ['Watch TV', 2],
                ['Sleep',    7]
        ]);


        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
     	}
    	</script>
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		
		<!-- body content -->
		<div style="padding: 15px">
			<div class="pure-form">
				<fieldset>
					<legend>銷售排行</legend>
					${ salesRankingMap }
				</fieldset>
			</div>
		</div>
	</body>
</html>