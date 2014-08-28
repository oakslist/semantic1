package org.poligraph.controller.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class MainController {

	private static final Logger LOG = Logger.getLogger(MainController.class);

	@RequestMapping(value = "/main")
	public String mainPage(HttpServletRequest request, Model model,
			ModelMap modelMap) {

		HttpSession session = request.getSession(false);

		return AppConstants.MAIN_PAGE;
	}

	@RequestMapping(value = "/{category}")
	public String booksPage(@PathVariable("category") String category,
			HttpServletRequest request, Model model, ModelMap modelMap) {

		
		FileManager.get().addLocatorClassLoader(
				MainController.class.getClassLoader());

		com.hp.hpl.jena.rdf.model.Model tripleModel = FileManager.get()
				.loadModel(getClass().getResource(AppConstants.FILE_NAME).toString());
		
		String categoryDB = category;

		List<Product> itemsList = new ArrayList<Product>();
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "select ?bookName WHERE {?bookName rdf:type ?y ."
				+ "?y rdfs:label '" + categoryDB + "' .} ORDER BY ?bookName";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, tripleModel);

		List<Subject> seeAlsoList = new ArrayList<Subject>();
		String queryStringAlso = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "select ?also WHERE {?s rdf:type ?y ."
				+ "?y rdfs:label '"
				+ categoryDB + "' ." + "?s rdfs:seeAlso ?also .}";
		Query queryAlso = QueryFactory.create(queryStringAlso);
		QueryExecution qexecAlso = QueryExecutionFactory.create(queryAlso,
				tripleModel);
		try {
			ResultSet results = qexec.execSelect();
			ResultSet resultsAlso = qexecAlso.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Product p = new Product();
				p.setFullName(soln.get("bookName").toString());
				p.setCategory(categoryDB);
				itemsList.add(p);
			}
			while (resultsAlso.hasNext()) {
				QuerySolution soln = resultsAlso.nextSolution();
				Subject s = new Subject();
				s.setSubject(soln.get("also").toString());
				seeAlsoList.add(s);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			qexec.close();
			qexecAlso.close();
		}

		model.addAttribute(AppConstants.ITEMS_LIST, itemsList);
		model.addAttribute(AppConstants.SEE_ALSO_LIST, seeAlsoList);

		return AppConstants.MAIN_PAGE;

	}

	@RequestMapping(value = "/{category}/{itemName}")
	public String bookNamedPage(@PathVariable("itemName") String itemName,
			@PathVariable("category") String category,
			HttpServletRequest request, Model model, ModelMap modelMap) {

		FileManager.get().addLocatorClassLoader(
				MainController.class.getClassLoader());

		com.hp.hpl.jena.rdf.model.Model tripleModel = FileManager.get()
				.loadModel(getClass().getResource(AppConstants.FILE_NAME).toString());

		if (!category.equals("filterQuery")) {
			Product itemProduct = null;
			List<Subject> seeAlsoList = new ArrayList<Subject>();
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ "SELECT ?book ?price ?quan ?link ?cat ?comment WHERE {"
					+ "?book rdfs:label '" + itemName + "' ."
					+ "?book <http://www.polygraphy.com/ontologies/polygraphy.owl#hasQuantity> ?quan ."
					+ "?book <http://www.polygraphy.com/ontologies/polygraphy.owl#hasPriceUSD> ?price ."
					+ "?book <http://www.polygraphy.com/ontologies/polygraphy.owl#hasPicture> ?link ."
					+ "?book rdf:type ?cat ."
					+ "?cat rdf:type <http://www.w3.org/2002/07/owl#Class> ."
					+ "?book rdfs:comment ?comment .}";
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query,
					tripleModel);

			String queryStringAlso = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ "SELECT ?book ?link WHERE {"
					+ "?book rdfs:label '"
					+ itemName + "' ." + "?book rdfs:seeAlso ?link .}";

			Query queryAlso = QueryFactory.create(queryStringAlso);
			QueryExecution qexecAlso = QueryExecutionFactory.create(queryAlso,
					tripleModel);
			try {
				ResultSet results = qexec.execSelect();
				ResultSet resultsAlso = qexecAlso.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					itemProduct = new Product();

					itemProduct.setFullName(soln.get("book").toString());
					Literal literal = soln.getLiteral("price");
					itemProduct.setPrice(literal.getInt());

					literal = soln.getLiteral("quan");
					itemProduct.setQuantity(literal.getInt());

					literal = soln.getLiteral("link");
					itemProduct.setLink(literal.getString());
					
					itemProduct.setCategory(soln.get("cat").toString());
					itemProduct.setComment(soln.get("comment").toString());
				}
				while (resultsAlso.hasNext()) {
					QuerySolution soln = resultsAlso.nextSolution();
					Subject s = new Subject();
					s.setSubject(soln.get("link").toString());
					seeAlsoList.add(s);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				qexec.close();
				qexecAlso.close();
			}
//			System.out.println(itemProduct);
			model.addAttribute(AppConstants.PRODUCT, itemProduct);
			model.addAttribute(AppConstants.SEE_ALSO_LIST, seeAlsoList);

			return AppConstants.PRODUCT_PAGE;

		} else {

			// create filter query
			List<Product> itemProductList = new ArrayList<Product>();
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ "SELECT ?item ?price ?quan ?link ?cat ?comment WHERE {"
					+ "?item <http://www.polygraphy.com/ontologies/polygraphy.owl#hasQuantity> ?quan ."
					+ "?item <http://www.polygraphy.com/ontologies/polygraphy.owl#hasPriceUSD> ?price ."
					+ "?item <http://www.polygraphy.com/ontologies/polygraphy.owl#hasPicture> ?link ."
					+ "?item rdfs:comment ?comment ."
					+ "?item rdf:type ?cat ."
					+ "?cat rdf:type <http://www.w3.org/2002/07/owl#Class> ."
					+ "FILTER regex(str(?item), '" + itemName + "', 'i')}";
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query,
					tripleModel);
			try {
				ResultSet results = qexec.execSelect();
				while (results.hasNext()) {
					QuerySolution soln = results.nextSolution();
					Product itemProduct = new Product();

					itemProduct.setFullName(soln.get("item").toString());
					Literal literal = soln.getLiteral("price");
					itemProduct.setPrice(literal.getInt());

					literal = soln.getLiteral("quan");
					itemProduct.setQuantity(literal.getInt());

					literal = soln.getLiteral("link");
					itemProduct.setLink(literal.getString());
					
					itemProduct.setCategory(soln.get("cat").toString());
					
					itemProduct.setComment(soln.get("comment").toString());
										
					itemProductList.add(itemProduct);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				qexec.close();
			}

//			System.out.println(itemProductList);
			model.addAttribute(AppConstants.PRODUCTS_LIST, itemProductList);
			return AppConstants.SEARCH_PRODUCT_PAGE;
		}
	}

}
