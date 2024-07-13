package com.rocketseat.planner.activity;

import com.rocketseat.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository repository;

    public ActivityResponse registerActivity(ActivityRequestPayload payload, Trip trip) {
        Activity newActivity = new Activity(payload.title(), payload.occurs_at(), trip);

        repository.save(newActivity);

        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivitiesFromId(UUID tripId) {
        return repository.findByTripId(tripId).stream().map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
    }
}
