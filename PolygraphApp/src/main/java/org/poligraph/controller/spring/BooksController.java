package org.poligraph.controller.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.poligraph.constants.AppConstants;
import org.poligraph.model.bean.Product;
import org.poligraph.model.bean.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.util.FileManager;

@Controller
public class BooksController {
	
	private static final Logger LOG = Logger.getLogger(BooksController.class);
	
	@RequestMapping(value = "/books")
	public String booksPage(HttpServletRequest request, Model model,
			ModelMap modelMap) {
		
		FileManager.get().addLocatorClassLoader(MainController.class.getClassLoader());

		com.hp.hpl.jena.rdf.model.Model tripleModel = FileManager.get()
				.loadModel(AppConstants.FILE_NAME);
		List<Product> itemsList = new ArrayList<Product>();
		String queryString = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"select ?bookName WHERE {?bookName rdf:type ?y ." +
                 "?y rdfs:label 'BookProduct' .} ORDER BY ?bookName";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, tripleModel);
		
		List<Subject> seeAlsoList = new ArrayList<Subject>();
		String queryStringAlso = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"select ?also WHERE {?s rdf:type ?y ." +
                 "?y rdfs:label 'BookProduct' ." +
                 "?s rdfs:seeAlso ?also .}";
		Query queryAlso = QueryFactory.create(queryStringAlso);
		QueryExecution qexecAlso = QueryExecutionFactory.create(queryAlso, tripleModel);
		try {
			ResultSet results = qexec.execSelect();
			ResultSet resultsAlso = qexecAlso.execSelect();
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Product p = new Product();
				p.setFullName(soln.get("bookName").toString());
				itemsList.add(p);
			}
			while(resultsAlso.hasNext()) {
				QuerySolution soln = resultsAlso.nextSolution();
				Subject s = new Subject();
				s.setSubject(soln.get("also").toString());
				seeAlsoList.add(s);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			qexec.close();
			qexecAlso.close();
		}
		
		model.addAttribute(AppConstants.ITEMS_LIST, itemsList);
		model.addAttribute(AppConstants.SEE_ALSO_LIST, seeAlsoList);
		
		return AppConstants.MAIN_PAGE;
	}
	
	@RequestMapping(value = "/books/{itemName}")
	public String bookNamedPage(@PathVariable("itemName") String itemName,
			HttpServletRequest request, Model model,
			ModelMap modelMap) {
		
		FileManager.get().addLocatorClassLoader(MainController.class.getClassLoader());

		com.hp.hpl.jena.rdf.model.Model tripleModel = FileManager.get()
				.loadModel(AppConstants.FILE_NAME);
		Product itemProduct = null;
		List<Subject> seeAlsoList = new ArrayList<Subject>();
		String queryString = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"SELECT ?book ?price ?quan WHERE {" + 
				"?book rdfs:label '" + itemName + "' ." +
				"?book <http://www.polygraphy.com/ontologies/polygraphy.owl#hasQuantity> ?quan ." +
				"?book <http://www.polygraphy.com/ontologies/polygraphy.owl#hasPriceUSD> ?price .}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, tripleModel);

		String queryStringAlso = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"SELECT ?links" +
				"WHERE {?book <http://www.w3.org/2000/01/rdf-schema#seeAlso> ?links ." +
				"?book rdfs:label '" + itemName + "' .}";

		Query queryAlso = QueryFactory.create(queryStringAlso);
		QueryExecution qexecAlso = QueryExecutionFactory.create(queryAlso, tripleModel);
		try {
			ResultSet results = qexec.execSelect();
			ResultSet resultsAlso = qexecAlso.execSelect();
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				itemProduct = new Product();
				
				itemProduct.setFullName(soln.get("book").toString());
				Literal literal = soln.getLiteral("price");
				itemProduct.setPrice(literal.getInt());
				
				literal = soln.getLiteral("quan");
				itemProduct.setQuantity(literal.getInt());
				
			}
			while(resultsAlso.hasNext()) {
				QuerySolution soln = resultsAlso.nextSolution();
				Subject s = new Subject();
				System.out.println(soln.get("links").toString());
				s.setSubject(soln.get("links").toString());
				System.out.println(s);
				seeAlsoList.add(s);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			qexec.close();
			qexecAlso.close();
		}
		System.out.println(seeAlsoList);
		model.addAttribute(AppConstants.PRODUCT, itemProduct);
		model.addAttribute(AppConstants.SEE_ALSO_LIST, seeAlsoList);
		
		return AppConstants.PRODUCT_PAGE;
	}

}
