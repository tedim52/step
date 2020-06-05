// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

//String to JSON import
import com.google.gson.Gson;

//Database imports
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


import com.google.sps.data.Testimonial;

//Java library imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader; 

/** Servlet that creates a new entity and stores into the DB. 
    Returns serialized testimonial as json */
@WebServlet("/getTestimonials")
public class ListAllTestimonialsServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //Setup a query to retrieve testimonials
      System.out.println("Data retrieved.");
      Query query = new Query("Testimonial").addSort("Upvote", SortDirection.DESCENDING);
      
      //Retrieve entities from DB according to query
      DatastoreService database = DatastoreServiceFactory.getDatastoreService();
      PreparedQuery results = database.prepare(query);

      //Adds all entitys to a list of testimonials
      List<Testimonial> testimonials = new ArrayList<>();
      for(Entity entity:results.asIterable()) {  
        String name = (String) entity.getProperty("Name");
        String relationship = (String) entity.getProperty("Relationship");
        String text = (String) entity.getProperty("Text");
        long voteCount = (long) entity.getProperty("Upvote");
        String sentiment = (String) entity.getProperty("Sentiment");
        System.out.println(name + " " + relationship + " " + text + " " + voteCount);
          
        Testimonial testimonial = new Testimonial(name, relationship, text, voteCount, sentiment);
        testimonials.add(testimonial);
      }

      Gson gson = new Gson();

      //Returns response as a list of json strings
      response.setContentType("application/json");
      response.getWriter().println(gson.toJson(testimonials));
      System.out.println("Data sent through.");
  }  
}
