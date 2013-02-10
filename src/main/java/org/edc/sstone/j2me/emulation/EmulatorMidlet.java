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

import java.util.Hashtable;

import org.edc.sstone.io.InputStreamProvider;
import org.edc.sstone.j2me.Main;
import org.edc.sstone.j2me.mod.ModuleLoader;
import org.edc.sstone.record.reader.RecordFactory;

/**
 * @author Greg Orlowski
 */
public class EmulatorMidlet extends Main {

    private final InputStreamProvider inputStreamProvider;
    private final int[] initialScreenPath;
    private static final Hashtable midletProps = new Hashtable();

    public EmulatorMidlet(InputStreamProvider inputStreamProvider, final int[] initialScreenPath) {
        super();
        this.initialScreenPath = initialScreenPath != null ? initialScreenPath : new int[] {};
        midletProps.put("modulePaths", "/");
        midletProps.put("fontEngine", "bitmap");
        midletProps.put("fontSizes", "12 14 17");
        midletProps.put("showPreferencesInMainMenu", "true");
        midletProps.put("enableLogScreen", "true");

        this.inputStreamProvider = inputStreamProvider;
    }

    protected InputStreamProvider initStreamProvider() {
        return inputStreamProvider;
    }

    protected ModuleLoader initModuleLoader(InputStreamProvider streamProvider, RecordFactory rf) {
        return new EmulatorModuleLoader(streamProvider, rf, initialScreenPath);
    }

    public String getMidletProperty(String key) {
        Object val = midletProps.get(key);
        if (val != null && val instanceof String) {
            return (String) val;
        }
        return super.getMidletProperty(key);
    }

    // override

}
