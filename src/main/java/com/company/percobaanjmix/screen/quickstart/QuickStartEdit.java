package com.company.percobaanjmix.screen.quickstart;

import io.jmix.ui.screen.*;
import com.company.percobaanjmix.entity.QuickStart;

@UiController("QuickStart.edit")
@UiDescriptor("quick-start-edit.xml")
@EditedEntityContainer("quickStartDc")
public class QuickStartEdit extends StandardEditor<QuickStart> {
}