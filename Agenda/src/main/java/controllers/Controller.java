package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = {"/Controller",  "/main", "/new", "/registerContact", "/updateContact", "/doUpdateContact", "/delete", "/report"})
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The bean. */
	JavaBeans bean = new JavaBeans();
	
    /**
     * Instantiates a new controller.
     */
    public Controller() {
     }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/main")) {
			contacts(request, response);
		}else if(action.equals("/new")) {
			newcontact(request, response);
		}else if(action.equals("/registerContact")) {
			registerNewContact(request, response);
		}else if(action.equals("/updateContact")) {
			prepareUpdateContact(request, response);
		}else if(action.equals("/doUpdateContact")) {
			doUpdateContact(request, response);
		}else if(action.equals("/delete")) {
			deleteContact(request, response);
		}else if(action.equals("/report")) {
			generateReport(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	
	/**
	 * Contacts.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contacts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<JavaBeans> listAllContacts = dao.getAllContacts();
		request.setAttribute("contacts", listAllContacts);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Newcontact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void newcontact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("novo.html");
	}
	
	/**
	 * Register new contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void registerNewContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bean.setNome(request.getParameter("name"));
		bean.setPhone(request.getParameter("phone"));
		bean.setEmail(request.getParameter("email"));
		
		dao.insertContact(bean);
		
		response.sendRedirect("main");
	}
	
	/**
	 * Prepare update contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void prepareUpdateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		bean.setId(id);
		dao.selectOneContact(bean);
		
		request.setAttribute("id", bean.getId());
		request.setAttribute("name", bean.getNome());
		request.setAttribute("phone", bean.getPhone());
		request.setAttribute("email", bean.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Do update contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doUpdateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bean.setId(Integer.parseInt(request.getParameter("id")));
		bean.setNome(request.getParameter("name"));
		bean.setPhone(request.getParameter("phone"));
		bean.setEmail(request.getParameter("email"));
		
		dao.updateContact(bean);
		
		response.sendRedirect("main");
	}
	
	/**
	 * Delete contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deleteContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bean.setId(Integer.parseInt(request.getParameter("id")));
		
		dao.deleteContact(bean);
		
		response.sendRedirect("main");
		
	}

	/**
	 * Generate report.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void generateReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document document = new Document();
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; file name="+ "contactos.pdf");
			PdfWriter.getInstance(document, response.getOutputStream());
			
			document.open();
			
			document.add(new Paragraph("Lista de Contactos"));
			document.add(new Paragraph(""));
			PdfPTable table = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("#"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			
			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);
			table.addCell(col4);
			
			ArrayList<JavaBeans> listContacts = dao.getAllContacts();
			for (int i = 0; i < listContacts.size(); i++) {
				table.addCell(String.valueOf(listContacts.get(i).getId()));
				table.addCell(listContacts.get(i).getNome());
				table.addCell(listContacts.get(i).getPhone());
				table.addCell(listContacts.get(i).getEmail());
			}
			
			document.add(table);
			
			document.close();
		} catch (Exception e) {
			System.out.println("Erro ao criar o Relatorio" + e.getMessage());
			document.close();
		}
	}
	
}
