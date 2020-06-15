package com.google.sps.servlets;

//String to JSON import
import com.google.gson.Gson;

//Wine
import com.google.sps.data.Wine;

//Java library imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;  
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet that reads in wines data and returns it to frontend as an array 
of jsons. */
@WebServlet("/wines")
public class WinesServlet extends HttpServlet {
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<Wine> wines = new ArrayList<>();
    System.out.println("Start collecting wines.");
    Path pathToFile = Paths.get("/home/tedimitiku/step/portfolio/src/main/java/com/google/sps/data/wine.csv");
    System.out.println(pathToFile);

    
    //Create ArrayList full of wine information from wines.csv
    try(BufferedReader br = Files.newBufferedReader(pathToFile)) {
        String line = br.readLine();
        while(line != null){
            String[] info = line.split(";");
            Wine wine = createWine(info[1],info[2],info[3]);
            wines.add(wine);

            line = br.readLine();
        }
        System.out.println(wines);
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }

    Gson gson = new Gson();

    //Returns response as a list of wine json strings
    response.setContentType("application/json");
    response.getWriter().println(gson.toJson(wines));
    System.out.println("Data sent through.");
    
  }

  public static Wine createWine(String description, String designation, String province){
    return new Wine(description, designation, province);
  }  
}
