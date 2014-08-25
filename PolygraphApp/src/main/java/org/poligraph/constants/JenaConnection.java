package org.poligraph.constants;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaConnection {
	
	public static void main(String args[]) {
		
		OntModel m = ModelFactory.createOntologyModel();
		
		Model m2 = ModelFactory.createDefaultModel();
		String NS = "http://example.com/test/";
		
		Resource r = m2.createResource(NS + "r");
		Property p = m2.createProperty(NS + "p");
		
		r.addProperty(p, "hello world", XSDDatatype.XSDstring);
		m2.write(System.out, "Turtle");
	}
	
//	static private final String SERVER_URL = "http://epbygomw0098t20:10035";
//	static private final String CATALOG_ID = "java-catalog";
//	static private final String REPOSITORY_ID = "Varachai";
//	static private final String USERNAME = "test";
//	static private final String PASSWORD = "test";
//	static private final String TEMPORARY_DIRECTORY = "";
//
//	static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";
//
//	/**
//	 * Creating a Repository
//	 */
//	public static AGGraphMaker example1(boolean close)
//			throws Exception {
//		// Tests getting the repository up.
//		ModelFactory mf = null;
//		println("\nStarting example1().");
//		AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
//		println("Available catalogs: " + server.listCatalogs());

}
