First git push using git bash
first go to root directory where project folder is present
AJAY@DESKTOP-52UK9EE MINGW64 /c/Users
$ cd /E/Projects/scm
AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git init
Reinitialized existing Git repository in E:/Projects/scm/.git/
AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git add -A
warning: in the working copy of '.gitignore', LF will be replaced by CRLF the next time Git touches it
AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git commit -m 'First Commit'
[main 068ffe6] First Commit
AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git remote add origin https://github.com/aky18997/SmartContactManager.git
error: remote origin already exists.
AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git remote rm origin

AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git remote add origin https://github.com/aky18997/SmartContactManager.git

AJAY@DESKTOP-52UK9EE MINGW64 /E/Projects/scm (main)
$ git push -u -f origin main
Enumerating objects: 2713, done.
Counting objects: 100% (2713/2713), done.
Delta compression using up to 4 threads
Compressing objects: 100% (2582/2582), done.
Writing objects: 100% (2713/2713), 3.82 MiB | 1.41 MiB/s, done.
Total 2713 (delta 553), reused 0 (delta 0), pack-reused 0 (from 0)
remote: Resolving deltas: 100% (553/553), done.
To https://github.com/aky18997/SmartContactManager.git
 * [new branch]      main -> main
branch 'main' set up to track 'origin/main'.



