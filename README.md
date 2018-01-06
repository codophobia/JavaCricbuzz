# JavaCricbuzz

# <b>Cricbuzz for JAVA</b>
A Java interface to cricbuzz, with options to get live scores and live commentary.

You can find detailed explaination here: <a href = "https://cricstatshub.com/2017/12/03/cricket-api-for-python/">pycricbuzz blog</a>

<b>Requirements</b>

jsoup library: https://jsoup.org/download

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
Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

```python
Map<String,Map> comm = c.commentary(id);
json = gson.toJson(comm);
System.out.println(json);
```


