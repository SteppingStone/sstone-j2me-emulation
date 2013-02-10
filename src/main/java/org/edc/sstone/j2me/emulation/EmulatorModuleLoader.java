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

import org.edc.sstone.io.InputStreamProvider;
import org.edc.sstone.j2me.mod.ModuleLoader;
import org.edc.sstone.j2me.nav.ScreenNavigator;
import org.edc.sstone.nav.ModuleManager;
import org.edc.sstone.record.reader.RecordFactory;

/**
 * @author Greg Orlowski
 */
public class EmulatorModuleLoader extends ModuleLoader {

    private final int[] initialScreenPath;

    public EmulatorModuleLoader(InputStreamProvider streamProvider, RecordFactory rf, int[] initialScreenPath) {
        super(streamProvider, rf, "/");
        this.initialScreenPath = initialScreenPath;
    }

    protected ScreenNavigator initScreenNavigator(ModuleManager mm, boolean otherModulesAvailable) {
        return new EmulatorScreenNavigator(mm, otherModulesAvailable, initialScreenPath);
    }

}
