package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository  implements TimeEntryRepository{

    private HashMap<Long, TimeEntry> listEntry = new HashMap<>();

    private Long localId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = localId++;

        TimeEntry timeEntryNew = new TimeEntry();
        timeEntryNew.setId(id);
        timeEntryNew.setDate(timeEntry.getDate());
        timeEntryNew.setHours(timeEntry.getHours());
        timeEntryNew.setProjectId(timeEntry.getProjectId());
        timeEntryNew.setUserId(timeEntry.getUserId());

        listEntry.put(id, timeEntryNew);

        return timeEntryNew;
    }


    @Override
    public TimeEntry find(long id) {

        return listEntry.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(listEntry.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(find(id) == null){
            return null;
        }
        TimeEntry timeEntryUpdate= new TimeEntry();
        timeEntryUpdate.setId(id);
        timeEntryUpdate.setDate(timeEntry.getDate());
        timeEntryUpdate.setHours(timeEntry.getHours());
        timeEntryUpdate.setProjectId(timeEntry.getProjectId());
        timeEntryUpdate.setUserId(timeEntry.getUserId());

        listEntry.replace(id, timeEntryUpdate);
        return timeEntryUpdate;
    }

    @Override
    public void delete(long id) {
        listEntry.remove(id);
    }
}
