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

