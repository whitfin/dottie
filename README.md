# dottie

Dottie is a super simple dot notation parser/generator for Java. It provides an easy way to join/split notation from/to individual keys.

### Installation

Dottie is available on JCenter, and so can be used via Maven and/or Gradle pretty easily.

To setup JCenter repositories (if you haven't already), you need to add the repositories to your project pom.xml, or your settings.xml:

```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
```

Then you can just add the dependency as normal (you might have to check for the latest version, rather than what's shown below):

```xml
<dependency>
    <groupId>io.whitfin</groupId>
    <artifactId>dottie</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Usage

The main functionality provided in Dottie is the ability to split/join notation easily. This can be done by two main classes, `NotationJoiner` and `NotationSplitter`:

```java
import io.whitfin.dottie.joiner.NotationJoiner;
import io.whitfin.dottie.segment.NotationSegment;
import io.whitfin.dottie.splitter.NotationSplitter;
    
import java.util.List;
    
public class DottieExample {
    
    public static void main(String[] args) {
        // join props/indices into a string
        String notation = new NotationJoiner()
            .append("this")
            .append("is")
            .append("test")
            .append(0)
            .append("!")
            .toString();
            
        // this.is.test[0]["!"]
        System.out.println(notation);
        
        // split it back into keys
        List<NotationSegment> keys = NotationSplitter.split(notation);
        
        // 5 keys are returned
        System.out.println(keys.size());
    }
    
}
```

For any other functionality, please see the documentation or the code itself.