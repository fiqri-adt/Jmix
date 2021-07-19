package com.company.percobaanjmix.screen.quickstart;

import io.jmix.ui.screen.*;
import com.company.percobaanjmix.entity.QuickStart;

@UiController("QuickStart.browse")
@UiDescriptor("quick-start-browse.xml")
@LookupComponent("quickStartsTable")
public class QuickStartBrowse extends StandardLookup<QuickStart> {
}