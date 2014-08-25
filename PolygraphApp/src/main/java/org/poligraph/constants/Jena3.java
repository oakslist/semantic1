//package org.poligraph.constants;
//
//import java.util.List;
//
//import com.franz.agraph.http.exception.AGHttpException;
//import com.franz.agraph.jena.AGGraphMaker;
//import com.franz.agraph.repository.AGCatalog;
//import com.franz.agraph.repository.AGRepository;
//import com.franz.agraph.repository.AGRepositoryConnection;
//import com.franz.agraph.repository.AGServer;
//
//public class Jena3 {
//	
//	static private final String SERVER_URL = "http://epbygomw0098t20:10035";
//	static private final String CATALOG_ID = "java-catalog";
//	static private final String REPOSITORY_ID = "TestJenaVarachai";
//	static private final String USERNAME = "test";
//	static private final String PASSWORD = "test";
//	static private final String TEMPORARY_DIRECTORY = "";
//	
//	static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";
//
//	/**
//	 * Creating a Repository
//	 * @throws AGHttpException 
//	 */
//	//1
//	public static AGGraphMaker example1(boolean close)
//			throws Exception, AGHttpException {
//		// Tests getting the repository up.
//		System.out.println("\nStarting example1().");
//		AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
//		System.out.println("AvailablSe catalogs: " + server.listCatalogs());
//		
//		//2
//		AGCatalog catalog = server.getCatalog(CATALOG_ID);
//		System.out.println("Available repositories in catalog "
//				+ (catalog.getCatalogName()) + ": ");
////				+ catalog.listRepositories().toString());
//		return null;
//		//3
////		catalog.deleteRepository(REPOSITORY_ID); 
//		
//		//4
////		AGRepository myRepository = catalog.createRepository(REPOSITORY_ID);
////		System.out.println("Got a repository.");
////		myRepository.initialize();
////		System.out.println("Initialized repository.");
//		
////		//5
////		AGRepository myRepository = catalog.
////		AGRepositoryConnection conn = myRepository.getConnection();
////		closeBeforeExit(conn);
////		System.out.println("Got a connection.");
////		System.out.println("Repository " + (myRepository.getRepositoryID())
////				+ " is up! It contains " + (conn.size()) + " statements.");
////		
////		//6
////		AGGraphMaker maker = new AGGraphMaker(conn);
////		System.out.println("Got a graph maker for the connection.");
////		
////		//7
////		List<String> indices = conn.listValidIndices();
////		System.out.println("All valid triple indices: " + indices);
////	    
////	    //8
////	    indices = conn.listIndices();
////	    System.out.println("Current triple indices: " + indices);
////        
////        //9
////	    System.out.println("Removing graph indices...");
////        conn.dropIndex("gospi");
////        conn.dropIndex("gposi");
////        conn.dropIndex("gspoi");
////        indices = conn.listIndices();
////        println("Current triple indices: " + indices);
////        
////        //10
////        System.out.println("Adding one graph index back in...");
////        conn.addIndex("gspoi");
////        indices = conn.listIndices();
////        System.out.println("Current triple indices: " + indices);
////        
////        //11
////        if (close) {
////			// tidy up
////			maker.close();
////			conn.close();
////			myRepository.shutDown();
////			return null;
////		}
////		return maker;
//	}
//		
//		
//
//}
