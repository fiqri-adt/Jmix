package com.company.percobaanjmix.screen.talk;

import com.company.percobaanjmix.app.TalksScheduleService;
import com.company.percobaanjmix.entity.Talk;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.Calendar;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@SuppressWarnings("ConstantConditions")
@UiController("Talk.browse")
@UiDescriptor("talk-browse.xml")
@LookupComponent("talksTable")
public class TalkBrowse extends StandardLookup<Talk> {
    private final TalksScheduleService talkScheduleService;

    public TalkBrowse(TalksScheduleService talkScheduleService) {
        this.talkScheduleService = talkScheduleService;
    }

    @Subscribe("talksCalendar")
    public void onTalksCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        screenBuilders.editor(Talk.class, this)
                .editEntity((Talk) event.getEntity())
                .withOpenMode(OpenMode.DIALOG)
                .withScreenClass(TalkEdit.class)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                        getScreenData().loadAll();
                    }
                }).show();
    }

    @Autowired
    private ScreenBuilders screenBuilders;

    @Subscribe("talksCalendar")
    public void onTalksCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        talkScheduleService.rescheduleTalk((Talk) event.getEntity(), event.getNewStart());
        getScreenData().loadAll();
    }
}