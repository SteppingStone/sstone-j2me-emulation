/*
 * Copyright (c) 2012 EDC
 * 
 * This file is part of Stepping Stone.
 * 
 * Stepping Stone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Stepping Stone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Stepping Stone.  If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */
package org.edc.sstone.j2me.emulation;

import org.edc.sstone.CheckedException;
import org.edc.sstone.j2me.nav.ScreenNavigator;
import org.edc.sstone.nav.ModuleManager;
import org.edc.sstone.record.reader.model.MenuItemRecord;
import org.edc.sstone.record.reader.model.ScreenRecord;

/**
 * @author Greg Orlowski
 */
public class EmulatorScreenNavigator extends ScreenNavigator {

    public EmulatorScreenNavigator(ModuleManager moduleNav, boolean otherModulesAvailable, int[] initialPosition) {
        super(moduleNav, otherModulesAvailable);
        setInitialPosition(initialPosition);
    }

    private void setInitialPosition(int[] modulePath) {
        // moduleManager.keepStreamOpen = (modulePath.length > 0);
        MenuItemRecord menuItemRecord = null;
        try {
            ScreenRecord currScreenRecord = moduleManager.firstScreen();
            for (int level = 0; level < modulePath.length; level++) {
                int offset = modulePath[level];
                // moduleManager.keepStreamOpen = (level < modulePath.length - 1);
                if (moduleManager.isCurrLevelScreenSeries()) {
                    for (int i = 0; i < offset; i++) {
                        currScreenRecord = moduleManager.next();
                    }
                } else {
                    menuItemRecord = (MenuItemRecord) currScreenRecord.componentRecords.elementAt(offset);
                    currScreenRecord = moduleManager.descend(menuItemRecord);
                }
            }
        } catch (CheckedException ce) {
            showErrorScreen(ce);
        }
    }

    // this is here just to expose moduleManager in unit tests
    ModuleManager getModuleManager() {
        return moduleManager;
    }
}
