package javaweb.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 *  功能：分數計算與統計
 *  網址：/score?score=100&score=85&score=45 
 *  印出：總分、平均、最高、最低
 */

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] scores = req.getParameterValues("score"); 
	
		
		int[] intScores = Arrays.stream(scores).mapToInt(Integer::parseInt).toArray();  // String[] 轉 int []
		
		IntSummaryStatistics stat = Arrays.stream(intScores).summaryStatistics();       // 統計數據
		
		resp.getWriter().println("scores:"+Arrays.toString(scores));
		resp.getWriter().println("sum:"+stat.getSum());
		resp.getWriter().println("avg:"+stat.getAverage());
		resp.getWriter().println("max:"+stat.getMax());
		resp.getWriter().println("min:"+stat.getMin());
		resp.getWriter().println("count:"+stat.getCount());
				
	    /* 
	     * 使用 Stream 當中的 summaryStatistics() 方法
	     * 可以計算以下統計數據
	
	     * Count：元素的數量
	     * Sum：所有元素的總和
	     * Min：最小值
	     * Max：最大值
	     * Average：平均值
	     */
		
		  
				
		
		
		
		
		
	    
	    
	    
	}
	
	
}
