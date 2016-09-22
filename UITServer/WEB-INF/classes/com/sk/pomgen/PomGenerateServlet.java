package com.sk.pomgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * Servlet implementation class PomGenerateServlet
 */
@WebServlet("/PomGenerateServlet")
public class PomGenerateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PomGenerateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String xpath = request.getParameter("xapthval");
		String action = request.getParameter("actionval");
		String javaNAme = request.getParameter("fileName");
		Configuration cfg = new Configuration();
	    System.out.println(" xpath = "+xpath);
	    System.out.println(" action = "+action);
	    // Where do we load the templates from:
	    cfg.setClassForTemplateLoading(PomGenerateServlet.class, ".");
	    
	    // Some other recommended settings:
	    cfg.setIncompatibleImprovements(new Version(2, 3, 20));
	    cfg.setDefaultEncoding("UTF-8");
	    cfg.setLocale(Locale.US);
	    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	    // 2. Proccess template(s)
	    //
	    // You will do this for several times in typical applications.
	    
	    // 2.1. Prepare the template input:
	    
	    Map<String, Object> input = new HashMap<String, Object>();
	    
	    input.put("xpathval", xpath);
	    input.put("actionval", action);
	    Template template = cfg.getTemplate("sampletemplate.ftl");
	      
	    // 2.3. Generate the output
	    
	    Writer fileWriter  =null;
	    try {
	    // Write output to the console
	    Writer consoleWriter = new OutputStreamWriter(System.out);
	    template.process(input, consoleWriter);
	    File f = new File("/Volumes/Satheesh/JavaPrograms/EclipseWS/POMGenerator/output/"+javaNAme);
	    System.out.println(getServletContext().getRealPath("/"));
	    System.out.println(f.getAbsolutePath());
	    // For the sake of example, also write output into a file:
	     fileWriter = new FileWriter(f);
	  
	      template.process(input, fileWriter);
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
	      fileWriter.close();
	    }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
