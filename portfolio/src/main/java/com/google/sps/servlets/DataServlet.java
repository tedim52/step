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

import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import com.google.sps.data.Testimonial;

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
@WebServlet("/testimonials")
public class DataServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    ///Retrieving form data to be put in array list with contents
    BufferedReader reader = request.getReader();
    List<String> formContent = new ArrayList<String>();
    int i = 0;
    while(reader.readLine()!=null) {
        String line = reader.readLine();
        if(i%2==1) {
            formContent.add(line);
        }
        i++;
    }
    
    //Print form - verify backend received it - remove before deployment
    for(String s:formContent) {
       System.out.println(s);
    }
    
    //Create new testimonial object
    Testimonial testimonial = new Testimonial(formContent.get(0), formContent.get(1), formContent.get(2));
    
    //Creating new testimonial entity to store in DB
    Entity testimonialEntity = new Entity("Testimonial");
    testimonialEntity.setProperty("Name", testimonial.getName());
    testimonialEntity.setProperty("Relationship", testimonial.getRelationship());
    testimonialEntity.setProperty("Text", testimonial.getText());
    testimonialEntity.setProperty("Upvote", testimonial.getUpvote());
    testimonialEntity.setProperty("Sentiment", "Positive");

    DatastoreService database = DatastoreServiceFactory.getDatastoreService();
    database.put(testimonialEntity);

    //Return json containing testimonial information
    response.setContentType("application/json");
    response.getWriter().println(convertToJsonUsingGson(testimonial));


    //TODO: Sentiment analysis
    
  }

  /* Converts a Testimonial instance into a JSON string using the Gson library. */
  private String convertToJsonUsingGson(Testimonial testimonial) {
    Gson gson = new Gson();
    String json = gson.toJson(testimonial);
    return json;
  }

  /* Analyzes the sentiment of a sentence. 
    Returns true if positive sentiment, false otherwise. */
  private boolean isPositive(String text) {
      //TODO: Sentiment analysis
      return true;
  }
}
