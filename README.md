Cuke-Up Your Tests
==================

## Download & Install:

- Java IDE: [IntelliJ community edition](https://www.jetbrains.com/idea/download/#section=windows) 
> You can use your own preferred IDE for Java (e.g. Eclipse). However the trainers know the settings and hotkeys for IntelliJ which are written in the [CheatSheet.pdf](CheatSheet.pdf), so the latter is preferred.
- Chrome Plugin: [CSS Selector Helper for Chrome](https://chrome.google.com/webstore/detail/css-selector-helper-for-c/gddgceinofapfodcekopkjjelkbjodin)
- [Git](https://git-scm.com)
- [Java jdk](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (not the JRE)


## Setup

- Start up IntelliJ
- Choose 'Get from VCS'
- Fill in URL "https://gitlab.com/the-ta-crowd/training/selenium-webdriver-cucumber"
- Choose a folder to clone the project to on your machine (IntelliJ will suggest a default location)
- Press Clone
- If it is your first IntelliJ project then in IntelliJ:
    - Open project structure (CTRL + Shift + Alt + S), navigate to the 'Project' tab, choose new in Project SDK section, select JDK and direct to your java/jdk folder

- Open Settings (CTRL + ALT + S), navigate to 'Plugins' and install the plugins: `Gherkin` and `Cucumber for Java` (**Don't** install the `Substeps` plugin)
- Open the Maven Projects Tool window (Navigation bar > `View` > `Tool Windows` > `Maven Projects`), this will open a view on the right side of the screen.
- Double click under `cukeupyourtests` > `Lifecycle` the `compile` option. This will install the drivers of Chrome, Firefox and InternetExplorer. _If the drivers don't download you might be behind a proxy, visit [the plugin page](https://github.com/webdriverextensions/webdriverextensions-maven-plugin#using-a-proxy) for help_.

  

## Assignments
The assignments can be found in the file [Assignments.md](Assignments.md)

## Disclaimer
This is a workshop architecture and is only for that goal intended. Using this framework on a larger scale is not recommended.
