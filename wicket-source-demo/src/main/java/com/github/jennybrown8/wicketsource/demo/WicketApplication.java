package com.github.jennybrown8.wicketsource.demo;

import org.apache.wicket.protocol.http.WebApplication;
import com.github.jennybrown8.wicketsource.WicketSource;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * See com.github.jennybrown8.wicketsource.demo.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		WicketSource.configure(this);
	}
}
