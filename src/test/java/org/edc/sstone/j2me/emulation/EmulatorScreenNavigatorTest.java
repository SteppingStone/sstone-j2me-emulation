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

import junit.framework.TestCase;

import org.edc.sstone.io.ClasspathInputStreamProvider;
import org.edc.sstone.j2me.core.Registry;
import org.edc.sstone.nav.ModuleManager;
import org.edc.sstone.record.reader.RecordFactory;
import org.edc.sstone.record.reader.model.IntArrayRecord;
import org.edc.sstone.record.reader.model.MenuItemRecord;
import org.edc.sstone.record.reader.model.Record;
import org.edc.sstone.record.reader.model.ResourceComponentRecord;
import org.edc.sstone.record.reader.model.ScreenRecord;
import org.edc.sstone.record.reader.model.ScreenSeriesRecord;
import org.edc.sstone.record.reader.model.StyleRecord;
import org.edc.sstone.record.reader.model.TextAreaComponentRecord;

/**
 * @author Greg Orlowski
 */
public class EmulatorScreenNavigatorTest extends TestCase {

    public void testEmulatorScreenNavigator() throws Exception {
        StubMidletManager mm = new StubMidletManager();
        Registry.init(mm);
        EmulatorScreenNavigator nav = new EmulatorScreenNavigator(getNavigator(), false, new int[] { 2,0 });
        ScreenRecord mathScreen1 = nav.getModuleManager().currentScreen();
        assertEquals("math_screen1", mathScreen1.title);
        
        assertEquals("clip2", new EmulatorScreenNavigator(getNavigator(), false, new int[] { 3,2 })
                .getModuleManager().currentScreen().resourcePath);
    }

    static ModuleManager getNavigator() throws Exception {
        return getNavigator("/modules/mod1");
    }

    static ModuleManager getNavigator(String modPath) throws Exception {
        RecordFactory rf = getFactory();
        ModuleManager ret = new ModuleManager(rf, modPath, new ClasspathInputStreamProvider());
        ret.initProperties();
        return ret;
    }

    static RecordFactory getFactory() {
        RecordFactory rf = new RecordFactory();

        Record[] recordObjects = new Record[] {
                new StyleRecord(),
                new TextAreaComponentRecord(),
                new ScreenRecord(),
                new ScreenSeriesRecord(),
                new MenuItemRecord(),
                new IntArrayRecord(),
                new ResourceComponentRecord()
        };

        for (int i = 0; i < recordObjects.length; i++)
            rf.registerType(recordObjects[i]);

        return rf;
    }

}
