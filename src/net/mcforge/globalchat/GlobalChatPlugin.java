/*******************************************************************************
 * Copyright (c) 2012 MCForge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
/*
 * Plugin shows last edited block if using wom
 */
package net.mcforge.globalchat;

import java.io.IOException;
import java.util.Random;

import net.mcforge.API.ManualLoad;
import net.mcforge.API.plugin.Plugin;
import net.mcforge.server.Server;
import net.mcforge.util.properties.Properties;

@ManualLoad
public class GlobalChatPlugin extends Plugin {
	private GlobalChatBot gcBot;
	@Override
	public String getName() {
		return "MCForge Global Chat Plugin";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	public GlobalChatPlugin(Server server) {
		super(server);
	}

	public GlobalChatPlugin(Server server, java.util.Properties properties) {
		super(server, properties);
	}

	@Override
	public void onLoad(String[] args) {

		Properties p = getServer().getSystemProperties();
	    String name = "ForgeBot" + new Random(System.currentTimeMillis()).nextInt(1000000000);
	    boolean enabled = true;
	    if (!p.hasValue("GC-Enabled")) { 
	    	p.addSetting("GC-Enabled", true);
	    	saveConfig();
	    }
	    else
	    	enabled = p.getBool("GC-Enabled");
	    
	    if (!p.hasValue("GC-Nickname")) {
	    	p.addSetting("GC-Nickname", name);
	    	saveConfig();
	    }
	    else
	    	name = p.getValue("GC-Nickname");
	    
	    if (!enabled)
	    	return;
		gcBot = new GlobalChatBot(this, getServer(), name);
		try {
			gcBot.startBot();
		}
		catch (IOException e) {
			getServer().Log("Error while starting GC Bot! Global Chat disabled!");
			gcBot.disposeBot();
			e.printStackTrace();
		}
	}

	@Override
	public void onUnload() {
		
	}
	
	private void saveConfig() {
		try {
			getServer().getSystemProperties().save("system.config");		
		}
		catch (IOException ex) {
		}
	}
}