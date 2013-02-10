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
package org.edc.sstone.j2me.io;

import gnu.classpath.java.util.zip.ZipEntry;
import gnu.classpath.java.util.zip.ZipInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.edc.sstone.Constants;
import org.edc.sstone.io.InputStreamProvider;

/**
 * This {@link InputStreamProvider} is intended to be used when running sstone-j2me in the emulator
 * in the desktop authoring tool. It should read
 * 
 * @author Greg Orlowski
 */
public class EmulatorInputStreamProvider implements InputStreamProvider {

    private final ByteArrayInputStream projectIndexInputStream;
    private final ByteArrayInputStream projectPropertiesInputStream;
    private final SimpleInputStreamProvider projectZipFileStreamProvider;

    public EmulatorInputStreamProvider(ByteArrayInputStream projectIndexInputStream,
            ByteArrayInputStream projectPropertiesInputStream, SimpleInputStreamProvider projectZipFileStreamProvider) {
        this.projectIndexInputStream = projectIndexInputStream;
        this.projectPropertiesInputStream = projectPropertiesInputStream;
        this.projectZipFileStreamProvider = projectZipFileStreamProvider;
    }

    public InputStream getInputStream(String path) throws IOException {
        InputStream ret = null;
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if (Constants.MODULE_INDEX_FILENAME.equals(path)) {
            ret = projectIndexInputStream;
            ret.reset();
        } else if (Constants.MODULE_PROPERTIES_FILENAME.equals(path)) {
            ret = projectPropertiesInputStream;
            ret.reset();
        } else {
            ret = getZipFileResourceStream(path);
        }
        return ret;
    }

    private InputStream getZipFileResourceStream(String path) {
        ZipInputStream zin = new ZipInputStream(getProjectZipFileInputStream());
        ZipEntry ze = null;
        try {
            while ((ze = zin.getNextEntry()) != null) {
                if (path.equals(ze.getName()))
                    return zin;
            }
        } catch (IOException e) {
            // TODO do something better if reading the project zip throws an IOException
            throw new RuntimeException("cannot read project zip file: " + e.getMessage());
        }
        if (zin != null) {
            try {
                zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private InputStream getProjectZipFileInputStream() {
        return projectZipFileStreamProvider.getInputStream();
    }

    // private

    public String getUrl(String path) {
        return path;
    }

}
