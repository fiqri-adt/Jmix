package com.company.percobaanjmix.screen.talk;

import io.jmix.ui.screen.*;
import com.company.percobaanjmix.entity.Talk;

@UiController("Talk.edit")
@UiDescriptor("talk-edit.xml")
@EditedEntityContainer("talkDc")
public class TalkEdit extends StandardEditor<Talk> {
    @Subscribe
    public void onInitEntity(InitEntityEvent<Talk> event) {
        event.getEntity().setDuration(1);
    }
}