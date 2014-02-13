@Grab( 'commons-io:commons-io:2.4' )
import org.apache.commons.io.FileUtils

def sourceDirPath = "/Volumes/Data/Temp/IdentificationIcons";
def destinationDirPath = "/Volumes/Data/Temp/IdentificationIcons/Renamed"

def renamer
renamer = { File d ->
    println "Current directory: ${d.canonicalPath}"
    d.eachDir(renamer)
    d.eachFileMatch(~/.*\.png/) {
        File f ->
            print f.absolutePath
            def newName = d.name + "_" + f.name
            print " -> Renaming to: ${newName}"
            println ""
            FileUtils.copyFile(f, new File(destinationDirPath, newName))
    }
}

renamer(new File(sourceDirPath))