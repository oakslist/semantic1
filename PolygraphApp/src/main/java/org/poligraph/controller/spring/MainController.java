package org.poligraph.controller.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.poligraph.constants.AppConstants;
import org.poligraph.model.bean.Triple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.util.FileManager;


@Controller
public class MainController {

	private static final Logger LOG = Logger.getLogger(MainController.class);
	
//		<a href="<c:url value='/games'/>"><spring:message
//			code="label.GAMES" /></a>
//		<a href="<c:url value='/cards'/>"><spring:message
//			code="label.CARDS" /></

	@RequestMapping(value = "/main")
	public String mainPage(HttpServletRequest request, Model model,
			ModelMap modelMap) {

		HttpSession session = request.getSession(false);
		
		List<Triple> itemsList = new ArrayList<Triple>();
		model.addAttribute(AppConstants.ITEMS_LIST, itemsList);
		
		return AppConstants.MAIN_PAGE;
	}
	
		
	@RequestMapping(value = "/notes")
	public String notesPage(HttpServletRequest request, Model model,
			ModelMap modelMap) {

		HttpSession session = request.getSession(false);
		
		FileManager.get().addLocatorClassLoader(MainController.class.getClassLoader());

		com.hp.hpl.jena.rdf.model.Model tripleModel = FileManager.get()
				.loadModel(AppConstants.FILE_NAME);
		List<Triple> itemsList = new ArrayList<Triple>();
		String queryString = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
				"SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, tripleModel);
		try {
			ResultSet results = qexec.execSelect();
			while(results.hasNext()) {
				
				QuerySolution soln = results.nextSolution();
				itemsList.add(new Triple(soln.get("s").toString(), 
						soln.get("p").toString(), soln.get("o").toString()));
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			qexec.close();
		}
		
		model.addAttribute(AppConstants.ITEMS_LIST, itemsList);
		
		return AppConstants.MAIN_PAGE;
	}
	
	@RequestMapping(value = "/calendars")
	public String calendarsPage(HttpServletRequest request, Model model,
			ModelMap modelMap) {

		HttpSession session = request.getSession(false);
		
		FileManager.get().addLocatorClassLoader(MainController.class.getClassLoader());

		com.hp.hpl.jena.rdf.model.Model tripleModel = FileManager.get()
				.loadModel(AppConstants.FILE_NAME);
		List<Triple> itemsList = new ArrayList<Triple>();
		String queryString = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
				"SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, tripleModel);
		try {
			ResultSet results = qexec.execSelect();
			while(results.hasNext()) {
				
				QuerySolution soln = results.nextSolution();
				itemsList.add(new Triple(soln.get("s").toString(), 
						soln.get("p").toString(), soln.get("o").toString()));
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			qexec.close();
		}
		
		model.addAttribute(AppConstants.ITEMS_LIST, itemsList);
		
		return AppConstants.MAIN_PAGE;
	}
	

}

