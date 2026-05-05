# DomoDomo 

Marvels Project Repo

By Chris Jimenez, Giovanni De Leon, Nicholas Brown, Bidisha Dalai

A JavaFX desktop application built by team **Marvels DomoDomo** for UTSA CS 3443. DomoDomo is a virtual pet + task manager combo app. Please take care of your Domo while staying on top of your to-do list!

---

## File Structure

```
DomoDomo/
├── .idea/
├── .mvn/
│   └── wrapper/
├── data/
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java
│       │   └── edu/utsa/cs3443/marvels_domodomo/
│       │       ├── DomoDomoApplication.java
│       │       ├── EditController.java
│       │       ├── MainScreenController.java
│       │       ├── OptionsController.java
│       │       ├── PetController.java
│       │       ├── Task.java
│       │       └── TaskManager.java
│       └── resources/
│           └── edu/utsa/cs3443/marvels_domodomo/
│               ├── Edit-screen.fxml
│               ├── Main-screen.fxml
│               ├── Options-screen.fxml
│               ├── Pet-screen.fxml
│               ├── images/BGDomo/
│               └── style.css
├── .gitignore
├── FXML_Domo.fxml
├── README.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---


## How to Run

**Prerequisites:** Java 17+ and an internet connection (Maven wrapper auto-downloads on first run).

### macOS / Linux

```bash
# 1. Create a folder and navigate to it (or clone straight to Desktop)
mkdir ~/Desktop/DomoDomo

# 2. Clone the repository
git clone https://github.com/UTSA-CS-3443/DomoDomo.git ~/Desktop/DomoDomo
cd ~/Desktop/DomoDomo

# 3. Give the Maven wrapper execute permission
chmod +x mvnw

# 4. Run the application
./mvnw javafx:run
```

### Windows (Command Prompt or PowerShell)

```bat
:: 1. Create a folder on your Desktop and navigate to it
mkdir %USERPROFILE%\Desktop\DomoDomo

:: 2. Clone the repository
git clone https://github.com/UTSA-CS-3443/DomoDomo.git %USERPROFILE%\Desktop\DomoDomo
cd %USERPROFILE%\Desktop\DomoDomo

:: 3. Run the application
mvnw.cmd javafx:run
```

> **IntelliJ users:** Open the project folder, let Maven sync dependencies automatically, then run `DomoDomoApplication.java` directly.

---

## Troubleshooting

**`zsh: permission denied: ./mvnw`** *(macOS/Linux)*
The wrapper script isn't marked as executable. Fix it with:
```bash
chmod +x mvnw
```

---

**`Could not locate the Maven launcher JAR`** *(macOS/Linux)*
The cached Maven distribution is corrupted. Clear it and re-run:
```bash
rm -rf ~/.m2/wrapper/dists/apache-maven-3.8.5-bin
./mvnw javafx:run
```

---

**`Error: JAVA_HOME not found in your environment`** *(Windows)*

This means Maven can't find your Java installation. Follow these steps:

**Step 1 — Confirm Java is installed:**
```bat
java -version
```
If this fails, download and install [Java 17+ from Eclipse Adoptium](https://adoptium.net).

**Step 2 — Find the true Java path:**

PowerShell:
```powershell
Get-Command java | Select-Object -ExpandProperty Source
```

Command Prompt:
```bat
where java
```

Both should output something like:
```
C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot\bin\java.exe
```

**Step 3 — Set `JAVA_HOME` and run** (replace the version number with yours):
```bat
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot
mvnw.cmd javafx:run
```

> To make this permanent, add `JAVA_HOME` to your Windows System Environment Variables so you don't need to re-run `set` every time.

---

**`JAVA_HOME is not set` or wrong Java version** *(macOS/Linux)*
```bash
java -version
```
If the version is wrong, install [Java 17+](https://adoptium.net) and add this to your `~/.zshrc` or `~/.bashrc`:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

---

**JavaFX runtime components missing**
If you see a JavaFX-related error, ensure the `pom.xml` has the JavaFX Maven plugin configured. Do not try to run the `.java` file directly — always use `./mvnw javafx:run` (macOS/Linux) or `mvnw.cmd javafx:run` (Windows).
