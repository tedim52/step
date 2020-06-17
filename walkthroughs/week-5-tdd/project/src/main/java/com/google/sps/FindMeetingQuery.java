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

package com.google.sps;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class containerizing a method to find a suitable meeting time for a list of attendees.
 */
public final class FindMeetingQuery {
  
  /**
   * Finds time slots that all attendees are available to have a meeting.
   *
   * @param events  A list of events that with a time range and list of attendees.
   * @param request A request for a meeting with a list of mandatory attendees and duration of meeting.
   * @return        A list of time ranges that attendees are free to attend the meeting.
   */
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<TimeRange> availableTimeRanges = new ArrayList<>();
    availableTimeRanges.add(TimeRange.WHOLE_DAY);//List of available time ranges starts as the whole day

    //If there are no conflicting events, return the entire day
    if(events.isEmpty()) return availableTimeRanges;//If there are no conflicting events, return the entire day.
    
    //If meeting duration is longer than 24 hours return no available time ranges
    if(request.getDuration() > 1440) return new ArrayList<TimeRange>();
    
    
    for(Event event:events) {
        //Check to make sure that mandatory events for the meeting are attending this event. If not, remove event.
        Set<String> temp = new HashSet<>(event.getAttendees());
        Set<String> intersection = temp.retainAll(request.getAttendees());
        if(intersection.isEmpty()){
            events.remove(event);
            continue;    
        }

        //Find all time ranges in the current available time ranges that conflict with this event.
        Collection<TimeRange> conflictingTimeRanges = new ArrayList<>();
        for(TimeRange timeRange:availableTimeRanges){
            if(timeRange.overlaps(event.getWhen()))
                conflictingTimeRanges.add(timeRange);
        }

        //For conflicting time range, split it up to account for conflicting event
        for(TimeRange conflictingTime: conflictingTimeRanges) {
            //If the event contains the time range
            
            //If the conflicting time contains the event

            //If the conflicting time overlaps the event
                
        
        }













      }
  }
}
