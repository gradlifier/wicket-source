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

Tools: maven, java, git
Prereqs: none, this is a standalone project

## Building wicket-source

Ensure the pom.xml at the parent level and within the project are set to the upstream version
of wicket that you want to build against.

Ensure both poms agree on the snapshot version for this build, and that `wicket-source-demo/pom.xml` also agrees.

From the parent project dir:

    mvn package
    
This will create a jar artifact in your local maven repository, typically `~/.m2/repository`, with
the latest snapshot build.

## Testing wicket-source

The project does not include its own tests.  Instead, it is tested interactively by using
the `wicket-source-demo` project, which is a small sample web app packaged with embedded jetty.

To test, launch the demo project's jetty via `test/Start.java > Run as > Java Application`, and 
go to `http://localhost:8080/` in your web browser of choice.

View Page Source, and look for `wicketsource="com.github.jennybrown8.wicketsource.demo:HomePage.java:37"` 
(or other similar line numbers) in the various wicket-created components of the web page.  If the
package, filename, and line number attributes are showing up, then `wicket-source` is working as
intended.

The only other thing to check is that the line numbers make sense with respect to the original source code.
The project uses an exception stacktrace to determine where each component exists in the source code, and if
that stack trace changes in a future version of wicket, the data shown could become incorrect.


## Signing and Publishing wicket-source

The wicket-source project is published as a maven artifact to the OSS Sonatype maven central repository.

TODO: More details


# Developing on wicket-source-demo

The `wicket-source-demo` project exists as a demonstration and testing application for the 
`wicket-source` server-side plugin.  It consists of embedded jetty web server plus a tiny web app,
with wicket-source installed and configured on.

## Setup for wicket-source-demo

Tools: maven, java, git
Prereqs: wicket-source packaged and present in your local maven repository

## Building wicket-source-demo

Ensure the pom.xml at the parent level and within the wicket-source project are set to the upstream version
of wicket that you want to build against.

Ensure both poms agree on the snapshot version for this build, and that `wicket-source-demo/pom.xml` also agrees.

From the parent project dir:

    mvn package
    
This will create a jar artifact in your local maven repository, typically `~/.m2/repository`, with
the latest snapshot build, and it ensures that all of your maven configuration is suitable.


## Testing wicket-source-demo

In Eclipse, open `wicket-source-demo/src/test/java/com.github.jennybrown8.wicketsource.demo/Start.java`

Right click on the source, Run As > Java Application

In your web browser, go to `http://localhost:8080/`

You should see a small web application (no errors) come up, stating the version of wicket it is
compiled against, and showing some links and text for inspection.

View Page Source, and look for `wicketsource="com.github.jennybrown8.wicketsource.demo:HomePage.java:37"` 
(or other similar line numbers) in the various wicket-created components of the web page.  If the
package, filename, and line number attributes are showing up, then `wicket-source` is working as
intended.

Close out the view source, and click around on various links to ensure the application doesn't throw errors.

When you're done, go to Eclipse, Console, and press the red square to stop Jetty.


## Signing and Publishing wicket-source-demo

The wicket-source-demo project is published as a maven artifact to the OSS Sonatype maven central repository.

TODO: More details

# Developing on wicket-source-opener

This covers how to prepare, build, test, and publish the Eclipse plugin that 
opens a given source code file when a reference to it is clicked.  This is a
standard Eclipse plugin and developer docs can be found on eclipse.org website.

## Setup for wicket-source-opener

Tools: Eclipse, maven, java, git
Prereqs: wicket-source-demo running
Helpful: chrome plugin or firefox plugin working even if only in dev mode

Alternately you can fake the necessary http requests by constructing URLs manually, but that's a 
pain due to url encoding the colons as `%3A`.  It's much nicer if you have one of the browser plugins working first.

Sample url:

    http://localhost:9123/open?src=com.github.jennybrown8.wicketsource.demo%3AHomePage.java%3A100
    
Sample url with password:

    http://localhost:9123/open?src=com.github.jennybrown8.wicketsource.demo%3AHomePage.java%3A100&p=foo

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
instance, and restart Eclipse.  Ensure that it also works there.  You may wish to delete prior versions
of the jar if the name has changed.

After you're sure it's stable and complete, move on to publishing it to the Eclipse plugin store.

## Signing and Publishing wicket-source-opener

TODO: Details

# Developing on WicketSourceForChrome

The plugin for Chrome adds a "sidebar pane" to Chrome's "Developer Tools", with an html snippet that
produces a link to the Eclipse wicket-source-opener plugin's http server on localhost.  Clicking on
the link in the browser plugin sends a message to the eclipse plugin to open a specific source file on
a specific line.

The browser plugins are the most complex code in this entire project.  Web browsers are solidly sandboxed,
so their plugin code is extremely modular to cooperate with sandboxing requirements.  These are also
the places most prone to change over the years.

## Setup for WicketSourceForChrome

Tools: java, git, chrome
Prereqs: wicket-source and wicket-source-demo work, demo's jetty is running
Helpful: wicket-source-opener installed and working in Eclipse

https://developer.chrome.com/extensions/getstarted 

https://developer.chrome.com/extensions/devguide

https://developer.chrome.com/extensions/devtools

## Building WicketSourceForChrome

For development mode, Chrome can load a directory as a browser extension, so you don't have to
bother packaging until after testing is done.  Per their instructions:

1. Visit chrome://extensions in your browser (or open up the Chrome menu by clicking the icon to the 
far right of the Omnibox:  The menu's icon is three horizontal bars. and select Extensions under the 
Tools menu to get to the same place).
2. Ensure that the Developer mode checkbox in the top right-hand corner is checked.
3. Click `Load unpacked extension...` to pop up a file-selection dialog.
4. Navigate to the directory in which your extension files live, and select it.
5. If the extension is valid, it'll be loaded up and active right away! If it's invalid, an error message 
will be displayed at the top of the page. Correct the error, and try again.

During development, as your plugin changes:

1. Visit the extensions page (go to chrome://extensions, or Tools > Extensions under the Chrome menu), and 
click Reload under your extension. 
2. All extensions are also reloaded when the extensions page is reloaded, e.g. after hitting F5 or Ctrl-R.
3. You can inspect a nonfunctional version of any page by getting the id of your extension out of the
Manage Extensions menu, and then hitting a url like: `chrome-extension://hddgkgcpjigidjnmcpaojgokekplmkgz/sidebar.html`

## Troubleshooting and Logs

Read up on the latest information for how to troubleshoot Chrome plugins, as 
some of the strategies that used to work don't anymore.  Console logging may
land on a plugin page instead of the active web page, making it hard to find and see.

One such page is the Options page, which you can open, and then inspect and read
the javascript console logs.

I haven't figured out how to get the console logs from sidebar.js yet.  Rather, if 
something breaks, comment out a section of the javascript and rerun to narrow down
where the failure is occurring.  Work in small increments and it's not too hard to avoid
crashes or at least detect them the moment you break the code.  The `alert("foo");` call 
still works for checking values.


## Testing WicketSourceForChrome

First we'll test the core behavior of the plugin.

1. Assuming `wicket-source-demo` is working and jetty is running...
2. Assuming the browser plugin has been loaded and seems to come up okay...
3. Assuming Eclipse has source opener installed and its view tab open...
4. Go to `http://localhost:8080/` with Chrome.
5. Right-click, Inspect, on one of the hyperlinks on the demo page.
6. In the developer tools panel, look in the panel on the right for the wicket source tab; click it.
7. Ensure that text about the wicket attributes shows up, and one of the lines is hyperlinked.
8. Click the hyperlink and you should see Eclipse open the right file and jump to the right line number.
9. Inspect something else in the web page.  The panel should update with the item you're now inspecting.
10. In the sidebar pane, hover over `eclipse-url` and validate that it's creating a URL correctly, and
that the hover tooltip css behavior looks decent.

Next we'll test the preferences pane and custom settings.

1. In Chrome's plugin icon bar (upper right) click the wicket-source icon so its dropdown opens.
2. Ensure the dropdown makes sense.
3. Click Options.  The preferences page should open.
4. By default, server runs on localhost:9123 - try setting it to something else and saving preferences.
5. Close out the options.  Come back into options.  Make sure the settings saved and come back up.
6. Close out of Chrome.  Restart it.  Come back into options.  Make sure the settings come back up.
7. Verify that the hyperlink produces an updated host and port call.
8. Ensure clicking the link goes to the right thing by looking at the hover tooltip on `eclipse-url` in the 
dev tools sidebar pane while inspecting a wicket component.
9. Modify the required password both in wicket-source-opener and in the browser plugin.  Save.
10. Reload the plugin.  Click on a link and ensure that the request to wicket-source-opener still works with password.
11. Take the password back out of just the chrome plugin; ensure that saving works and the default empty boxes come back in the options page.
12. Verify that requests to wicket source opener don't work when it requires a password and the browser isn't supplying one.
13. Take the password back out of wicket source opener, and verify that requests work again.
14. Look at the icon in Chrome.  It should be an orange circle and not grayed out.
15. Verify that there are no javascript errors in any console, including the options page console and
the popup page console.


## Signing and Publishing WicketSourceForChrome

The chrome plugin is published via the Chrome Web Store using the Chrome Developer Dashboard.

Instructions: https://developer.chrome.com/extensions/packaging

You can optionally make a crx file with `Pack extension` if you want to distribute privately (for 
instance to testers), but this is not needed for updating using the developer dashboard.  

For publishing to the web store, you will instead make a zip file (used only for that!), 
with your private pem file inside using a special filename, so that it recognizes the relationship 
to the prior publish.  Find the existing item on the developer dashboard, and click "Edit", and 
then find the tiny button "upload updated package" near the top the of the form.

Be sure you've re-read the instructions at the URL above carefully before doing this.  The details
might change over time.


# Developing on WicketSourceForFirebug

The plugin for Firefox's Firebug adds a tab pane to Firebug, with an html snippet that
produces a link to the Eclipse wicket-source-opener plugin's http server on localhost.  Clicking on
the link in the browser plugin sends a message to the eclipse plugin to open a specific source file on
a specific line.

The browser plugins are the most complex code in this entire project.  Web browsers are solidly sandboxed,
so their plugin code is extremely modular to cooperate with sandboxing requirements.  These are also
the places most prone to change over the years.

## Setup for WicketSourceForFirebug

Tools: java, git, firefox, firebug
Prereqs: wicket-source and wicket-source-demo work, demo's jetty is running
Helpful: wicket-source-opener installed and working in Eclipse

## Building WicketSourceForFirebug

## Testing WicketSourceForFirebug

## Signing and Publishing WicketSourceForFirebug

The firebug plugin is published via the Firefox app store.

TODO: Details
