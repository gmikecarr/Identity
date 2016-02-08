# Identity
Running the application
---------------------
Save Identity.jar in a directory of your choice.

In a terminal window type;
        java -jar Identity.jar <Full path of working copy to be scanned>

The output to console a list of the Identity.yml files found followed by the package attributes as per the example below followed by a
list of the licenses found.

                Package Name: FFmpeg
                      Origin: https://github.com/gmikecarr/FFmpeg.git
                     License: LGPL
                   Copyright: Copyright (c) 2011 Stefano Sabatini
                    Licensor: Stefano Sabatini
                       Dependencies
                       -------------------------
                              libavcodec
                              libavdevice
                              libavresample
                              libavfilter
                              libavutil
                       -------------------------


Assumptions
----------
1) The Yaml files of interest are consistenly named "Identity.yml" (case insensitive). The name "Identity.yml" is not used by other applications.

2) All Identity.yml are valid yaml files and conform to the class Identity. Invalid files cause an exception and the job will fail.

3) For the purpose of this excercise the package attributes are limited, package version numbers are ignored for example.

4) The repository is cloned on the local file system and we are only concerned with the working copy. The repository itself (.git) is ignored.

5) Package names are consistent, ie. the name used in the dependency list for a parent package is the same as the name used in the dependant package Identity file. The future intention is to be able to generate a dependency tree for each package.

6) Package names are unique.  

Limitations
-----------
1) No process or error logging is implemented.
2) There is no user feedback to idicated that the job is still running.
3) Exception handling is limited, in particular the immediate termination on failing to parse an Identity.yml file needs addressing. No account is taken of file access permissions.
4) The order of processing files is not guarenteed, parent/child relationships are not imposed by directory structure.


