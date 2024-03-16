package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Model.ProductDTO;

@WebServlet("/addProduct")
public class ProductController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productName = req.getParameter("productName");
		double productPrice = Double.parseDouble(req.getParameter("productPrice"));
		String productCategoty = req.getParameter("productCategory");
		int productQty = Integer.parseInt(req.getParameter("productQty"));
		
		ProductDTO p = new ProductDTO();
		p.setProductName(productName);
		p.setProductPrice(productPrice);
		p.setProductCategory(productCategoty);
		p.setProductQty(productQty);
		
		Session session = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(ProductDTO.class).buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(p);
		tx.commit();
		
		session.close();
		
		RequestDispatcher rd = req.getRequestDispatcher("index.html");
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		pw.print("<h1>DATA INSERTED SUCCESSFULLY</h1>");
		rd.include(req, resp);
	}

}
