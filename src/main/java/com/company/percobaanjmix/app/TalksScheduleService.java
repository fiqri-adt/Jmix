package com.company.percobaanjmix.app;

import com.company.percobaanjmix.entity.Talk;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component("TalksScheduleService")
public class TalksScheduleService {

    private final DataManager dataManager;

    public TalksScheduleService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void rescheduleTalk(Talk talk, LocalDateTime newStartDate) {
        LocalDateTime dayStart = newStartDate.truncatedTo(ChronoUnit.DAYS).withHour(8);
        LocalDateTime dayEnd = newStartDate.truncatedTo(ChronoUnit.DAYS).withHour(19);

        Long talksSameTime = dataManager.loadValue("select count(t) " +
                "from planner_Talk t where " +
                "(t.startDate between :dayStart and :dayEnd) " +
                "and t.speaker = :speaker " +
                "and t.id <> :talkId", Long.class)
                .parameter("dayStart", dayStart)
                .parameter("dayEnd", dayEnd)
                .parameter("speaker", talk.getSpeaker())
                .parameter("talkId", talk.getId())
                .one();
        if (talksSameTime >= 2) {
            return;
        }
        talk.setStartDate(newStartDate);
        dataManager.save(talk);
    }

}