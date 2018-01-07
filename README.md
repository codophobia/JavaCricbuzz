# JavaCricbuzz

# <b>Cricbuzz for JAVA</b>
A Java interface to cricbuzz, with options to get live scores and live commentary.

You can find detailed explaination here: <a href = "https://cricstatshub.com/2018/01/06/cricket-api-for-java/">javacricbuzz blog</a>

<b>Requirements</b>

jsoup library: https://jsoup.org/download

<b>One can also directly download the jar file which conatins all the dependencies packed: <a href="https://drive.google.com/open?id=1T-nWER9LmfLPatJbn5gKfvKaJe6qEnZ2">Cricbuzz.jar</a></b>

<b>Features</b>
<ul>
<li>Get upcoming, live and recently concluded matches</li>
<li>Summary of each of the matches</li>
<li>Commentary for live matches</li>
</ul>

<b>Basic Usage</b>
```java
import cricbuzz.Cricbuzz; #Assuming that source file is in a package named cricbuzz
Cricbuzz c = new Cricbuzz();
```

<b>Get all the matches(live,upcoming and recently finished matches)</b>

```java
Vector<HashMap<String,String>> matches = c.matches();
Gson gson = new GsonBuilder().setPrettyPrinting().create(); #Add gson library in case you want a pretty output
String json = gson.toJson(matches);
System.out.println(json);
```
Each match will have an attribute 'id'. Use this 'id' to get scorecard, brief score and commentary of matches.

<b>Get brief score of a match</b>

```java
String id = matches.get(0).get("id");
Map<String,Map> score = c.livescore(id);
json = gson.toJson(score);
System.out.println(json);
```

<b>Get commentary of a match</b>

```java
Map<String,Map> comm = c.commentary(id);
json = gson.toJson(comm);
System.out.println(json);
```


