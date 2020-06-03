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

import com.google.sps.data.Testimonial;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader; 

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
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
    
    //for(String s:formContent) {
    //   System.out.println(s);
    //}
    
    Testimonial testimonial = new Testimonial(formContent.get(0), formContent.get(1), formContent.get(2));
    
    response.setContentType("application/json");
    response.getWriter().println(convertToJsonUsingGson(testimonial));


    //TODO: Sentiment Analysis processing
    
  }

  /* Converts a Testimonial instance into a JSON string using the Gson library. */
  private String convertToJsonUsingGson(Testimonial testimonial) {
    Gson gson = new Gson();
    String json = gson.toJson(testimonial);
    return json;
  }
}
