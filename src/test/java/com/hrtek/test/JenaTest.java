package com.hrtek.test;

import com.hrtek.base.BaseJunitTest;
import com.hrtek.tool.RestHttpClient;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.system.Txn;
import org.apache.jena.update.Update;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JenaTest extends BaseJunitTest {
    @Test
    public void jenaTDB(){
        Query query = QueryFactory.create("PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "prefix a: <http://www.owl/military.owl#>\n" +
                "SELECT ?s ?p ?o \n" +
                "WHERE {\n" +
                "?s   rdf:type  ?o  \n" +
                "}\n" +
                "LIMIT 1000");
        String queryService = "http://127.0.0.1:3030/data";
        // Query service, no update, no graph store protocol.
        long start =System.currentTimeMillis();
        try ( RDFConnection conn = RDFConnectionFactory.connect(queryService) ) {
            //conn.queryResultSet(query, ResultSetFormatter::out);

            Dataset ds =  conn.fetchDataset();
            Model model = ds.getDefaultModel();
            OntModel ontModel=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF,model);
            ontModel.listDatatypeProperties().forEachRemaining(obj -> System.out.println("dataproperty:"+obj.getLocalName()));
            ontModel.listUnionClasses().forEachRemaining(obj -> System.out.println("Unionclass:"+obj.getLocalName()));
            ontModel.listObjects().forEachRemaining(obj -> System.out.println("object:"+obj.toString()));


        /*   StmtIterator stmt= ds.getDefaultModel().listStatements();
               stmt.forEachRemaining(obj -> System.out.println("Bag:--"+obj.getSeq()));*/
            long end =System.currentTimeMillis();
            System.out.println("所用时间:-------"+(end - start));

            //   conn.fetch();
            /*Txn.executeRead(conn,()->{
                conn.queryResultSet(query,ResultSetFormatter::out);
            });*/
        }
    }

    @Test
    public  void JenaRead(){
        try {
         // RestHttpClient.restrequest("http://localhost:3030/zsk?query","utf-8");
            String str =     RestHttpClient.restresponse("http://127.0.0.1:3030/zsk","utf-8","PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "prefix a: <http://www.owl/military.owl#>\n" +
                    "SELECT ?s ?p ?o \n" +
                    "WHERE {\n" +
                    "?s   rdf:type  ?o \n" +
                    "}\n" +
                    "LIMIT 1000");
            System.out.println(str) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void JdbcTest() {
        Query query = QueryFactory.create("SELECT * { {?s ?p ?o } UNION { GRAPH ?g { ?s ?p ?o } } }");
        Dataset dataset = DatasetFactory.createTxnMem();
        RDFConnection conn = RDFConnectionFactory.connect(dataset);

        Txn.executeWrite(conn, () ->{
            System.out.println("Load a file");
           // conn.load("zsk.ttl");
            conn.load("http://localhost:3030", "zsk.ttl");
            System.out.println("In write transaction");
            conn.queryResultSet(query, ResultSetFormatter::out);
        });
        // And again - implicit READ transaction.
        System.out.println("After write transaction");
        conn.queryResultSet(query, n -> System.out.println(n));
    }



    public  void SDBTest(){

    }
}
