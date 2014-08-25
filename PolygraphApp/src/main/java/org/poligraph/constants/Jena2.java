package org.poligraph.constants;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class Jena2 {
	
//	static private final String SERVER_URL = "http://epbygomw0098t20:10035";
//	static private final String CATALOG_ID = "java-catalog";
//	static private final String REPOSITORY_ID = "TestJenaVarachai";
//	static private final String USERNAME = "test";
//	static private final String PASSWORD = "test";
//	static private final String TEMPORARY_DIRECTORY = "";
	
//	https://svn.apache.org/repos/asf/jena/trunk/jena-core/src-examples/jena/examples/rdf/Tutorial06.java
	
//	public static void main(String args[]) {
//		FileManager.get().addLocatorClassLoader(Jena2.class.getClassLoader());
//		Model model = FileManager.get().loadModel("c:/Users/Siarhei_Varachai/work/github/repo/semantic1/PolygraphApp/src/main/resources/data.rdf");
//		model.write(System.out, "RDF/JSON"); //TURTLE
//	}
	public static void main(String args[]) {
		sparqlTest();
	}
	
	static void sparqlTest() {
		FileManager.get().addLocatorClassLoader(Jena2.class.getClassLoader());
		Model model = FileManager.get().loadModel("c:/Users/Siarhei_Varachai/work/github/repo/semantic1/PolygraphApp/src/main/resources/data.rdf");
		String queryString = 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
				"SELECT * WHERE {" +
				" ?person foaf:name ?x ." +
//				" FILTER(?x = \"Alice\")" +
//				" ?person foaf:knows ?person2 ." +
				"}";
//				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
//				"PREFIX foaf: <http://xmlns.com/foaf/0.1> " +
//				"SELECT ?s ?p ?o WHERE { " +
//				" ?s ?p ?o" +
//				"}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Literal name = soln.getLiteral("x");
				System.out.println(name);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			qexec.close();
		}
	}
}
