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

import org.edc.sstone.il8n.MessageSource;
import org.edc.sstone.j2me.audio.AudioPlayer;
import org.edc.sstone.j2me.core.DeviceScreen;
import org.edc.sstone.j2me.core.MIDletManager;
import org.edc.sstone.j2me.device.BacklightControl;
import org.edc.sstone.j2me.font.FontFactory;
import org.edc.sstone.j2me.ui.style.theme.Theme;
import org.edc.sstone.nav.ScreenNavigation;
import org.edc.sstone.res.ResourceProvider;
import org.edc.sstone.store.ValueSource;

/**
 * @author Greg Orlowski
 */
public class StubMidletManager implements MIDletManager {

    private ScreenNavigation nav;
    Hashtable midletProps = new Hashtable();

    StubMidletManager() {
        midletProps.put("enableLogScreen", "false");
    }

    public Theme getTheme() {
        return null;
    }

    public void setTheme(Theme theme) {
    }

    public void exit() {
    }

    public void setScreen(DeviceScreen deviceScreen) {
        // TODO Auto-generated method stub

    }

    public BacklightControl getBacklightControl() {
        // TODO Auto-generated method stub
        return null;
    }

    public DeviceScreen getScreen() {
        // TODO Auto-generated method stub
        return null;
    }

    public MessageSource getMessageSource() {
        // TODO Auto-generated method stub
        return null;
    }

    public AudioPlayer getAudioPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        // TODO Auto-generated method stub

    }

    public ResourceProvider getResourceProvider() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setResourceProvider(ResourceProvider resourceProvider) {
        // TODO Auto-generated method stub

    }

    public String getMidletProperty(String key) {
        Object val = midletProps.get(key);
        return val != null ? ((String) val) : null;
    }

    public void handleException(Throwable e) {
        e.printStackTrace();
    }

    public void setScreenNavigation(ScreenNavigation nav) {
        this.nav = nav;
    }

    public ScreenNavigation getScreenNavigation() {
        return nav;
    }

    public Object getUserPreference(int recordId) {
        return null;
    }

    public ValueSource getUserPreferences() {
        return null;
    }

    public FontFactory getFontFactory() {
        return null;
    }

    public void showMainMenu() {
    }

    public void setMessageSource(String lang) {
        // DO NOTHING
    }

}
