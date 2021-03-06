/*******************************************************************************
 * Copyright (c) 2012 MCForge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package net.mcforge.groupmanager.main;

import java.util.Properties;

import net.mcforge.API.ManualLoad;
import net.mcforge.API.plugin.Plugin;
import net.mcforge.groupmanager.commands.CmdDemote;
import net.mcforge.groupmanager.commands.CmdGroup;
import net.mcforge.groupmanager.commands.CmdPromote;
import net.mcforge.groupmanager.commands.CmdSetGroup;
import net.mcforge.iomodel.Player;
import net.mcforge.server.Server;


@ManualLoad
public class GroupPlugin extends Plugin {

	public static Server server;
	
	@Override
	public String getName() {
		return "Group Manager";
	}
	@Override
	public String getVersion() {
		return "1.0.0";
	}
	@Override
	public String getAuthor() {
		return "Wouto1997";
	}
	
	public GroupPlugin(Server server)
	{
		super(server);
	}
	public GroupPlugin(Server server, Properties properties)
	{
		super(server, properties);
	}
	@Override
	public void onLoad(String[] args) {
		server = getServer();
		getServer().getCommandHandler().addCommand(new CmdGroup());
		getServer().getCommandHandler().addCommand(new CmdDemote());
		getServer().getCommandHandler().addCommand(new CmdPromote());
		getServer().getCommandHandler().addCommand(new CmdSetGroup());
	}

	@Override
	public void onUnload() {
		getServer().getCommandHandler().removeCommand("group");
		getServer().getCommandHandler().removeCommand("demote");
		getServer().getCommandHandler().removeCommand("promote");
		getServer().getCommandHandler().removeCommand("setgroup");
	}
	
	public static Player find(String username)
	{
		return server.findPlayer(username);
	}
}

