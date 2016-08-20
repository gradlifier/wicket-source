# Developing on the Wicket Source Project

## Project Structure

    wicket-source-parent
    ├── WicketSourceForChrome
    ├── WicketSourceForFirebug
    │   └── wicketsource@gmail.com
    ├── build
    ├── docs
    │   └── icons_psd
    ├── wicket-source-opener
    │   ├── META-INF
    │   ├── bin
    │   ├── icons
    │   └── src
    ├── wicket-source
    │   ├── src
    │   └── target
    └── wicket-source-demo
        ├── src
        └── target

There is one parent folder (the git repo) which contains 5 sub projects, each of 
which has its own build and package routine.

The parent project contains a `pom.xml` that is crucial for the functioning of maven 
in sub-projects.  Maven builds generally should originate from the parent level.
This makes it important to keep a terminal window open while working on the project or import the 
parent project as a separate Eclipse project (uncheck `Search for nested projects` when importing the parent).

Development is currently configured for Eclipse.  Each of the subprojects should be
imported as its own project in Eclipse, and the parent as its own project.

The Chrome and Firebug plugin projects are built independently using their own 
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
## Publishing and Signing wicket-source

# Developing on wicket-source-demo
## Setup for wicket-source-demo

Prereqs: maven, java, git

## Building wicket-source-demo
## Testing wicket-source-demo
## Publishing and Signing wicket-source-demo

# Developing on wicket-source-opener
## Setup for wicket-source-opener

Prereqs: Eclipse, maven, java, git

## Building wicket-source-opener
## Testing wicket-source-opener
## Publishing and Signing wicket-source-opener

# Developing on WicketSourceForChrome
## Setup for WicketSourceForChrome

Prereqs: java, git, chrome

## Building WicketSourceForChrome
## Testing WicketSourceForChrome
## Publishing and Signing WicketSourceForChrome

# Developing on WicketSourceForFirebug
## Setup for WicketSourceForFirebug

Prereqs: java, git, firefox, firebug

## Building WicketSourceForFirebug
## Testing WicketSourceForFirebug
## Publishing and Signing WicketSourceForFirebug

