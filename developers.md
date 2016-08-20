# Developing on the Wicket Source Project

## Project Structure

    wicket-source-parent
    ├── WicketSourceForChrome
    ├── WicketSourceForFirebug
    ├── build
    ├── docs
    │   └── icons_psd
    ├── wicket-source
    │   ├── src
    │   └── target
    ├── wicket-source-demo
    │   ├── src
    │   └── target
    └── wicket-source-opener
        ├── META-INF
        ├── bin
        ├── icons
        └── src

There is one parent folder (the git repo) which contains 5 sub projects, each of 
which has its own build and package routine.

The parent project contains a `pom.xml` that is crucial for the functioning of maven 
in sub-projects.  Maven builds generally should originate from the parent level.
This makes it important to keep a terminal window open while working on the project or import the 
parent project as a separate Eclipse project (uncheck `Search for nested projects` when importing the parent).

Development is currently configured for Eclipse.  Each of the subprojects should be
imported as its own project in Eclipse, and the parent as its own project.

The Chrome and Firebug plugin projects, and the Eclipse plugin, are built independently using their own 
strategies for plugin packaging.


### Local Repository Setup

While working on wicket-source-demo, at the same time as you are updating code in wicket-source,
you'll be working against a wicket-source snapshot build that is not available in the public
repository.  Eclipse will fuss about not being able to find the artifact, unless you 
configure a local repo:

    ~/.m2/settings.xml should contain something like:
    
    <settings>
      <localRepository>/Users/jennybrown/.m2/repository</localRepository>
    </settings>


That should be enough to be able to build both projects from the parent level and then 
launch Start.java from demo to run jetty.

# Developing on wicket-source
## Setup for wicket-source

Prereqs: maven, java, git


## Building wicket-source
## Testing wicket-source
## Signing and Publishing wicket-source

# Developing on wicket-source-demo
## Setup for wicket-source-demo

Prereqs: maven, java, git

## Building wicket-source-demo
## Testing wicket-source-demo
## Signing and Publishing wicket-source-demo

# Developing on wicket-source-opener

This covers how to prepare, build, test, and publish the Eclipse plugin that 
opens a given source code file when a reference to it is clicked.  This is a
standard Eclipse plugin and developer docs can be found on eclipse.org website.

## Setup for wicket-source-opener

Tools: Eclipse, maven, java, git
Prereqs: wicket-source-demo running, and chrome plugin or firefox plugin working

Alternately you can fake the necessary http requests by constructing URLs manually, but that's a pain.

## Building wicket-source-opener

Eclipse > File > Export As > Jar > with existing manifest file (META-INF/manifest.mf)

Jar name: com.github.jennybrown8.wicketsourceopener.jar

This can be installed by hand by copying the jar into Eclipse's plugins directory.

On mac: `~/Applications/Eclipse.app/Contents/Eclipse/plugins/`

## Testing wicket-source-opener

### Testing Prep for wicket-source-opener

Ensure you have:
* wicket-source-demo compiling 
* wicket-source-demo running with the jetty `Start.java`
* opened in a web browser `http://localhost:8080/`
* the browser plugin working

Open the project's plugin.xml file in Eclipse.  The Overview graphical editor should have a link
on the right hand column for running a test version of eclipse with this plugin installed.
Launch it using that link.

From within the test eclipse: 

    Window > Show View > Wicket Source Opener > Wicket Source Opener

If you make major changes or rename packages, it's safest to close the source opener view tab before
you exit the test Eclipse, so that it doesn't try to launch a stale view the next time you go
in. (Stale views produce confusing error messages that will lead you on a wild goose chase.)

### Test Cases for wicket-source-opener

1. Once test eclipse is open, ensure you can show and close the plugin's view
    `Window > Show View > Wicket Source Opener > Wicket Source Opener`
2. Click on a hyperlink from the chrome or firefox plugin, and ensure that the
the filename and line number show up in the tab of the source opener. 
You probably won't have the actual wicket-source-demo source up in the test eclipse, so it's fine 
if it doesn't open the actual file.  You're just checking to see that the http listener is 
functioning, that your plugin was recognized at all, and that it doesn't instantly destabilize eclipse.
3. Next, import the wicket-source-demo project from within the test eclipse.  (It has a persistent
workspace, so this should stick around.)  Alternately you can take the risky path and install the
plugin on your primary eclipse.
4. Then begin manual testing of more advanced features, ensuring that clicking a link takes you to
the right line of source code in the demo project, and that if there are multiple source files
with the same name, a dialog box pops up to ask the user which one to open.  
5. Try clicking on several different links so there are several items in the plugin's view.  Then 
double-click on one of the older items in the list, and ensure it still takes you to that file 
(whether or not that file is already open).

Once you think the plugin is fairly stable, export the jar and install by hand in your primary Eclipse
instance, and restart Eclipse.  Ensure that it also works there.


## Signing and Publishing wicket-source-opener

TODO

# Developing on WicketSourceForChrome
## Setup for WicketSourceForChrome

Prereqs: java, git, chrome

## Building WicketSourceForChrome
## Testing WicketSourceForChrome
## Signing and Publishing WicketSourceForChrome

# Developing on WicketSourceForFirebug
## Setup for WicketSourceForFirebug

Prereqs: java, git, firefox, firebug

## Building WicketSourceForFirebug
## Testing WicketSourceForFirebug
## Signing and Publishing WicketSourceForFirebug

