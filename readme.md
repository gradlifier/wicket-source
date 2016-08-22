Wicket-Source speeds up wicket development by providing click-through from browser HTML back to the original Wicket components in your source.

## Features

1. Wicket Module "wicketsource" - records where in the code each component is constructed. Adds an HTML attribute.
2. Browser Extension "WicketSource" - displays html attribute and lets you click to open (for Chrome).
3. Eclipse plugin "Wicket Source Opener" - listens for clicks from browser and opens the file to that line.

## Installation

See the [Wiki page](https://github.com/jennybrown8/wicket-source/wiki) for deeper details.

1. Install the wicket-source jar into your project using maven or your preferred build tool.  Add it to your WicketApplication `init()` as
     `WicketSource.configure(this);`

2. Visit the Chrome Web Store and search for wicket to find the chrome plugin.

3. Install WicketSourceOpener into Eclipse by putting its .jar into 
the eclipse drop-ins folder and restarting Eclipse.  Then "Show View" / "Wicket 
Source Opener, Wicket Source Opener" to get to the tab.


## Notes

August 2016: I am working on updating all components to work with the latest software.

Chrome plugin is updated in the web store.

Firefox plugin is dead.  I might try again after the Web Extensions API stabilizes and firebug 3 releases.

Eclipse plugin is updated to work with Eclipse Neon.  I tried to figure out Eclipse Marketplace, but the self-hosting
is complicated, so I'm providing a [jar for your dropins folder](https://github.com/jennybrown8/binaries/raw/master/wicket-source-opener/com.github.jennybrown8.wicketsourceopener-7.4.0.1-eclipse-neon.jar), still (click to download).


Feedback and bug reports welcome. See the [Issue Tracker](https://github.com/jennybrown8/wicket-source/issues). 
