# How to Publish to Maven Central

see 
- https://dzone.com/articles/publish-your-artifacts-to-maven-central
- https://dev.to/mlniang_/publish-your-artifacts-to-maven-central-2608

`.m2/settings.xml`:

# Preconditions

* Generate a GPG key (see [Generate a Key Pair](https://dzone.com/articles/publish-your-artifacts-to-maven-central))

```xml
 <settings>
  <servers>
    <server>
        <id>ossrh</id>
        <username>${TODO: sonatype login}></username>
        <password>${TODO: sonatype password}</password>
    </server>
  </servers>

    <profiles>
        <profile>
            <id>ossrh</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
            <gpg.passphrase>${TODO: ppg passphrase}</gpg.passphrase>
            </properties>
        </profile>
    </profiles>  
</settings>

```

# Release
* Do the release
```bash
mvn clean
mvn release:prepare -P release
mvn release:perform -P release
```

* Push the tag and code to your remote repo.
```bash
git push --tags
git push origin master
```
          
# What to Do If Something Goes Wrong

* Step 1: Undo the release:
```bash
    git reset –hard HEAD~1 (You may have to do it a second time, depending upon when the error occurred.)
    
    git reset –hard HEAD~1
```
* Step 2: Delete the tag.
```
    git tag -d tagName
    
    git push origin :refs/tags/tagName                                                 
```
