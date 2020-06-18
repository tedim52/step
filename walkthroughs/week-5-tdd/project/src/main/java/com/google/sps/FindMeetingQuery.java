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

//Guava library for intersection method
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
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
    //If meeting duration is longer than 24 hours return no available time ranges
    if(request.getDuration() > 1400) return new ArrayList<>();
    
    Collection<TimeRange> availableTimeRanges = new ArrayList<>();
    
    //List of available time ranges is initially the entire day
    availableTimeRanges.add(TimeRange.WHOLE_DAY);

    //If there are no conflicting events, return the entire day
    if(events.isEmpty()) return availableTimeRanges;
    
    //If meeting duration is longer than 24 hours return no available time ranges
    
    for(Event event:events) {
        //Check to make sure that mandatory events for the meeting are attending this event. If not, remove event.
        /*Note: Attempted to use guava intersection method but type errors occurred*/
        Collection<String> meetingAttendees = request.getAttendees();
        int count = 0;
        for(String attendee:meetingAttendees) {
            if(event.getAttendees().contains(attendee))
                count++;
        }
        if(count == 0) continue;

        //Find all time ranges in the current available time ranges that conflict with this event.
        Collection<TimeRange> conflictingTimeRanges = new ArrayList<>();
        for(TimeRange timeRange:availableTimeRanges){
            if(timeRange.overlaps(event.getWhen()))
                conflictingTimeRanges.add(timeRange);
        }

        //For conflicting time range, construct new time ranges or remove time range depending on case
        for(TimeRange conflictingTime: conflictingTimeRanges) {
            
            //Case 1: event Time Range:          |-------------|
            //        conflicting Time Range:         |--|
            if(event.getWhen().contains(conflictingTime)){
                //remove event from available time ranges
                availableTimeRanges.remove(conflictingTime);
                continue;
            }

            //Case 2: event Time Range:               |--|
            //        conflicting Time Range:    |-------------|
            if(conflictingTime.contains(event.getWhen())){
                //Split into two time ranges
                TimeRange beforeEvent = TimeRange.fromStartEnd(conflictingTime.start(), event.getWhen().start(), false);
                TimeRange afterEvent = TimeRange.fromStartEnd(event.getWhen().end(), conflictingTime.end(), false);
                
                //Check that the time ranges are long enough for meeting
                if(beforeEvent.duration() >= request.getDuration())
                    availableTimeRanges.add(beforeEvent);
                if(afterEvent.duration() >= request.getDuration())
                    availableTimeRanges.add(afterEvent);
                availableTimeRanges.remove(conflictingTime);
                continue;
            }

            //Case 3: event Time Range:          |-------|     or     |-------|
            //        conflicting Time Range:       |-------|      |-------|
            //If conflicting Time's start time is in event's Time Range
            TimeRange removeConflict = null;
            if(event.getWhen().contains(conflictingTime.start())){
                removeConflict = TimeRange.fromStartEnd(event.getWhen().end(), conflictingTime.end(), false);                    TimeRange.fromStartEnd(event.getWhen().end(), conflictingTime.end(), false);
            }
            //If event's Time Range start time is in conflicting Time's Time Range
            if(conflictingTime.contains(event.getWhen().start())){
                removeConflict = TimeRange.fromStartEnd(conflictingTime.start(), event.getWhen().end(), false);
            }
                
            //Check that the time range is long enough for meeting
            if(removeConflict.duration() >= request.getDuration())
                availableTimeRanges.add(removeConflict);
                
            availableTimeRanges.remove(conflictingTime);
            
            
        }
      }
      return availableTimeRanges;
  }
}
