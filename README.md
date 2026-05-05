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

```bash
# 1. Clone the repository
git clone https://github.com/UTSA-CS-3443/DomoDomo.git
cd DomoDomo

# 2. Give the Maven wrapper execute permission (macOS/Linux only)
chmod +x mvnw

# 3. Run the application
./mvnw javafx:run        # macOS/Linux
mvnw.cmd javafx:run      # Windows
```

> **IntelliJ users:** Open the project folder, let Maven sync dependencies automatically, then run `DomoDomoApplication.java` directly.

---

##  Troubleshooting

**`zsh: permission denied: ./mvnw`**
The wrapper script isn't marked as executable. Fix it with:
```bash
chmod +x mvnw
```

---

**`Could not locate the Maven launcher JAR`**
The cached Maven distribution is corrupted. Clear it and re-run:
```bash
rm -rf ~/.m2/wrapper/dists/apache-maven-3.8.5-bin
./mvnw javafx:run
```

---

**`JAVA_HOME is not set` or wrong Java version**
Make sure Java 17+ is installed and active:
```bash
java -version
```
If the version is wrong, install [Java 17+](https://adoptium.net) and set `JAVA_HOME` to point to it.

---

**JavaFX runtime components missing**
If you see a JavaFX-related error, ensure the `pom.xml` has the JavaFX Maven plugin configured. Do not try to run the `.java` file directly — always use `./mvnw javafx:run`.
