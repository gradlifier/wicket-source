package com.github.jennybrown8.wicketsourceopener;

import java.util.logging.Logger;

import org.eclipse.ui.IStartup;

import com.github.jennybrown8.wicketsourceopener.preferences.PreferenceValueService;

public class Startup implements IStartup {
	Logger log = Logger.getLogger("Startup");
	
	@Override
	public void earlyStartup()
	{
		if (PreferenceValueService.isStartListenerOnStartup()) {
			// don't have to do anything, just be registered.
		}
	}

}
