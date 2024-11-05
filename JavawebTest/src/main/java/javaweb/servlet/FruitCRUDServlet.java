package javaweb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 水果盤 CRUD
 * 新增路徑：/fruit/create?name=apple or /fruit/create?name=banana etc...
 * 查詢路徑：/fruit/read
 * 修改路徑：/fruit/update?name=apple&newName=pineapple
 * 刪除路徑：/fruit/delete?name=banana
 */

@WebServlet("/fruit/*")
public class FruitCRUDServlet extends HttpServlet {

	private List<String> fruits = new CopyOnWriteArrayList<String>(); // CopyOnWriteArrayList() 可支援多人同時存取

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		String name = req.getParameter("name");
		String newName = req.getParameter("newName");
		
		switch(pathInfo) {
		case"/create":
			fruits.add(name);
		    resp.getWriter().print("Create fruit OK");
		    break;
		case"/read":
		    resp.getWriter().print(fruits);
		    break;
		case"/update":
			int idx = fruits.indexOf(name);
			fruits.set(idx, newName);
			resp.getWriter().print("Update fruit OK");
			break;
		case"/delete":
			fruits.remove(name);
			resp.getWriter().print("Delete fruit OK");
			break;

		
		}
		
	}

}
