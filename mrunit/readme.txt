cat intellij-idea.desktop 
[Desktop Entry]
Version=1.0
Name=Intellij IDEA
Comment=Develop Java Project IDE
Exec=/home/zhishan/bin/intellijIdea/bin/idea.sh
Terminal=false
Icon=idea.png
Type=Application
Categories=Java;Development;IDE


1. mockito: http://code.google.com/p/mockito/wiki/DeclaringMockitoDependency
      <dependency>
          <groupId>org.mockito</groupId>
          <artifactId>mockito-all</artifactId>
          <version>1.9.5</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <!-- needs extra dependencies: objenesis & hamcrest -->
          <groupId>org.mockito</groupId>
          <artifactId>mockito-core</artifactId>
          <version>1.9.5</version>
          <scope>test</scope>
      </dependency>

mockito-core VS mockito-all

Mockito-all is a single jar will all dependencies inlined inside (that is: hamcrest and objenesis libs as of June'11). Mockito-core is just the mockito jar.
Use mockito-core if you want to pull a specific version of hamcrest or objenesis. Mockito-core gives finer control on what jars end up on your classpath.
Since maven deals with transitive dependencies well maven users can use mockito-core without any extra hassle.

2. maven-dependency-plugin

includeScope:
scope to include
e.g
By default, the "test" scope gives all dependencies
 >ls target/dependencies/
 hadoop-core-1.2.1.jar  mockito-core-1.9.5.jar  mrunit-0.9.0-incubating-hadoop1.jar  testng-6.8.jar

However,
<includeScope>runtime</includeScope>
>ls target/dependencies/
hadoop-core-1.2.1.jar


3.Here, maven-assembly-plugin make the plugin maven-jar-plugin useless.
http://etfdevlab.blogspot.com/2010/10/assembly-executable-jar-with.html

         <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-jar-plugin</artifactId>
              <executions>
                  <execution>
                      <phase>package</phase>
                      <goals>
                          <goal>jar</goal>
                      </goals>
                      <configuration>
                          <classifier>dependencies</classifier>
                          <archive>
                              <manifest>
                                  <addClasspath>true</addClasspath>
                                  <classpathPrefix>dependencies</classpathPrefix>
                                  <mainClass>org.apache.hadoop.examples.WordCount</mainClass>
                              </manifest>
                          </archive>
                      </configuration>
                  </execution>
              </executions>
          </plugin>


                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-assembly-plugin</artifactId>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>attached</goal>
                                </goals>
                                <phase>package</phase>
                                <configuration>
                                    <descriptorRefs>
                                        <descriptorRef>jar-with-dependencies</descriptorRef>
                                    </descriptorRefs>
                                    <archive>
                                        <manifest>
                                            <addClasspath>true</addClasspath>
                                            <mainClass>com.yahoo.slingshot.di.offgrid.yuidtosid.client.Y2SClientMapperMain</mainClass>
                                        </manifest>
                                        <manifestEntries>
                                            <Class-Path>../target/classes</Class-Path>
                                        </manifestEntries>
                                    </archive>
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>

4. maven-enforcer-plugin

The Enforcer plugin provides goals to control certain environmental
constraints such as Maven version, JDK version and OS family along with many
more standard rules and user created rules.

$ mvn enforcer:display-info
...
[INFO]    task-segment: [enforcer:display-info]
[INFO]
------------------------------------------------------------------------
[INFO] [enforcer:display-info {execution: default-cli}]
[INFO] Maven Version: 2.2.1
[INFO] JDK Version: 1.7.0_13 normalized as: 1.7.0-13
[INFO] OS Info: Arch: amd64 Family: unix Name: linux Version:
2.6.32-220.4.2.el6.yahoo.20120217.x86_64

5. maven-compiler-plugin
5.1 Compiling Sources Using A Different JDK
http://maven.apache.org/plugins/maven-compiler-plugin/examples/compile-using-different-jdk.html
5.2 Compile Using Memory Allocation Enhancements
   <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <fork>true</fork>
          <meminitial>128m</meminitial>
          <maxmem>512m</maxmem>
        </configuration>
      </plugin>
5.3 Pass Compiler Arguments
http://maven.apache.org/plugins/maven-compiler-plugin/examples/pass-compiler-arguments.html
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <compilerArgument>-verbose -bootclasspath ${java.home}\lib\rt.jar</compilerArgument>
        </configuration>
      </plugin>

6. maven-enforce-plugin
The Enforcer plugin provides goals to control certain environmental constraints such as Maven version, JDK version and OS family along with many more standard rules and user created rules.
 <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-enforcer-plugin</artifactId>
        <version>1.3.1</version>
        <executions>
          <execution>
            <id>enforce-versions</id>
            <goals>
              <goal>enforce</goal>
            </goals>
            <configuration>
              <rules>
                <requireMavenVersion>
                  <version>2.0.6</version>
                </requireMavenVersion>
                <requireJavaVersion>
                  <version>1.5</version>
                </requireJavaVersion>
                <requireOS>
                  <family>unix</family>
                </requireOS>
              </rules>
            </configuration>
          </execution>
        </executions>
      </plugin>

7. diff vs patch
$ diff 1.state 2.state --ignore-matching-lines=^yum.timestamp:.*$ --ignore-matching-lines=^+++.*$ --ignore-matching-lines=^yum.command:.*$ --side-by-side  --suppress-common-lines

